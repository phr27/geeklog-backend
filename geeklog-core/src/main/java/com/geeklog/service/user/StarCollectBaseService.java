package com.geeklog.service.user;

import com.geeklog.common.exception.StarCollectBaseException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.dto.StarCollectRequestBody;
import com.geeklog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：点赞和校验服务的基类，封装一些公共的校验代码
 */
public abstract class StarCollectBaseService {

    @Autowired
    private ArticleMapper articleMapper;

    protected void valid(StarCollectRequestBody starCollectRequestBody, StarCollectBaseException starCollectBaseException) {
        Validator.validStarCollectRequestBody(starCollectRequestBody, starCollectBaseException);

        Article article = articleMapper.selectByPrimaryKey(starCollectRequestBody.getArticleId());
        Validator.notNull(article, ValidatorException.ARTICLE_NOT_EXIST);
    }
}
