package com.tlms.core.groovy;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.map.HashedMap;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.omg.CORBA.portable.InputStream;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.Script;

public class GroovyUtil {
	
	private static Map<String,Object> context = new HashMap<String,Object>();
	private static GroovyClassLoader loader = new GroovyClassLoader();
	private static Map<String,Class<?>> classPool = new ConcurrentHashMap<String,Class<?>>();
	
	public static void bind(String key,String value) {
		context.put(key, value);
	}
	
	/**
	 * 执行字符串脚本
	 * @param scriptStr
	 * @return
	 */
	public Object runScriptStr(String scriptStr) {
		String className = String.valueOf(scriptStr.hashCode());
		System.out.println("className:"+className);
		Class<?> scriptClass = classPool.get(className);
		if(scriptClass == null) {
			scriptClass = loader.parseClass(scriptStr, className);
			classPool.put(className, scriptClass);
		}
		Binding binding = this.getBinging();
		Script script = InvokerHelper.createScript(scriptClass, binding);
		return script.run();
	}
	
	public Object runScript(Reader reader) {
		String className = String.valueOf(reader.hashCode());
		System.out.println("className:"+className);
		Class<?> scriptClass = classPool.get(className);
		if(scriptClass == null) {
//			scriptClass = loader.parseClass(scriptStr, className);
			scriptClass = loader.parseClass(reader, className);
			classPool.put(className, scriptClass);
		}
		Binding binding = this.getBinging();
		Script script = InvokerHelper.createScript(scriptClass, binding);
		return script.run();
	}
	
	public Binding getBinging() {
		Binding binding = new Binding();
		for(Entry<String, Object> entry:context.entrySet()) {
			binding.setVariable(entry.getKey(), entry.getValue());
		}
		return binding;
	}
	
	public static void main(String[] args) throws Exception {
		GroovyUtil hg = new GroovyUtil();
		GroovyUtil.bind("name", "王小二");
		GroovyUtil.bind("company", "潽金金融");
	
		String scriptStr = "System.out.println(\"姓名:\"+name + \",公司:\"+company)";
		hg.runScriptStr(scriptStr);
		
	}
}
