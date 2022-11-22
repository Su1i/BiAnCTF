package com.suli.bianctf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suli.bianctf.domain.User;
import com.suli.bianctf.domain.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;

/**
* @author 32937
* @description 针对表【lab_user(实验室用户信息表)】的数据库操作Mapper
* @createDate 2022-11-17 12:11:47
* @Entity com.suli.bianctf.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectPage(Page<User> pageParam, @Param("vo") SysUserQueryVo sysUserQueryVo);


}




