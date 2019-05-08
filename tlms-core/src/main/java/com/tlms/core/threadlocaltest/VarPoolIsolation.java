package com.tlms.core.threadlocaltest;

import java.util.HashMap;
import java.util.Map;

/**
 * 变量池：线程隔离作用域
 * @author 160068
 * 2019年1月18日 下午3:35:20
 */
public class VarPoolIsolation {
//	private static VarPoolIsolation varPool = null;
	private Map<String,Object> map = null;
	private static ThreadLocal<VarPoolIsolation>  threadLocal = new ThreadLocal<VarPoolIsolation>();
	
	private VarPoolIsolation() {
		
	}

	public static VarPoolIsolation getInstanceIsolation() {
		VarPoolIsolation varPool = threadLocal.get();
		String currThreadName = "线程："+Thread.currentThread().getName();
		if(varPool == null) {
			System.out.println("变量池：VarPoolIsolation----"+currThreadName+"-->varPool为null");
			varPool = new VarPoolIsolation();
			varPool.map = new HashMap<String,Object>();
			threadLocal.set(varPool);
		}else {
			System.out.println("变量池：VarPoolIsolation----"+currThreadName+"-->varPool已存在");
		}
		return varPool;
	}
	
	public void put(String key,Object value) {
		this.map.put(key, value);
	}
	
	public Object get(String key) {
		return this.map.get(key);
	}
}
