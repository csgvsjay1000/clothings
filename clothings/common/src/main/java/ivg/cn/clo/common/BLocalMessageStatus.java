package ivg.cn.clo.common;

/**  本地消息表相关状态 */
public interface BLocalMessageStatus {

	
	int WaitHandle = 1; // 待处理 
	int Handling = 2; // 处理中 
	int Finshed = 3; // 处理完毕 
	int Discard = 4; // 已废弃 
	
	interface Business{
		/**  工厂装箱 */
		String FZX = "fzx";
	}
	
	
	
}
