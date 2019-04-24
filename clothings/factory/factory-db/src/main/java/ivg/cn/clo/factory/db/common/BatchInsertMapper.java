package ivg.cn.clo.factory.db.common;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;

@tk.mybatis.mapper.annotation.RegisterMapper
public interface BatchInsertMapper<T> {

    @InsertProvider(type = BatchInsertProvider.class, method = "dynamicSQL")
	int batchInsert(List<T> list);
	
}
