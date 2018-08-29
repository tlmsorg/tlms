package com.test.number;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tlms.core.util.Utils;

/**
 * @author tom
 *
 */
public class BigDecimalTest {
	public static boolean isNumberOrLetter(String strSrc) {
		boolean bool = true;
		/**
		 * 	大写  65-90
			小写  97-122
			数字  48-57
		 */
		strSrc = strSrc.replace(" ", "");
		strSrc = strSrc.trim();
		System.out.println(strSrc);
		for (int i = 0; i < strSrc.length(); i++) {
			int charAscii = strSrc.charAt(i);
			System.out.println(charAscii);
			if( !((charAscii >= 65 && charAscii <= 90)
					||(charAscii >= 97 && charAscii <= 122)
					||(charAscii >= 48 && charAscii <= 57)) ) {
				bool = false;
			}
		}
		return bool;
	}

	/**
	 * tom 2016年11月24日
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal bd1 = new BigDecimal("5.2135468745687964546874564564845646");
		BigDecimal bd2 = new BigDecimal("8.1234879754168764348764543");
		double db1 = Utils.formateDouble2Double(bd1, 2);
		double db2 = Utils.formateDouble2Double(bd2, 2);
 		System.out.println(db1+"|"+db2);
		System.out.println(db2 - db1);
		double db3 = 2.1;
		double db4 = 7.5;
		System.out.println(db4 - db3);
		
		String str = "222";
		Pattern patter = Pattern.compile(".[0,9]*.");
		Matcher matcher = patter.matcher(str);
		
		System.out.println(matcher.matches());
		System.out.println(str.matches("^2?"));
		String content = "0123456789abyzABYZ";
		String pattern = ".[0-9]+[a-z]+.";
		boolean isMatch = Pattern.matches(pattern, content);
		System.out.println("字符串中是否包含了 'runoob' 子字符串? " + Pattern.matches(pattern, content));

		for (int i = 0; i < content.length(); i++) {
			int currChar = content.charAt(i);
			System.out.println(currChar);
		}
		
		System.out.println(BigDecimalTest.isNumberOrLetter("1 3 jkdf"));
		

		
		
	}
	
	
}
