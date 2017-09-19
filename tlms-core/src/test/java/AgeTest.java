import java.util.Calendar;

public class AgeTest {

	public static int getAge(String idNo){
		int birthYear = Integer.parseInt(idNo.substring(6, 10));
		int birthMonth = Integer.parseInt(idNo.substring(10, 12));
		int birthDayOfMonth = Integer.parseInt(idNo.substring(12, 14));
		Calendar cl = Calendar.getInstance();
		int currYear = cl.get(Calendar.YEAR);
		int currMonth = cl.get(Calendar.MONTH) + 1;
		int currDayOfMonth = cl.get(Calendar.DAY_OF_MONTH);
		int age = 0;
		if(birthYear == currYear){
			age = 0;
		}else if(birthYear < currYear){
			if(birthMonth < currMonth){
				age = currYear - birthYear;
			}else if(birthMonth == currMonth){
				if(birthDayOfMonth <= currDayOfMonth){
					age = currYear - birthYear;
				}else{
					age = currYear - birthYear - 1;
				}
			}else{
				age = currYear - birthYear -1;
			}
		}
		return age;
	}
	
	public static String getSex(String idNo){
		int sexNum = 0;
		String sex = "男";
		if(idNo.length() == 18){
			sexNum = Integer.parseInt(idNo.substring(16, 17));
		}else{
			sexNum = Integer.parseInt(idNo.substring(idNo.length() - 1, idNo.length()));
		}
		if(sexNum % 2 == 0){
			sex = "女";
		}
		return sex;
	}
	public static void main(String[] args) {
		String idNo = "500383198808181998";
		System.out.println(AgeTest.getAge(idNo));
		System.out.println(AgeTest.getSex(idNo));
		System.out.println(idNo.substring(6, 14));
	}

}
