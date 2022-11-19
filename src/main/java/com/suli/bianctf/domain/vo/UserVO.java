package com.suli.bianctf.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author blue
 * @date 2022/3/7
 * @apiNote
 */
@Data
public class UserVO {

    private Integer id;

    @Schema(name = "状态")
    private Integer status;

    @Schema(name = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(name = "最后更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @Schema(name = "最后登录时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date lastLoginTime;

    @Schema(name = "角色ID")
    private Integer roleId;

    @Schema(name = "IP地址")
    private String ipAddress;

    @Schema(name = "IP来源")
    private String ipSource;

    private Integer loginType;

    /**
     * 昵称
     * */
    private String nickname;

    /**
     * 头像
     * */
    private String avatar;
}
