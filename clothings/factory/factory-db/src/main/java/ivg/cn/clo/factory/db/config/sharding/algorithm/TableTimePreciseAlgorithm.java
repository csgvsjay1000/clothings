package ivg.cn.clo.factory.db.config.sharding.algorithm;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**  table时间精准算法 */
public class TableTimePreciseAlgorithm implements PreciseShardingAlgorithm<Date>{

	String tbPre;
	int tbCountPerYear;
	
	public TableTimePreciseAlgorithm(String tbPre, int tbCountPerYear) {
		this.tbPre = tbPre;
		this.tbCountPerYear = tbCountPerYear;
	}
	
	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
		String name = tableName(shardingValue.getValue());
		for (String string : availableTargetNames) {
			if (string.equals(name)) {
				return string;
			}
		}
		return null;
	}
	
	private String tableName(Date date) {
		Calendar cale = null;  
        cale = Calendar.getInstance();
        cale.setTime(date);
        int year = cale.get(Calendar.YEAR);  
        int month = cale.get(Calendar.MONTH);
        
        int m = 12/tbCountPerYear;
        return tbPre+"_"+ year + "_" + month/m;
	}

}
