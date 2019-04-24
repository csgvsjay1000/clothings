package ivg.cn.clo.factory.db.config.sharding.algorithm;

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
import ivg.cn.clo.factory.db.config.sharding.ShardingUtil;

/**  数据库时间范围算法 */
public class DBTimeRangeAlgorithm implements RangeShardingAlgorithm<Date>{

	int beginYear;
	String dbPre;
	int yearsPerDB;
	
	volatile Map<String/**dbName*/, Range<Date>> rangeMap = new HashMap<>();
	
	public DBTimeRangeAlgorithm(int beginYear,String dbPre, int yearsPerDB) {
		this.beginYear = beginYear;
		this.dbPre = dbPre;
		this.yearsPerDB = yearsPerDB;
	}
	
	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			RangeShardingValue<Date> shardingValue) {
		List<String> target = new ArrayList<>();
		Range<Date> shardRange = shardingValue.getValueRange();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println(
				"sharding value : "+format.format(shardRange.lowerEndpoint())+" --- " + format.format(shardRange.upperEndpoint()));
		
		for (String dbName : availableTargetNames) {
			
			// 通过缓存获取范围
			Range<Date> range = rangeMap.get(dbName);
			if (range == null) {
				synchronized (this) {
					range = rangeMap.get(dbName);
					if (range == null) {
						range = ShardingUtil.calculateDbRange(dbName, beginYear, yearsPerDB);
						Map<String/**dbName*/, Range<Date>> newMap = new HashMap<>(rangeMap.size()+1);
						newMap.putAll(rangeMap);
						newMap.put(dbName, range);
						rangeMap = newMap;
					}
				}
			}
			
			// 计算是否在范围内
			try {
				System.out.println(
						"range value : "+format.format(range.lowerEndpoint())+" --- " + format.format(range.upperEndpoint()));
				// 小的 小于 大的
				int lowerCmp = shardRange.lowerEndpoint().compareTo(range.upperEndpoint());
				
				// 大的小于小的
				int uperCmp = shardRange.upperEndpoint().compareTo(range.lowerEndpoint());
				
				if (lowerCmp > 0) {
					continue;
				}
				if (uperCmp < 0) {
					continue;
				}
				target.add(dbName);
//				if (uperCmp >=0 || lowerCmp>=0) {
//					target.add(dbName);
//				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("db: "+target);
		return target;
	}
	
	

}
