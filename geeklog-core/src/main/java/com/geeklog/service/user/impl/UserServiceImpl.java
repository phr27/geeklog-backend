package com.geeklog.service.user.impl;

import java.util.List;

import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Converter;
import com.geeklog.common.util.MD5Util;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Forbidden;
import com.geeklog.domain.User;
import com.geeklog.dto.UserInfoUpdate;
import com.geeklog.dto.UserRegistry;
import com.geeklog.dto.UserWithPermissionBio;
import com.geeklog.mapper.ForbiddenMapper;
import com.geeklog.mapper.UserMapper;
import com.geeklog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 潘浩然
 * 创建时间 2018/09/13
 * 功能：用户模块的用户服务实现
 */
@Service("user.UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ForbiddenMapper forbiddenMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/14
     * 功能：根据 userId 查找用户
     */
    @Transactional
    public UserWithPermissionBio findUserById(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        UserWithPermissionBio userWithPermissionBio = Converter.convert(user, UserWithPermissionBio.class);
        userWithPermissionBio.setPermissions(forbiddenMapper.queryByUserId(userId));

        return userWithPermissionBio;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/14
     * 功能：用户注册服务
     */
    @Transactional
    public UserWithPermissionBio register(UserRegistry userRegistry) {
        Validator.notNull(userRegistry, ValidatorException.NO_REGISTER_INFO);
        Validator.username(userRegistry.getUsername());
        Validator.password(userRegistry.getPassword());

        User user = userMapper.queryUsername(userRegistry.getUsername());
        Validator.isNull(user, RoleException.USER_ALREADY_EXIST);

        User newUser = Converter.convert(userRegistry, User.class);
        newUser.setIsAdmin(false);
        newUser.setPassword(MD5Util.md5(newUser.getPassword()));
        int effectRow = userMapper.insert(newUser);
        Validator.equals(effectRow, 1, ValidatorException.unexpected("UserServiceImpl.register(..) 插入错误，未知异常"));

        UserWithPermissionBio userWithPermissionBio = Converter.convert(newUser, UserWithPermissionBio.class);
        userWithPermissionBio.setUserId(null);

        return userWithPermissionBio;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/14
     * 功能：更新自己的个人信息
     */
    @Transactional
    public UserWithPermissionBio updateUserInfo(int userId, UserInfoUpdate userInfoUpdate) {
        Validator.isCurrentUser(userId, RoleException.UPDATE_OTHER_USER);
        Validator.notNull(userInfoUpdate, ValidatorException.NO_USER_UPDATE_INFO);

        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, ValidatorException.unexpected("UserServiceImpl.updateUserInfo(..) 当前会话的用户在数据库中没找到"));

        if (userInfoUpdate.getNickname() != null || userInfoUpdate.getBio() != null) {
            User newUser = Converter.convert(userInfoUpdate, User.class);
            newUser.setUserId(userId);

            int effectRow = userMapper.updateByPrimaryKey(newUser);
            Validator.equals(effectRow, 1, ValidatorException.unexpected("UserServiceImpl.updateUserInfo(..) 更新用户信息错误，未知错误"));

            if (userInfoUpdate.getNickname() != null) {
                user.setNickname(userInfoUpdate.getNickname());
            }
            if (userInfoUpdate.getBio() != null) {
                user.setBio(userInfoUpdate.getBio());
            }
        }

        UserWithPermissionBio userWithPermissionBio = Converter.convert(user, UserWithPermissionBio.class);
        userWithPermissionBio.setUserId(null);
        userWithPermissionBio.setPermissions(forbiddenMapper.queryByUserId(userId));

        return userWithPermissionBio;
    }
}
