package com.geeklog.mapper;

import com.geeklog.domain.Article;
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
}