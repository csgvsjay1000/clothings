package ivg.cn.clo.factory.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.factory.dbservice.PurchaseOrderService;
import ivg.cn.clo.factory.model.dto.FGoodsPurchaseOrderDTO;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

	@Reference
	PurchaseOrderService purchaseOrderService;
	
	@Override
	public DreamResponse<Integer> insert(FGoodsPurchaseOrderDTO insertDTO) {
//		FGoodsPurchaseOrderDTO dto = new FGoodsPurchaseOrderDTO();
//		dto.setNum("PA34234");
//		dto.setBookQty(20);
		return purchaseOrderService.insert(insertDTO);
	}

}
