package ivg.cn.clo.tag.db.config.sharding;

import java.util.Calendar;
import java.util.Date;
import com.google.common.collect.Range;

public class ShardingUtil {

	/**  根据时间分片 */
	public static String actualDataNodes(String dbPrex, String tbPrex, int dbCount,
			int beginYear, int yearsPerDB, int tbCountPerYear) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dbCount; i++) {
			
			for (int j = 0; j < yearsPerDB; j++) {
				for (int j2 = 0; j2 < tbCountPerYear; j2++) {
					if (sb.length()>0) {
						sb.append(",");
					}
					sb.append(dbPrex).append("_").append(i).append(".").append(tbPrex).append("_")
						.append(beginYear).append("_").append(j2);
					
				}
				beginYear++;
			}
		}
		
		return sb.toString();
	}
	
	/**  根据字段hash分片 */
	public static String actualDataNodesWithHash(String dbPrex, String tbPrex, int dbCount,
			int tbCountPerDB) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dbCount; i++) {
			
			for (int j = 0; j < tbCountPerDB; j++) {
				if (sb.length()>0) {
					sb.append(",");
				}
				sb.append(dbPrex).append("_").append(i).append(".").append(tbPrex).append("_")
					.append(j);
			}
		}
		
		return sb.toString();
	}
	
	/**  获取分片 */
	public static int getDBShard(String val, int modCount) {
		return (Math.abs(val.hashCode())%modCount);
	}
	
	/**  获取分片 */
	public static int getTBShard(String val, int modCount) {
		return (Math.abs(("ABC"+val).hashCode())%modCount);
	}
	
	/**
	 * @param tableName 表名，如：f_delivery_bill_2018_4
	 * @param tbCountPerYear 一年分多少个表，最好能平分12个月
	 * 根据表名计算表范围f_delivery_bill_2018_4范围是 2018-10~12
	 * */
	public static Range<Date> calculateTbRange(String tableName, int tbCountPerYear) {
		String monthSux = tableName.substring(tableName.lastIndexOf("_")+1);
		String yearAllStr = tableName.substring(0, tableName.lastIndexOf("_"));
		String yearStr = yearAllStr.substring(yearAllStr.lastIndexOf("_")+1);
		
		int monthInt = Integer.valueOf(monthSux);
		int yearInt = Integer.valueOf(yearStr);
		
		int size = 12/tbCountPerYear;
		
		int beginMonth = size*(monthInt);
		int endMonth = size*(monthInt) + size;
		
		Calendar cale = null;  
        cale = Calendar.getInstance();
        
        cale.set(yearInt, beginMonth, 1,0,0,0);
		Date beginDate = cale.getTime();
		cale.set(yearInt, endMonth, cale.get(Calendar.DAY_OF_MONTH),23,59,59);
        cale.add(Calendar.DAY_OF_MONTH, -1);
        
		Date endDate = cale.getTime();
		
		Range<Date> range = Range.open(beginDate, endDate);
		return range;
		
	}
	
	
	public static Range<Date> calculateDbRange(String dbName, int beginYear, int yearsPerDB) {
		String yearStr = dbName.substring(dbName.lastIndexOf("_")+1);
		
		int yearInt = Integer.valueOf(yearStr);
		
		
		Calendar cale = null;  
        cale = Calendar.getInstance();
        int beginY = yearsPerDB*(yearInt)+ beginYear;
		int endY = yearsPerDB*(yearInt) + yearsPerDB + beginYear;
        
        cale.set(beginY, 0, 1,0,0,0);
		Date beginDate = cale.getTime();
		cale.set(endY, 0, 1,23,59,59);
        cale.add(Calendar.DAY_OF_MONTH, -1);
		Date endDate = cale.getTime();
		
		Range<Date> range = Range.open(beginDate, endDate);
		return range;
		
	}
	
	
}
