package ivg.cn.clo.factory.db.config.sharding.tables;

import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import ivg.cn.clo.factory.db.config.sharding.ShardingUtil;
import ivg.cn.clo.factory.db.config.sharding.algorithm.DBTimePreciseAlgorithm;
import ivg.cn.clo.factory.db.config.sharding.algorithm.DBTimeRangeAlgorithm;
import ivg.cn.clo.factory.db.config.sharding.algorithm.TableTimePreciseAlgorithm;
import ivg.cn.clo.factory.db.config.sharding.algorithm.TableTimeRangeAlgorithm;

public class FDeliveryBillTable {

	
	public static TableRuleConfiguration build() {
		TableRuleConfiguration configuration = new TableRuleConfiguration();
		
		configuration.setLogicTable("f_delivery_bill");
		int dbCount = 2;
		int tbCountPerYear = 2;
		int yearsPerDB = 2;
		int beginYear = 2018;
		String dbPre = "db";
		String tbPre = "f_delivery_bill";
		
		configuration.setActualDataNodes(
				ShardingUtil.actualDataNodes(dbPre, tbPre, dbCount, beginYear, yearsPerDB, tbCountPerYear));
		
		
		StandardShardingStrategyConfiguration tableStrategy = 
				new StandardShardingStrategyConfiguration("create_date", 
						new TableTimePreciseAlgorithm(configuration.getLogicTable(), tbCountPerYear),
						new TableTimeRangeAlgorithm(tbPre, tbCountPerYear));
		configuration.setTableShardingStrategyConfig(tableStrategy);
		
		StandardShardingStrategyConfiguration dbStrategy = 
				new StandardShardingStrategyConfiguration("create_date", 
						new DBTimePreciseAlgorithm(beginYear, dbPre, yearsPerDB),
						new DBTimeRangeAlgorithm(beginYear, dbPre, yearsPerDB));
		configuration.setDatabaseShardingStrategyConfig(dbStrategy);
		
		return configuration;
	}
	
}
