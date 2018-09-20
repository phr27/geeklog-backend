package com.geeklog.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：数据校验异常
 */
public class ValidatorException extends CommonException {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：是否是未知的参数校验错误
     */
    private boolean innerError;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：异常日志，给后端看的，继承的 message 字段是异常信息，是发送给前端的
     */
    private String log;

    private ValidatorException(int code, String message) {
        super(code, message);
    }

    private ValidatorException(int code, String message, String log, boolean innerError) {
        super(code, message);
        this.log = log;
        this.innerError = innerError;
    }

    public boolean isInnerError() {
        return innerError;
    }

    public String getLog() {
        return log;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：创建一个未知的参数校验错误
     * @param log 异常日志
     */
    public static ValidatorException unexpected(String log) {
        return new ValidatorException(500, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), log, true);
    }

    public static final ValidatorException USERNAME_INVALID = new ValidatorException(601, "用户名最少 6 个字符，最多 20 个字符，由字母数字下划线组成");

    public static final ValidatorException PWD_INVALID = new ValidatorException(602,
            "密码最少 6 个字符，包括字母数字下划线，可使用的特殊字符有 ~`!@#$%^&*?,:;()-.+={}[]");

    public static final ValidatorException NO_AUTH_TOKEN = new ValidatorException(603, "需要用户名密码");

    public static final ValidatorException USERNAME_OR_PWD_ERROR = new ValidatorException(604, "用户名或密码错误");

    public static final ValidatorException NO_JWT_TOKEN = new ValidatorException(605, "请先登录");

    public static final ValidatorException PAGE_OUT_OF_RANGE = new ValidatorException(606, "页码超出范围");

    public static final ValidatorException SIZE_OUT_OF_RANGE = new ValidatorException(607, "页长过小");

    public static final ValidatorException CATEGORY_NOT_EXIST = new ValidatorException(608, "该分类不存在");

    /**
     * @author 午康俊
     */
    public static final ValidatorException AUTHORITY_OUT_OF_RANGE = new ValidatorException(609, "权限码超出范围");

    public static final ValidatorException ARTICLE_NOT_EXIST = new ValidatorException(610, "该文章不存在");

    public static final ValidatorException COMMENT_NOT_EXIST = new ValidatorException(611, "该评论不存在");

    public static final ValidatorException STILL_HAVE_ARTICLE = new ValidatorException(612, "仍有文章存在");

    public static final ValidatorException NO_REGISTER_INFO = new ValidatorException(613, "需要用户注册信息");

    public static final ValidatorException NO_USER_UPDATE_INFO = new ValidatorException(614, "需要用户更新信息");

    public static final ValidatorException NO_PWD_UPDATE_INFO = new ValidatorException(615, "需要密码更新信息");

    public static final ValidatorException OLD_PWD_ERROR = new ValidatorException(616, "原密码错误");

    public static final ValidatorException NO_STAR_INFO = new ValidatorException(617, "需要点赞或取消点赞的信息");

    public static final ValidatorException ALREADY_STAR = new ValidatorException(618, "已点赞");

    public static final ValidatorException ALREADY_UNSTAR = new ValidatorException(619, "已取消点赞");

    public static final ValidatorException NO_COLLECT_INFO = new ValidatorException(620, "需要收藏或取消收藏的信息");

    public static final ValidatorException ALREADY_COLLECT = new ValidatorException(621, "已收藏");

    public static final ValidatorException ALREADY_UNCOLLECT = new ValidatorException(622, "已取消收藏");

    public static final ValidatorException LATEST_COMMENT_COUNT_OUT_OF_RANGE = new ValidatorException(623, "请求的最新评论数量过小");

    public static final ValidatorException NO_COMMENT_PUBLISH_INFO = new ValidatorException(624, "需要发布评论的相关信息");

    public static final ValidatorException PARENT_COMMENT_NOT_EXIST = new ValidatorException(625, "父评论不存在");

    public static final ValidatorException COMMENT_CONTENT_BLANK = new ValidatorException(626, "评论内容不能为空");

    public static final ValidatorException NO_MULTIPART_FILE = new ValidatorException(627, "上传头像不能为空");

    public static final ValidatorException FILE_TYPE_INVALID = new ValidatorException(628, "上传文件类型只能是 .jpeg .jpg .png");

}
