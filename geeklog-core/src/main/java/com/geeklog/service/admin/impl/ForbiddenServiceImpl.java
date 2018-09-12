package com.geeklog.service.admin.impl;

import com.geeklog.domain.Forbidden;
import com.geeklog.mapper.AuthorityMapper;
import com.geeklog.mapper.ForbiddenMapper;
import com.geeklog.service.admin.ForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 午康俊
 * 创建时间 2018/9/12
 * 功能：管理员设置用户权限实现
 */

@Service
public class ForbiddenServiceImpl implements ForbiddenService {
    @Autowired
    private ForbiddenMapper forbiddenMapper;

    Forbidden f = new Forbidden();

    public Forbidden forbid(int userId, int authorityId){
        Forbidden forbidden = forbiddenMapper.queryByUserIdAndAuthorityId(userId, authorityId);
        if (forbidden == null){
            f.setUserId(userId);
            f.setAuthorityId(authorityId);
            forbiddenMapper.insert(f);
            return f;
        }
        return forbidden;
    }


}
