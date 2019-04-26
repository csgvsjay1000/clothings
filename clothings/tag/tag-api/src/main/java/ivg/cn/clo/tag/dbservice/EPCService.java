package ivg.cn.clo.tag.dbservice;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.tag.model.dto.EPCBindDTO;

/**  
 * EPC服务
 * 包括：绑定、解绑、查询
 * 
 * */
public interface EPCService {

	final String EPC_OPERATION_BIND = "绑定"; 
	final String EPC_OPERATION_UNBIND = "解绑"; 
	
	/**  绑定EPC */
	DreamResponse<Integer> bind(EPCBindDTO epcBindDTO);
	
}
