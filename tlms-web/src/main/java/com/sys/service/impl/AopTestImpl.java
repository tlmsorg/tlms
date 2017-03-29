package com.sys.service.impl;


import org.springframework.stereotype.Service;

import com.pujjr.utils.TransactionMapData;
import com.sys.service.IAopTest;

@Service
public class AopTestImpl implements IAopTest {

	@Override
	public String aopTest(TransactionMapData tmd) {
		System.out.println("aopTest");
		return "aopTest";
	}

}
