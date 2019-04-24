package ivg.cn.clo.factory.db;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.robert.vesta.service.intf.IdService;

import ivg.cn.clo.factory.db.mapper.FGoodsPackageMapper;
import ivg.cn.clo.factory.model.entity.FGoodsPackage;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FDeliveryBillMapperTest {
	
	@Autowired
	private FGoodsPackageMapper packageMapper;
	
	@Autowired
	IdService idService;
	
	@Test
	public void testInsertListSelective() {
		
		List<FGoodsPackage> packages = new ArrayList<>();
		
		Date date = new Date();
		
		for (int i = 0; i < 1; i++) {
			FGoodsPackage p = new FGoodsPackage();
//			p.setFid(idService.genId());
			p.setCreateDate(date);
			packages.add(p);
		}
		packageMapper.batchInsertSelective(packages);
	}
	
	@Test
	public void testInsertList() {
		
		List<FGoodsPackage> packages = new ArrayList<>();
		
		Date date = new Date();
		
		for (int i = 0; i < 1; i++) {
			FGoodsPackage p = new FGoodsPackage();
//			p.setFid(idService.genId());
			p.setCreateDate(date);
			packages.add(p);
		}
		packageMapper.batchInsert(packages);
	}
	
	@Test
	public void testSelect() {
		
	}
	
}
