package ivg.cn.clo.factory.db.config.sharding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.google.common.collect.Range;

public class ShardingUtil {

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
	
	@Test
	public void testA() {
		String result = actualDataNodes("db", "f_delivery_bill", 1,2018,1,3);
		System.out.println(result);
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
	
	@Test
	public void testB() {
		String result = "db_0.f_delivery_bill_2018_0";
		int tbCountPerYear = 3;
		
		Range<Date> range1 = calculateTbRange(result, tbCountPerYear);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(format.format(range1.lowerEndpoint())+" --- " + format.format(range1.upperEndpoint()));
		
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
	
	
	
	@Test
	public void testC() {
		String result = "db_0";
		
		Range<Date> range = calculateDbRange(result, 2018, 3);
		System.out.println(range);
		
	}
	
}
