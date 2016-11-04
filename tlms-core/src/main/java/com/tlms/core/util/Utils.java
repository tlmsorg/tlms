package com.tlms.core.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.test.postloan.RepayScheduleDetailVo;
import com.test.postloan.RepaySchedulePo;
import com.test.postloan.RepayScheduleVo;

public class Utils {
	private Logger logger = Logger.getLogger(Utils.class);
	/**
	 * 递归所有父类field
	 * @param obj 当前递归对象
	 * @param fieldList 所有field列表
	 */
	public static void getField(Class obj,List<Field> fieldList){
		Field[] fields = obj.getDeclaredFields();
		if(!obj.getName().equals("java.lang.Object")){
			for (Field field : fields) {
				field.setAccessible(true);
				fieldList.add(field);
			}
			Utils.getField(obj.getSuperclass(), fieldList);
		}
	}
	/**
	 * 获取对象所有field
	 * @param obj
	 * @return
	 */
	public static List<Field> getFieldList(Class obj){
		List<Field> fieldList = new LinkedList<Field>();
		Utils.getField(obj, fieldList);
		return fieldList;
	}
	
	public static void copyProperties(Object source,Object dest){
//		String jsonStr = JSONObject.toJSONString(source);
//		RepayScheduleDetailVo rscv  = (RepayScheduleDetailVo) JSONObject.parse(jsonStr);
//		System.out.println("dest:"+dest);
		Class srcCls = source.getClass();
		Class destCls = dest.getClass();
		List<Field> srcFieldList = Utils.getFieldList(srcCls);
		List<Field> destFieldList = Utils.getFieldList(destCls);
		Method[] srcMethods = srcCls.getMethods();
		Method[] destMethods = destCls.getMethods();
		List destList = new ArrayList();
		/*for (int i = 0; i < srcFieldList.size(); i++) {
			Field tempField = srcFieldList.get(i);
			try {
				System.out.println(tempField.get(srcCls));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		for (int i = 0; i < srcMethods.length; i++) {
			Method method = srcMethods[i];
//			System.out.println(method.getName());
		}
		/*
		for (int i = 0; i < destMethods.length; i++) {
			Method method = srcMethods[i];
		}*/
		for (Field srcField : srcFieldList) {
			String srcFieldName = srcField.getName();
			Class srcFieldType = srcField.getType();
			Object srcFieldValue = null;
			try {
				srcFieldValue = srcField.get(source);
//				System.out.println(srcFieldType+"|"+srcFieldValue);
				if((srcFieldType+"").equals("interface java.util.List")){
//					System.out.println(srcFieldType+"|"+srcFieldName+"|"+srcField.getGenericType());
					List listObj = (List) srcField.get(source);
//					System.out.println(listObj.size());
					for (Object object : listObj) {
						RepayScheduleDetailVo rsdv = new RepayScheduleDetailVo();
//						System.out.println("object:"+object);
						Utils.copyProperties(object, rsdv);
//						System.out.println("rsdv:"+JSONObject.toJSONString(rsdv));
						destList.add(rsdv);
					}
					Type gType  = srcField.getGenericType();
					ParameterizedType pType = (ParameterizedType) gType;
//					System.out.println("pType:"+pType);
					Type[] typs = pType.getActualTypeArguments();
					for (int i = 0; i < typs.length; i++) {
//						System.out.println(typs[i]);
					}
//					System.out.println("ttt:"+srcField.get(source));
				}
			} catch (Exception e) {
			}
			for (Field destField : destFieldList) {
				String destFieldName = destField.getName();
				Class destFieldType = destField.getType();
//				System.out.println(destFieldType+"|"+destFieldName);
				if(destFieldName.equals(srcFieldName)){
					for (int i = 0; i < destMethods.length; i++) {
						Method destMethod = destMethods[i];
						String methodName = destMethod.getName();
						if(("set"+destFieldName).toLowerCase().equals(methodName.toLowerCase())){
							try {
								if(!(srcFieldType+"").equals("interface java.util.List")){
									destMethod.invoke(dest, srcFieldValue+"");
								}else{
									destMethod.invoke(dest, destList);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			
			try {
//				System.out.println("***********"+srcCls.getMethod("getMortgageAmt", null).getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*for (Field fieldDest : destFieldList) {
				String nameDest = fieldDest.getName();
				try {
//					destCls.getMethod(nameSource, typeClass2).invoke(dest, args);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}*/
		}
//		System.out.println(JSONObject.toJSONString(srcFieldList));
	}
	
	/**
	 * 双精度浮点数转制定格式双进度浮点数
	 * tom 2016年11月2日
	 * @param number 数据源
	 * @param scale 小数位数
	 * @return 格式化后双精度浮点数（输入：number=123.1 scale=3 输出：123.1）
	 */
	public static Double formateDouble2Double(BigDecimal bigDecimal,int scale){
		return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}
	
	/**
	 * 双精度浮点数转制定格式双进度浮点数
	 * tom 2016年11月2日
	 * @param number 数据源
	 * @param scale 小数位数
	 * @return 格式化后双精度浮点数（输入：number=123.1 scale=3 输出：123.1）
	 */
	public static Double formateDouble2Double(double number,int scale){
		Double formateDouble = null;
		BigDecimal formater = new BigDecimal(number);
//		方法一：
		formateDouble = formater.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
//		方法二：
//		formateDouble = Math.round(number * Math.pow(10, new Double(scale)))/Math.pow(10, new Double(scale));
		return formateDouble;
	}
	
	/**
	 * 双精度浮点数转制定格式字符串
	 * tom 2016年11月2日
	 * @param number 数据源
	 * @param scale 小数位数
	 * @return 格式化后双精度浮点数（输入：number=123.1 scale=3 输出："123.100"）
	 */
	public static String formateDouble2String(double number,int scale){
		String formateDouble = "";
		BigDecimal formater = new BigDecimal(number);
		new Double("");
		formateDouble = formater.setScale(scale, BigDecimal.ROUND_HALF_EVEN).toString();
		return formateDouble;
	}
	
	
	/**
	 * 格式化双精度浮点数
	 * tom 2016年11月2日
	 * @param number 数据源
	 * @param place 小数位数
	 * @return 格式化后双精度浮点数
	 */
	public static double formateDouble(double number,int place){
//		System.out.println(number);
		double formateDouble = 0;
//		formateDouble = Math.round(number * Math.pow(10, place))/Math.pow(10, place);
//		DecimalFormat formater = new DecimalFormat("####0.##");
//		formater.setRoundingMode(RoundingMode.HALF_UP);
//		String numberStr = formater.format(number);
//		System.out.println(numberStr);
		/*try {
			number = formater.parse(numberStr).doubleValue();
			System.out.println(number);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//		formateDouble = Double.parseDouble(formater.format(number));
		BigDecimal formater = new BigDecimal(number);
		formateDouble = formater.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
		String formateStr = formater.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
//		System.out.println("double转字符串："+formateStr);
		
		BigDecimal d1 = new BigDecimal(900);
		BigDecimal d2 = new BigDecimal(100);
//		System.out.println(d1.add(d2));
		return formateDouble;
	}
	
	/**
	 * 获取首期还款日期
	 * tom 2016年11月2日
	 * @param loadDate
	 * @return
	 */
	public static Date getFirstRepayDate(Date loadDate){
		Date firstRepayDate = null;
		Calendar firstRepayCl = Calendar.getInstance();
		firstRepayCl.setTime(loadDate);
		firstRepayCl.add(Calendar.MONTH, 1);
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return firstRepayCl.getTime();
	}
	
	/**
	 * 生成还款日期
	 * tom 2016年11月2日
	 * @param loadDate
	 * @return
	 */
	public static Date generateRepayDate(Date loadDate,int period){
		Date firstRepayDate = null;
		Calendar firstRepayCl = Calendar.getInstance();
		for (int i = 1; i < period; i++) {
			firstRepayCl.setTime(loadDate);
			firstRepayCl.add(Calendar.MONTH, i);
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println((i+1)+"期还款日："+formater.format(firstRepayCl.getTime()));
		}
		return firstRepayCl.getTime();
	}
	
	/**
	 * 获取某年总天数
	 * tom 2016年11月2日
	 * @param year 年份
	 * @return 总天数
	 */
	public static int getDayCountOfYear(int year){
		SimpleDateFormat formater = new SimpleDateFormat("yyyy");
		Calendar cl = Calendar.getInstance();
		try {
			cl.setTime(formater.parse(year+""));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean isLeapYear = new GregorianCalendar().isLeapYear(cl.get(Calendar.YEAR));
		System.out.println("年份："+year+"|是否闰年："+isLeapYear);
		return isLeapYear?366:365;
	}
	
	public static String field2GetMethod(String fieldName){
		StringBuffer buffer = new StringBuffer();
		buffer.append("get");
		buffer.append(fieldName.substring(0, 1).toUpperCase());
		buffer.append(fieldName.substring(1, fieldName.length()));
		return buffer.toString();
	}
	public static String field2SetMethod(String fieldName){
		StringBuffer buffer = new StringBuffer();
		buffer.append("set");
		buffer.append(fieldName.substring(0, 1).toUpperCase());
		buffer.append(fieldName.substring(1, fieldName.length()));
		return buffer.toString();
	}
	
	/**
	 * 数据库列名转javabean属性名
	 * tom 2016年11月1日
	 * 输入：user_name_test 输出：userNameTest
	 */
	public static String col2Field(String colName){
		StringBuffer fieldNameBuf = new StringBuffer();
		String[] colNames = colName.split("_");
		for (int i = 0; i < colNames.length; i++) {
			String temp = colNames[i];
			if(i==0)
				fieldNameBuf.append(temp);
			else{
				fieldNameBuf.append(temp.substring(0, 1).toUpperCase());
				fieldNameBuf.append(temp.substring(1,temp.length()));
			}
		}
		return fieldNameBuf.toString();
	}
	
	/**
	 * 对象属性转换 
	 * @param propName  输入格式："myUserName"
	 * @return 返回格式：：MY_USER_NAME
	 */
	public static String field2Col(String propName){
		System.out.println("对象属性转换前："+propName);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < propName.length(); i++) {
			char c = propName.charAt(i);
			if(Character.isUpperCase(c)){
				sb.append("_"+Character.toLowerCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString().toUpperCase();
	}
}
