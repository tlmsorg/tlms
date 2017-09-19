package com.tlms.core.dao;

import java.util.List;

import com.tlms.core.domain.SysGroup;

public interface SysGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysGroup record);

    int insertSelective(SysGroup record);

    SysGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysGroup record);

    int updateByPrimaryKey(SysGroup record);
    
    List<SysGroup> selectAllGroup();
}