package ivg.cn.clo.factory.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.config.annotation.Reference;

import ivg.cn.clo.factory.dbservice.PurchaseOrderService;
import ivg.cn.clo.factory.model.dto.FGoodsPurchaseOrderDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class dubboTest {

//	@Autowired
	@Reference
	PurchaseOrderService purchaseOrderService;
	
	@Test
	public void testInsert() {
		FGoodsPurchaseOrderDTO dto = new FGoodsPurchaseOrderDTO();
		dto.setNum("PA34234");
		dto.setBookQty(20);
		purchaseOrderService.insert(dto);
	}
	
}
