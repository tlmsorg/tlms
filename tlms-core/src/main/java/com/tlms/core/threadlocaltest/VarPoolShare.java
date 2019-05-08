package com.tlms.core.threadlocaltest;

import java.util.HashMap;
import java.util.Map;

public class VarPoolShare {
	private static VarPoolShare varPool = null;
	private Map<String,Object> map = null;
//	private static ThreadLocal<VarPoolShare>  threadLocal = new ThreadLocal<VarPoolShare>();
	
	private VarPoolShare() {
		
	}
	
	public static VarPoolShare getInstanceNormal() {
		String currThreadName = "线程："+Thread.currentThread().getName();
		if(varPool == null) {
			System.out.println("变量池：VarPoolShare----"+currThreadName+"-->varPool为null");
			varPool = new VarPoolShare();
			varPool.map = new HashMap<String,Object>();
		}else {
			System.out.println("变量池：VarPoolShare----"+currThreadName+"-->varPool已存在");
		}
		return varPool;
	}
	
	public void put(String key,Object value) {
		varPool.map.put(key, value);
	}
	
	public Object get(String key) {
		return varPool.map.get(key);
	}
}
