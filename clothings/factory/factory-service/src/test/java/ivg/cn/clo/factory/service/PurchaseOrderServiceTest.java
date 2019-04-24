package ivg.cn.clo.factory.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import ivg.cn.clo.factory.dbservice.PurchaseOrderService;
import ivg.cn.clo.factory.model.dto.FGoodsPurchaseOrderDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseOrderServiceTest {

	@Autowired
//	@Reference
	PurchaseOrderService purchaseOrderService;
	
	@Test
	public void testInsert() {
		FGoodsPurchaseOrderDTO dto = new FGoodsPurchaseOrderDTO();
		dto.setNum("JA1190416003173");
		dto.setFromLocationId("SGBLG");  // 尚谷贝莱歌工厂
		dto.setToLocationId("000");  // 成都仓库
		dto.setBookQty(20);  // 采购总数量
		dto.setItemNumber("Y92241209");  // 款号
		
		List<FGoodsPurchaseOrderDTO.OrderDetail> details = new ArrayList<>();
		FGoodsPurchaseOrderDTO.OrderDetail detail = new FGoodsPurchaseOrderDTO.OrderDetail();
		detail.setBarcode("Y9224120953006");
		detail.setBookQty(20);
		
		details.add(detail);
		dto.setDetails(details);
		
		purchaseOrderService.insert(dto);
	}
	
}
