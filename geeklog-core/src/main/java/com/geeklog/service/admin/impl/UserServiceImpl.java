package com.geeklog.service.admin.impl;

import java.util.List;

import com.geeklog.common.enumeration.Permission;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Converter;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Forbidden;
import com.geeklog.domain.User;
import com.geeklog.dto.Page;
import com.geeklog.dto.UserWithPermission;
import com.geeklog.mapper.ForbiddenMapper;
import com.geeklog.mapper.UserMapper;
import com.geeklog.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 潘浩然
 * 创建时间 2018/09/11
 * 功能：管理员模块的用户服务实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ForbiddenMapper forbiddenMapper;

    @Transactional
    public Page<UserWithPermission> listUser(int page, int size) {
        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        User notAdmin = new User();
        notAdmin.setIsAdmin(false);
        int total = userMapper.queryNumOfUsers(notAdmin);
        int totalPage = total / size;
        if (total % size != 0) {
            totalPage++;
        }
        if (totalPage == 0) {
            totalPage = 1;
        }

        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);

        List<User> users = userMapper.queryPaging(notAdmin, (page - 1) * size, size);
        UserWithPermission[] usersWithPermission = new UserWithPermission[users.size()];
        int j;
        for (int i = 0; i < usersWithPermission.length; i++) {
            usersWithPermission[i] = Converter.domainToDTO(users.get(i), UserWithPermission.class);
            List<Forbidden> forbiddens = forbiddenMapper.queryByUserId(usersWithPermission[i].getUserId());
            if (forbiddens == null || forbiddens.size() == 0) {
                continue;
            }
            for (j = 0; j < forbiddens.size(); j++) {
                switch (Permission.getPermission(forbiddens.get(j).getAuthorityId())) {
                    case CAN_COMMENT:
                        usersWithPermission[i].setCanComment(false);
                        break;
                    case CAN_WRITE_ARTICLE:
                        usersWithPermission[i].setCanWriteArticle(false);
                        break;
                    default:
                        ValidatorException.unexpected("Unkown authority id in database table `geeklog.forbidden`");
                }
            }
        }

        return new Page<>(total, usersWithPermission);
    }
}
