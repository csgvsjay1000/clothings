package ivg.cn.es.monitor;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import ivg.cn.es.monitor.service.NodeStatService;

public class ESController {

	ClusterAdminClient clusterAdminClient;
	
	IndicesAdminClient indicesAdminClient;
	
	NodeStatService nodeStatService;
	
	public ESController() {
		
	}
	
	public boolean startup() {
		
		try {
			createClient();
			nodeStatService = new NodeStatService(clusterAdminClient);
			return true;

		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void createClient() throws UnknownHostException {
		Settings settings = Settings.builder()
				.put("cluster.name","clothings-prod")
				.build();
		@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.5.131"), 12510));
		clusterAdminClient = client.admin().cluster();
		indicesAdminClient = client.admin().indices();
	}
	
}
