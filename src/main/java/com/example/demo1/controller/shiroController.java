package com.example.demo1.controller;

import com.example.demo1.entity.ShiroUser;
import com.example.demo1.service.IShiroUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class shiroController {

    @Autowired
    private IShiroUserService iShiroUserService;

    @RequestMapping("/regester")
    public String regester(ShiroUser shiroUser){
        try{
            iShiroUserService.register(shiroUser);
            return "redirect:/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/regester.jsp";
        }
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();//退出用户
        return "redirect:/login.jsp";
    }
    @RequestMapping("/login")
    public String login(String username,String password){
        //获取主体对象
        Subject subject=SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/index.jsp";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }
        return "redirect:/login.jsp";
    }
    //授权
    @RequestMapping("/order/save")
    @RequiresRoles("admin") //用来判断角色
    public String save(){

        //获取主体对象
        Subject subject=SecurityUtils.getSubject();
        //代码方式
        if (subject.hasRole("admin")){
            System.out.println("保存订单");
        }else {
            System.out.println("无权访问");
        }

        return "redirect:/index.jsp";
    }
}
