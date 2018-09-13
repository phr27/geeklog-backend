package com.geeklog.service.user.impl;

import java.util.List;

import com.geeklog.common.enumeration.Permission;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Converter;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Forbidden;
import com.geeklog.domain.User;
import com.geeklog.dto.UserWithPermission;
import com.geeklog.dto.UserWithPermissionBio;
import com.geeklog.mapper.ForbiddenMapper;
import com.geeklog.mapper.UserMapper;
import com.geeklog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserWithPermissionBio findUserById(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, ValidatorException.USER_NOT_EXIST);

        UserWithPermissionBio userWithPermissionBio = Converter.domainToDTO(user, UserWithPermissionBio.class);

        List<Forbidden> forbiddens = forbiddenMapper.queryByUserId(userId);
        userWithPermissionBio.setPermissions(forbiddens);

        return userWithPermissionBio;
    }
}
