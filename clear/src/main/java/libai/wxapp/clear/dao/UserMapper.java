package libai.wxapp.clear.dao;

import java.util.List;
import libai.wxapp.clear.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int insert(User record);

    List<User> selectAll();

    int getTask(@Param("taskId") int taskId,@Param("id") int id);

    int postTask(@Param("id") int id,@Param("score") int score);

    User selectUser(@Param("username") String name,@Param("password") String password);

    User selectById(int id);


}