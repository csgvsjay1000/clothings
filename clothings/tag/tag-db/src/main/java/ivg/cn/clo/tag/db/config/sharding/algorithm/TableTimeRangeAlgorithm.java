package ivg.cn.clo.tag.db.config.sharding.algorithm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Range;

import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import ivg.cn.clo.tag.db.config.sharding.ShardingUtil;

public class TableTimeRangeAlgorithm implements RangeShardingAlgorithm<Date>{

	String tbPre;
	int tbCountPerYear;
	
	volatile Map<String/**tbName*/, Range<Date>> rangeMap = new HashMap<>();
	
	public TableTimeRangeAlgorithm(String tbPre, int tbCountPerYear) {
		this.tbPre = tbPre;
		this.tbCountPerYear = tbCountPerYear;
	}
	
	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			RangeShardingValue<Date> shardingValue) {
		List<String> target = new ArrayList<>();
		Range<Date> shardRange = shardingValue.getValueRange();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(format.format(shardRange.lowerEndpoint())+" --- " + format.format(shardRange.upperEndpoint()));
		
		for (String tableName : availableTargetNames) {
			
			// 通过缓存获取范围
			Range<Date> range = rangeMap.get(tableName);
			if (range == null) {
				synchronized (this) {
					range = rangeMap.get(tableName);
					if (range == null) {
						range = ShardingUtil.calculateTbRange(tableName, tbCountPerYear);
						Map<String/**dbName*/, Range<Date>> newMap = new HashMap<>(rangeMap.size()+1);
						newMap.putAll(rangeMap);
						newMap.put(tableName, range);
						rangeMap = newMap;
					}
				}
			}
			
			// 计算是否在范围内
			try {
				// 小的 小于 大的  > 
				int lowerCmp = shardRange.lowerEndpoint().compareTo(range.upperEndpoint());
				
				// 大的小于小的
				int uperCmp = shardRange.upperEndpoint().compareTo(range.lowerEndpoint());
				if (lowerCmp > 0) {
					continue;
				}
				if (uperCmp < 0) {
					continue;
				}
				target.add(tableName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("tables: "+target);

		return target;
	}

}
