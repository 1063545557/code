package com.example.demo1.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class ShiroDemo {
    public static void main(String[] args) {
        //1.创建安全管理器对象
        DefaultSecurityManager securityManager=new DefaultSecurityManager();

        //2.给安全管理器设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        //3.SecurityUtils 给安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //4.关键对象 subject主体
        Subject subject=SecurityUtils.getSubject();

        //5.创建令牌
        UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123456");
        try {
            subject.login(token);
            System.out.println("认证状态："+subject.isAuthenticated());
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("认证失败：账号错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("认证失败：密码错误");
        }
    }
}
