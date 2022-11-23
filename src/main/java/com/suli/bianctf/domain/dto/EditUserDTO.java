package com.suli.bianctf.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class EditUserDTO {
    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空")
    private Long userId;

    /**
     * 用户邮箱
     */
    @NotBlank(message = "用户邮箱不能为空")
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 学号/工号
     */
    private String studentId;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String speciality;

    /**
     * 年级
     */
    private String grade;

    /**
     * 身份证号
     */
    private String identityCard;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 用户类型（sys_user系统用户）
     */
    private String userType;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
}
