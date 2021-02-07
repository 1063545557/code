package com.example.demo1.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user")
public class User1 {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private Integer deptId;
    private String username;
    private String nickName;
    private String gender;
    private String phone;
    private String email;
    private String avatarName;
    private String avatarPath;
    private String password;
    private String isAdmin;
    private String enabled;
    private String createBy;
    private String updateBy;
    private String pwdResetTime;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
