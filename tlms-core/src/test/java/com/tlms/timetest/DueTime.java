/**
 * tom 2016年10月27日
 */
package com.tlms.timetest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tom
 *
 */
public class DueTime {

	/**
	 * @param date 给定日期
	 * @param interval 间隔月份，示例：5：5个月以后;-6:6个月以前
	 * @return 间隔后日期
	 */
	public static Date getDateAfterMonth(Date date,int interval){
		String afterYear = "";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, interval);
		return calendar.getTime();
	}
	/**
	 * @param args
	 * tom 2016年10月27日
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dateStr = "2012-02-11 12:11:15";
		Calendar calendar = Calendar.getInstance();
		Calendar afterCl = calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		try {
			Date date = formater.parse(dateStr);
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 5);
			Date dateAfter = calendar.getTime();
			afterCl.setTime(dateAfter);
			System.out.println(formater.format(afterCl.getTime()));
			
			System.out.println(afterCl.get(Calendar.YEAR));
			System.out.println(afterCl.get(Calendar.MONTH)+1);
			System.out.println(afterCl.get(Calendar.DAY_OF_MONTH));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
