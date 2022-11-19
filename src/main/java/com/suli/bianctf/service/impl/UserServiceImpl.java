package com.suli.bianctf.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suli.bianctf.common.ResponseResult;
import com.suli.bianctf.domain.User;
import com.suli.bianctf.domain.dto.EmailLoginDTO;
import com.suli.bianctf.domain.dto.EmailRegisterDTO;
import com.suli.bianctf.domain.vo.UserVO;
import com.suli.bianctf.enums.UserStatusEnum;
import com.suli.bianctf.exception.BusinessException;
import com.suli.bianctf.mapper.UserMapper;
import com.suli.bianctf.service.UserService;
import com.suli.bianctf.utils.AesEncryptUtils;
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
* @author 32937
* @description 针对表【lab_user(实验室用户信息表)】的数据库操作Service实现
* @createDate 2022-11-17 12:11:47
*/
@Service
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
//     * 修改密码
//     * @param map
//     * @return
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public ResponseResult updatePassword(Map<String, String> map) {
//
//        User user = baseMapper.selectById(StpUtil.getLoginIdAsInt());
//        Assert.notNull(user,ERROR_USER_NOT_EXIST.getDesc());
//
//        boolean isValid = AesEncryptUtils.validate(user.getPassword(),map.get("oldPassword"));
//        Assert.isTrue(isValid,"旧密码校验不通过!");
//
//        String newPassword = AesEncryptUtils.aesEncrypt(map.get("newPassword"));
//        user.setPassword(newPassword);
//        baseMapper.updateById(user);
//        return ResponseResult.success("修改成功");
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

//        //组装数据
//        UserInfoVO userInfoVO = UserInfoVO.builder().id(user.getId()).userInfoId(auth.getId()).avatar(auth.getAvatar()).nickname(auth.getNickname())
//                .intro(auth.getIntro()).webSite(auth.getWebSite()).email(user.getUsername()).loginType(user.getLoginType()).token(StpUtil.getTokenValue()).build();

        // return ResponseResult.success(userInfoVO);
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


    //---------------自定义方法开始-------------

//    public void checkEmail(String email){
//        boolean matches = Pattern.compile("\\w+@{1}\\w+\\.{1}\\w+").matcher(email).matches();
//        Assert.isTrue(matches, EMAIL_ERROR.getDesc());
//    }

//    public User getByUserName(String username){
//        return userService.getOne(new QueryWrapper<User>().eq(SqlConf.USERNAME, username));
//    }

}




