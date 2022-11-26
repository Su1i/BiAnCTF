package com.suli.bianctf.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统管理 - 用户角色关联表
 * </p>
 *
 * @author blue
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_role")
public class UserRole implements Serializable {



    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @TableField("role_id")
    private Integer roleId;


    @TableField("user_id")
    private Integer userId;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date lastTime;

}
