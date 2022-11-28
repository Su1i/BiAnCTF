package com.suli.bianctf.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suli.bianctf.domain.Role;
import com.suli.bianctf.mapper.RoleMapper;
import com.suli.bianctf.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

//    private final RoleMapper baseMapper;
//
//
    /**
     * 角色列表
     * @param name
     * @return
     */
    @Override
    public SaResult listRole(String name) {
        List<Role> roles = baseMapper.selectList(new QueryWrapper<>());
        return SaResult.data(roles);
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaResult createRole(Role role) {
        baseMapper.insert(role);
        baseMapper.insertBatchByRole(role.getMenus(), role.getId());
        return SaResult.ok();
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaResult updateRole(Role role) {
        baseMapper.updateById(role);

        //先删所有权限在新增
        baseMapper.delByRoleId(role.getId(),null);
        baseMapper.insertBatchByRole(role.getMenus(), role.getId());
        return SaResult.ok("修改成功");
    }

    /**
     * 删除角色
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaResult deleteBatch(List<Integer> ids) {
        baseMapper.deleteBatchIds(ids);
        ids.forEach(id -> baseMapper.delByRoleId(id, null));
        return SaResult.ok();
    }

    /**
     * 获取当前登录用户所拥有的权限
     * @param
     * @return
     */
    @Override
    public SaResult getCurrentUserRole() {
        Integer roleId = baseMapper.queryByUserId(StpUtil.getLoginId());
        List<Integer> list = baseMapper.queryByRoleMenu(roleId);
        return SaResult.data(list);
    }

    /**
     * 获取该角色所有的权限
     * @param
     * @return
     */
    @Override
    public SaResult selectById(Integer roleId) {
        List<Integer> list = baseMapper.queryByRoleMenu(roleId);
        return SaResult.data(list);
    }
}
