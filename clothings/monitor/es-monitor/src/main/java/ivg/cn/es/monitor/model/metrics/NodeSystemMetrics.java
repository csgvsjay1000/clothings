package ivg.cn.es.monitor.model.metrics;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


/**  
 * 节点系统负载和网络流量
 * */
@Getter
@Setter
public class NodeSystemMetrics {

	/**  CPU 使用率 */
	private int cpuPercent;
	
	/**  ES 打开文件描述符数 */
	private int openFileDescriptors;
	
	/**  磁盘可用空间 */
	private int freeInBytes;
	
	/**  网络流入速率，每秒字节数 */
	private BigDecimal rxRate;
	
	/**  网络流出速率，每秒字节数 */
	private BigDecimal txRate;
	
}
