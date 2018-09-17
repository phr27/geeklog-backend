package com.geeklog.service.user.impl;

import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.StarCollectBaseException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Converter;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.domain.Star;
import com.geeklog.dto.StarCollectRequestBody;
import com.geeklog.mapper.ArticleMapper;
import com.geeklog.mapper.StarMapper;
import com.geeklog.service.user.StarCollectBaseService;
import com.geeklog.service.user.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 潘浩然
 * 创建时间 2018/09/15
 * 功能：点赞相关服务实现
 * 修改人 潘浩然
 * 修改时间 2018/09/17
 */
@Service("user.StarService")
public class StarServiceImpl extends StarCollectBaseService implements StarService {

    @Autowired
    private StarMapper starMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/15
     * 功能：点赞
     */
    @Transactional
    public Star star(StarCollectRequestBody starCollectRequestBody) {
        super.valid(starCollectRequestBody, StarCollectBaseException.STAR);

        Star star = starMapper.queryByUserIdAndArticleId(starCollectRequestBody.getUserId(), starCollectRequestBody.getArticleId());
        Validator.isNull(star, ValidatorException.ALREADY_STAR);

        star = Converter.convert(starCollectRequestBody, Star.class);
        int effectRow = starMapper.insert(star);
        Validator.equals(effectRow, 1, ValidatorException.unexpected("StarServiceImpl.star(..) 点赞信息保存至数据库失败，未知错误"));

        star = starMapper.queryByUserIdAndArticleId(starCollectRequestBody.getUserId(), starCollectRequestBody.getArticleId());
        Validator.notNull(star, ValidatorException.unexpected("StarServiceImpl.star(..) 点赞记录丢失，未知错误"));

        return star;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/15
     * 功能：取消点赞
     */
    @Transactional
    public Star unstar(StarCollectRequestBody starCollectRequestBody) {
        super.valid(starCollectRequestBody, StarCollectBaseException.STAR);

        Star star = starMapper.queryByUserIdAndArticleId(starCollectRequestBody.getUserId(), starCollectRequestBody.getArticleId());
        Validator.notNull(star, ValidatorException.ALREADY_UNSTAR);

        int effectRow = starMapper.deleteByPrimaryKey(star.getStarId());
        Validator.equals(effectRow, 1, ValidatorException.unexpected("StarServiceImpl.unstar(..) 点赞信息删除异常，未知错误"));

        return star;
    }
}
