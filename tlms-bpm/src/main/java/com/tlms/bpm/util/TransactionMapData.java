package com.tlms.bpm.util;

import java.util.HashMap;
import java.util.Map;
/**
 * @company 潽金金融
 * @project_name gds-pdf
 * @ClassName TranctionMapData
 * @Description 交易上下文
 * @author 160068
 * @date 2019年7月8日 上午10:33:32
 * @version V1.0
 */
public class TransactionMapData {
	private Map<String,Object> map = null;
	private static ThreadLocal<TransactionMapData> threadLocal = new ThreadLocal<TransactionMapData>();
	/**
	 * 获取交易上下文实例
	 * 160068
	 * 2019年5月8日 下午4:35:05
	 * @return
	 */
	public static TransactionMapData getInstance() {
		TransactionMapData tmd = threadLocal.get();
		if(tmd == null) {
			tmd = new TransactionMapData();
			tmd.map = new HashMap<String,Object>();
			threadLocal.set(tmd);
		}
		return tmd;
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
