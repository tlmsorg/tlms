package com.tlms.core.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	/**
	 * @Description 正则表达式 group测试
	 * 
	 * @CreateTime 160068 2019年11月23日
	 */
	public void groupTest() {
		String str = "Hello,World! in Java.";
		Pattern pattern = Pattern.compile("W(or)(ld!)");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			System.out.println("Group 0:" + matcher.group(0));// 得到第0组——整个匹配
			System.out.println("Group 1:" + matcher.group(1));// 得到第一组匹配——与(or)匹配的
			System.out.println("Group 2:" + matcher.group(2));// 得到第二组匹配——与(ld!)匹配的，组也就是子表达式
			System.out.println("Start 0:" + matcher.start(0) + " End 0:" + matcher.end(0));// 总匹配的索引
			System.out.println("Start 1:" + matcher.start(1) + " End 1:" + matcher.end(1));// 第一组匹配的索引
			System.out.println("Start 2:" + matcher.start(2) + " End 2:" + matcher.end(2));// 第二组匹配的索引
			System.out.println(str.substring(matcher.start(0), matcher.end(1)));// 从总匹配开始索引到第1组匹配的结束索引之间子串——Wor
		}
	}
	
	public static void main(String[] args) throws Exception {
		String path = RegexTest.class.getResource("/").getPath();
		System.out.println(path);
		File file = new File(path + File.separator + "RegexSrc.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String regexStr = br.readLine();
		System.out.println(regexStr);
		
		Pattern pattern = Pattern.compile("@RequestMapping *\\( *(value=|)*\"/.*\" *\\)");
		Matcher matcher = pattern.matcher(regexStr);
		System.out.println(matcher.matches());
	}

}
