package ivg.cn.clo.factory.db;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexAction;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import ivg.cn.clo.factory.model.entity.FGoodsPackage;


public class ESTest {

	TransportClient client;
	
	@SuppressWarnings("resource")
	@Before
	public void createClient() throws UnknownHostException {
		client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.5.131"), 9300));
	}
	
	@After
	public void close() {
		client.close();
	}
	
	@Test
	public void createFGoodPack() {
		
		FGoodsPackage goodsPackage = new FGoodsPackage();
		goodsPackage.setFid(1L);
		goodsPackage.setNum("ZX190");
		
		IndexResponse response = client.prepareIndex("fgoodspackage", "pack","1")
				.setSource(JSON.toJSONString(goodsPackage),XContentType.JSON)
				.get()
				;
		System.out.println(response.toString());
	}
	
	@Test
	public void searchPack() {
		
	}
	
	@Test
	public void searchAllPack() {
		
		SearchResponse response = client.prepareSearch("epc")
				.setTypes("epctype")
				.setQuery(QueryBuilders.matchQuery("barcode", "Y92241209530"))
				.setFrom(0)
				.setSize(10)
				.get();
		
		System.out.println(response.toString());
	}
	
	@Test
	public void testClear() throws InterruptedException, ExecutionException {
		
		DeleteIndexRequestBuilder builder = new DeleteIndexRequestBuilder(client, DeleteIndexAction.INSTANCE, "epc");
		AcknowledgedResponse response = builder.execute().get();
		System.out.println(response.isAcknowledged());
	}
	
	@Test
	public void testTypeCount() {
		
		
		
		SearchRequestBuilder builder = client.prepareSearch("epc")
				.setTypes("epctype");
		
//		System.out.println(response.toString());
	}
	
	
	
}
