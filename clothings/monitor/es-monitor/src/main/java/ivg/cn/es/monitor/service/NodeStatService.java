package ivg.cn.es.monitor.service;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.admin.cluster.node.stats.NodeStats;
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsRequest;
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsResponse;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.monitor.jvm.JvmStats;
import org.elasticsearch.monitor.jvm.JvmStats.GarbageCollector;
import org.elasticsearch.threadpool.ThreadPoolStats;

import ivg.cn.es.monitor.model.entify.TNodeJvm;
import ivg.cn.es.monitor.utils.GCUtil;

public class NodeStatService {

	private static final String GC_OLD_NAME = "old";
	private static final String GC_YONG_NAME = "yong";
	private static final int SCHEDULE = 10;  // 每隔10s收集一次
	
	ScheduledExecutorService scheduledService;
	
	ThreadPoolExecutor poolExecutor;
	
	ClusterAdminClient clusterAdminClient;
	
	GCUtil gcUtil;
	
	public NodeStatService(ClusterAdminClient clusterAdminClient) {
		
		this.clusterAdminClient = clusterAdminClient;
		
		scheduledService = Executors.newSingleThreadScheduledExecutor();
		scheduledService.scheduleAtFixedRate(scheduleTask(), 2, SCHEDULE, TimeUnit.SECONDS);
		// 统计最近一分钟gc信息
		long second = 60*1;
		gcUtil = new GCUtil(second/second);
		
	}
	
	
	private Runnable scheduleTask() {
		return new Runnable() {
			
			@Override
			public void run() {
				nodesStatsRequest();
			}
		};
	}
	public void nodesStatsRequest() {
		
		NodesStatsRequest request = new NodesStatsRequest();
		request.clear();
		request.threadPool(true);
		request.indices(true);
		request.jvm(true);
		request.os(true);
		request.process(true);
		request.fs(true);
		request.transport(true);
		NodesStatsResponse response = clusterAdminClient.nodesStats(request).actionGet();
		
		if (response == null || response.getNodesMap() == null) {
			return;
		}
		
		for(Map.Entry<String, NodeStats> nodeMap : response.getNodesMap().entrySet()){
			NodeStats nodeStats = nodeMap.getValue();
			String nodeName = nodeStats.getNode().getName();
			// 线程池统计
			handleThreadPoolStats(nodeStats.getThreadPool(),nodeName);
			handleJvmStats(nodeStats.getJvm(),nodeName);
		}
		
	}
	
	private void handleThreadPoolStats(ThreadPoolStats threadPoolStats, String nodeName) {
		
	}
	
	private void handleJvmStats(JvmStats jvmStats, String nodeName) {
		
		TNodeJvm nodeJvm = new TNodeJvm();
		// 长久内存大小
		nodeJvm.setHeapCommittedInBytes(jvmStats.getMem().getHeapCommitted().getBytes());
		// 最大内存大小
		nodeJvm.setHeapMaxInBytes(jvmStats.getMem().getHeapMax().getBytes());
		// 堆内存使用量
		nodeJvm.setHeapUsedInBytes(jvmStats.getMem().getHeapUsed().getBytes());
		// 堆内存使用量, HeapUsed/HeapMax
		nodeJvm.setHeapUsedPercent(Integer.valueOf(jvmStats.getMem().getHeapUsedPercent()));
		nodeJvm.setIp(nodeName);
		
		Iterator<GarbageCollector> gcIt = jvmStats.getGc().iterator();
		while (gcIt.hasNext()) {
			JvmStats.GarbageCollector garbageCollector = (JvmStats.GarbageCollector) gcIt.next();
			if (GC_OLD_NAME.equals(garbageCollector.getName())) {
				// 年老代
				long oldTotalCount = gcUtil.getTotalCount();
				long newValue = garbageCollector.getCollectionCount();
				if (gcUtil.countHasUpdate(oldTotalCount, newValue)) {
					gcUtil.updateTime(garbageCollector.getCollectionTime().getMillis());
				}
				nodeJvm.setOldAvgCollectionTimeInMillis((int)gcUtil.avgTimeInMillis());
				nodeJvm.setOldCollectionFrequency((int)gcUtil.frequency());
				nodeJvm.setOldCollectionCount(garbageCollector.getCollectionCount());
				System.out.println("HeapUsed:"+nodeJvm.getHeapUsedInBytes()+
						",OldAvgCollectionTime: "+nodeJvm.getOldAvgCollectionTimeInMillis());
			}
			
		}
		
		
	}
	
}
