package ivg.cn.es.monitor.model.metrics;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**  JVM指标  */
public class JVMMetrics {

	/**  堆内存使用量(单位字节) */
	private long heapUsedInBytes;
	
	/**  堆内存使用百分比，若超过分配内存的75%，ES会触发GC，这时可能需要增加内存或增加节点 */
	private BigDecimal heapUsedPercent;
	
	/**  堆内存指定大小 */
	private long heapCommittedInBytes;
	
	/**  堆内存指定大小 */
	private long heapMaxInBytes;
	
	private int oldGcCount;
	
	private int oldGcTime;
	
	/**  GC持续时间 */
	private BigDecimal oldGcDuration;
	
	/**  GC频率，可以统计1个小时内GC次数 */
	private BigDecimal oldGcFrequence;
	
}
