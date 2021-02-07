package com.example.demo1.mapper;

import com.example.demo1.entity.Perms;
import com.example.demo1.entity.Role;
import com.example.demo1.entity.ShiroUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiroUserMapper {
    void save(ShiroUser shiroUser);
    ShiroUser findByUsername(@Param("username") String username);
    ShiroUser findRolesByUsername(@Param("username") String username);
//根据角色id查询权限集合
    List<Perms> findPermsById(@Param("roleId") Integer roleId);
}
