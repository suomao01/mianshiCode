package web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import web.domain.User;

import java.util.List;

/**
 * Created by wolfcode-lanxw
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(id,name) value (#{id},#{name})")
    int insert(User user);
    @Select("select * from user")
    List<User> selectAll();
}
