package libai.wxapp.clear.dao;

import java.util.List;
import libai.wxapp.clear.pojo.Task;

public interface TaskMapper {
    int insert(Task record);

    List<Task> selectAll();

    Task selectTaskById(int taskId);

    //获取当前用户发布的任务数
    int getNum(String name);

    int getRNum(String name);

    List<Task> unaccept();

    List<Task> myPostTask(String name);

    List<Task> myGetTask(String name);

    List<Task> accepted();

    int addReceiver(String name);
}