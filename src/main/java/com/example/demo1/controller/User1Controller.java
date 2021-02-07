package com.example.demo1.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1.entity.User1;
import com.example.demo1.mapper.User1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user1")
public class User1Controller {
    @Autowired
    private User1Mapper user1Mapper;

    @RequestMapping("/selectList")
    public List selectList(){
        return user1Mapper.selectList(null);
    }

    @RequestMapping("/select")
    public void select(){
        user1Mapper.selectById(79);
    }
    @RequestMapping("/selectPage")
    public void selectPage(){
        Page<User1> user1Page = new Page<>(2,5);
        user1Mapper.selectPage(user1Page,null);
        user1Page.getRecords().forEach(System.out::println);
    }
    @RequestMapping("insert")
    public void insert(){
        User1 user1=new User1();
        user1.setUsername("qqq1");
        user1Mapper.insert(user1);
    }
    @RequestMapping("update")
    public void update(){
        User1 user2=new User1();
        user2.setUserId(78);
        user2.setUsername("qqq2");
        user1Mapper.updateById(user2);
    }
    @RequestMapping("/delete")
    public void delete(){
        user1Mapper.deleteById(79);
    }

}
