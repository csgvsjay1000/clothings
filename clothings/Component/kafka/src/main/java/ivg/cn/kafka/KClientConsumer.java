package ivg.cn.kafka;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KClientConsumer {

	static Logger logger = LoggerFactory.getLogger(KClientConsumer.class);
	
	private String propertiesFile;
	private Properties properties;
	
	MessageHandler handler;
	Consumer<String, String> consumer;
	
	public KClientConsumer(String propertiesFile) {
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
		logger.info("The KClientConsumer Properties: "+properties);
		
		initKafka();
	}
	
	private void initKafka() {
		consumer = new KafkaConsumer<>(properties);
	}
	
	public void subscribeTopic(String... topic) {
		consumer.subscribe(Arrays.asList(topic));
	}
	
	public void start() {
		new Thread("KClientConsumer-Master-Thread"){
			public void run() {
				while (true) {
					ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
			        for (ConsumerRecord<String, String> record : records){
			        	logger.info("receiveMsg, offset={}, key={}, value={}",record.offset() ,record.key(),record.value());
			        	handler.execute(record.topic(),record.key(), record.value());
			        }
				}
			};
		}.start();
	}
	
	public void setHandler(MessageHandler handler) {
		this.handler = handler;
	}
}
