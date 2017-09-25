package com.tlms.core.service;

import com.tlms.core.domain.SysUser;
import com.tlms.core.vo.TokenVo;

public interface IAuthService {
	/**
	 * 创建webtoken字符串
	 * @return
	 */
	public TokenVo createJwt(SysUser user);
	/**
	 * 校验webtoken字符串
	 * @param jwt
	 * @return
	 */
	public TokenVo checkJwt(String jwt);
}
