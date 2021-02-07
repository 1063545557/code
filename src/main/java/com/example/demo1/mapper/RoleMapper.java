package com.example.demo1.mapper;

import com.example.demo1.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    Role selectRole(@Param("roleId") Integer roleId);
}
