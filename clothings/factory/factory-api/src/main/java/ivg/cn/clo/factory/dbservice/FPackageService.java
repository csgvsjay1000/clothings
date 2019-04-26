package ivg.cn.clo.factory.dbservice;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.factory.model.dto.FGoodsPackageInsertDTO;

/**  装箱逻辑 */
public interface FPackageService {

	/**  生成箱号 */
	DreamResponse<String> genPackNum();
	
	/**  装箱 */
	DreamResponse<Integer> insertPackage(FGoodsPackageInsertDTO insertDTO);
	
	/**  查询箱 */
	DreamResponse<Integer> selectPackage();
	
	/**  查询箱详情，箱里每个条码的商品数 */
	DreamResponse<Integer> selectPackageDetail();
	
	/**  选择送货地址一致的，生成一个发货单 */
	DreamResponse<Integer> delivery();
	
}
