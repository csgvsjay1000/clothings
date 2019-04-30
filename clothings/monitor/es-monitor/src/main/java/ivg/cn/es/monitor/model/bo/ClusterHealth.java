package ivg.cn.es.monitor.model.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**  集群健康状态 */
public class ClusterHealth {

	/**  集群名 */
	private String clusterName;
	
	/**  集群状态: green,yellow,red */
	private String status;
	
	/**  集群节点数 */
	private int numberOfNodes;
	
	/**  集群数据节点数 */
	private int numberOfDataNodes;
	
	/**  集群中活跃的主分片数 */
	private int activePrimaryShards;
	
	/**  集群中活跃的分片数(包括副分片) */
	private int activeShards;
	
	
}
