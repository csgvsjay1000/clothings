package ivg.cn.clo.tag.db.mapper;



import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: lj
 * @description:
 * @date: 10:55 2018/7/23
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>{
    
    
}
