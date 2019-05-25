package ivg.cn.clo.tag.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.robert.vesta.service.intf.IdService;

import ivg.cn.clo.common.ConcurrentTestTools;
import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.tag.dbservice.EPCService;
import ivg.cn.clo.tag.model.dto.EPCBindDTO;
import ivg.cn.clo.tag.model.dto.GoodDetail;
import ivg.cn.clo.tag.model.entify.BTag;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EPCServiceTest {

	@Autowired
	EPCService epcService;
	
	@Autowired
	IdService idService;
	
	@Test
	public void testInsert() {
		
		int thread = 50;
		int loop = 10;
		int perEpc = 10;
		
		AtomicInteger value = new AtomicInteger(0);
		
		ConcurrentTestTools tools = new ConcurrentTestTools(thread, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				EPCBindDTO dto = new EPCBindDTO();
				dto.setLocationNum("SGBLG");
				dto.setUser("chengsheng");
				
				List<GoodDetail> dList = new ArrayList<>();
				for (int i = 0; i < loop; i++) {
					GoodDetail detail = new GoodDetail();
					detail.setBarcode("Y9224120953006");
					detail.setName("9夏男意七分印花C-1209");
					detail.setItemNumber("Y92241209");
					detail.setColor("深兰色");
					detail.setSize("30");
					List<String> epList = new ArrayList<>();
					for (int j = 0; j < perEpc; j++) {
//						epList.add("EA"+idService.genId());
						epList.add("EA"+value.incrementAndGet());
					}
					detail.setEpcs(epList);
					dList.add(detail);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				dto.setDetails(dList);
				
				epcService.bind(dto);
			}
		});
		tools.start();
		System.out.println("epc count: "+(thread*perEpc*loop));
	}
	
	@Test
	public void testUpdate() {
		EPCBindDTO dto = new EPCBindDTO();
		dto.setLocationNum("SGBLG");
		dto.setUser("chengsheng");
		
		DreamResponse<BTag> taDreamResponse = epcService.selectEpc(dto);
		
		List<BTag> bTags = taDreamResponse.getData();
		List<String> epList = new ArrayList<>(bTags.size());
		for (BTag bTag : bTags) {
			epList.add(bTag.getEpc());
		}
		long begin = System.currentTimeMillis();
		DreamResponse<Integer> response = epcService.batchUpdateEpc(epList);
		
		long end = System.currentTimeMillis();
		System.out.println("更新数量： "+response.getObjData()+", cost: "+(end-begin)+"ms");
	}
	
}
