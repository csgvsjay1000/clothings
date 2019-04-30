package ivg.cn.es.monitor.model.metrics;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


/**  
 * 索引性能（类似传统DB的write） 
 * 当 es 插入、删除或更新文档时，索引中的每个分片都会经历refresh和flush两个阶段
 * */
@Getter
@Setter
public class IndexingPerformanceMetrics {

	/**  索引负载，即当前有多少插入/删除或更新索引请求 */
	private int indexingLoad;
	
	/**  索引延迟，定期抽样查询总数和对应的总耗用时间，可以计算出平均时间，若超过一定时间可以给予告警 */
	private BigDecimal indexingLatency;
	
	/**  flush延迟 */
	private BigDecimal flushLatency;
	
}
