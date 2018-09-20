package com.geeklog.service.common.impl;

import java.io.IOException;
import java.util.List;

import com.geeklog.common.exception.FTPException;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Converter;
import com.geeklog.common.util.FileHeaderUtil;
import com.geeklog.common.util.FtpClient;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Forbidden;
import com.geeklog.domain.User;
import com.geeklog.dto.UserWithPermissionBio;
import com.geeklog.mapper.ForbiddenMapper;
import com.geeklog.mapper.UserMapper;
import com.geeklog.service.common.AvatarService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 潘浩然
 * 创建时间 2018/09/19
 * 功能：头像相关服务实现
 */
@Service("common.AvatarService")
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ForbiddenMapper forbiddenMapper;

    private static final String AVATAR_URL_PREFIX = "http://47.106.158.254/static/";

    /**
     * @author 潘浩然
     * 创建时间 2018/09/20
     * 功能：上传头像
     */
    @Transactional
    public UserWithPermissionBio uploadAvatar(int userId, MultipartFile avatarFile) {
        Validator.notNull(avatarFile, ValidatorException.NO_MULTIPART_FILE);

        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        Validator.isCurrentUser(userId, RoleException.OTHER_USER_AVATAR);

        String filename = userId + "." + FileHeaderUtil.getFileType(avatarFile);

        FtpClient ftpClient = new FtpClient();
        ftpClient.login();
        ftpClient.changeDirectory("/");

        String oldAvatarURL = user.getAvatar();
        if (oldAvatarURL != null) {
            String oldAvatarFilename = oldAvatarURL.substring(AVATAR_URL_PREFIX.length());
            ftpClient.deleteFile(oldAvatarFilename);
        }

        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.storeFile(filename, avatarFile);
        ftpClient.logout();

        User userForUpdateAvatar = new User();
        userForUpdateAvatar.setUserId(userId);
        userForUpdateAvatar.setAvatar(AVATAR_URL_PREFIX + filename);
        int effectRow = userMapper.updateByPrimaryKey(userForUpdateAvatar);
        Validator.equals(effectRow, 1,
                ValidatorException.unexpected("AvatarServiceImpl.uploadAvatar(..) 数据库更新用户头像 URL 失败，未知错误"));

        user.setAvatar(userForUpdateAvatar.getAvatar());

        UserWithPermissionBio userWithPermissionBio = Converter.convert(user, UserWithPermissionBio.class);
        List<Forbidden> forbiddens = forbiddenMapper.queryByUserId(userId);
        userWithPermissionBio.setPermissions(forbiddens);

        return userWithPermissionBio;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/20
     * 功能：删除头像
     */
    @Transactional
    public UserWithPermissionBio deleteAvatar(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        Validator.isCurrentUser(userId, RoleException.OTHER_USER_AVATAR);

        String avatarURL = user.getAvatar();
        if (avatarURL != null) {
            String avatarFilename = avatarURL.substring(AVATAR_URL_PREFIX.length());

            FtpClient ftpClient = new FtpClient();
            ftpClient.login();
            ftpClient.changeDirectory("/");
            ftpClient.deleteFile(avatarFilename);
            ftpClient.logout();

            int effectRow = userMapper.updateAvatarNull(userId);
            Validator.equals(effectRow, 1,
                    ValidatorException.unexpected("AvatarServiceImpl.deleteAvatar(..) 数据库删除用户头像 URL 失败，未知错误"));

            user.setAvatar(null);
        }

        UserWithPermissionBio userWithPermissionBio = Converter.convert(user, UserWithPermissionBio.class);
        List<Forbidden> forbiddens = forbiddenMapper.queryByUserId(userId);
        userWithPermissionBio.setPermissions(forbiddens);

        return userWithPermissionBio;
    }
}
