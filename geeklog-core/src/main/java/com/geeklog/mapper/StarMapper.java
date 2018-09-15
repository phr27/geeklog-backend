package com.geeklog.mapper;

import com.geeklog.domain.Star;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 *  @author 朱远飞
 *  @create_time 2018年9月9日15:11:30
 *  @describe Star DAO
 */
public interface StarMapper {
    /**
     * @describe 删除指定主键的行记录
     * @param starId 主键
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:15:54
     */
    int deleteByPrimaryKey(Integer starId);

    /**
     * @describe 添加新的记录
     * @param star 要添加的信息在里面
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:16:43
     */
    int insert(Star star);

    /**
     * @describe 查询指定主键的行记录
     * @param starId 主键
     * @return  Star
     * @author 朱远飞
     * @create_time 2018年9月9日15:17:25
     */
    Star selectByPrimaryKey(Integer starId);

    /**
     * @describe 查询出所有记录
     * @return 所有记录的列表
     * @author 朱远飞
     * @create_time 2018年9月9日15:18:02
     */
    List<Star> selectAll();

    /**
     * @describe 更新记录
     * @param star 指定要更新的主键，其他信息表示要更新成的数据
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:18:57
     */
    int updateByPrimaryKey(Star star);

    /**
     * @describe 根据用户id和文章id查询star
     * @param userId 用户id
     * @param articleId 文章id
     * @return Star
     * @author 朱远飞
     * @create_time 2018年9月15日20:02:42
     */
    Star queryByUserIdAndArticleId(@Param("userId") int userId, @Param("articleId") int articleId);

}