package com.tlms.core.groovy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import com.alibaba.fastjson.JSONObject;

public class GroovyMain {
	public static void main(String[] args) throws Exception {
		GroovyUtil hg = new GroovyUtil();
		GroovyUtil.bind("name", "王小二");
		GroovyUtil.bind("company", "潽金金融");
		/*
		for (int i = 0; i < 10000000; i++) {
			String scriptStr = "\"姓名:\"+name + \",公司:\"+company";
			Object objRet = hg.runScript(scriptStr);
			System.out.println("脚本执行结果："+i+","+objRet);
		}
		*/
		
		StringBuffer sb = new StringBuffer();
		JSONObject jsonObj = new JSONObject();
		
//		String packageName = JSONObject.class.getPackage().getName();
		String formatePackageName = MessageFormat.format("import {0}.*;", JSONObject.class.getPackage().getName());
		sb.append(formatePackageName);
		
		String packageName = MessageFormat.format("import {0}.*;", TransactionMapData.class.getPackage().getName());
		sb.append(packageName);
		
		System.out.println(sb.toString());
		String allPackage = sb.toString();
		System.out.println(allPackage);
		String scriptStr = allPackage + "JSONObject json = new JSONObject();json.put(\"name\", \"tom\");TransactionMapData tmd = TransactionMapData.getInstance();tmd.set(\"tenantName\", \"变量池中设置的承租人姓名\"); return json;";
		Object objRet = hg.runScriptStr(scriptStr);
		System.out.println("脚本执行结果："+objRet);
		TransactionMapData tmd = TransactionMapData.getInstance();
		System.out.println("脚本中设置的变量池值："+tmd.get("tenantName"));
		while(true) {
			File file = new File("d:\\groovyScript.txt");
			System.out.println(file.hashCode());
			FileReader reader = new FileReader(new File("groovyScript.txt"));
//			hg.runScript(reader);
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			StringBuffer lineSb = new StringBuffer();
			while((line = br.readLine()) != null) {
				lineSb.append(line);
			}
			try {
				System.out.println("文件内容:"+lineSb.toString());
				objRet = hg.runScriptStr(lineSb.toString());
				System.out.println(objRet);
				tmd = TransactionMapData.getInstance();
				System.out.println("脚本中设置的变量池值："+tmd.get("tenantName"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Thread.currentThread().sleep(10000);
		}
		
	}
}
