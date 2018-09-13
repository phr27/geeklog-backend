package com.geeklog.service.admin.impl;

import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Forbidden;
import com.geeklog.domain.User;
import com.geeklog.mapper.ForbiddenMapper;
import com.geeklog.mapper.UserMapper;
import com.geeklog.service.admin.ForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 午康俊
 * 创建时间 2018/9/12
 * 功能：管理员设置用户权限实现
 */
@Service("admin.ForbiddenService")
public class ForbiddenServiceImpl implements ForbiddenService {
    @Autowired
    private ForbiddenMapper forbiddenMapper;

    @Autowired
    private UserMapper userMapper;



    @Transactional
    public Forbidden forbid(int userId, int authorityId){
        Forbidden forbidden = forbiddenMapper.queryByUserIdAndAuthorityId(userId, authorityId);
        if (forbidden == null){
            Forbidden f = new Forbidden();
            f.setUserId(userId);
            f.setAuthorityId(authorityId);
            forbiddenMapper.insert(f);
            return f;
        }else {
            return forbidden;
        }

    }

    @Transactional
    public Forbidden deleteForbidden(int userId, int authorityId){
        Forbidden forbidden = forbiddenMapper.queryByUserIdAndAuthorityId(userId, authorityId);
        if (forbidden != null) {
            forbiddenMapper.deleteByPrimaryKey(forbidden.getForbiddenId());
            return forbidden;
        }else {
            return null;
        }
    }

    public void isLegalUser(int userId, int authorityId){
        User user = userMapper.selectByPrimaryKey(userId);

        Validator.isLegal(authorityId, ValidatorException.AUTHORITY_OUT_OF_RANGE);

        Validator.notNull(user, ValidatorException.USER_NOT_EXIST);

        Validator.isTrue(!user.getIsAdmin(),ValidatorException.USER_IS_ADMIN);

    }

}
