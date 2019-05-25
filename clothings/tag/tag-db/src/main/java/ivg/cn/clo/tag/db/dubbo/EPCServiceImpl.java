package ivg.cn.clo.tag.db.dubbo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
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
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
				tag.setFstatus(EPC_BIND);
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
		
//		epcEsService.put(bTags);
		
		return DreamResponse.createOKResponse();
	}
	
	public DreamResponse<BTag> selectEpc(EPCBindDTO epcBindDTO) {
		
		Example example = new Example(BTag.class);
		example.selectProperties("epc","fstatus");
//		Criteria criteria = example.createCriteria();
		
		RowBounds rowBounds = new RowBounds(6*200, 500);
		
		List<BTag> tags = tagMapper.selectByExampleAndRowBounds(example, rowBounds);
		
		 DreamResponse<BTag> response = DreamResponse.createOKResponse();
		 response.setData(tags);
		
		return response;
	}
	
	public DreamResponse<Integer> batchUpdateEpc(List<String> epcs) {
		
		Example example = new Example(BTag.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("epc", epcs);
		
		BTag tag = new BTag();
		tag.setFstatus(EPC_BIND);
		
		Integer updateCount = tagMapper.updateByExampleSelective(tag, example);
		
		 DreamResponse<Integer> response = DreamResponse.createOKResponse();
		 response.setObjData(updateCount);
		 
		 example = new Example(BTag.class);
		 criteria = example.createCriteria();
		 criteria.andEqualTo("fstatus", EPC_BIND);
		int count = tagMapper.selectCountByExample(example);
		response.setObjData(count);
		return response;
	}
	

}
