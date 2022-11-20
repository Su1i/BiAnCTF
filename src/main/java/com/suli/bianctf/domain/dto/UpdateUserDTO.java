package com.suli.bianctf.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class UpdateUserDTO {
    /**
     *  邮箱
     */
    @Schema(name = "email", required = true, type = "String")
    private String email;

    /**
     * 用户昵称
     */
    @Schema(name = "userName", required = true, type = "String")
    private String userName;

    /**
     * 真实姓名
     */
    @Schema(name = "realName", required = true, type = "String")
    private String realName;

    /**
     * 学号/工号
     */
    @Schema(name = "studentId", required = true, type = "String")
    private String studentId;

    /**
     * 学院
     */
    @Schema(name = "college", required = true, type = "String")
    private String college;

    /**
     * 专业
     */
    @Schema(name = "speciality", required = true, type = "String")
    private String speciality;

    /**
     * 年级
     */
    @Schema(name = "grade", required = true, type = "String")
    private String grade;

    /**
     * 身份证号
     */
    @Schema(name = "identityCard", required = true, type = "String")
    private String identityCard;

    /**
     * 手机号码
     */
    @Schema(name = "phoneNumber", required = true, type = "String")
    private String phoneNumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @Schema(name = "sex", required = true, type = "String")
    private String sex;

    /**
     * 头像地址
     */
    @Schema(name = "avatar", required = true, type = "String")
    private String avatar;

}
