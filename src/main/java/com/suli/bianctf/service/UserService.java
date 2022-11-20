package com.suli.bianctf.service;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suli.bianctf.common.ResponseResult;
import com.suli.bianctf.domain.User;
import com.suli.bianctf.domain.dto.EmailLoginDTO;
import com.suli.bianctf.domain.dto.EmailRegisterDTO;
import com.suli.bianctf.domain.dto.UpdatePwdDTO;
import com.suli.bianctf.domain.dto.UpdateUserDTO;

import java.util.List;
import java.util.Map;

/**
* @author suli
* @description 针对表【lab_user(实验室用户信息表)】的数据库操作Service
* @createDate 2022-11-17 12:11:47
*/
public interface UserService extends IService<User> {

//
//    ResponseResult listUser(String username, Integer loginType);
//
//    ResponseResult getUserById(Integer id);
//
//    ResponseResult insertUser(User user);
//
//    ResponseResult updateUser(User user);
//
//    ResponseResult deleteBatch(List<Integer> ids);
//
//    ResponseResult getCurrentUserInfo();
//
//    ResponseResult getCurrentUserMenu();
//
//    ResponseResult updatePassword(Map<String, String> map);
//
//    ResponseResult listOnlineUsers(String keywords);
//
//    ResponseResult kick(String token);

    ResponseResult emailLogin(EmailLoginDTO emailLoginDTO);

    ResponseResult emailRegister(EmailRegisterDTO emailRegisterDTO);

    SaResult logout();

    SaResult updatePassword(UpdatePwdDTO updatePwdDTO);

    SaResult updateUser(UpdateUserDTO updateUserDTO);
}
