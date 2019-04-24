package ivg.cn.clo.factory.dbservice;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.factory.model.dto.FGoodsPurchaseOrderDTO;

/**  查询采购订单服务 */
public interface PurchaseOrderService {

	/**  添加采购订单 */
	DreamResponse<Integer> insert(FGoodsPurchaseOrderDTO insertDTO);
	
}
