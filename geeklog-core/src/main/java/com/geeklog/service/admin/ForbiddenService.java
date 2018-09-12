package com.geeklog.service.admin;

import com.geeklog.domain.Forbidden;

public interface ForbiddenService {
    Forbidden forbid (int userId, int authorityId);
}
