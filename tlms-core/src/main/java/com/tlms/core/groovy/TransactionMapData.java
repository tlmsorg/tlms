package com.tlms.core.groovy;

import java.util.HashMap;
import java.util.Map;
/**
 * 打印命令上下文对象
 * @author 160068
 * 2019年5月8日 下午4:29:17
 */
public class TransactionMapData {
	//申请单号
	private String appId;
	//系统上下文路径
	private String contextPath;
	//合同标识
	private String contractKey;
	
	private Map<String,Object> map = null;
	
	private static ThreadLocal<TransactionMapData> threadLocal = new ThreadLocal<TransactionMapData>();
	
	/**
	 * 获取文件命令上线文实例
	 * 160068
	 * 2019年5月8日 下午4:35:05
	 * @return
	 */
	public static TransactionMapData getInstance() {
		TransactionMapData cmdContext = threadLocal.get();
		if(cmdContext == null) {
			cmdContext = new TransactionMapData();
			cmdContext.map = new HashMap<String,Object>();
			threadLocal.set(cmdContext);
		}
		return cmdContext;
	}
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getContractKey() {
		return contractKey;
	}

	public void setContractKey(String contractKey) {
		this.contractKey = contractKey;
	}
	
	public Object get(String key) {
		return this.map.get(key);
	}
	
	public void set(String key,Object value) {
		this.map.put(key, value);
	}
	
	public Map<String,Object> getMap() {
		return this.map;
	}
	
}
