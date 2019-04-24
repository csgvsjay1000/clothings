package ivg.cn.clo.factory.db;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class SimpleTesrt {

	@Test
	public void testCurrent() {
		
		Calendar cale = null;  
        cale = Calendar.getInstance();
        
        cale.set(2017, 12, 0);
        cale.getTime();
        
        cale.setTime(new Date());
        int year = cale.get(Calendar.YEAR);  
        int month = cale.get(Calendar.MONTH) + 1;  
        
        
        
        System.out.println(year+":"+month);
	}
}
