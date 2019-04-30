package ivg.cn.es.monitor.model.metrics;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**  查询性能（类似传统DB的read） */
public class SearchPerformanceMetrics {

	/**  查询负载，即当前有多少查询请求 */
	private int queryLoad;
	
	/**  查询延迟，定期抽样查询总数和对应的总耗用时间，可以计算出平均查询时间，若超过一定时间可以给予告警 */
	private BigDecimal queryLatency;
	
	/**  提取延迟，搜索的第二阶段,通常比查询时间花费的少的多时间，若发现该值持续增加,则表示可能磁盘缓慢 */
	private BigDecimal fetchLatency;
	
}
