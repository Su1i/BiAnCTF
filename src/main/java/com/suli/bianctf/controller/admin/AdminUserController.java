package com.suli.bianctf.controller.admin;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suli.bianctf.domain.User;
import com.suli.bianctf.domain.dto.EmailRegisterDTO;
import com.suli.bianctf.domain.dto.admin.EditPwdDTO;
import com.suli.bianctf.domain.dto.admin.EditUserDTO;
import com.suli.bianctf.domain.vo.SysUserQueryVo;
import com.suli.bianctf.mapper.UserMapper;
import com.suli.bianctf.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author suli
 */
@SaCheckLogin
@RequestMapping("/admin/user")
@RestController
@Tag(name = "用户接口")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping(value = "/addUser")
    @Operation(summary = "添加用户", method = "POST", description = "添加用户")
    public SaResult addUser(@Valid @RequestBody EmailRegisterDTO emailRegisterDTO){
        return userService.emailRegister(emailRegisterDTO);
    }

    @DeleteMapping (value = "/deleteUser/{id}")
    @Operation(summary = "删除用户", method = "DELETE", description = "删除用户")
    public SaResult deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @PostMapping(value = "/editPassword")
    @Operation(summary = "修改用户密码", method = "POST", description = "修改用户密码")
    public SaResult editPassword(@Valid @RequestBody EditPwdDTO editPwdDTO){
        return userService.editPassword(editPwdDTO);
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

    @Operation(summary = "管理员修改用户信息", method = "POST", description = "管理员修改用户信息")
    @PostMapping(value = "/editUser")
    public SaResult editUser(@RequestBody EditUserDTO editUserDTO){
        return userService.editUser(editUserDTO);
    }




    @GetMapping(value = "/list/{page}/{limit}")
    @Operation(summary = "获取用户列表", method = "GET", description = "获取用户列表")
    public SaResult list(@PathVariable Long page,
                         @PathVariable Long limit,
                         SysUserQueryVo sysUserQueryVo){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class, info -> !info.getColumn().equals("password"));
        //创建page对象
        Page<User> pageParam = new Page<>(page.intValue(),limit.intValue());

        //Page<User> list = userService.page(pageParam, queryWrapper);
//         Page<User> list = userMapper.selectPage(pageParam, queryWrapper);
        //调用service方法
        IPage<User> pageModel = userService.selectPage(pageParam,sysUserQueryVo);
        //Page对象通过传入页码和每页条目数达到分页目的
//        PageResult<Entity> result = null;
//        try {
//            result = Db.use().page(Entity.create("lab_user"), new cn.hutool.db.Page(page.intValue(),limit.intValue()));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return SaResult.data(pageModel);
    }

}


