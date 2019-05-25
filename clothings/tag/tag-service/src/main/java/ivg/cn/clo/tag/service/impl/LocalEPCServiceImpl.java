package ivg.cn.clo.tag.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.tag.dbservice.EPCService;
import ivg.cn.clo.tag.model.dto.EPCBindDTO;
import ivg.cn.clo.tag.model.entify.BTag;

@Service
public class LocalEPCServiceImpl implements EPCService{

	@Reference(retries=0,timeout=600000)
	private EPCService dubboEpcService;
	
	@Override
	public DreamResponse<Integer> bind(EPCBindDTO epcBindDTO) {
		return dubboEpcService.bind(epcBindDTO);
	}

	@Override
	public DreamResponse<BTag> selectEpc(EPCBindDTO epcBindDTO) {
		return dubboEpcService.selectEpc(epcBindDTO);
	}

	@Override
	public DreamResponse<Integer> batchUpdateEpc(List<String> epcs) {
		return dubboEpcService.batchUpdateEpc(epcs);
	}

}
