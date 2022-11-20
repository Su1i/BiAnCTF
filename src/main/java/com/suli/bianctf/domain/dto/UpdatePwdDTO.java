package com.suli.bianctf.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePwdDTO {
    /**
     *  邮箱
     */
    @NotBlank(message = "旧密码不能为空")
    @Schema(name = "oldPwd", required = true, type = "String")
    private String oldPwd;

    /**
     * 密码
     */
    @NotBlank(message = "新密码不能为空")
    @Schema(name = "newPwd", required = true, type = "String")
    private String newPwd;

    /**
     * 校验密码
     */
    @NotBlank(message = "请再次输入新密码")
    @Schema(name = "checkNewPwd", required = true, type = "String")
    private String checkNewPwd;

}
