package com.tlms.core.service;

import com.tlms.core.domain.Test1;
import com.tlms.core.domain.Test2;

public interface IUserService {
	public void userUpd(Test1 test1,String accountId);
	public void userUpd2(Test2 test2,String accountId);
	public void userUpd3(Test2 test2,String accountId);
	public boolean userSignIn(String userId,String passwd);
	public boolean userSignOut(String userId);
	public boolean userValid(String token);
}
