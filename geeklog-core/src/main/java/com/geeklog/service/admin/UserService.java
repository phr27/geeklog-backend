package com.geeklog.service.admin;

import com.geeklog.dto.Page;
import com.geeklog.dto.UserWithPermission;

/**
 * @author 潘浩然
 * 创建时间 2018/09/11
 * 功能：管理员模块的用户服务接口
 */
public interface UserService {

    Page<UserWithPermission> listUser(int page, int size);
}
