package com.geeklog.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 潘浩然
 * 创建时间：2018年9月5日
 * 功能：仅用于测试容器编排是否正常，数据库能否正常连接以及数据库是否乱码，正式环境删除
 */
@RestController
@CrossOrigin
public class DeployTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/deploy/test")
    public List<String> deployTest() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> titles = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT title FROM article LIMIT 0, 5");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                titles.add(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return titles;
    }
}
