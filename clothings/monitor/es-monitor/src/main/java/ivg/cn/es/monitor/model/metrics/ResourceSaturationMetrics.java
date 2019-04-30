package ivg.cn.es.monitor.model.metrics;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


/**  
 * 资源饱和度统计
 * */
@Getter
@Setter
public class ResourceSaturationMetrics {

	/**  写线程使用情况 */
	private int writeQueue;
	private int writeRejected;
	private int writeCompleted;
	
	/**  查询线程使用情况 */
	private int searchQueue;
	private int searchRejected;
	private int searchCompleted;
	
	/**  get线程使用情况 */
	private int getQueue;
	private int getRejected;
	private int getCompleted;
	
	/**  index 线程使用情况 */
	private int indexQueue;
	private int indexRejected;
	private int indexCompleted;
	
	
	
	/**  ES 打开文件描述符数 */
	private int openFileDescriptors;
	
	/**  磁盘可用空间 */
	private int freeInBytes;
	
	/**  网络流入速率，每秒字节数 */
	private BigDecimal rxRate;
	
	/**  网络流出速率，每秒字节数 */
	private BigDecimal txRate;
	
}
