package com.suli.bianctf.service.impl;


import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suli.bianctf.common.SqlConf;
import com.suli.bianctf.domain.Menu;
import com.suli.bianctf.mapper.MenuMapper;
import com.suli.bianctf.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * <p>
 * 系统管理-权限资源表  服务实现类
 * </p>
 *
 * @author blue
 * @since 2021-09-24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    /**
     * 获取菜单树
     * @param list
     * @return
     */
    @Override
    public List<Menu> listMenuTree(List<Menu> list) {
        List<Menu> resultList = new ArrayList<>();
        for (Menu menu : list) {
            Integer parentId = menu.getParentId();
            if ( parentId == null || parentId == 0)
                resultList.add(menu);
        }
        for (Menu menu : resultList) {
            menu.setChildren(getChild(menu.getId(),list));
        }
        return resultList;
    }

    /**
     * 接口列表
     * @return
     */
    @Override
    public SaResult listMenuApi(Integer id) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>()
                .eq("level",1).eq(id != null, SqlConf.ID,id);
        List<Menu> list = baseMapper.selectList(queryWrapper);
        for (Menu menu : list) {
            List<Menu> childrens = baseMapper.selectList(new QueryWrapper<Menu>().eq(SqlConf.PARENT_ID,menu.getId()));
            menu.setChildren(childrens);
        }
        return SaResult.data(list);
    }

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaResult insertMenu(Menu menu) {
        int rows = baseMapper.insert(menu);
        return rows > 0 ? SaResult.ok(): SaResult.error("添加菜单失败");
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaResult updateMenu(Menu menu) {
        int rows = baseMapper.updateById(menu);
        return rows > 0 ? SaResult.ok(): SaResult.error("修改菜单失败");
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaResult deleteMenuById(Integer id) {
        int rows = baseMapper.deleteById(id);
        return rows > 0 ? SaResult.ok(): SaResult.error("删除菜单失败");
    }


    //----------------自定义方法开始------------
    private List<Menu> getChild(Integer pid , List<Menu> menus){
        List<Menu> childrens = new ArrayList<>();
        for (Menu e: menus) {
            Integer parentId = e.getParentId();
            if(parentId != null && parentId.equals(pid)){
                // 子菜单的下级菜单
                childrens.add( e );
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu e: childrens) {
            // childrens
            e.setChildren(getChild(e.getId(),menus));
        }
        //停下来的条件，如果 没有子元素了，则停下来
        if( childrens.size()==0 ){
            return null;
        }
        return childrens;
    }
}
