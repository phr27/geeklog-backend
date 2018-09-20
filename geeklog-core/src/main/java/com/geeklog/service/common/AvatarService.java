package com.geeklog.service.common;

import com.geeklog.dto.UserWithPermissionBio;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 潘浩然
 * 创建时间 2018/09/19
 * 功能：头像相关服务接口
 */
public interface AvatarService {

    UserWithPermissionBio uploadAvatar(int userId, MultipartFile avatarFile);

    UserWithPermissionBio deleteAvatar(int userId);
}
