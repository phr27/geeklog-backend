package com.geeklog.service.user;

import com.geeklog.domain.Collect;
import com.geeklog.dto.StarCollectRequestBody;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：收藏相关服务接口
 */
public interface CollectService {

    Collect collect(StarCollectRequestBody starCollectRequestBody);

    Collect uncollect(StarCollectRequestBody starCollectRequestBody);
}
