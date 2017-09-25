package com.tlms.core.dao;

import org.apache.ibatis.annotations.Param;

import com.tlms.core.domain.SysUser;

public interface SysUserMapper {

	int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    SysUser SelectByUseridAndPasswd(@Param("userId") String userId,@Param("passwd") String passwd);
}