package com.sys.service;

public interface TokenManager {
	String createToken(String userId);
	boolean checkToken(String token);
}
