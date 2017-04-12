package com.test.enumtest;

public class EnumTestMain {

	public enum ColorEnum{
		/*red{
			public String getName(){
				return "红色";
			}
		};
		public abstract String getName();*/
	}
	
	public void sendFlagTest(ESendFlag sendFlag){
		switch(sendFlag.getCode()){
			case "01":
				break;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ColorEnum colorEnum;
//		System.out.println(ColorEnum.values()[0]);
		new EnumTestMain().sendFlagTest(ESendFlag.havaSend);
	}

}
