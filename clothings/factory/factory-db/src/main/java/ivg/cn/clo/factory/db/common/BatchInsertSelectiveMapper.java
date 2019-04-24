package ivg.cn.clo.factory.db.common;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;

@tk.mybatis.mapper.annotation.RegisterMapper
public interface BatchInsertSelectiveMapper<T> {

	/**
	 * 暂时不可用
	 * */
	@Deprecated
    @InsertProvider(type = BatchInsertSelectiveProvider.class, method = "dynamicSQL")
	int batchInsertSelective(List<T> list);
	
}
