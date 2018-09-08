package com.geeklog.mapper;

import com.geeklog.domain.Authority;
import java.util.List;

public interface AuthorityMapper {
    int deleteByPrimaryKey(Integer autnorityId);

    int insert(Authority record);

    Authority selectByPrimaryKey(Integer autnorityId);

    List<Authority> selectAll();

    int updateByPrimaryKey(Authority record);
}