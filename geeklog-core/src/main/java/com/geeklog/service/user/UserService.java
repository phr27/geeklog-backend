package com.geeklog.service.user;

import com.geeklog.dto.UserWithPermissionBio;

/**
 * @author 潘浩然
 * 创建时间 2018/09/13
 * 功能：用户模块的用户服务接口
 */
public interface UserService {

    UserWithPermissionBio findUserById(int userId);
}
