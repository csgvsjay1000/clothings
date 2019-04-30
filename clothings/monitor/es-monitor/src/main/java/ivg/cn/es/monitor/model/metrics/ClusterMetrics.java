package ivg.cn.es.monitor.model.metrics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**  集群健康状态 */
public class ClusterMetrics {

	/**  集群名 */
	private String clusterName;
	
	/**  集群状态: green,yellow,red */
	private String status;
	
	/**  正在初始化的分片数，当创建index，或节点重启时 */
	private int initializingShards;
	
	/**  未分配分片数 */
	private int unassignedShards;
	
}
