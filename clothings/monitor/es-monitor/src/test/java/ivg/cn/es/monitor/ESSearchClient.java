package ivg.cn.es.monitor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ESSearchClient {

	TransportClient client;

	@SuppressWarnings("resource")
	@Before
	public void createClient() throws UnknownHostException {
		Settings settings = Settings.builder()
				.put("cluster.name","clothings-prod")
				.build();
		client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.5.131"), 12510));
	}
	
	@After
	public void close() {
		client.close();
	}
	
	@Test
	/**  查询每款商品的数量 
	 * @throws ExecutionException 
	 * @throws InterruptedException */
	public void testCount() throws InterruptedException, ExecutionException {
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("barcode", "Y9224120953007");
		
		SearchResponse response = client.prepareSearch("epc")
		
			.setQuery(queryBuilder)
			.setSize(2)
			.addSort(SortBuilders.fieldSort("createDate").order(SortOrder.fromString("ASC")))
			.execute().get();
		response.getHits().getTotalHits();
		System.out.println(response);
	}
	
	@Test
	/**  查询每款商品的数量 
	 * @throws ExecutionException 
	 * @throws InterruptedException */
	public void testAggCount() throws InterruptedException, ExecutionException {
		
		SearchResponse response = client.prepareSearch("epc")
//				.setQuery(QueryBuilders.matchPhraseQuery("itemNumber", "l91242150"))  // 条件查询
			.addAggregation(AggregationBuilders.terms("epc_count").field("barcode"))  // 按款分组
			.setSize(0)
			.execute().get();
		System.out.println(response);
	}
	
}
