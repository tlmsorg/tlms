package com.test.number;

import java.math.BigDecimal;

import com.tlms.core.util.Utils;

/**
 * @author tom
 *
 */
public class BigDecimalTest {

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
	}

}
