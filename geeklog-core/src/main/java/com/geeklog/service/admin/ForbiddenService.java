package com.geeklog.service.admin;

import com.geeklog.domain.Forbidden;

/**
 * @author 午康俊
 * 创建时间 2018/9/12
 * 功能 管理员管理服务接口
 */

public interface ForbiddenService {
    Forbidden forbid (int userId, int authorityId);
}
