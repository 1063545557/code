package com.example.demo1.demo;

import com.example.demo1.entity.Role;
import com.example.demo1.mapper.RoleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class TestDemo {
    public static void main(String[] args) {
        try{
            InputStream inputStream= Resources.getResourceAsStream("src/main/resources/mapping/RoleMapper.xml");
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession=sqlSessionFactory.openSession();
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            Role role=roleMapper.selectRole(1);
            System.out.println(role);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
