package com.geeklog.mapper;

import com.geeklog.domain.Star;
import java.util.List;

public interface StarMapper {
    int deleteByPrimaryKey(Integer starId);

    int insert(Star record);

    Star selectByPrimaryKey(Integer starId);

    List<Star> selectAll();

    int updateByPrimaryKey(Star record);
}