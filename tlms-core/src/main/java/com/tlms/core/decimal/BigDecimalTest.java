package com.tlms.core.decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.tlms.core.util.Utils;

public class BigDecimalTest {
	public static void main(String[] args) {
		Double db = new Double("1.1");
		double db5 = 1.1;
		System.out.println(db/10);
		System.out.println(db5/10);

		BigDecimal fenzi = new BigDecimal(db5);
		BigDecimal fenmu = new BigDecimal(10);
		System.out.println(fenzi.divide(fenmu).setScale(2, RoundingMode.HALF_UP));
		
		System.out.println();
		db = db/3;
		db = Utils.formateDouble2Double(db, 2);
		Double db2 = new Double("12.3333");
		Double db3 = db + db2;
		System.out.println(db3 * 2);
		
		double db4 = 120.3;
		
		System.out.println(db2/100);
		long long1 = Math.round(db4/3);
		System.out.println(long1/5);
	}
}
