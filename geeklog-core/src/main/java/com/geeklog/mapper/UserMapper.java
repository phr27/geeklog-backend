package com.geeklog.mapper;

import com.geeklog.domain.User;
import java.util.List;

/**
 * 作者：朱远飞
 * 创建时间：2018年9月9日15:14:59
 * 说明：User Dao
 */
public interface UserMapper {
    /**
     * 删除指定主键的用户
     * @param userId 用户主键
     * @return  受影响的行数
     * 创建时间：2018年9月9日15:15:54
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 添加新的用户
     * @param user 要添加的用户的信息在里面
     * @return  受影响的行数
     * 创建时间：2018年9月9日15:16:43
     */
    int insert(User user);

    /**
     * 查询指定主键的用户
     * @param userId 用户主键
     * @return  User
     * 创建时间：2018年9月9日15:17:25
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 查询出所有用户
     * @return 所有用户的列表
     * 创建时间：2018年9月9日15:18:02
     */
    List<User> selectAll();

    /**
     * 更新用户
     * @param user 指定要更新的user主键，其他信息表示要更新成的数据
     * @return  受影响的行数
     * 创建时间：2018年9月9日15:18:57
     */
    int updateByPrimaryKey(User user);
}