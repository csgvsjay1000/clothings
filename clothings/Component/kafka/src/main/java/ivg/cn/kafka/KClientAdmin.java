package ivg.cn.kafka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KClientAdmin {
	static Logger logger = LoggerFactory.getLogger(KClientConsumer.class);
	
	private String propertiesFile;
	private Properties properties;
	
	AdminClient adminClient;
	
	public KClientAdmin(String propertiesFile) {
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
		logger.info("The adminClient Properties: "+properties);
		
		initKafka();
	}
	
	private void initKafka() {
		adminClient = AdminClient.create(properties);
	}
	
	public List<String> listTopics() {
		ListTopicsResult topicsResult = adminClient.listTopics();
		if (topicsResult != null) {
			try {
				return new ArrayList<>(topicsResult.names().get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Collection<ConsumerGroupListing> listAllConsumerGroups() {
		try {
			return adminClient.listConsumerGroups().all().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<ConsumerGroupListing> listValidConsumerGroups() {
		try {
			return adminClient.listConsumerGroups().valid().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void listConsumerGroupOffsets(String groupId) {
		adminClient.listConsumerGroupOffsets(groupId);
	}
}
