package libai.wxapp.clear.service;

import libai.wxapp.clear.pojo.Msg;
import libai.wxapp.clear.pojo.Task;
import libai.wxapp.clear.pojo.User;

import java.util.List;

public interface TaskService {
    //登录
    Msg login(String username,String password);
    //获取所有用户
    List<User> getUser();
    //获取所有任务
    List<Task> getTask();
    //实现用户接收任务
    int updateTask(int taskId,int id);
    //根据id查询用户
    User selectUserById(int id);
    //根据id查询任务
    Task selectTaskById(int id);
    //发布任务
    int putTask(Task task);

    int getTaskNum(String name);

    int putTaskNum(User user);

    List<Task> unacceptTask();

    List<Task> acceptedTask();

    int putTaskCount(int id,int score);
    int addReceiver(String name);

    int getRNum(String name);

    List<Task> myPostTask(String name);

    List<Task> myGetTask(String name);
}
