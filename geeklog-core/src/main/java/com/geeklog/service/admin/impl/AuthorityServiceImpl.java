package com.geeklog.service.admin.impl;

import java.util.List;

import com.geeklog.domain.Authority;
import com.geeklog.mapper.AuthorityMapper;
import com.geeklog.service.admin.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：权限相关服务实现
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：列出数据库中的所有权限
     */
    public List<Authority> listAuthority() {
        return authorityMapper.selectAll();
    }
}
