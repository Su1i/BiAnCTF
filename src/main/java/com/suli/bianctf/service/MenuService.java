package com.suli.bianctf.service;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suli.bianctf.domain.Menu;

import java.util.List;

/**
 * @author blue
 * @description:
 * @date 2021/8/20 9:30
 */
public interface MenuService extends IService<Menu> {

    List<Menu> listMenuTree(List<Menu> list);

    SaResult listMenuApi(Integer id);

    SaResult insertMenu(Menu menu);

    SaResult updateMenu(Menu menu);

    SaResult deleteMenuById(Integer id);
}
