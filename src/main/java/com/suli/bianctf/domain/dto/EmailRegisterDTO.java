package com.suli.bianctf.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author suli
 * @date 2022/2/25
 * @apiNote
 */
@Data
@Schema(description = "邮箱注册信息")
public class EmailRegisterDTO {
    /**
     *  邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Schema(name = "email", required = true, type = "String")
    private String email;

    /**
     *  昵称
     */
    @NotBlank(message = "用户昵称不能为空")
    @Schema(name = "userName", required = true, type = "String")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Schema(name = "password", required = true, type = "String")
    private String password;

    /**
     * 校验密码
     */
    @NotBlank(message = "请再次输入密码")
    @Schema(name = "password", required = true, type = "String")
    private String checkPwd;


}
