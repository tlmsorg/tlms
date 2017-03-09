package com.tlms.core.util;

public interface TokenManager {
	String createToken(String userId);
	boolean checkToken(String token);
}
