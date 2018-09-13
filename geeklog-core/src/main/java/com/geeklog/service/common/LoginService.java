package com.geeklog.service.common;

import com.geeklog.common.enumeration.Role;
import com.geeklog.dto.JwtToken;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：管理员登录服务接口
 */
public interface LoginService {

    JwtToken login(String username, String password, Role requireRole);
}
