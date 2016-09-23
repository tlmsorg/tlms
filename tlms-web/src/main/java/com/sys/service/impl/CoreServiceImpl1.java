package com.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Transactional
public class CoreServiceImpl1 {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void rollback_test(){
		System.out.println("CoreServiceImpl1->rollback_test");
		String sql = "insert into test1(userId,couse,point,id) values('1','1','1','1')";
		jdbcTemplate.update(sql);
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
}
