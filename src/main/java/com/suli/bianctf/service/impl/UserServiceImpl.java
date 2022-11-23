package com.suli.bianctf.service.impl;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suli.bianctf.common.ResponseResult;
import com.suli.bianctf.domain.User;
import com.suli.bianctf.domain.dto.*;
import com.suli.bianctf.domain.vo.SysUserQueryVo;
import com.suli.bianctf.domain.vo.UserVO;
import com.suli.bianctf.enums.UserStatusEnum;
import com.suli.bianctf.exception.BusinessException;
import com.suli.bianctf.mapper.UserMapper;
import com.suli.bianctf.service.UserService;
import com.suli.bianctf.utils.AesEncryptUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.suli.bianctf.common.ResultCode.*;

/**
* @author suli
* @description 针对表【lab_user(实验室用户信息表)】的数据库操作Service实现
* @createDate 2022-11-17 12:11:47
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {



//    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

//    private final MenuService menuService;

//    private final UserAuthMapper userAuthMapper;


//    /**
//     * 用户列表
//     * @param username
//     * @param loginType
//     * @return
//     */
//    @Override
//    public ResponseResult listUser(String username, Integer loginType) {
//        Page<UserVO> page = baseMapper.selectPageRecord(new Page<>(PageUtils.getPageNo(), PageUtils.getPageSize()),username,loginType);
//        return ResponseResult.success(page);
//    }

//    /**
//     * 用户详情
//     * @param id
//     * @return
//     */
//    @Override
//    public ResponseResult getUserById(Integer id) {
//        SystemUserVO user = baseMapper.getById(id);
//        return ResponseResult.success(user);
//    }

//    /**
//     *  添加用户
//     * @param user
//     * @return
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public ResponseResult insertUser(User user) {
//        user.setPassword(AesEncryptUtils.aesEncrypt(user.getPassword()));
//        user.setStatus(UserStatusEnum.normal.code);
//        baseMapper.insert(user);
//        // roleMapper.insertToUserId(user.getId(),user.getRoleId());
//        return ResponseResult.success(user);
//    }

//    /**
//     *  修改用户
//     * @param user
//     * @return
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public ResponseResult updateUser(User user) {
//        baseMapper.updateById(user);
//        //roleMapper.updateByUserId(user.getId(),user.getRoleId());
//        return ResponseResult.success();
//    }
//
//    /**
//     * 删除用户
//     * @param ids
//     * @return
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public ResponseResult deleteBatch(List<Integer> ids) {
//        userAuthMapper.deleteByUserIds(ids);
//        int rows = baseMapper.deleteBatchIds(ids);
//        return rows > 0? ResponseResult.success(): ResponseResult.error("删除失败");
//    }

//    /**
//     * 获取当前登录用户详情
//     * @return
//     */
//    @Override
//    public ResponseResult getCurrentUserInfo() {
//        return ResponseResult.success("获取当前登录用户信息成功", baseMapper.getById(StpUtil.getLoginIdAsInt()));
//    }

//    /**
//     * 获取当前登录用户所拥有的菜单权限
//     * @return
//     */
//    @Override
//    public ResponseResult getCurrentUserMenu() {
//        List<Integer> menuIds = baseMapper.getMenuId(StpUtil.getLoginIdAsInt());
//        List<Menu> menus = menuService.listByIds(menuIds);
//        List<Menu> menuTree = menuService.listMenuTree(menus);
//        return ResponseResult.success(menuTree);
//    }


//    /**
//     * 在线用户
//     * @param keywords
//     * @return
//     */
//    @Override
//    public ResponseResult listOnlineUsers(String keywords) {
//        int pageNo = PageUtils.getPageNo().intValue();
//        int pageSize = PageUtils.getPageSize().intValue();
//
//        List<OnlineUser> onlineUsers = MySaTokenListener.ONLINE_USERS;
//        //根据关键词过滤
//        if (StringUtils.isNotBlank(keywords)) {
//            onlineUsers = MySaTokenListener.ONLINE_USERS.stream().filter(item -> item.getNickname().contains(keywords)).collect(Collectors.toList());
//        }
//        //排序
//        onlineUsers.sort((o1, o2) -> DateUtil.compare(o2.getLoginTime(), o1.getLoginTime()));
//        int fromIndex = (pageNo-1) * pageSize;
//        int toIndex = onlineUsers.size() - fromIndex > pageSize ? fromIndex + pageSize : onlineUsers.size();
//        List<OnlineUser> userOnlineList = onlineUsers.subList(fromIndex, toIndex);
//        logger.info("memory用户数：{}", userOnlineList.size());
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("total",onlineUsers.size());
//        map.put("records",userOnlineList);
//        return ResponseResult.success(map);
//    }

//    /**
//     * 踢人下线
//     * @param token
//     * @return
//     */
//    @Override
//    public ResponseResult kick(String token) {
//        logger.info("当前踢下线的用户token为:{}",token);
//        StpUtil.kickoutByTokenValue(token);
//        return ResponseResult.success();
//    }

    @Override
    public ResponseResult emailLogin(EmailLoginDTO emailLoginDTO) {

        String email = emailLoginDTO.getEmail();
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("email", email));
        if (user == null) {
            throw new BusinessException(ERROR_MUST_REGISTER.desc);
        }
        // 密码验证
        if (!SaSecureUtil.md5(emailLoginDTO.getPassword()).equals(user.getPassword())) {
            return ResponseResult.error(ERROR_PASSWORD.code, ERROR_PASSWORD.desc);
        }

        //登录
        StpUtil.login(user.getUserId());
        return ResponseResult.success();
    }


    @Override
    public ResponseResult emailRegister(EmailRegisterDTO emailRegisterDTO){
        //检查用户两次输入的密码是否一致
        String password = emailRegisterDTO.getPassword();
        String checkPwd = emailRegisterDTO.getCheckPwd();
        //如果不一致则返回错误信息
        if (!password.equals(checkPwd)){
            return ResponseResult.error("密码不一致");
        }
        String email = emailRegisterDTO.getEmail();
        //查询用户邮箱是否已注册
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("email", email);
        List<User> userList = baseMapper.selectList(queryWrapper);
        //如果注册则返回注册失败
        if (!userList.isEmpty()){
            return ResponseResult.error("用户邮箱已注册");
        }
        //用户未注册则注册用户
        User registerUser = new User();
        registerUser.setEmail(email);
        registerUser.setUserName(emailRegisterDTO.getUserName());
        registerUser.setPassword(SaSecureUtil.md5(password));
        registerUser.setCreateBy("system");
        registerUser.setCreateTime(DateUtil.date());
        registerUser.setUpdateTime(DateUtil.date());
        int i = baseMapper.insert(registerUser);
        if (i==0){
            return ResponseResult.error("插入失败");
        }
        return ResponseResult.success("注册成功");
    }

    @Override
    public SaResult logout() {
        if (StpUtil.getLoginIdDefaultNull() == null){
            return SaResult.error("用户未登录");
        }
        StpUtil.logout();
        return SaResult.ok("注销成功");
    }

    @Override
    public SaResult updatePassword(UpdatePwdDTO updatePwdDTO) {
        if (!updatePwdDTO.getNewPwd().equals(updatePwdDTO.getCheckNewPwd())){
            return SaResult.error("两次输入的新密码不相同");
        }
        Long id = StpUtil.getLoginIdAsLong();
        User user = baseMapper.selectById(id);
        if (!SaSecureUtil.md5(updatePwdDTO.getOldPwd()).equals(user.getPassword())){
            return SaResult.error("旧密码输入错误");
        }
        user.setPassword(SaSecureUtil.md5(updatePwdDTO.getNewPwd()));
        int i = baseMapper.updateById(user);
        if (i==0){
            return SaResult.error("密码修改失败");
        }
        return SaResult.ok("密码修改成功");
    }

    @Override
    public SaResult updateUser(UpdateUserDTO updateUserDTO) {
        Long id = StpUtil.getLoginIdAsLong();
        User user = baseMapper.selectById(id);
        String email = updateUserDTO.getEmail();
        if (!StrUtil.hasBlank(email)){
            //查询用户邮箱是否已注册
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("email", email);
            List<User> userList = baseMapper.selectList(queryWrapper);
            //如果注册则返回修改邮箱失败
            if (!userList.isEmpty()){
                return SaResult.error("用户邮箱已注册");
            }
            //如果未注册则修改邮箱
            user.setEmail(email);
        }
        user.setUserName(updateUserDTO.getUserName());
        user.setRealName(updateUserDTO.getRealName());
        user.setStudentId(updateUserDTO.getStudentId());
        user.setCollege(updateUserDTO.getCollege());
        user.setSpeciality(updateUserDTO.getSpeciality());
        user.setGrade(updateUserDTO.getGrade());
        user.setIdentityCard(updateUserDTO.getIdentityCard());
        user.setPhoneNumber(updateUserDTO.getPhoneNumber());
        user.setSex(updateUserDTO.getSex());
        user.setAvatar(updateUserDTO.getAvatar());
        user.setUpdateTime(DateUtil.date());
        user.setUpdateBy(updateUserDTO.getUserName());
        int i = baseMapper.updateById(user);
        if (i==0){
            return SaResult.error("修改失败");
        }

        return SaResult.ok("修改成功");
    }

    //用户列表
    @Override
    public IPage<User> selectPage(Page<User> pageParam, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParam,sysUserQueryVo);
    }

    //管理员修改用户密码

    @Override
    public SaResult editPassword(EditPwdDTO editPwdDTO) {
        if (!editPwdDTO.getNewPwd().equals(editPwdDTO.getCheckNewPwd())){
            return SaResult.error("两次输入的新密码不相同");
        }
        String userId = editPwdDTO.getUserId();
        User user = baseMapper.selectById(userId);
        user.setPassword(SaSecureUtil.md5(editPwdDTO.getNewPwd()));
        int i = baseMapper.updateById(user);
        if (i==0){
            return SaResult.error("密码修改失败");
        }
        return SaResult.ok("密码修改成功");
    }

    @Override
    public SaResult editUser(EditUserDTO editUserDTO) {
        Long userId = editUserDTO.getUserId();
        String email = editUserDTO.getEmail();
        if (StrUtil.hasBlank(email)){
            return SaResult.error("用户邮箱不能为空");
        }
        User user = baseMapper.selectById(userId);
        if (!user.getEmail().equals(email)){
            //查询用户邮箱是否已注册
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("email", email);
            List<User> userList = baseMapper.selectList(queryWrapper);
            //如果注册则返回修改邮箱失败
            if (!userList.isEmpty()){
                return SaResult.error("用户邮箱已注册");
            }
            //如果未注册则修改
        }
        BeanUtil.copyProperties(editUserDTO,user);
        int i = baseMapper.updateById(user);
        if (i==0){
            return SaResult.error("修改失败");
        }

        return SaResult.ok("修改成功");
    }

    //---------------自定义方法开始-------------

//    public void checkEmail(String email){
//        boolean matches = Pattern.compile("\\w+@{1}\\w+\\.{1}\\w+").matcher(email).matches();
//        Assert.isTrue(matches, EMAIL_ERROR.getDesc());
//    }

//    public User getByUserName(String username){
//        return userService.getOne(new QueryWrapper<User>().eq(SqlConf.USERNAME, username));
//    }

}




