package com.suli.bianctf.service;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suli.bianctf.domain.Role;

import java.util.List;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author blue
 * @since 2021-11-09
 */
public interface RoleService extends IService<Role> {


    SaResult listRole(String name);
//
//    SaResult insertRole(Role role);
//
//    SaResult updateRole(Role role);
//
//    SaResult deleteBatch(List<Integer> ids);
//
//    SaResult getCurrentUserRole();
//
//    SaResult selectById(Integer roleId);

}
