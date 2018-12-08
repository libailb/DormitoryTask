package libai.wxapp.clear.dao;

import libai.wxapp.clear.pojo.Task;
import org.hibernate.validator.constraints.EAN;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;
    @Test
    void insert() {
        Task task=new Task();
        task.setTName("圆通快递");
        task.setTText("在东门，今天两点之前");
        task.setTScore(4);
        task.setTUser("王宏观");
        int a=taskMapper.insert(task);
        System.out.println(a);
    }

    @Test
    void selectAll() {
        List<Task> list=taskMapper.selectAll();
        System.out.println(list);
    }

    @Test
    void selectTaskById() {
        Task task=taskMapper.selectTaskById(1);
        System.out.println(task);
    }

    @Test
    void insert1() {
    }

    @Test
    void selectAll1() {
    }

    @Test
    void selectTaskById1() {
    }

    @Test
    void getNum() {
    }

    @Test
    void unaccept() {
    }

    @Test
    void accepted() {
        List<Task> list=taskMapper.accepted();
        System.out.println(list.size());
        System.out.println(list);
    }
}