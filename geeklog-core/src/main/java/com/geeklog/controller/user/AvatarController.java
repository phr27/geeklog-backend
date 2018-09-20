package com.geeklog.controller.user;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.dto.UserWithPermissionBio;
import com.geeklog.service.common.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 潘浩然
 * 创建时间 2018/09/20
 * 功能：用户头像相关控制器
 */
@GeekLogController(path = "/avatars", value = "user.AvatarController")
@RequireRole(Role.USER)
public class AvatarController {

    @Autowired
    @Qualifier("common.AvatarService")
    private AvatarService avatarService;

    @PostMapping("/{user_id}")
    public ResponseEntity<UserWithPermissionBio> uploadAvatar(@PathVariable("user_id") int userId, @RequestParam MultipartFile avatar) {
        Validator.notNull(avatar, ValidatorException.NO_MULTIPART_FILE);
        Validator.isCurrentUser(userId, RoleException.OTHER_USER_AVATAR);

        return ResponseEntity.ok("success", avatarService.uploadAvatar(userId, avatar));
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<UserWithPermissionBio> deleteAvatar(@PathVariable("user_id") int userId) {
        Validator.isCurrentUser(userId, RoleException.OTHER_USER_AVATAR);

        return ResponseEntity.ok("success", avatarService.deleteAvatar(userId));
    }
}
