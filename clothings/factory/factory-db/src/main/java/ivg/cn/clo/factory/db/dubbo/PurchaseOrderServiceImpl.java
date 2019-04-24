package ivg.cn.clo.factory.db.dubbo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.robert.vesta.service.intf.IdService;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.factory.db.mapper.FGoodsPurchaseOrderDetailMapper;
import ivg.cn.clo.factory.db.mapper.FGoodsPurchaseOrderMapper;
import ivg.cn.clo.factory.dbservice.PurchaseOrderService;
import ivg.cn.clo.factory.model.dto.FGoodsPurchaseOrderDTO;
import ivg.cn.clo.factory.model.dto.FGoodsPurchaseOrderDTO.OrderDetail;
import ivg.cn.clo.factory.model.entity.FGoodsPurchaseOrder;
import ivg.cn.clo.factory.model.entity.FGoodsPurchaseOrderDetail;

@org.springframework.stereotype.Service
@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IdService idService;
	
	@Autowired
	FGoodsPurchaseOrderMapper purchaseOrderMapper;
	
	@Autowired
	FGoodsPurchaseOrderDetailMapper purchaseOrderDetailMapper;
	
	@Override
	public DreamResponse<Integer> insert(FGoodsPurchaseOrderDTO insertDTO) {
		log.info(JSON.toJSON(insertDTO).toString());
		// 1、插入order
		// 2、插入orderDetail
		// 3、插入es, 若成功则结束，若失败则计入失败表，以便下次继续装入
		
		long fid = idService.genId();
		Date date = new Date();
		FGoodsPurchaseOrder order = new FGoodsPurchaseOrder();
		BeanUtils.copyProperties(insertDTO, order);
		order.setFid(fid);
		order.setCreateDate(date);
		purchaseOrderMapper.insert(order);
		
		List<FGoodsPurchaseOrderDetail> details = new ArrayList<>(insertDTO.getDetails().size());
		for(OrderDetail orderDetail : insertDTO.getDetails()){
			FGoodsPurchaseOrderDetail d = new FGoodsPurchaseOrderDetail();
			BeanUtils.copyProperties(orderDetail, d);
			d.setFid(idService.genId());
			d.setCreateDate(date);
			d.setPurchaseOrderNum(order.getNum());
			details.add(d);
		}
		purchaseOrderDetailMapper.batchInsert(details);
		
		return DreamResponse.createOKResponse();
	}

}
