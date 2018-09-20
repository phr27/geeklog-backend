package com.geeklog.service.common.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.List;

import com.geeklog.common.exception.FTPException;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Converter;
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
import org.springframework.context.annotation.Profile;
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

    private static final String IP = System.getenv("FTP_IP");

    private static final int PORT = Integer.parseInt(System.getenv("FTP_PORT"));

    private static final String USERNAME = System.getenv("FTP_USER");

    private static final String PASSWORD = System.getenv("FTP_PWD");

    private static final String AVATAR_URL_PREFIX = "http://47.106.158.254/static/";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ForbiddenMapper forbiddenMapper;

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

        FTPClient ftpClient = new FTPClient();
        try {
            InputStream inputStream = avatarFile.getInputStream();
            ftpClient.connect(IP, PORT);
            Validator.isTrue(ftpClient.login(USERNAME, PASSWORD), FTPException.unexpected("ftpClient.login(..) == false"));

            Validator.isTrue(ftpClient.changeWorkingDirectory("/"),
                    FTPException.unexpected("ftpClient.changeWorkingDirectory(..) == false"));
            Validator.isTrue(ftpClient.setFileType(FTP.BINARY_FILE_TYPE), FTPException.unexpected("ftpClient.setFileType(..) == false"));
            Validator.isTrue(ftpClient.storeFile(userId + "", inputStream),
                    FTPException.unexpected("ftpClient.storeFile(..) == false"));
        } catch (IOException e) {
            throw FTPException.unexpected(e);
        } finally {
            try {
                Validator.isTrue(ftpClient.logout(), FTPException.unexpected("ftpClient.logout() == false"));
            } catch (IOException e) {
                throw FTPException.unexpected(e);
            }
        }

        User userForUpdateAvatar = new User();
        userForUpdateAvatar.setUserId(userId);
        userForUpdateAvatar.setAvatar(AVATAR_URL_PREFIX + userId);
        int effectRow = userMapper.updateByPrimaryKey(userForUpdateAvatar);
        Validator.equals(effectRow, 1,
                ValidatorException.unexpected("AvatarServiceImpl.uploadAvatar(..) 数据库更新用户头像 URL 失败，未知错误"));

        UserWithPermissionBio userWithPermissionBio = Converter.convert(user, UserWithPermissionBio.class);
        userWithPermissionBio.setAvatar(userForUpdateAvatar.getAvatar());
        List<Forbidden> forbiddens = forbiddenMapper.queryByUserId(userId);
        userWithPermissionBio.setPermissions(forbiddens);

        return userWithPermissionBio;
    }
}
