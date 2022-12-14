package com.suli.bianctf.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.util.SaResult;
import com.suli.bianctf.common.ResponseResult;
import com.suli.bianctf.domain.dto.EmailLoginDTO;
import com.suli.bianctf.domain.dto.EmailRegisterDTO;
import com.suli.bianctf.domain.dto.UpdatePwdDTO;
import com.suli.bianctf.domain.dto.UpdateUserDTO;
import com.suli.bianctf.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author suli
 */
@RequestMapping("/user")
@RestController
@Tag(name = "用户接口")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SaIgnore
    @PostMapping(value = "/emailLogin")
    @Operation(summary = "邮箱登录", method = "POST", description = "邮箱登录")
    public ResponseResult emailLogin(@Valid @RequestBody EmailLoginDTO emailLoginDTO){
        return userService.emailLogin(emailLoginDTO);
    }

    @SaIgnore
    @PostMapping(value = "/emailRegister")
    @Operation(summary = "邮箱账号注册", method = "POST", description = "邮箱账号注册")
    public SaResult emailRegister(@Valid @RequestBody EmailRegisterDTO emailRegisterDTO){
        return userService.emailRegister(emailRegisterDTO);
    }

    // 注销

    @SaCheckLogin()
    @Operation(summary = "注销", method = "GET", description = "注销")
    @RequestMapping("/logout")
    public SaResult logout() {
        return userService.logout();
    }

    @PostMapping(value = "/updatePassword")
    @Operation(summary = "邮箱账号修改密码", method = "GET", description = "邮箱账号修改密码")
    public SaResult updatePassword(@Valid @RequestBody UpdatePwdDTO updatePwdDTO){
        return userService.updatePassword(updatePwdDTO);
    }


//    @RequestMapping(value = "/sendEmailCode",method = RequestMethod.GET)
//    @Operation(summary = "发送邮箱验证码", method = "GET",  description = "发送邮箱验证码")
//    public ResponseResult sendEmailCode(String email){
//        return userAuthService.sendEmailCode(email);
//    }
//
//    @RequestMapping(value = "/bindEmail",method = RequestMethod.POST)
//    @SaCheckLogin
//    // @BusinessLogger(value = "个人中心模块-绑定邮箱",type = "修改",desc = "绑定邮箱")
//    public ResponseResult bindEmail(@RequestBody UserAuthDTO vo){
//        return userAuthService.bindEmail(vo);
//    }
//
    @SaCheckLogin
    @Operation(summary = "修改用户信息", method = "POST", description = "修改用户信息")
    @PostMapping(value = "/updateUser")
    public SaResult updateUser(@RequestBody UpdateUserDTO updateUserDTO){
        return userService.updateUser(updateUserDTO);
    }

    @SaIgnore
    @GetMapping(value = "/quantity")
    @Operation(summary = "获取用户数量", method = "GET", description = "获取用户数量")
    public SaResult quantity(){
        int count = userService.count();
        String s = String.valueOf(count);
        return SaResult.ok(s);
    }
}

