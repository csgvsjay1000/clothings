package ivg.cn.es.monitor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequest;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsRequest;
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsResponse;
import org.elasticsearch.action.admin.cluster.tasks.PendingClusterTasksRequest;
import org.elasticsearch.action.admin.cluster.tasks.PendingClusterTasksResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClusterTest {
	
	TransportClient client;
	
	ClusterAdminClient clusterAdminClient;
	
	IndicesAdminClient indicesAdminClient;
	
	@SuppressWarnings("resource")
	@Before
	public void createClient() throws UnknownHostException {
		Settings settings = Settings.builder()
				.put("cluster.name","clothings-prod")
				.build();
		client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.5.131"), 12510));
		clusterAdminClient = client.admin().cluster();
		indicesAdminClient = client.admin().indices();
	}
	
	@After
	public void close() {
		client.close();
	}
	
	@Test
	public void testHealth() {
		ClusterHealthRequest request = new ClusterHealthRequest();
		ClusterHealthResponse response = clusterAdminClient.health(request).actionGet();
		
		System.out.println(response);
	}
	
	@Test
	public void testNodesInfo() {
		
		NodesInfoRequest request = new NodesInfoRequest();
		request.clear();
		request.threadPool(true);
		NodesInfoResponse response = clusterAdminClient.nodesInfo(request).actionGet();
		System.out.println(response);
	}
	
	@Test
	public void testNodesStats() {
		
		NodesStatsRequest request = new NodesStatsRequest();
		request.clear();
		request.threadPool(true);
//		request.indices(true);
//		request.jvm(true);
//		request.os(true);
//		request.process(true);
//		request.fs(true);
//		request.transport(true);
		NodesStatsResponse response = clusterAdminClient.nodesStats(request).actionGet();
		System.out.println(response);
	}
	
	@Test
	public void testIndexStats() {
		
		IndicesStatsRequest request = new IndicesStatsRequest();
		request.clear();
//		request.indices("epc");
//		request.types("epctype");
		request.indexing(true);
		IndicesStatsResponse response = indicesAdminClient.stats(request).actionGet();
		
		System.out.println(response);
	}
	
	@Test
	public void testPendingTask() {
		
		PendingClusterTasksRequest request = new PendingClusterTasksRequest();
		PendingClusterTasksResponse response = clusterAdminClient.pendingClusterTasks(request).actionGet();
		System.out.println(response);
	}
	
	@Test
	public void testIndexPutMapping() {
		
		PutMappingRequest request = new PutMappingRequest();
		request.indices("epc");
		request.type("epctype");
		
		
		try {
			XContentBuilder builder =  XContentFactory.jsonBuilder();
			
			builder.startObject()
			.startObject("properties")
					.startObject("barcode")
							.field("type", "text")
							.field("fielddata", true)
					.endObject()
					.endObject()
			.endObject();
			
			request.source(builder);
			indicesAdminClient.putMapping(request).actionGet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
