package ivg.cn.clo.tag.dbservice;

import java.util.List;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.tag.model.dto.EPCBindDTO;
import ivg.cn.clo.tag.model.entify.BTag;

/**  
 * EPC服务
 * 包括：绑定、解绑、查询
 * 
 * */
public interface EPCService {

	final String EPC_OPERATION_BIND = "绑定"; 
	final String EPC_OPERATION_UNBIND = "解绑"; 
	
	final String EPC_BIND = "1"; 
	final String EPC_UNBIND = "2"; 
	
	/**  绑定EPC */
	DreamResponse<Integer> bind(EPCBindDTO epcBindDTO);
	
	/**  查询EPC状态 */
	public DreamResponse<BTag> selectEpc(EPCBindDTO epcBindDTO);
	
	
	/**  批量更新EPC状态 */
	public DreamResponse<Integer> batchUpdateEpc(List<String> epcs);
	
}
