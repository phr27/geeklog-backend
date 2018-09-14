package com.geeklog.service.user;

import com.geeklog.dto.PasswordUpdate;
import com.geeklog.dto.UserInfoUpdate;
import com.geeklog.dto.UserRegistry;
import com.geeklog.dto.UserWithPermissionBio;

/**
 * @author 潘浩然
 * 创建时间 2018/09/13
 * 功能：用户模块的用户服务接口
 */
public interface UserService {

    UserWithPermissionBio findUserById(int userId);

    UserWithPermissionBio register(UserRegistry userRegistry);

    UserWithPermissionBio updateUserInfo(int userId, UserInfoUpdate userInfoUpdate);

    UserWithPermissionBio updatePassword(PasswordUpdate passwordUpdate);
}
