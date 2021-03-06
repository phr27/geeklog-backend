package com.geeklog.mapper;

import com.geeklog.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 朱远飞
 * @create_time 2018年9月9日15:14:59
 * @describe User Dao
 */
public interface UserMapper {
    /**
     * @describe 删除指定主键的用户
     * @param userId 用户主键
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:15:54
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * @describe 添加新的用户
     * @param user 要添加的用户的信息在里面
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:16:43
     */
    int insert(User user);

    /**
     * @describe 查询指定主键的用户
     * @param userId 用户主键
     * @return  User
     * @author 朱远飞
     * @create_time 2018年9月9日15:17:25
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * @describe 查询出所有用户
     * @return 所有用户的列表
     * @author 朱远飞
     * @create_time 2018年9月9日15:18:02
     */
    List<User> selectAll();

    /**
     * @describe 更新用户
     * @param user 指定要更新的user主键，其他信息表示要更新成的数据
     * @return  受影响的行数
     * @author 朱远飞
     * @create_time 2018年9月9日15:18:57
     */
    int updateByPrimaryKey(User user);

    /**
     * @describe 以一个条件来查询用户数量
     * @param user 查询条件
     * @return 用户数量
     * @author 朱远飞
     * @create_time 2018年9月9日16:03:34
     */
    int queryNumOfUsers(User user);

    /**
     * @describe 分页查询
     * @param user 分页查询条件
     * @param rowIndex  limit第一个参数 起始位置
     * @param pageSize  limit第二个参数 每页数量
     * @return 分页查询的用户集合
     * @author 朱远飞
     * @create_time 2018年9月9日16:17:48
     */
    List<User> queryPaging(@Param("user") User user, @Param("rowIndex") int rowIndex,
                           @Param("pageSize") int pageSize);

    /**
     * @describe 用户登录
     * @param username 用户名
     * @param password 密码
     * @return User
     * @author 朱远飞
     * @create_time 2018年9月9日16:31:19
     */
    User login(@Param("username") String username, @Param("password") String password);

    /**
     * @describe 可能会有这样的需求，前端发AJAX判断，该账号是否已经存在
     * @param username  账号
     * @return  User
     * @author 朱远飞
     * @create_time 2018年9月9日16:40:26
     */
    User queryUsername(String username);

    /**
     * @author 潘浩然
     * 创建时间 2018/09/20
     * 功能：将指定用户的 avatar 设为 null
     */
    int updateAvatarNull(@Param("userId") int userId);

}