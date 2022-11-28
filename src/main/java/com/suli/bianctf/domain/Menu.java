package com.suli.bianctf.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统管理-权限资源表
 * </p>
 *
 * @author blue
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID=1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty(value = "上级资源ID")
    private Integer parentId;

//    @ApiModelProperty(value = "url")
    private String url;

//    @ApiModelProperty(value = "资源编码")
    private String component;

//    @ApiModelProperty(value = "资源名称")
    private String title;

//    @ApiModelProperty(value = "资源级别")
    private Integer level;

//    @ApiModelProperty(value = "排序")
    private Integer sortNo;

//    @ApiModelProperty(value = "资源图标")
    private String icon;

//    @ApiModelProperty(value = "类型 menu、button")
    private String type;

//    @ApiModelProperty(value = "资源名字")
    private String name;

//    @ApiModelProperty(value = "备注")
    private String remarks;

//    @ApiModelProperty(value = "是否显示")
    private Integer hidden;

//    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
//    @JsonFormat(pattern = DateUtils.FORMAT_STRING,timezone="GMT+8")
    private Date createdTime;

//    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.UPDATE)
//    @JsonFormat(pattern = DateUtils.FORMAT_STRING,timezone="GMT+8")
    private Date updateTime;

    @TableField(exist = false)
    private List<Menu> children;
}
