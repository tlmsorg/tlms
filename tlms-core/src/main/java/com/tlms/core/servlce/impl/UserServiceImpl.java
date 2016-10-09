package com.tlms.core.servlce.impl;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.tlms.core.annotion.AnnotationTest;
import com.tlms.core.dao.Test1Mapper;
import com.tlms.core.dao.Test2Mapper;
import com.tlms.core.domain.Test1;
import com.tlms.core.domain.Test2;
import com.tlms.core.service.IUserSerivice;

@Service
@Transactional
public class UserServiceImpl implements IUserSerivice {
	@Autowired
	private Test1Mapper test1Mapper;
	@Autowired
	private Test2Mapper test2Mapper;
	
	public void userUpd(Test1 test1,String accountId) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl->userUpd，开始更新test1表");
//		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		if(test1Mapper.updateByPrimaryKeySelective(test1) <= 0){
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("test1未更新");
		}else{
			System.out.println("test1更新成功");
		}
//		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//		throw new RuntimeException("ttttttttttt");
	}
	
	public void userUpd2(Test2 test2,String accountId) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl->userUpd2,开始更新test2表");
		if(test2Mapper.updateByPrimaryKey(test2) <=0 ){
//		if(test2Mapper.insert(test2) <=0 ){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("test2未更新");
		}else{
			System.out.println("test2更新成功");
		}
		
	}

	@AnnotationTest
	public void userUpd3(Test2 test2,String accountId) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl->userUpd3,开始更新test2表");
//		if(test2Mapper.updateByPrimaryKey(test2) <=0 ){
		if(test2Mapper.insert(test2) <=0 ){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("test2未更新");
		}else{
			System.out.println("test2更新成功");
		}
	}


}
