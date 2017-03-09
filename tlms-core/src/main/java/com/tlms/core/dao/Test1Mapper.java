package com.tlms.core.dao;

import com.tlms.core.domain.Test1;

public interface Test1Mapper {
    int deleteByPrimaryKey(String id);

    int insert(Test1 record);

    int insertSelective(Test1 record);

    Test1 selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Test1 record);

    int updateByPrimaryKey(Test1 record);
}