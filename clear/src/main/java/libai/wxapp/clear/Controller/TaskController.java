package libai.wxapp.clear.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import libai.wxapp.clear.pojo.Msg;
import libai.wxapp.clear.pojo.Task;
import libai.wxapp.clear.pojo.User;
import libai.wxapp.clear.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/clear/")
public class TaskController {
    @Autowired
    @Qualifier("taskService")
    private TaskService taskService;
    //登录
    @PostMapping("login")
    public Msg login(String username, String password, HttpSession session){
        Msg msg=taskService.login(username,password);
        if(msg.getCode()==100){
            session.setAttribute("currentUser",msg.getExtend().get("User"));
        }
        return msg;
    }

    @GetMapping("currentUser")
    public Msg currentUser(HttpSession session){
        User user=(User)session.getAttribute("currentUser");
        if(user==null){
            return Msg.fail("用户未登录");
        }
        return Msg.success().add("currentUser",taskService.selectUserById(user.getId()));
    }

    @GetMapping("listUser")
    public Msg listAllUser(){
        List<User> list=taskService.getUser();
        return Msg.success().add("user",list);
    }

    @GetMapping("listTask")
    public Msg listAllTask( @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                        @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        PageHelper.startPage(pageNum,pageSize);
        List<Task> list=taskService.getTask();
        PageInfo<Task> pageInfo=new PageInfo<>(list);
        return Msg.success().add("user",pageInfo);
    }
    //当前登录用户接收任务
   @PostMapping("getTask")
    public Msg daTask(int taskId,HttpSession session){
        User user=(User)session.getAttribute("currentUser");
        if(user==null){
            return Msg.fail("用户未登录");
        }
        int id=user.getId();
        int count= taskService.updateTask(taskId,id);
        if(count==0){
            return Msg.fail("接受任务失败");
        }
        int c1=taskService.addReceiver(user.getName());
        if(c1==0){
            return Msg.fail("接受任务失败");
        }
        User user1=taskService.selectUserById(id);
        Task task1=taskService.selectTaskById(taskId);
        return Msg.success().add("user",user1).add("task",task1);
    }
    //当前登录用户发布任务
@PostMapping("postTask")
    public Msg putTask(HttpSession session,String tName,String tText,int score){
        User user=(User)session.getAttribute("currentUser");
    if(user==null){
        return Msg.fail("用户未登录");
    }
        Task task=new Task();
        task.setTName(tName);
        task.setTScore(score);
        task.setTText(tText);
        task.setTUser(user.getName());
        int count =taskService.putTask(task);
    if(count==0 ){
        return Msg.fail("发布任务失败");
    }
       int c=taskService.putTaskCount(user.getId(),score);
        if(c==0){
            return Msg.fail("发布任务失败");
        }

        return Msg.success("发布任务成功");

    }
    //获取用户接收任务的数据数量
    @GetMapping("getTaskNum")
     public Msg getTaskNum(HttpSession session){
         User user=(User)session.getAttribute("currentUser");
         if(user==null){
             return Msg.fail("用户未登录");
         }
        return Msg.success().add("getTaskNum",taskService.getRNum(user.getName()));
     }

     //获取用户发布任务的数量
    @GetMapping("postTaskNum")
    public Msg postTaskNum(HttpSession session){
        User user=(User)session.getAttribute("currentUser");
        if(user==null){
            return Msg.fail("用户未登录");
        }
        return Msg.success().add("postTaskNum",taskService.getTaskNum(user.getName()));
    }

    //列出所有没有被接受的任务
    @GetMapping("listUnacceptedTask")
    public Msg listunacceptedTask(HttpSession session){
        User user=(User)session.getAttribute("currentUser");
        if(user==null){
            return Msg.fail("用户未登录");
        }
        List<Task> list=taskService.unacceptTask();
        if(list.size()==0){
            return Msg.success("当前没有需要接受的任务");
        }
        return Msg.success().add("list",list);
    }

    //列出所有被接受的任务
    @GetMapping("listAcceptedTask")
    public Msg listacceptedTask(HttpSession session){
        User user=(User)session.getAttribute("currentUser");
        if(user==null){
            return Msg.fail("用户未登录");
        }
        List<Task> list=taskService.acceptedTask();
        if(list.size()==0){
            return Msg.success("当前没有已经接受的任务");
        }
        return Msg.success().add("list",list);
    }

    //列出当前用户接受的任务
    @GetMapping("listMyGetTask")
    public Msg listMyGetTask(HttpSession session){
        User user=(User)session.getAttribute("currentUser");
        if(user==null){
            return Msg.fail("用户未登录");
        }
        List<Task> list=taskService.myGetTask(user.getName());
        if(list.size()==0){
            return Msg.success("我还没有接受过任务");
        }
        return Msg.success().add("list",list);
    }

    //列出当前用户发布的任务
    @GetMapping("listMyPostTask")
    public Msg listMyPostTask(HttpSession session){
        User user=(User)session.getAttribute("currentUser");
        if(user==null){
            return Msg.fail("用户未登录");
        }
        List<Task> list=taskService.myPostTask(user.getName());
        if(list.size()==0){
            return Msg.success("我还没有发布过任务");
        }
        return Msg.success().add("list",list);
    }


}

