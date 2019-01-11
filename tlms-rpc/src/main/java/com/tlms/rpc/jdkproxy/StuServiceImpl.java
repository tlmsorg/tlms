package com.tlms.rpc.jdkproxy;

import org.mvel2.ast.Safe;

public class StuServiceImpl implements IStuService,Safe{

	@Override
	public void saveStu(StuPojo stu) {
		System.out.println("StuServiceImpl--->saveStu:"+stu.getName());
	}

	@Override
	public void doSomeThing() {
		System.out.println("doSomeThing");
	}

}
