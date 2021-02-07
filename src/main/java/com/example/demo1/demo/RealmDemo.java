package com.example.demo1.demo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 *  自定义realm,将认证/授权数据的来源转为数据库的实现
 */
public class RealmDemo extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("------------------");
        String primaryPrincipal=(String)principals.getPrimaryPrincipal();
        System.out.println("身份信息："+primaryPrincipal);
        //根据身份信息 用户名 获取当前用户的角色信息，以及权限信息  zhangsan  admin  user
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");
        //将数据库中的查询权限信息赋值给权限对象
        simpleAuthorizationInfo.addStringPermission("user:*:01");
        return simpleAuthorizationInfo;

    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //在token中获取用户名
        String principal=(String) token.getPrincipal();
        System.out.println(principal);
        //根据身份信息使用jdbc,mybatis查询相关数据库
        if("zhangsan".equals(principal)){
            //参数1：返回数据库中的用户名 参数2:返回数据库中正确的密码 参数3：提供当前realm的名字
            //参数1：返回数据库中的用户名 参数2:数据库MD5+salt之后的密码 参数3：注册时的随机盐 参数4：realm的名字
            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo("zhangsan","123456", ByteSource.Util.bytes("xo*7ps"),this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
