package ivg.cn.clo.factory.dbservice;

import ivg.cn.clo.common.DreamResponse;

/**  工厂发货实现 */
public interface FDeliveryService {

	/**  列表显示发货单, 包括发货地址，收货地址，箱数，商品数 */
	DreamResponse<Integer> selectDelivery();
	
	/**  删除某单: 只能删除还未收货的单 */
	DreamResponse<Integer> deleteDelivery();
	
}
