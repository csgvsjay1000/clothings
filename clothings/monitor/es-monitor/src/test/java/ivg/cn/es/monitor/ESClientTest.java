package ivg.cn.es.monitor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexAction;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.robert.vesta.service.impl.IdServiceImpl;
import com.robert.vesta.service.impl.bean.IdMetaFactory;
import com.robert.vesta.service.impl.bean.IdType;
import com.robert.vesta.service.impl.converter.IdConverterImpl;

import ivg.cn.es.monitor.utils.ConcurrentTestTools;


public class ESClientTest {
	
	TransportClient client;
	
	IdServiceImpl idService;
	
	// 0~1亿
	AtomicLong first = new AtomicLong(0);
	
	// 2亿-10亿
	AtomicLong second = new AtomicLong(200000000);
	
	// 11亿-20亿
	AtomicLong third = new AtomicLong(1100000000);
	
	Map<Long, BGoodDetail> goodMap = new HashMap<>();
	
	@SuppressWarnings("resource")
	@Before
	public void createClient() throws UnknownHostException {
		Settings settings = Settings.builder()
				.put("cluster.name","clothings-prod")
				.build();
		client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.5.131"), 12510));
		
		idService = new IdServiceImpl();
		idService.setMachineId(1);
		idService.setIdMeta(IdMetaFactory.getIdMeta(IdType.MAX_PEAK));
		idService.setIdConverter(new IdConverterImpl(IdType.MAX_PEAK));
		
		generateTestData();
		
	}
	
	@After
	public void close() {
		client.close();
	}
	
	@Test
	public void testUpdateIndexMapping() {
//		client.prepareIndex()
	}
	
	/**  制造一千万数据 */
	@Test
	public void testPut10000000() {
		ConcurrentTestTools tools = new ConcurrentTestTools(100, new Runnable() {
			
			@Override
			public void run() {
				BulkRequestBuilder builder = client.prepareBulk();
				for (int i = 0; i < 1000; i++) {
					for (BTag bTag : getTags_1(1000,first)) {
						builder.add(client.prepareIndex("epc", "epctype", String.valueOf(bTag.getFid()))
								.setSource(JSON.toJSONString(bTag), XContentType.JSON));
					}
					builder.execute().actionGet();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(first.get());
				}
				
			}
		});
		tools.start();
	}
	
	/**  制造一亿数据 */
	@Test
	public void testPut100000000() {
		
	}
	
	/**  制造10亿数据 */
	@Test
	public void testPut1000000000() {
		
	}
	
	
	@Test
	public void testPut() {
		
		ConcurrentTestTools tools = new ConcurrentTestTools(1, new Runnable() {
			
			@Override
			public void run() {
				BulkRequestBuilder builder = client.prepareBulk();
				for (int i = 0; i < 100; i++) {
					for (BTag bTag : getTags(100)) {
						builder.add(client.prepareIndex("epc", "epctype", String.valueOf(bTag.getFid()))
								.setSource(JSON.toJSONString(bTag), XContentType.JSON));
					}
					builder.execute().actionGet();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		tools.start();
	}
	
	private List<BTag> getTags_1(int times,AtomicLong ato) {
		Date date = new Date();
		int size = goodMap.size();
		List<BTag> bTags = new ArrayList<>();
		for (int i = 0; i < times; i++) {
			
			BGoodDetail mDetail = goodMap.get(Long.valueOf((i%size)));
			
			BTag tag = new BTag();
			tag.setFid(ato.getAndIncrement());
			tag.setLocationNum(mDetail.getLocationNum());
			tag.setCreater("chengsheng");
			tag.setEpc("EA"+tag.getFid());
			tag.setBarcode(mDetail.getBarcode());
			tag.setColor(mDetail.getColor());
			tag.setItemNumber(mDetail.getItemNumber());
			tag.setCreateDate(date);
			tag.setSize(mDetail.getSize());
			bTags.add(tag);
		}
		return bTags;
	}
	
	private List<BTag> getTags(int times) {
		Date date = new Date();
		
		List<BTag> bTags = new ArrayList<>();
		for (int i = 0; i < times; i++) {
			BTag tag = new BTag();
			tag.setFid(idService.genId());
			tag.setLocationNum("SGBLG");
			tag.setCreater("chengsheng");
			tag.setEpc("EA"+idService.genId());
			tag.setBarcode("Y9224120953006");
			tag.setColor("深兰色");
			tag.setItemNumber("Y92241209");
			tag.setCreateDate(date);
			tag.setSize("30");
			bTags.add(tag);
		}
		return bTags;
	}
	
	@Test
	public void testClear() throws InterruptedException, ExecutionException {
		
		DeleteIndexRequestBuilder builder = new DeleteIndexRequestBuilder(client, DeleteIndexAction.INSTANCE, "epc");
		AcknowledgedResponse response = builder.execute().get();
		System.out.println(response.isAcknowledged());
	}
	
	/**  构造测试数据
	 * <pre>
	 * | Y92241209	|	9夏男意七分印花C-1209	|	1个颜色	|	2个size	|
	 * | L91242150	|	9春女佳棉布翻领拼接C-2150	|	1个颜色	|	2个size	|
	 *  */
	public void generateTestData() {
		
		BGoodDetail detail = new BGoodDetail();
		detail.setItemNumber("Y92241209");
		detail.setBarcode("Y9224120953006");
		detail.setColor("深兰色");
		detail.setName("9夏男意七分印花C-1209");
		detail.setSize("30");
		detail.setLocationNum("SGBLG");
		goodMap.put(0L, detail);
		
		detail = new BGoodDetail();
		detail.setItemNumber("Y92241209");
		detail.setBarcode("Y9224120953007");
		detail.setColor("墨色");
		detail.setName("9夏男意七分印花墨绿C-1209");
		detail.setSize("30");
		detail.setLocationNum("SGBLG");
		goodMap.put(1L, detail);
		
		detail = new BGoodDetail();
		detail.setItemNumber("L91242150");
		detail.setBarcode("L912421503107");
		detail.setColor("粉红色");
		detail.setName("9春女佳棉布翻领拼接C-2150");
		detail.setSize("31");
		detail.setLocationNum("SGBLG");
		goodMap.put(2L, detail);
		
		detail = new BGoodDetail();
		detail.setItemNumber("L91242150");
		detail.setBarcode("L912421503007");
		detail.setColor("粉红色");
		detail.setName("9春女佳棉布翻领拼接C-2150");
		detail.setSize("30");
		detail.setLocationNum("SGBLG");
		goodMap.put(3L, detail);

	}
}
