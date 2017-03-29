package com.sys.service;

import org.springframework.stereotype.Service;
import com.pujjr.utils.TransactionMapData;
@Service
public interface IAopTest {
	public String aopTest(TransactionMapData tmd);
}
