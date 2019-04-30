package ivg.cn.clo.tag.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.robert.vesta.service.intf.IdService;

import ivg.cn.clo.common.ConcurrentTestTools;
import ivg.cn.clo.tag.dbservice.EPCService;
import ivg.cn.clo.tag.model.dto.EPCBindDTO;
import ivg.cn.clo.tag.model.dto.GoodDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EPCServiceTest {

	@Autowired
	EPCService epcService;
	
	@Autowired
	IdService idService;
	
	@Test
	public void testInsert() {
		
		int thread = 1;
		int loop = 2;
		int perEpc = 2;
		
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
						epList.add("EA"+idService.genId());
					}
					detail.setEpcs(epList);
					dList.add(detail);
					try {
						Thread.sleep(1);
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
}
