package ivg.cn.vesta.impl;

import java.util.Date;
import java.util.Random;


import ivg.cn.vesta.Id;
import ivg.cn.vesta.impl.bean.IdMeta;
import ivg.cn.vesta.impl.bean.IdMetaFactory;
import ivg.cn.vesta.impl.bean.IdType;
import ivg.cn.vesta.impl.converter.IdConverter;
import ivg.cn.vesta.impl.converter.IdConverterImpl;
import ivg.cn.vesta.impl.provider.MachineIdProvider;
import ivg.cn.vesta.impl.timer.SimpleTimer;
import ivg.cn.vesta.impl.timer.Timer;
import ivg.cn.vesta.IdService;

public abstract class AbstractIdServiceImpl implements IdService{

	
	protected long machineId = 1;
	protected long genMethod = 0;
	protected long version = 0;
	
	protected IdType idType;
	protected IdMeta idMeta;
	
	protected IdConverter idConverter;
	
	protected MachineIdProvider machineIdProvider;
	
	protected Timer timer;
	
	public AbstractIdServiceImpl() {
		idType = IdType.SECONDS;
		init();
	}
	
	public void init() {
		if (idMeta == null) {
			this.setIdMeta(IdMetaFactory.getIdMeta(idType));
		}
		if (this.idConverter == null) {
			this.idConverter = new IdConverterImpl();
		}
		if (this.timer == null) {
			this.timer = new SimpleTimer();
		}
		
		this.timer.init(idMeta, idType);
//		this.machineId = machineIdProvider.getMachineId();
		if (this.machineId == 0) {
			this.machineId = new Random().nextInt(100);
		}
	}

	@Override
	public long genId() {
		Id id = new Id();
		
		id.setMachine(machineId);
		id.setGenMethod(genMethod);
		id.setType(idType.value());
		id.setVersion(version);
		
		// 生成time,seq
		this.populateId(id);
		
		long ret = idConverter.convert(id, idMeta);
		
		return ret;
	}

	@Override
	public Id expId(long id) {
		return idConverter.convert(id, idMeta);
	}
	
	@Override
	public Date transTime(long time) {
		return timer.transTime(time);
	}
	
	protected abstract void populateId(Id id);
	
	public void setIdMeta(IdMeta idMeta) {
		this.idMeta = idMeta;
	}

	public long getMachineId() {
		return machineId;
	}

	public void setMachineId(long machineId) {
		this.machineId = machineId;
	}
	
}
