package com.tlms.core.dao;

import com.tlms.core.domain.Test1TableMap;

public interface Test1TableMapMapper {

	int deleteByPrimaryKey(String id);

	int insert(Test1TableMap record);

	int insertSelective(Test1TableMap record);

	Test1TableMap selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Test1TableMap record);

	int updateByPrimaryKey(Test1TableMap record);
	
	Test1TableMap selectByClassname(String className);

}