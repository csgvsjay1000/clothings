package ivg.cn.clo.tag.db.config.sharding.algorithm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

public class TableHashPreciseAlgorithm implements PreciseShardingAlgorithm<String>{

	volatile Map<String, Integer> rangeMap = new HashMap<>();
	
	private int modCount;
	private String prefix;
	
	public TableHashPreciseAlgorithm(int modCount, String prefix) {
		this.modCount = modCount;
		this.prefix = prefix;
	}
	
	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
		
		String target =  prefix+"_"+(Math.abs((shardingValue.getValue()+"ABC").hashCode())%modCount);
		
		System.out.println(target);
		for (String string : availableTargetNames) {
			if (string.endsWith(target)) {
				return string;
			}
		}
		return null;
	}

}
