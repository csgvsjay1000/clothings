package ivg.cn.clo.tag.db.dubbo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robert.vesta.service.intf.IdService;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.tag.db.es.EpcEsService;
import ivg.cn.clo.tag.db.mapper.BTagFlowMapper;
import ivg.cn.clo.tag.db.mapper.BTagMapper;
import ivg.cn.clo.tag.dbservice.EPCService;
import ivg.cn.clo.tag.model.dto.EPCBindDTO;
import ivg.cn.clo.tag.model.dto.GoodDetail;
import ivg.cn.clo.tag.model.entify.BTag;
import ivg.cn.clo.tag.model.entify.BTagFlow;

@Service
@com.alibaba.dubbo.config.annotation.Service(retries=0,timeout=6000)
public class EPCServiceImpl implements EPCService{

	@Autowired
	IdService idService;

	@Autowired
	BTagMapper tagMapper;
	
	@Autowired
	BTagFlowMapper tagFlowMapper;
	
	@Autowired
	EpcEsService epcEsService;
	
	@Transactional
	@Override
	public DreamResponse<Integer> bind(EPCBindDTO epcBindDTO) {
		// 1、先插入EPC表
		// 2、插入跟踪表
		// 3、插入ES

		
		List<BTag> bTags = new ArrayList<>();
		List<BTagFlow> bTagFlows = new ArrayList<>();
		
		Date date = new Date();
		
		List<GoodDetail> details = epcBindDTO.getDetails();
		for (GoodDetail goodDetail : details) {
			for(String epc : goodDetail.getEpcs()){
				BTag tag = new BTag();
				tag.setFid(idService.genId());
				tag.setLocationNum(epcBindDTO.getLocationNum());
				tag.setCreater(epcBindDTO.getUser());
				tag.setEpc(epc);
				tag.setBarcode(goodDetail.getBarcode());
				tag.setColor(goodDetail.getColor());
				tag.setItemNumber(goodDetail.getItemNumber());
				tag.setCreateDate(date);
				bTags.add(tag);
				
				// 组装标签跟踪数据
				BTagFlow tagFlow = new BTagFlow();
				BeanUtils.copyProperties(tag, tagFlow);
				tagFlow.setOperationName(EPC_OPERATION_BIND);
				bTagFlows.add(tagFlow);
			}
		}
		// 1、先插入EPC表
		// 2、插入跟踪表
		tagMapper.insertList(bTags);
		tagFlowMapper.insertList(bTagFlows);

//		throw new RuntimeException("测试事物回滚");
		
		epcEsService.put(bTags);
		
		return DreamResponse.createOKResponse();
	}

}
