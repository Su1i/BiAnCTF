package com.suli.bianctf.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统管理-角色表
 * </p>
 *
 * @author suli
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("remarks")
    private String remarks;


    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;


    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private List<Integer> menus;
}
