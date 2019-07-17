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
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class GroovyUtil {
	
	private static Map<String,Object> context = new HashMap<String,Object>();
	private static GroovyClassLoader loader = new GroovyClassLoader();
	//class池
	private static Map<String,Class<?>> classPool = new ConcurrentHashMap<String,Class<?>>();
	//className池
	private static Map<String,Script> scriptPool = new ConcurrentHashMap<String,Script>();
	
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
	
	/**
	 * 
	 * @param reader
	 * @return
	 */
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
	
	/**
	 * scriptShell方式运行groovy脚本
	 * @param scriptStr
	 * @return
	 */
	public Object runWithShell(String scriptStr) {
		GroovyShell groovyShell = new GroovyShell();
		String scriptName = String.valueOf(scriptStr.hashCode());
		Script script = this.getScript(scriptName);
		if(script == null) {
			System.out.println(scriptName);
			script = groovyShell.parse(scriptStr, scriptName);
			this.scriptPool.put(scriptName, script);
		}
		script.setProperty("name", "王小二");
		script.setProperty("company", "潽金金融");
		script.run();
		return null;
	}
	public Script getScript(String scriptName) {
		return this.scriptPool.get(scriptName);
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
//		hg.runScriptStr(scriptStr);
		for(int i =0;i< 100000;i++) {
			System.out.println(i);
			hg.runWithShell(scriptStr);
		}
	}
}
