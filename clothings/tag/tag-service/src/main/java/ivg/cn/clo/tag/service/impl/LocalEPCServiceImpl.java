package ivg.cn.clo.tag.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.tag.dbservice.EPCService;
import ivg.cn.clo.tag.model.dto.EPCBindDTO;

@Service
public class LocalEPCServiceImpl implements EPCService{

	@Reference(retries=0,timeout=6000)
	private EPCService dubboEpcService;
	
	@Override
	public DreamResponse<Integer> bind(EPCBindDTO epcBindDTO) {
		return dubboEpcService.bind(epcBindDTO);
	}

}
