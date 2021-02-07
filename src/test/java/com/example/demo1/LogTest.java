package com.example.demo1;

import com.example.demo1.entity.Role;
import com.example.demo1.mapper.RoleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;


@SpringBootTest
@RunWith(SpringRunner.class)
public class LogTest {
    Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Before
    public void testBefore(){
        logger.info("before test");
    }
    @After
    public void testAfter(){
        logger.info("after test");
    }
    @Test
    public void test1() {
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");

    }
//    @Test
//    public void mybatistest(){
//        try{
//            InputStream inputStream= Resources.getResourceAsStream("mybatisConfig.xml");
//            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
//            SqlSession sqlSession=sqlSessionFactory.openSession();
//            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
//            Role role=roleMapper.selectRole(1);
//            System.out.println(role);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}
