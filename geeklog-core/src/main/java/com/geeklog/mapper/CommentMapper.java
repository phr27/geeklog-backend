package com.geeklog.mapper;

import com.geeklog.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 *  @author 朱远飞
 *  @create_time 2018年9月9日15:11:30
 *  @describe Comment DAO
 */
public interface CommentMapper {
    /**
     * @describe 删除指定主键的行记录
     * @param commentId 主键
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:15:54
     */
    int deleteByPrimaryKey(Integer commentId);

    /**
     * @describe 添加新的记录
     * @param comment 要添加的信息在里面
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:16:43
     */
    int insert(Comment comment);

    /**
     * @describe 查询指定主键的行记录
     * @param commentId 主键
     * @return  Comment
     * @author 朱远飞
     * @create_time 2018年9月9日15:17:25
     */
    Comment selectByPrimaryKey(Integer commentId);

    /**
     * @describe 查询出所有记录
     * @return 所有记录的列表
     * @author 朱远飞
     * @create_time 2018年9月9日15:18:02
     */
    List<Comment> selectAll();

    /**
     * @describe 更新记录
     * @param comment 指定要更新的主键，其他信息表示要更新成的数据
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:18:57
     */
    int updateByPrimaryKey(Comment comment);

    /**
     * @describe 以一个条件来查询评论数量
     * @param comment 查询条件
     * @return 评论数量
     * @author 朱远飞
     * @create_time 2018年9月12日09:40:23
     */
    int queryNumOfComments(Comment comment);

    /**
     * @describe 以一个条件来查询首评论数量
     * @param comment 查询条件
     * @return 首评论数量
     * @author 朱远飞
     * @create_time 2018年9月12日09:40:23
     */
    int queryNumOfRoot(Comment comment);

    /**
     * @describe 以一个条件来查询某个首评论的回复数量
     * @param comment 查询条件
     * @return 回复数量
     * @author 朱远飞
     * @create_time 2018年9月12日09:40:23
     */
    int queryNumOfReply(Comment comment);

    /**
     * @describe 分页查询评论
     * @param comment 分页查询条件
     * @param rowIndex  limit第一个参数 起始位置
     * @param pageSize  limit第二个参数 每页数量
     * @return 分页查询的评论集合
     * @author 朱远飞
     * @create_time 2018年9月12日09:40:26
     */
    List<Comment> queryPaging(@Param("comment") Comment comment, @Param("rowIndex") int rowIndex,
                                  @Param("pageSize") int pageSize);

    /**
     * @describe 分页查询首评论（即parentId为null的评论，升序，最新的放在最后）
     * @param comment 分页查询条件
     * @param rowIndex  limit第一个参数 起始位置
     * @param pageSize  limit第二个参数 每页数量
     * @return 分页查询的评论集合
     * @author 朱远飞
     * @create_time 2018年9月12日09:40:26
     */
    List<Comment> queryPagingRoot(@Param("comment") Comment comment, @Param("rowIndex") int rowIndex,
                           @Param("pageSize") int pageSize);

    /**
     * @describe 分页查询首评论的回复（即rootId = 某一个评论的Id，升序，最新的放在最后）
     * @param comment 分页查询条件
     * @param rowIndex  limit第一个参数 起始位置
     * @param pageSize  limit第二个参数 每页数量
     * @return 分页查询的评论集合
     * @author 朱远飞
     * @create_time 2018年9月12日09:40:26
     */
    List<Comment> queryPagingReply(@Param("comment") Comment comment, @Param("rowIndex") int rowIndex,
                              @Param("pageSize") int pageSize);

    /**
     * @describe 分页查询评论
     * @param comment 分页查询条件
     * @param rowIndex  limit第一个参数 起始位置
     * @param pageSize  limit第二个参数 每页数量
     * @return 分页查询的评论集合
     * @author 朱远飞
     * @create_time 2018年9月12日09:40:26
     */
    List<Comment> queryPagingDESC(@Param("comment") Comment comment, @Param("rowIndex") int rowIndex,
                              @Param("pageSize") int pageSize);

}