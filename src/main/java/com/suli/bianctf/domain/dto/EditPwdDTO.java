package com.suli.bianctf.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditPwdDTO {

    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    @Schema(name = "userId", required = true, type = "String")
    private String userId;

    /**
     * 新密码
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
