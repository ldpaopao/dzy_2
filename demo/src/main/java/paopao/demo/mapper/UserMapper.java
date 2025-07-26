package paopao.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import paopao.demo.entity.User;
@Mapper
public interface UserMapper {
    User findByUsername(String username);
    void insert(User user);
}