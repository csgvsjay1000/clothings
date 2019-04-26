package ivg.cn.clo.tag.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.robert.vesta.service.impl.IdServiceImpl;
import com.robert.vesta.service.impl.bean.IdMetaFactory;
import com.robert.vesta.service.impl.bean.IdType;
import com.robert.vesta.service.impl.converter.IdConverterImpl;
import com.robert.vesta.service.impl.populater.AtomicIdPopulator;
import com.robert.vesta.service.intf.IdService;

@Configuration
public class IDConfig {

	@Bean
	public IdService idService() {
		IdServiceImpl idService = new IdServiceImpl();
		idService.setMachineId(1);
		idService.setIdMeta(IdMetaFactory.getIdMeta(IdType.MAX_PEAK));
		idService.setIdConverter(new IdConverterImpl(IdType.MAX_PEAK));
		idService.setIdPopulator(new AtomicIdPopulator());
		return idService;
	}
	
	
}
