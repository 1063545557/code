package com.example.demo1.config;

import com.example.demo1.entity.Perms;
import com.example.demo1.entity.Role;
import com.example.demo1.entity.ShiroUser;
import com.example.demo1.service.IShiroUserService;
import com.example.demo1.service.Impl.IShiroUserServiceImpl;
import com.example.demo1.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;

import java.security.Principal;
import java.util.List;


public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        String primaryPrincipal=(String) principals.getPrimaryPrincipal();
        //根据主身份信息获取角色和权限信息
        IShiroUserService userService=(IShiroUserService) ApplicationContextUtils.getBean("iShiroUserService");
        ShiroUser shiroUser=userService.findRolesByUsername(primaryPrincipal);
        //授权角色信息
        if (!CollectionUtils.isEmpty(shiroUser.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            shiroUser.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                //权限信息
                List<Perms> perms = userService.findPermsById(role.getRoleId());
                if (!CollectionUtils.isEmpty(perms)){
                   perms.forEach(perm->{
                       simpleAuthorizationInfo.addStringPermission(perm.getName());
                   });
                }
            });
            return simpleAuthorizationInfo;
        }
//        if("zhangsan".equals(primaryPrincipal)){
//            SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
//            simpleAuthorizationInfo.addRole("admin");
//
//            return simpleAuthorizationInfo;
//        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("---------------");
        //根据身份信息
        String principal=(String) token.getPrincipal();
        //在工厂中获取service对象
        IShiroUserService userService=(IShiroUserService) ApplicationContextUtils.getBean("iShiroUserService");
        ShiroUser shiroUser = userService.findByUsername(principal);

        if (shiroUser!=null){
            return new SimpleAuthenticationInfo(shiroUser.getUsername(),shiroUser.getPassword(), ByteSource.Util.bytes(shiroUser.getSalt()),this.getName());
        }
//        if("zhangsan".equals(principal)){
//            return new SimpleAuthenticationInfo("zhangsan","123456",this.getName());
//        }
        return null;
    }
}
