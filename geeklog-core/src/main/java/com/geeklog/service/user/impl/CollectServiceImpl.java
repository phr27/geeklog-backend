package com.geeklog.service.user.impl;

import java.util.Date;

import com.geeklog.common.exception.StarCollectBaseException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Converter;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Collect;
import com.geeklog.dto.StarCollectRequestBody;
import com.geeklog.mapper.CollectMapper;
import com.geeklog.service.user.CollectService;
import com.geeklog.service.user.StarCollectBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：收藏相关服务实现
 */
@Service("user.CollectService")
public class CollectServiceImpl extends StarCollectBaseService implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：收藏
     */
    @Transactional
    public Collect collect(StarCollectRequestBody starCollectRequestBody) {
        super.valid(starCollectRequestBody, StarCollectBaseException.COLLECT);

        Collect collect = collectMapper.queryByUserIdAndArticleId(starCollectRequestBody.getUserId(), starCollectRequestBody.getArticleId());
        Validator.isNull(collect, ValidatorException.ALREADY_COLLECT);

        collect = Converter.convert(starCollectRequestBody, Collect.class);
        collect.setCreatedAt(new Date());
        int effectRow = collectMapper.insert(collect);
        Validator.equals(effectRow, 1, ValidatorException.unexpected("CollectServiceImpl.collect(..) 收藏信息保存至数据库失败，未知错误"));

        collect = collectMapper.queryByUserIdAndArticleId(starCollectRequestBody.getUserId(), starCollectRequestBody.getArticleId());
        Validator.notNull(collect, ValidatorException.unexpected("CollectServiceImpl.collect(..) 收藏记录丢失，未知错误"));

        return collect;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：取消收藏
     */
    @Transactional
    public Collect uncollect(StarCollectRequestBody starCollectRequestBody) {
        super.valid(starCollectRequestBody, StarCollectBaseException.COLLECT);

        Collect collect = collectMapper.queryByUserIdAndArticleId(starCollectRequestBody.getUserId(), starCollectRequestBody.getArticleId());
        Validator.notNull(collect, ValidatorException.ALREADY_UNCOLLECT);

        int effectRow = collectMapper.deleteByPrimaryKey(collect.getCollectId());
        Validator.equals(effectRow, 1, ValidatorException.unexpected("CollectServiceImpl.uncollect(..) 收藏信息删除异常，未知错误"));

        return collect;
    }
}
