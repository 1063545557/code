package com.example.demo1.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1.创建shiroFilter
    @Bean
    public ShiroFilterFactoryBean  getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置系统受限资源

        //配置系统公共资源
        Map<String,String> map=new HashMap<>();
        map.put("/user/login","anon"); //设置为公共资源
        map.put("/user/register","anon");
        map.put("/regester.jsp","anon");
        map.put("/**","authc");  //请求这个资源需要认证和授权
        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm(){
        MyRealm myRealm=new MyRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        myRealm.setCredentialsMatcher(credentialsMatcher);
        //开启缓存管理
//        myRealm.setCacheManager(new EhCacheManager());
        myRealm.setCacheManager(new RedisCacheManager());
        myRealm.setCachingEnabled(true); //开启全局缓存
        myRealm.setAuthenticationCachingEnabled(true); //开启认证缓存
        myRealm.setAuthenticationCacheName("authenticationCache");
        myRealm.setAuthorizationCachingEnabled(true); //开启授权缓存
        myRealm.setAuthorizationCacheName("authorizationCache");

        return myRealm;
    }

}