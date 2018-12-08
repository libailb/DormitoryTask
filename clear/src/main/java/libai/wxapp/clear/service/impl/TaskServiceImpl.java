package libai.wxapp.clear.service.impl;

import libai.wxapp.clear.dao.TaskMapper;
import libai.wxapp.clear.dao.UserMapper;
import libai.wxapp.clear.pojo.Msg;
import libai.wxapp.clear.pojo.Task;
import libai.wxapp.clear.pojo.User;
import libai.wxapp.clear.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Msg login(String username,String password) {
        User user=userMapper.selectUser(username,password);
        if(user==null){
            return Msg.fail("登录失败");
        }
        return Msg.success().add("User",user);
    }

    @Override
    public List<User> getUser() {
        return userMapper.selectAll();
    }

    @Override
    public List<Task> getTask() {
        return taskMapper.selectAll();
    }

    @Override
    public int updateTask(int taskId,int id) {
        return userMapper.getTask(taskId,id);
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public Task selectTaskById(int id) {
        return taskMapper.selectTaskById(id);
    }

    @Override
    public int putTask(Task task) {
        return taskMapper.insert(task);
    }

    @Override
    public int getTaskNum(String name) {
        return taskMapper.getNum(name);
    }

    @Override
    public int putTaskNum(User user) {
        return user.getGetnum();
    }

    @Override
    public List<Task> unacceptTask() {
        return taskMapper.unaccept();
    }

    @Override
    public List<Task> acceptedTask() {
        return taskMapper.accepted();
    }

    @Override
    public int putTaskCount(int id, int score) {
        return userMapper.postTask(id,score);
    }

    @Override
    public int addReceiver(String name) {
        return taskMapper.addReceiver(name);
    }

    @Override
    public int getRNum(String name) {
        return taskMapper.getRNum(name);
    }

    @Override
    public List<Task> myPostTask(String name) {
        return taskMapper.myPostTask(name);
    }

    @Override
    public List<Task> myGetTask(String name) {
        return taskMapper.myGetTask(name);
    }
}
