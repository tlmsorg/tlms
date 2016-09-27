package com.tlms.core.dao;

import com.tlms.core.domain.Test1OperHis;

public interface Test1OperHisMapper {
    int deleteByPrimaryKey(String id);

	int insert(Test1OperHis record);

	int insertSelective(Test1OperHis record);

	Test1OperHis selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Test1OperHis record);

	int updateByPrimaryKey(Test1OperHis record);

	

}