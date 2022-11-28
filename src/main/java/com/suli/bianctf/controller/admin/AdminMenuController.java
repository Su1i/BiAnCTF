package com.suli.bianctf.controller.admin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.util.SaResult;
import com.suli.bianctf.domain.Menu;
import com.suli.bianctf.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author suli
 * @description: 后台系统菜单管理控制器
 * @date 2021/7/30 17:12
 */
@RestController
@RequestMapping("/admin/menu")
@Tag(name = "系统菜单管理-接口")
@RequiredArgsConstructor
public class AdminMenuController {

    private final MenuService menuService;

    @GetMapping(value = "/getMenuTree")
    @SaCheckLogin
    @Operation(summary = "获取菜单树", method = "GET", description = "获取菜单树")
    public SaResult getMenuTree() {
        List<Menu> result = menuService.listMenuTree(menuService.list());
        return SaResult.data(result);
    }

    @GetMapping(value = "/getMenuApi")
    @SaCheckLogin
    @Operation(summary = "获取所有接口", method = "GET", description = "获取所有接口")
    public SaResult listMenuApi(Integer id) {
         return menuService.listMenuApi(id);
    }

    @PostMapping(value = "/create")
    @SaCheckPermission("/system/menu/create")
    @Operation(summary = "添加菜单", method = "POST", description = "添加菜单")
    public SaResult insert(@RequestBody Menu menu) {
        return menuService.insertMenu(menu);
    }

    @PostMapping(value = "/update")
    @SaCheckPermission("/system/menu/update")
    @Operation(summary = "修改菜单", method = "POST", description = "修改菜单")
    public SaResult update(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping(value = "/remove")
    @SaCheckPermission("/system/menu/remove")
    @Operation(summary = "删除菜单", method = "DELETE", description = "删除菜单")
    public SaResult deleteMenuById(Integer id) {
        return menuService.deleteMenuById(id);
    }
}
