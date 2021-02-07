package com.example.demo1.service.Impl;

import com.example.demo1.entity.Perms;
import com.example.demo1.entity.Role;
import com.example.demo1.entity.ShiroUser;
import com.example.demo1.mapper.ShiroUserMapper;
import com.example.demo1.service.IShiroUserService;
import com.example.demo1.utils.SaltUtils;
import lombok.experimental.Accessors;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IShiroUserServiceImpl implements IShiroUserService {
    @Autowired
    private ShiroUserMapper shiroUserMapper;

    @Override
    public void register(ShiroUser shiroUser) {
        //处理业务调用dao
        //1.生成随机盐
        String salt= SaltUtils.getSalt(8);
        //2.将随机盐保存到数据
        shiroUser.setSalt(salt);
        //3.明文密码进行md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(shiroUser.getPassword(),salt,1024);
        shiroUser.setPassword(md5Hash.toHex());
        shiroUserMapper.save(shiroUser);
    }

    @Override
    public ShiroUser findByUsername(String username) {
        return shiroUserMapper.findByUsername(username);
    }

    @Override
    public ShiroUser findRolesByUsername(String username) {
        return shiroUserMapper.findRolesByUsername(username);
    }

    @Override
    public List<Perms> findPermsById(Integer roleId) {
        return shiroUserMapper.findPermsById(roleId);
    }
}
