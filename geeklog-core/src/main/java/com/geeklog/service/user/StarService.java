package com.geeklog.service.user;

import com.geeklog.domain.Star;
import com.geeklog.dto.StarCollectRequestBody;

/**
 * @author 潘浩然
 * 创建时间 2018/09/15
 * 功能：点赞相关服务接口
 */
public interface StarService {

    Star star(StarCollectRequestBody starCollectRequestBody);

    Star unstar(StarCollectRequestBody starCollectRequestBody);
}
