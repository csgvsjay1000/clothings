package ivg.cn.kafka;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KClientProducer {

	static Logger logger = LoggerFactory.getLogger(KClientProducer.class);
	
	private String propertiesFile;
	private Properties properties;
	
	Producer<String, String> producer;
	
	public KClientProducer(String propertiesFile) {
		this.propertiesFile = propertiesFile;
		
		init();
	}
	
	private void init() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile));
			} catch (IOException e) {
				logger.error("The properties file is not loaded.",e);
				throw new IllegalArgumentException("The properties file is not loaded.", e);
			}
		}
		logger.info("The KClientProducer Properties: "+properties);
		
		producer = new KafkaProducer<String, String>(properties);
	}
	
	public void send2Topic(String topic, String key,String message) {
		if (null == message) {
			return;
		}
		producer.send(new ProducerRecord<String, String>(topic, key, message));
		producer.flush();
		
		
	}
	
}
