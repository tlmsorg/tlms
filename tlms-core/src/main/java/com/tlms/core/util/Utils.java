package com.tlms.core.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tlms.core.enumeration.EIntervalMode;
public class Utils {
	private Logger logger = Logger.getLogger(Utils.class);
	
	
	/**
	 * bean 转map
	 * @param obj
	 * @return
	 */
	public Map<String,Object> bean2map(Object obj){
		if(obj == null)
			return null;
		Map<String,Object> beanMap = new HashMap<String,Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : pd) {
				String name = propertyDescriptor.getName();
				Method method = propertyDescriptor.getReadMethod();
				Object value = method.invoke(obj);
				if(!"class".equals(name)){
					beanMap.put(name, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanMap;
	}
	
	/**
	    * 数字金额转中文大写 15230.21元处显示0 ，存在bug
	    * @param money 数字金额
	    * @return 中文大写金额
	    */
	    public static String number2Chn2(double money) {
	    	/**
		     * 汉语中数字大写
		     */
		    final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
		            "伍", "陆", "柒", "捌", "玖" };
		    /**
		     * 汉语中货币单位大写，这样的设计类似于占位符
		     */
		    final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
		            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
		            "佰", "仟" };
		    /**
		     * 特殊字符：整
		     */
		    final String CN_FULL = "整";
		    /**
		     * 特殊字符：负
		     */
		    final String CN_NEGATIVE = "负";
		    /**
		     * 金额的精度，默认值为2
		     */
		    final int MONEY_PRECISION = 2;
	        /**
	        * 特殊字符：零元整
	        */
	        final String CN_ZEOR_FULL = "零元" + CN_FULL;
	    	
	    	BigDecimal numberOfMoney = new BigDecimal(money);
	        StringBuffer sb = new StringBuffer();
	        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
	        // positive.
	        int signum = numberOfMoney.signum();
	        // 零元整的情况
	        if (signum == 0) {
	            return CN_ZEOR_FULL;
	        }
	        //这里会进行金额的四舍五入
	        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
	                .setScale(0, 4).abs().longValue();
	        // 得到小数点后两位值
	        long scale = number % 100;
	        int numUnit = 0;
	        int numIndex = 0;
	        boolean getZero = false;
	        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
	        if (!(scale > 0)) {
	            numIndex = 2;
	            number = number / 100;
	            getZero = true;
	        }
	        if ((scale > 0) && (!(scale % 10 > 0))) {
	            numIndex = 1;
	            number = number / 10;
	            getZero = true;
	        }
	        int zeroSize = 0;
	        while (true) {
	            if (number <= 0) {
	                break;
	            }
	            // 每次获取到最后一个数
	            numUnit = (int) (number % 10);
	            if (numUnit > 0) {
	                if ((numIndex == 9) && (zeroSize >= 3)) {
	                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
	                }
	                if ((numIndex == 13) && (zeroSize >= 3)) {
	                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
	                }
	                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
	                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
	                getZero = false;
	                zeroSize = 0;
	            } else {
	                ++zeroSize;
	                if (!(getZero)) {
	                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
	                }
	                if (numIndex == 2) {
	                    if (number > 0) {
	                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
	                    }
	                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
	                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
	                }
	                getZero = true;
	            }
	            // 让number每次都去掉最后一个数
	            number = number / 10;
	            ++numIndex;
	        }
	        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
	        if (signum == -1) {
	            sb.insert(0, CN_NEGATIVE);
	        }
	        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
	        if (!(scale > 0)) {
	            sb.append(CN_FULL);
	        }
	        return sb.toString();
	    }
	
	/**
	    * 数字金额转中文大写
	    * @param money 数字金额
	    * @return 中文大写金额
	    */
	public static String number2Chn(double money){
    	String number = money + "";
    	/**
         * 人民币大写单位制
         */
    	HashMap<Integer, String> dws = new HashMap<Integer, String>();
        dws.put(-2, "分");
        dws.put(-1, "角");
        dws.put(0, "元");
        dws.put(1, "拾");
        dws.put(2, "佰");
        dws.put(3, "仟");
        dws.put(4, "万");//
        dws.put(5, "拾");
        dws.put(6, "佰");
        dws.put(7, "仟");
        dws.put(8, "亿");//
        dws.put(9, "拾");
        dws.put(10, "佰");
        dws.put(11, "仟");
        dws.put(12, "万");
        /**
         * 数字对应的中文
         */
        String[] jes = new String[]{"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    	
        StringBuffer su = new StringBuffer();
        // 整数部分
        number = delInvalidZero(number);
        String str = null;
        // 小数部分
        String decimal = null;
        if(number.contains(".")){
            // 截取整数位
            str = number.split("\\.")[0];
            decimal = number.split("\\.")[1];
        }else{
            str = number;
        }
        // 判断是否存在整数位
        if(str.length()>0){
            for (int i = 0; i < str.length() ; i++) {
                String context = str.substring(i, i+1);
                int pow = str.length()-i-1;
                Integer val = Integer.parseInt(context.toString());
                // 获取中文单位
                String sign = dws.get(pow);
                // 获取中文数字
                String name = jes[Integer.parseInt(context)];
                if(val==0){
                    if(pow%4!=0){// 删除单位
                        sign = "";
                    }
                    if(i < str.length()-1){
                        Integer val1 = Integer.parseInt(str.substring(i+1, i+2));
                        if(val==0&&val==val1){
                            name = "";
                        }
                    }else if(i == str.length()-1){
                        name = "";
                    }
                }
                su.append(name+sign);
            }
        }
        // 判断是否存在小数位
        if(decimal!=null){
            str = decimal.substring(0,1);
            if(!"0".equals(str)){
                su.append(jes[Integer.parseInt(str)]+dws.get(-1));
            }
            if(decimal.length()==2){
                str = decimal.substring(1,2);
                if(!"0".equals(str)){
                    su.append(jes[Integer.parseInt(str)]+dws.get(-2));
                }
            }
        }else{
            su.append("整");
        }
        return su.toString();
    }
	/**
     * 清理数字特殊字符
     * @param str
     * @return
     */
    private static String delInvalidZero(String str){
        if("0".equals(str.substring(0, 1))){
            return delInvalidZero(str.substring(1, str.length()));
        }else if(str.contains(",")){
            return delInvalidZero(str.replaceAll(",", ""));
        }else{
            return str;
        }
    }
	
	/**
	 * @param date 给定日期
	 * @param interval 间隔天数，示例：5：5天以后;-6:6天以前
	 * @return 间隔后日期
	 */
	public static Date getDateAfterDay(Date date,int interval){
		String afterYear = "";
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DAY_OF_MONTH, interval);
		return calender.getTime();
	}
	/**
	 * @param date 给定日期
	 * @param interval 间隔月份，示例：5：5个月以后;-6:6个月以前
	 * @return 间隔后日期
	 */
	public static Date getDateAfterMonth(Date date,int interval){
		String afterYear = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, interval);
		return calendar.getTime();
	}
	/**
	 * @param date 给定日期
	 * @param interval 间隔年份，示例：5：5年以后;-6:6年以前
	 * @return 间隔后日期
	 */
	public static Date getDateAfterYear(Date date,int interval){
		String afterYear = "";
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.YEAR, interval);
		return calender.getTime();
	}
	
	/**格式化对象中的Double对象
	 * tom 2016年11月23日
	 * @param obj 待转换Double成员属性对象
	 * @param scale 转换后Double成员属性小数位数
	 * @return
	 */
	public static Object formateDoubleOfObject(Object obj,int scale){
		Class objClass = obj.getClass();
		Field[] fields = objClass.getDeclaredFields();
		Method[] methods = objClass.getMethods();
		List<Field> fieldList = Utils.getFieldList(objClass);
		for (Field field : fieldList) {
			if(field.getType().getName().equals("double") || field.getType().getName().equals("java.lang.Double")){//目前仅支持转double、Double对象数据
				try {
//					System.out.println(field.getName());
					String getMethodStr = Utils.field2GetMethod(field.getName());
					String setMethodStr = Utils.field2SetMethod(field.getName());
					Method getMethod = objClass.getMethod(getMethodStr);
					Method setMethod = null;
					try {
						setMethod = objClass.getMethod(setMethodStr,Double.class);
					} catch (Exception e) {
						setMethod = objClass.getMethod(setMethodStr,double.class);
					}
					Double score = (Double) getMethod.invoke(obj, null);
					if(score != null)
						setMethod.invoke(obj,Utils.formateDouble2Double(score, scale));
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		return obj;
	}
	
	
	/**
	 * 获取时间间隔
	 * tom 2016年11月8日
	 * @param beginDate 开始日期
	 * @param endDate 截止日期
	 * @param intervalMode 间隔模式
	 * @return 时间间隔
	 */
	public static long getTimeInterval(Date beginDate,Date endDate,EIntervalMode intervalMode){
		long interval = 0;
		Calendar beginCl = Calendar.getInstance();
		Calendar endCl = Calendar.getInstance();
		beginCl.setTime(beginDate);
		endCl.setTime(endDate);
		switch(intervalMode.name()){
		case "YEARS":
			interval = endCl.get(Calendar.YEAR) - beginCl.get(Calendar.YEAR);
			break;
		case "MONTHS":
			interval = (endCl.get(Calendar.YEAR) - beginCl.get(Calendar.YEAR)) * 12 + endCl.get(Calendar.MONTH) - beginCl.get(Calendar.MONTH);
			break;
		case "DAYS":
			System.out.println(endCl.getTimeInMillis());
			System.out.println(beginCl.getTimeInMillis());
			interval = (endCl.getTimeInMillis() - beginCl.getTimeInMillis())/(24 * 60 * 60 * 1000);
			break;
		case "HOURS":
			interval = (endCl.getTimeInMillis() - beginCl.getTimeInMillis())/(60 * 60 * 1000);
			break;
		case "MINUTES":
			interval = (endCl.getTimeInMillis() - beginCl.getTimeInMillis())/(60 * 1000);
			break;
		case "SECONDS":
			interval = (endCl.getTimeInMillis() - beginCl.getTimeInMillis())/(1000);
		case "MIllISECCONDS":
			interval = endCl.getTimeInMillis() - beginCl.getTimeInMillis();
			break;
		}
		return interval;
	}
	
	/**
	 * 字符串转日期
	 * tom 2016年11月7日
	 * @param date
	 * @param formateStr
	 * @return
	 */
	public static Date formateString2Date(String date,String formateStr){
		SimpleDateFormat formate = new SimpleDateFormat(formateStr);
		Date dateRet = null;
		try {
			dateRet = formate.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateRet;
	}
	
	/**
	 * 日期转字符串
	 * tom 2016年11月7日
	 * @param date
	 * @param formateStr
	 * @return
	 */
	public static String formateDate2String(Date date,String formateStr){
		SimpleDateFormat formate = new SimpleDateFormat(formateStr);
		String dateRet = "";
		try {
			dateRet = formate.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateRet;
	}
	
	/**
	 * 日期格式化
	 * tom 2016年11月7日
	 * @param date
	 * @param formateStr
	 * @return
	 */
	public static Date formateDate(Date date,String formateStr){
		SimpleDateFormat formate = new SimpleDateFormat(formateStr);
		Date dateRet = null;
		try {
			dateRet = formate.parse(formate.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateRet;
	}
	
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
	
	/**
	 * 属性拷贝
	 * tom 2016年11月7日
	 * @param source 源对象
	 * @param dest 目标对象
	 */
	public static void copyProperties(Object source,Object dest){
		Class srcCls = source.getClass();
		Class destCls = dest.getClass();
		List<Field> srcFieldList = Utils.getFieldList(srcCls);
		List<Field> destFieldList = Utils.getFieldList(destCls);
		Method[] srcMethods = srcCls.getMethods();
		Method[] destMethods = destCls.getMethods();
		List destList = new ArrayList();
		for (int i = 0; i < srcFieldList.size(); i++) {
			Field srcField = srcFieldList.get(i);
			String srcFieldName = srcField.getName();
			Class srcFieldType = srcField.getType();
			String srcFieldTypeName = srcFieldType.getName();
			Object srcFieldValue = null;
			try {//处理source中list成员变量
				srcFieldValue = srcField.get(source);
//				System.out.println(srcFieldType.getName().equals(double.class.getName())+"*********|"+srcFieldValue);
				for (Field destField : destFieldList) {
					String destFieldName = destField.getName();
					Class destFieldType = destField.getType();
					if(srcFieldName.equals(destFieldName)){
						if(srcFieldTypeName.equals(List.class.getName())){//判断list变量
//							System.out.println(srcFieldType+"|"+srcFieldName+"|"+srcField.getGenericType());
							List tempSrcFieldValue = (List) srcField.get(source);
							Type gType  = destField.getGenericType();
							ParameterizedType pType = (ParameterizedType) gType;
							Type[] types = pType.getActualTypeArguments();
//							System.out.println("****types[0]:"+types[0]+"|"+"types[0].getTypeName():"+types[0].getTypeName()+"|"+types[0].getTypeName().equals(RepayScheduleDetailPo.class.getTypeName())+"|"+types[0].getClass());
//							System.out.println("ttt:"+srcField.get(source));
							for (Object object : tempSrcFieldValue) {
//								Object rsdv = Class.forName(types[0].getTypeName()).newInstance();//目前仅仅拷贝list泛型中含有一个参数的情况，如：List<String>
								Object rsdv = Class.forName(types[0].toString().split(" ")[1]).newInstance();
								Utils.copyProperties(object, rsdv);
								destList.add(rsdv);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//处理resource中普通成员变量
			for (Field destField : destFieldList) {
				String destFieldName = destField.getName();
				Class destFieldType = destField.getType();
//				System.out.println(destFieldType+"|"+destFieldName);
				if(destFieldName.equals(srcFieldName)){
					for (int j = 0; j < destMethods.length; j++) {
						Method destMethod = destMethods[j];
						String methodName = destMethod.getName();
						if(("set"+destFieldName).toLowerCase().equals(methodName.toLowerCase())){
							try {
								if(srcFieldTypeName.equals(List.class.getName())){
									destMethod.invoke(dest, destList);
								}else if(srcFieldValue != null){
									if(srcFieldTypeName.equals(Date.class.getName())){
										SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
										destMethod.invoke(dest, formater.format(srcFieldValue));
									}else if(srcFieldTypeName.equals(Double.class.getName()) || srcFieldTypeName.equals(double.class.getName())){
										destMethod.invoke(dest, Utils.formateDouble2String((double)srcFieldValue, 2));
//										destMethod.invoke(dest, srcFieldValue);
									}else if(srcFieldTypeName.equals(Integer.class.getName()) || srcFieldTypeName.equals(int.class.getName())){
										destMethod.invoke(dest, srcFieldValue+"");
//										destMethod.invoke(dest, srcFieldValue);
									}else if(!srcFieldType.isPrimitive()){
										destMethod.invoke(dest, srcFieldValue);
									}
//									System.out.println("***********************srcFieldType.isMemberClass():"+srcFieldType.getName()+"|destFieldType:"+destFieldType.getName()+"|"+srcFieldType.getSuperclass());
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 双精度浮点数转指定格式双进度浮点数
	 * tom 2016年11月2日
	 * @param number 数据源
	 * @param scale 小数位数
	 * @return 格式化后双精度浮点数（输入：number=123.1 scale=3 输出：123.1）
	 */
	public static Double formateDouble2Double(BigDecimal bigDecimal,int scale){
		System.out.println("formateDouble2Double");
		return Utils.formateDouble2Double(bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue(), scale);
	}
	
	/**
	 * 双精度浮点数转制定格式双进度浮点数
	 * tom 2016年11月2日
	 * @param number 数据源
	 * @param scale 小数位数
	 * @return 格式化后双精度浮点数（输入：number=123.1 scale=3 输出：123.1）
	 */
	public static Double formateDouble2Double(double number,int scale){
		System.out.println("formateDouble2Double2");
		Double formateDouble = null;
		BigDecimal formater = new BigDecimal(number);
//		方法一：
//		formateDouble = formater.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
//		方法二：
		formateDouble = Math.round(number * Math.pow(10, new Double(scale)))/Math.pow(10, new Double(scale));
		System.out.println(formateDouble);
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
//		new Double("");
		formateDouble = formater.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
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
		formateDouble = formater.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		String formateStr = formater.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
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
	public static Date getFirstRepayDate(Date valueDate){
		Date firstRepayDate = null;
		Calendar firstRepayCl = Calendar.getInstance();
		firstRepayCl.setTime(valueDate);
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
