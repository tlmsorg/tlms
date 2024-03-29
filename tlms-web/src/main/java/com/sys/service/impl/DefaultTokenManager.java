package com.sys.service.impl;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.sys.service.TokenManager;


public class DefaultTokenManager implements TokenManager {

	private HashMap<String,String> tokenMap = new HashMap<String,String>();
	@Override
	public String createToken(String userId) {
		String id = UUID.randomUUID().toString();
		tokenMap.put(id, userId);
		return id;
	}

	@Override
	public boolean checkToken(String token) {
		return !StringUtils.isEmpty(token) && tokenMap.containsKey(token);
	}

}
