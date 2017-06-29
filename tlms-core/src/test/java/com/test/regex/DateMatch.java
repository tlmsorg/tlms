package com.test.regex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;

public class DateMatch {

	/**
	 * 字符串转日期
	 * tom 2016年11月7日
	 * @param date
	 * @param formateStr
	 * @return
	 */
	public static Date formateString2Date(String date,String formateStr){
		String[] patterns = new String[]{"^\\d{4}$","^\\d{4}-\\d{2}$","^\\d{4}-\\d{2}-\\d{2}$","^\\d{4}-\\d{2}-\\d{2} \\d{2}$","^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$","^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$","^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d*$"};
		String[] dateFormats = new String[]{"yyyy","yyyy-MM","yyyy-MM-dd","yyyy-MM-dd HH","yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss.S"};
		Date dateRet = null;
		for (int i = 0;i < patterns.length;i++) {
			Pattern pt = Pattern.compile(patterns[i]);
			Matcher matcher = pt.matcher(date);
			if(matcher.find()){
				SimpleDateFormat sdfParse = new SimpleDateFormat(dateFormats[i]);
				SimpleDateFormat sdfFormat = new SimpleDateFormat(formateStr);
				try {
					Date dateSrc = sdfParse.parse(date);
					dateRet = dateSrc;
					System.out.println(sdfFormat.format(dateSrc));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return dateRet;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateMatch.formateString2Date("2017-05-15 15:12:12.888888", "yyyyMMddHHmmddSSS");
		
	}

}
