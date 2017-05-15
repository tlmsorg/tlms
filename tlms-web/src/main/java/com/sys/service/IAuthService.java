package com.sys.service;

import org.springframework.stereotype.Service;

public interface IAuthService {
	public String createJwt();
	public String checkJwt(String jwt);
}
