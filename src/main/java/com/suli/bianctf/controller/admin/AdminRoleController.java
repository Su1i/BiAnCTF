package com.suli.bianctf.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.util.SaResult;
import com.suli.bianctf.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/role")
@Tag(name = "角色管理-接口")
@RequiredArgsConstructor
@SaCheckLogin
public class AdminRoleController {

    private final RoleService roleService;

    @GetMapping(value = "list")
    @Operation(summary = "角色列表", method = "GET", description = "角色列表")
    public SaResult list(String name) {
        return roleService.listRole(name);
    }

//    @RequestMapping(value = "queryUserRole", method = RequestMethod.GET)
//    @SaCheckLogin
//    @ApiOperation(value = "获取当前登录用户所拥有的权限", httpMethod = "GET", response = ResponseResult.class, notes = "获取当前登录用户所拥有的权限")
//    public ResponseResult getCurrentUserRole() {
//        return roleService.getCurrentUserRole();
//    }
//
//    @RequestMapping(value = "queryRoleId", method = RequestMethod.GET)
//    @SaCheckLogin
//    @ApiOperation(value = "获取该角色所有的权限", httpMethod = "GET", response = ResponseResult.class, notes = "获取该角色所有的权限")
//    public ResponseResult selectById(Integer roleId) {
//        return roleService.selectById(roleId);
//    }
//
//    @RequestMapping(value = "create", method = RequestMethod.POST)
//    @SaCheckPermission("/system/role/create")
//    @ApiOperation(value = "添加角色", httpMethod = "POST", response = ResponseResult.class, notes = "添加角色")
//    @OperationLogger(value = "添加角色")
//    public ResponseResult insert(@RequestBody Role role) {
//        return roleService.insertRole(role);
//    }
//
//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    @SaCheckPermission("/system/role/update")
//    @ApiOperation(value = "修改角色", httpMethod = "POST", response = ResponseResult.class, notes = "修改角色")
//    @OperationLogger(value = "修改角色")
//    public ResponseResult update(@RequestBody Role role) {
//        return roleService.updateRole(role);
//    }
//
//    @RequestMapping(value = "remove", method = RequestMethod.DELETE)
//    @SaCheckPermission("/system/role/remove")
//    @ApiOperation(value = "删除角色", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除角色")
//    @OperationLogger(value = "删除角色")
//    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
//        return roleService.deleteBatch(ids);
//    }


}
