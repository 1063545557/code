package com.example.demo1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1.entity.User1;
import com.example.demo1.mapper.User1Mapper;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {
    @Autowired
    private User1Mapper user1Mapper;

    @Test
   public void contextLoads(){
        QueryWrapper<User1> wrapper=new QueryWrapper<>();
        wrapper.isNotNull("username")
                .isNotNull("user_id");
        user1Mapper.selectList(wrapper);

    }
}
