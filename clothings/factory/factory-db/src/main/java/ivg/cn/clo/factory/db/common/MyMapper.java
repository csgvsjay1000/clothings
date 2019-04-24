package ivg.cn.clo.factory.db.common;



import tk.mybatis.mapper.common.Mapper;

/**
 * @author: lj
 * @description:
 * @date: 10:55 2018/7/23
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface MyMapper<T> extends Mapper<T>, BatchInsertMapper<T>, BatchInsertSelectiveMapper<T>{
    
    
}
