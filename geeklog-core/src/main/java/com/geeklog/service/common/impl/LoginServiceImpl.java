package com.geeklog.service.common.impl;

import java.util.Objects;

import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.MD5Util;
import com.geeklog.common.util.Validator;
import com.geeklog.common.util.JwtUtil;
import com.geeklog.domain.User;
import com.geeklog.dto.JwtToken;
import com.geeklog.mapper.UserMapper;
import com.geeklog.service.common.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：管理员登录服务实现
 */
@Service("common.LoginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：对用户名密码进行校验，校验通过签发 jwt token
     */
    public JwtToken login(String username, String password, Role requireRole) {
        Validator.username(username);
        Validator.password(password);

        User user = userMapper.queryUsername(username);
        Validator.notNull(user, ValidatorException.USERNAME_OR_PWD_ERROR);
        if (Objects.equals(requireRole, Role.ADMIN)) {
            Validator.isTrue(user.getIsAdmin(), RoleException.NOT_ADMIN);
        }
        Validator.isTrue(MD5Util.verify(password, user.getPassword()), ValidatorException.USERNAME_OR_PWD_ERROR);

        return new JwtToken(JwtUtil.createJwt(user));
    }
}
