package com.example.demo1.service;

import com.example.demo1.entity.Perms;
import com.example.demo1.entity.Role;
import com.example.demo1.entity.ShiroUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IShiroUserService {
    void register(ShiroUser shiroUser);
    ShiroUser findByUsername(String username);
    ShiroUser findRolesByUsername(String username);
    List<Perms> findPermsById(Integer roleId);
}
