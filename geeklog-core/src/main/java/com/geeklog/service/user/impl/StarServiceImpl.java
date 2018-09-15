package com.geeklog.service.user.impl;

import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Converter;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.domain.Star;
import com.geeklog.dto.StarRequestBody;
import com.geeklog.mapper.ArticleMapper;
import com.geeklog.mapper.StarMapper;
import com.geeklog.service.user.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 潘浩然
 * 创建时间 2018/09/15
 * 功能：点赞相关服务实现
 */
@Service("user.StarService")
public class StarServiceImpl implements StarService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private StarMapper starMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/15
     * 功能：校验点赞或取消点赞的请求体
     */
    private void validStarRequestBody(StarRequestBody starRequestBody) {
        Validator.notNull(starRequestBody, ValidatorException.NO_STAR_INFO);
        Validator.notNull(starRequestBody.getUserId(), ValidatorException.NO_STAR_INFO);
        Validator.notNull(starRequestBody.getArticleId(), ValidatorException.NO_STAR_INFO);
        Validator.isCurrentUser(starRequestBody.getUserId(), RoleException.OTHER_USER_STAR);

        Article article = articleMapper.selectByPrimaryKey(starRequestBody.getArticleId());
        Validator.notNull(article, ValidatorException.ARTICLE_NOT_EXIST);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/15
     * 功能：点赞
     */
    @Transactional
    public Star star(StarRequestBody starRequestBody) {
        validStarRequestBody(starRequestBody);

        Star star = starMapper.queryByUserIdAndArticleId(starRequestBody.getUserId(), starRequestBody.getArticleId());
        Validator.isNull(star, ValidatorException.ALREADY_STAR);

        star = Converter.convert(starRequestBody, Star.class);
        int effectRow = starMapper.insert(star);
        Validator.equals(effectRow, 1, ValidatorException.unexpected("StarServiceImpl.star(..) 点赞信息保存至数据库失败，未知错误"));

        star = starMapper.queryByUserIdAndArticleId(starRequestBody.getUserId(), starRequestBody.getArticleId());
        Validator.notNull(star, ValidatorException.unexpected("StarServiceImpl.star(..) 点赞记录丢失，未知错误"));

        return star;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/15
     * 功能：取消点赞
     */
    @Transactional
    public Star unstar(StarRequestBody starRequestBody) {
        validStarRequestBody(starRequestBody);

        Star star = starMapper.queryByUserIdAndArticleId(starRequestBody.getUserId(), starRequestBody.getArticleId());
        Validator.notNull(star, ValidatorException.ALREADY_UNSTAR);

        int effectRow = starMapper.deleteByPrimaryKey(star.getStarId());
        Validator.equals(effectRow, 1, ValidatorException.unexpected("StarServiceImpl.unstar(..) 点赞信息删除异常，未知错误"));

        return star;
    }
}
