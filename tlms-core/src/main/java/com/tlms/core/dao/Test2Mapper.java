package com.tlms.core.dao;

import com.tlms.core.domain.Test2;

public interface Test2Mapper {
    int deleteByPrimaryKey(String id);

	int insert(Test2 record);

	int insertSelective(Test2 record);

	Test2 selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Test2 record);

	int updateByPrimaryKey(Test2 record);

	


}