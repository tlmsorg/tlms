package com.tlms.core.groovy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.groovy.runtime.InvokerHelper;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;

/**
 * 原帖地址：https://blog.csdn.net/qq_21508059/article/details/81842886
 * @company 潽金金融
 * @project_name tlms-core
 * @ClassName GroovyUtilBak
 * @Description
 * @author 160068
 * @date 2019年7月5日 下午4:17:40
 * @version V1.0
 */
public class GroovyUtilBak {
	/** 上下文环境 */
	private static final Map<String, Object> context = new HashMap<String, Object>();
	private static GroovyClassLoader gcl = new GroovyClassLoader();
	/** groovy生成的类缓存 */
	private static Map<String, Class<?>> classPool = new ConcurrentHashMap<String, Class<?>>();
	
	/**
	 * 绑定环境变量
	 */
	public static void bind(String key, Object value) {
		context.put(key, value);
	}
	
	public static void unBind(String key) {
		context.remove(key);
	}
	
	/**
	 * 执行groovy脚本
	 */
	public static void runScript(String scriptStr) {
		Binding binding = bindVariables();
		String className = genClassName(scriptStr);
		Class<?> clazz = classPool.get(className);
		if (clazz == null) {
			clazz = gcl.parseClass(scriptStr, className);
			classPool.put(className, clazz);
		}
		Script script = InvokerHelper.createScript(clazz, binding);
		Object obj = script.run();
		System.out.println(obj);
	}
	
	private static String genClassName(String scriptStr) {
		return String.valueOf(scriptStr.hashCode());
	}
	
	private static Binding bindVariables() {
		Binding binding = new Binding();
		for (Entry<String, Object> entry : context.entrySet()) {
			binding.setVariable(entry.getKey(), entry.getValue());
		}
		return binding;
	}
	
	public static void main(String[] args) {
		GroovyUtilBak.bind("name", "jack");
		GroovyUtilBak.bind("company", "otaku");
		GroovyUtilBak.runScript("name + company");
	}

}
