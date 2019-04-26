package ivg.cn.clo.tag.db.config.sharding;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.core.rule.ShardingRule;
import io.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import ivg.cn.clo.tag.db.config.sharding.tables.BTagFlowTable;
import ivg.cn.clo.tag.db.config.sharding.tables.BTagTable;

@Configuration
public class ShardingDSConfig {

	List<DSBaseConfig> dsConfigs;
	
	@Bean
	public DataSource getShardingDataSource() throws SQLException {
		ShardingRuleConfiguration ruleConfiguration = new ShardingRuleConfiguration();
		ruleConfiguration.getTableRuleConfigs().add(BTagTable.build());
		ruleConfiguration.getTableRuleConfigs().add(BTagFlowTable.build());
		ruleConfiguration.getBindingTableGroups().add("b_tag,b_tag_flow");  // 不配置也行
		ruleConfiguration.setDefaultDataSourceName("db_tag");
		Map<String, DataSource> dataSourceMap = createDataSourceMap();
		Properties properties = new Properties();
		properties.put("sql.show", true);
		
		return new ShardingDataSource(dataSourceMap, new ShardingRule(
				ruleConfiguration, dataSourceMap.keySet()),new ConcurrentHashMap<String, Object>(),properties);
	}
	
	public Map<String, DataSource> createDataSourceMap() {
		Map<String, DataSource> ds = new HashMap<>();
		List<DSBaseConfig> configs = loadDsConfigs();
		for (DSBaseConfig dsBaseConfig : configs) {
			ds.put(dsBaseConfig.getDbName(), createDS(dsBaseConfig));
		}
		return ds;
	}
	
	private List<DSBaseConfig> loadDsConfigs() {
		List<DSBaseConfig> configs = new ArrayList<>();
		DSBaseConfig one = new DSBaseConfig();
		one.setDbName("db_tag_0");
		one.setDriverClassName("com.mysql.jdbc.Driver");
		one.setUrl("jdbc:mysql://127.0.0.1:3306/db_tag_0?characterEncoding=utf8&useSSL=true");
		one.setUsername("root");
		one.setPassword("123456");
		configs.add(one);
		
//		DSBaseConfig last = new DSBaseConfig();
//		BeanUtils.copyProperties(one, last);
//		last.setDbName("db_1");
//		last.setUrl("jdbc:mysql://127.0.0.1:3306/db_1?characterEncoding=utf8&useSSL=true");
//		configs.add(last);
		
		return configs;
	}
	
	private DataSource createDS(DSBaseConfig config) {
		DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());// 用户名
        dataSource.setPassword(config.getPassword());// 密码
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(200);
        dataSource.setMinIdle(100);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);
        dataSource.setDriverClassName(config.getDriverClassName());
        return dataSource;
	}
	
}
