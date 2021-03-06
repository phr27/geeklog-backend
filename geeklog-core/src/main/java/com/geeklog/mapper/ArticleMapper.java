package com.geeklog.mapper;

import com.geeklog.domain.Article;
import com.geeklog.dto.ArticleDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 *  @author 朱远飞
 *  @create_time 2018年9月9日15:11:30
 *  @describe Article DAO
 */
public interface ArticleMapper {
    /**
     * @describe 删除指定主键的行记录
     * @param articleId 主键
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:15:54
     */
    int deleteByPrimaryKey(Integer articleId);

    /**
     * @describe 添加新的记录
     * @param article 要添加的信息在里面
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:16:43
     */
    int insert(Article article);

    /**
     * @describe 查询指定主键的行记录
     * @param articleId 主键
     * @return  Article
     * @author 朱远飞
     * @create_time 2018年9月9日15:17:25
     */
    Article selectByPrimaryKey(Integer articleId);

    /**
     * @describe 查询出所有记录
     * @return 所有记录的列表
     * @author 朱远飞
     * @create_time 2018年9月9日15:18:02
     */
    List<Article> selectAll();

    /**
     * @describe 更新记录
     * @param article 指定要更新的主键，其他信息表示要更新成的数据
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:18:57
     */
    int updateByPrimaryKey(Article article);

    /**
     * @describe 以一个条件来查询文章数量
     * @param article 查询条件
     * @return 文章数量
     * @author 朱远飞
     * @create_time 2018年9月12日09:13:25
     */
    int queryNumOfArticles(Article article);

    /**
     * @describe 分页查询(降序)
     * @param article 分页查询条件
     * @param rowIndex  limit第一个参数 起始位置
     * @param pageSize  limit第二个参数 每页数量
     * @return 分页查询的文章集合
     * @author 朱远飞
     * @create_time 2018年9月12日09:14:05
     */
    List<Article> queryPaging(@Param("article") Article article, @Param("rowIndex") int rowIndex,
                           @Param("pageSize") int pageSize);

    /**
     * @describe 分页排序查询(降序)
     * @param article 分页查询条件
     * @param rowIndex  limit第一个参数 起始位置
     * @param pageSize  limit第二个参数 每页数量
     * @param orderByTime 是否根据时间进行排序，true为按时间排序，false为按综合热度排序
     * @return 分页查询的文章集合
     * @author 朱远飞
     * @create_time 2018年9月12日09:14:05
     */
    List<ArticleDto> queryPagingOrder(@Param("article") Article article, @Param("rowIndex") int rowIndex,
                                      @Param("pageSize") int pageSize, @Param("orderByTime") boolean orderByTime);

    /**
     * @describe 根据文章主键查询ArticleDto
     * @param articleId 文章主键
     * @return ArticleDto
     * @author 朱远飞
     * @create_time 2018年9月18日11:28:04
     */
    ArticleDto queryOneArticle(@Param("articleId") Integer articleId);


    /**
     * @describe 分页排序查询(时间降序)  查询用户点赞过或者收藏过的文章
     * @param userId 用户id
     * @param rowIndex  limit第一个参数 起始位置
     * @param pageSize  limit第二个参数 每页数量
     * @param tableName 要查询的表名称，只有star点赞表和collect收藏表
     * @return 分页查询的文章集合
     * @author 朱远飞
     * @create_time 2018年9月19日09:50:41
     */
    List<ArticleDto> queryPagingByUser(@Param("userId") Integer userId, @Param("rowIndex") int rowIndex,
                                      @Param("pageSize") int pageSize, @Param("tableName") String tableName);



}