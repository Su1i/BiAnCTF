package com.suli.bianctf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suli.bianctf.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统管理-权限资源表  Mapper 接口
 * </p>
 *
 * @author blue
 * @since 2021-07-30
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> getMenuByUserId(Object loginId);
}
