package com.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

public class NumberRegex {

	/**
	 * 日期转字符串
	 * tom 2016年11月7日
	 * @param date
	 * @param formateStr
	 * @return
	 */
	public static boolean validGpsId(String gpsId){
		Pattern pattern = Pattern.compile("^[a-z0-9A-Z]{10,20}$");
		Matcher matcher = pattern.matcher(gpsId);
		Assert.isTrue(matcher.matches(),"GPS编码【"+gpsId+"】不合法,只能输入数字[0-9]、字母[a-zA-Z],最小位数：10位");
		Pattern pattern2 = Pattern.compile("(\\w)(\\1){9}");
        Matcher matcher2 = pattern2.matcher(gpsId);
        Assert.isTrue(!matcher2.find(), "GPS编码不能为同一个字符");
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		String str = "555555555555545434343434345454";
		Pattern pattern = Pattern.compile("^[a-z0-9A-Z]{10,20}$");
		Matcher matcher = pattern.matcher(str);
		
		Pattern pattern2 = Pattern.compile("^(\\w)(\\1)$");
		Matcher matcher2 = pattern2.matcher(str);
		System.out.println(matcher.matches());
		System.out.println(matcher2.matches());
		
		/**
		 * 匹配连续出现10次及以上字符
		 */
		Pattern pattern3 = Pattern.compile("(\\w)(\\1){9}");
        Matcher matcher3 = pattern3.matcher("11111111111111");
        System.out.println(matcher3.find());
        while (matcher3.find()) {
                System.out.print(matcher3.group()+"|");
        }
        
        NumberRegex.validGpsId("99999999");
	}
}
