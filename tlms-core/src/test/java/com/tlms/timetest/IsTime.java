package com.tlms.timetest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IsTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar cl = Calendar.getInstance();
		Object date = cl.getTime();
//		Object date = "";
		System.out.println("date instanceof Date:"+(date instanceof Date));
		if (date instanceof Date) {
			System.out.println("date为Date实例");
		}
		String str = "2016-06-07 10:11:12";
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		try {
			System.out.println(sdf.parse(str)+"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
