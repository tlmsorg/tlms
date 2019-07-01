package com.tlms.core.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

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
