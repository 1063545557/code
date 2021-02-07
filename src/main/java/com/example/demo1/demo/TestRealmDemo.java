package com.example.demo1.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;

import java.util.Arrays;

public class TestRealmDemo {
    public static void main(String[] args) {
        //创建securityManager
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();

        //注入realm
        RealmDemo realmDemo=new RealmDemo();
        //设置realm使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1024);
        realmDemo.setCredentialsMatcher(credentialsMatcher);
        //设置自定义realm
        defaultSecurityManager.setRealm(realmDemo);

        //将安全工具类设置安全工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //通过安全工具类获取subject
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123456");
        try{
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }

        //认证用户进行授权
        if(subject.isAuthenticated()){
            //基于角色权限控制
            System.out.println(subject.hasRole("admin"));
            //基于多角色的权限控制
            System.out.println(subject.hasRoles(Arrays.asList("admin", "user")));
            //是否只有其中一个角色
            boolean[] booleans=subject.hasRoles(Arrays.asList("admin","user","super"));
            for (boolean aBoolean:booleans){
                System.out.println(aBoolean);
            }
            //基于权限字符串的访问控制  资源标识符：操作：资源类型
            System.out.println(subject.isPermitted("user:*:*"));
            //分别具有哪些权限
            boolean[] permitted=subject.isPermitted("user:*:01","order:*:10");
            for (boolean b:permitted) {
                System.out.println(b);
            }
            //同时具有哪些权限
            boolean permittedAll=subject.isPermittedAll("user:*:01","product:*");
            System.out.println(permittedAll);
        }
    }
}
