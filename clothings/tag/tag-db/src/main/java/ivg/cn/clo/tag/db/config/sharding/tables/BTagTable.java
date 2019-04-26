package ivg.cn.clo.tag.db.config.sharding.tables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import ivg.cn.clo.tag.db.config.sharding.ShardingUtil;
import ivg.cn.clo.tag.db.config.sharding.algorithm.HashPreciseAlgorithm;
import ivg.cn.clo.tag.db.config.sharding.algorithm.TableHashPreciseAlgorithm;

public class BTagTable {

 	static Logger log = LoggerFactory.getLogger(BTagTable.class);

	public static TableRuleConfiguration build() {
		TableRuleConfiguration configuration = new TableRuleConfiguration();
		
		int dbCount = 1;
		int tbCountPerDB = 2;
		String dbPre = "db_tag";
		String tbPre = "b_tag";
		configuration.setLogicTable(tbPre);
		
		configuration.setActualDataNodes(
				ShardingUtil.actualDataNodesWithHash(dbPre, tbPre, dbCount, tbCountPerDB));
		log.info(configuration.getActualDataNodes());
		
		StandardShardingStrategyConfiguration tableStrategy = 
				new StandardShardingStrategyConfiguration("epc", 
						new TableHashPreciseAlgorithm(tbCountPerDB*dbCount,configuration.getLogicTable()));
		configuration.setTableShardingStrategyConfig(tableStrategy);
		
		StandardShardingStrategyConfiguration dbStrategy = 
				new StandardShardingStrategyConfiguration("epc", 
						new HashPreciseAlgorithm(dbCount,dbPre));
		configuration.setDatabaseShardingStrategyConfig(dbStrategy);
		
		return configuration;
	}
	
}
