/**
 * tom 2016年10月27日
 */
package com.tlms.timetest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tlms.core.enumeration.EIntervalMode;
import com.tlms.core.util.Utils;

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
		Calendar afterCl = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
		
		
		String beginDateStr = "2012-02-01 12:11:15";
		String endDateStr = "2012-02-05 12:11:15";
		Calendar beginCl = Calendar.getInstance();
		Calendar endCl = Calendar.getInstance();
		
		try {
			beginCl.setTime(formater.parse(beginDateStr));
			endCl.setTime(formater.parse(endDateStr));
//			System.out.println(beginCl.getTimeInMillis());
//			System.out.println(endCl.getTimeInMillis());
			System.out.println(Utils.getTimeInterval(formater.parse(beginDateStr), formater.parse(endDateStr), EIntervalMode.DAYS));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
