package com.tlms.halftest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;

import com.tlms.core.util.Utils;

/**
 * @author tom
 *
 */
public class HalfTest {

	/**
	 * tom 2016年11月23日
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal bd = new BigDecimal("0.365");
		BigDecimal fz = new BigDecimal(10.4567845675648568456123456413);
		BigDecimal fm = new BigDecimal(3);
		System.out.println("fz:"+fz);
		BigDecimal result = fz.divide(fm,RoundingMode.HALF_UP);
		System.out.println("result:"+result);
		System.out.println("result.doubleValue():"+result.doubleValue());
		System.out.println("result.floatValue():"+result.floatValue());
		System.out.println("格式化后："+Utils.formateDouble2Double(result, 2));
//		System.out.println(BigDecimal.ROUND_HALF_UP);
//		System.out.println(BigDecimal.ROUND_HALF_DOWN);
//		System.out.println(BigDecimal.ROUND_HALF_EVEN);
//		System.out.println(Utils.formateDouble2Double(bd, 2));
	}

}
