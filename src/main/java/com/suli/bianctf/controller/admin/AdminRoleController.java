package com.suli.bianctf.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.util.SaResult;
import com.suli.bianctf.domain.Role;
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
    @PostMapping(value = "create")
//    @SaCheckPermission("/system/role/create")
    @Operation(summary = "添加角色", method = "POST", description = "添加角色")
    public SaResult insert(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PostMapping(value = "update")
    @SaCheckPermission("update")
    @Operation(summary = "修改角色", method = "POST", description = "修改角色")
    public SaResult update(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping(value = "delete")
    @SaCheckPermission("remove")
    @Operation(summary = "删除角色", method = "DELETE", description = "删除角色")
    public SaResult deleteBatch(@RequestBody List<Integer> ids) {
        return roleService.deleteBatch(ids);
    }


}
