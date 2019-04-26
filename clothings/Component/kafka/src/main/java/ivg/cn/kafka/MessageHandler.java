package ivg.cn.kafka;

public interface MessageHandler {

	void execute(String topic,String key,String message);
	
}
