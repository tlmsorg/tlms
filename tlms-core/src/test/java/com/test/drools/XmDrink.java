package com.test.drools;
/**
 * Xm喝水
 * @author 160068
 * 2018年11月16日 下午2:40:22
 */
public class XmDrink {

	public static void main(String[] args) {
		int totalMoney = 50;
		int exchange = 2;
		int bottleNum = 0;
		int totalDrink = 0;
		while(totalMoney > 0) {
			totalMoney--;
			totalDrink++;
			bottleNum++;
			System.out.println("喝一瓶，已喝："+totalDrink+",空瓶："+bottleNum);
			if(bottleNum >= exchange) {
				totalDrink++;
				bottleNum = bottleNum - exchange + 1;
				System.out.println("换一瓶，已喝："+totalDrink+",空瓶："+bottleNum);
			}
		}
		System.out.println(totalDrink);
		
	}

}
