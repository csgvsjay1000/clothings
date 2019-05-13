package ivg.cn.es.monitor.service;

import org.elasticsearch.client.ClusterAdminClient;

/** 
 * 集群服务
 * 监控集群状态
 * */
public class ClusterService {
	
	ClusterAdminClient clusterAdminClient;
	
	public ClusterService(ClusterAdminClient clusterAdminClient) {
		this.clusterAdminClient = clusterAdminClient;
	}
	
	private void task() {
//		clusterAdminClient.
	}
	
}
