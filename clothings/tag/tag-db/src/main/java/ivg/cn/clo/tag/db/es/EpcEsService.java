package ivg.cn.clo.tag.db.es;

import java.util.List;

import ivg.cn.clo.tag.model.entify.BTag;

public interface EpcEsService {

	final String EPC_INDEX = "epc"; 
	final String EPC_INDEX_TYPE = "epctype"; 
	
	void put(List<BTag> tags);
	
}
