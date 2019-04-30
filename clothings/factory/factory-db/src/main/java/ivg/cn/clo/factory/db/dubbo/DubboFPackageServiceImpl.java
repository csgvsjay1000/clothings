package ivg.cn.clo.factory.db.dubbo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.robert.vesta.service.intf.IdService;

import ivg.cn.clo.common.DreamResponse;
import ivg.cn.clo.factory.db.mapper.FGoodsPackageDetailMapper;
import ivg.cn.clo.factory.db.mapper.FGoodsPackageItemMapper;
import ivg.cn.clo.factory.db.mapper.FGoodsPackageMapper;
import ivg.cn.clo.factory.dbservice.FPackageService;
import ivg.cn.clo.factory.model.dto.FGoodsPackageInsertDTO;
import ivg.cn.clo.factory.model.dto.FPackDetail;
import ivg.cn.clo.factory.model.entity.FGoodsPackage;
import ivg.cn.clo.factory.model.entity.FGoodsPackageDetail;
import ivg.cn.clo.factory.model.entity.FGoodsPackageItem;

@org.springframework.stereotype.Service
@Service(retries=0,timeout=6000)
@Transactional
public class DubboFPackageServiceImpl implements FPackageService{

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IdService idService;
	
	@Autowired
	FGoodsPackageMapper packageMapper;
	
	@Autowired
	FGoodsPackageItemMapper packageItemMapper;
	
	@Autowired
	FGoodsPackageDetailMapper packageDetailMapper;
	
	@Override
	public DreamResponse<String> genPackNum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DreamResponse<Integer> insertPackage(FGoodsPackageInsertDTO insertDTO) {
		// 装箱操作
		// 1、插入箱表
		// 2、插入箱详情表
		// 3、插入箱EPC表
		// 3、插入本地消息表
		// 采用分布式事物
		// 4、更新tag服务中，EPC状态,追加EPC生命周期
		// 采用异步方式
		// 5、将箱数据插入ES中，包括箱里有多少款，多少条码
		// 6、更新ES中EPC状态
		
		Date date = new Date();
		
		FGoodsPackage goodsPackage = new FGoodsPackage();
		BeanUtils.copyProperties(insertDTO, goodsPackage);
		goodsPackage.setFid(idService.genId());
		goodsPackage.setCreateDate(date);
		
		int total = 0;
		
		List<FGoodsPackageDetail> details = new ArrayList<>();
		List<FGoodsPackageItem> epcItems = new ArrayList<>();
		for(FPackDetail pDetail : insertDTO.getPacks()){
			FGoodsPackageDetail d = new FGoodsPackageDetail();
			BeanUtils.copyProperties(pDetail, d);
			d.setFid(idService.genId());
			d.setPackageId(goodsPackage.getFid());
			d.setGoodsQty(pDetail.getEpcs().size());
			details.add(d);
			total += d.getGoodsQty();
			
			for(String epc : pDetail.getEpcs()){
				FGoodsPackageItem item = new FGoodsPackageItem();
				item.setEpc(epc);
				item.setBarcode(pDetail.getBarcode());
				item.setPackageDetailId(d.getFid());
				item.setPackageId(goodsPackage.getFid());
				epcItems.add(item);
			}
			
		}
		goodsPackage.setGoodsQty(total);
		
		// 1、插入箱表
		// 2、插入箱详情表
		// 3、插入箱EPC表
		packageMapper.insert(goodsPackage);
		packageDetailMapper.batchInsert(details);
		packageItemMapper.batchInsert(epcItems);
		
		
		return null;
	}

	@Override
	public DreamResponse<Integer> selectPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DreamResponse<Integer> selectPackageDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DreamResponse<Integer> delivery() {
		// TODO Auto-generated method stub
		return null;
	}

}
