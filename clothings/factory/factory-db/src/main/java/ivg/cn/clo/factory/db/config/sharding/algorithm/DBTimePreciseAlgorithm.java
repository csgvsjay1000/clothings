package ivg.cn.clo.factory.db.config.sharding.algorithm;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**  数据库时间精准算法 */
public class DBTimePreciseAlgorithm implements PreciseShardingAlgorithm<Date>{

	int beginYear;
	String dbPre;
	int yearsPerDB;
	
	public DBTimePreciseAlgorithm(int beginYear,String dbPre, int yearsPerDB) {
		this.beginYear = beginYear;
		this.dbPre = dbPre;
		this.yearsPerDB = yearsPerDB;
	}
	
	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
		String name = dbName(shardingValue.getValue());
		for (String string : availableTargetNames) {
			if (string.equals(name)) {
				return string;
			}
		}
		return null;
	}
	
	private String dbName(Date date) {
		Calendar cale = null;  
        cale = Calendar.getInstance();
        cale.setTime(date);
        int year = cale.get(Calendar.YEAR);  
        
        return dbPre+"_"+(year-beginYear)/yearsPerDB;
	}

}
