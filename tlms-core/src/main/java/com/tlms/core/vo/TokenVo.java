package com.tlms.core.vo;

import com.tlms.core.domain.SysUser;

public class TokenVo extends SysUser{
	private String token;
	private Long expireTime;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	
}
