package com.geeklog.mapper;

import com.geeklog.domain.Forbidden;
import java.util.List;

public interface ForbiddenMapper {
    int deleteByPrimaryKey(Integer forbiddenId);

    int insert(Forbidden record);

    Forbidden selectByPrimaryKey(Integer forbiddenId);

    List<Forbidden> selectAll();

    int updateByPrimaryKey(Forbidden record);
}