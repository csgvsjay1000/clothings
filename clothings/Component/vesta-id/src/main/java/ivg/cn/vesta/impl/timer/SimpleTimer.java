package ivg.cn.vesta.impl.timer;

import java.util.Date;

import ivg.cn.vesta.impl.bean.IdMeta;
import ivg.cn.vesta.impl.bean.IdType;


public class SimpleTimer implements Timer{

	protected IdMeta idMeta;
	protected IdType idType;
	protected long maxTime;
	
	@Override
	public void init(IdMeta idMeta, IdType idType) {
		this.idMeta = idMeta;
		this.idType = idType;
		this.maxTime = (1L << idMeta.getTimeBits()) - 1;  // 最大时间 1左移20位 -1 
		this.genTime();
		this.timerUsedLog();
	}
	
	@Override
	public long genTime() {
		long time;
		if (idType == IdType.MILLISECONDS) {
			// 毫秒级时间
			time = (System.currentTimeMillis() - EPOCH);
		}else {
			// 秒级时间
			time = (System.currentTimeMillis() - EPOCH)/1000;
		}
		return time;
	}
	
	@Override
	public Date transTime(long time) {
		if (idType == IdType.MILLISECONDS) {
			return new Date(time + EPOCH);
		}else {
			return new Date(time*1000 + EPOCH);
		}
	}
	
	public void timerUsedLog(){
//        Date expirationDate = transTime(maxTime);
//        long days = ((expirationDate.getTime() - System.currentTimeMillis())/(1000 * 60 * 60 * 24));
    }
	
	@Override
	public void validateTimestamp(long lastTimestamp, long timestamp) {
		if (timestamp < lastTimestamp) {
			throw new IllegalStateException(String.format("Clock moved backwards.  Refusing to generate id for %d second/milisecond.",
                        lastTimestamp - timestamp));
		}
		
	}
	
	@Override
	public long tillNextTimeUnit(long lastTimestamp) {
		
		long timestamp = this.genTime();
		while (timestamp <= lastTimestamp) {
			timestamp = this.genTime();
		}
		
		return timestamp;
	}

}
