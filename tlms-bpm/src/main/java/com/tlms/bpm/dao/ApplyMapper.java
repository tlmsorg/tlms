package com.tlms.bpm.dao;

import com.tlms.bpm.domain.Apply;

public interface ApplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);
}