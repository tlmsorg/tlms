import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String str1 = new String("ttt");
		String str2 = new String("ttt");
		System.out.println(str1 == str2);
		Object obj = null;
		System.out.println(obj.equals(str2));
		Test2 test2 =new Test2();
		test2.setTest2(null);
		*/
		Object obj2 = "ttt";
		String str = "ttt";
		System.out.println(obj2.equals(str));
		Test2 test2 = new Test2();
		System.out.println(test2.getA());
		Calendar cl = Calendar.getInstance();
		System.out.println(cl.getTimeInMillis());
		
		String dateStr1 = "2016-02-29";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = format.parse(dateStr1);
			cl.setTime(date1);
			System.out.println("前："+format.format(cl.getTime()));
			cl.add(Calendar.MONTH, 1);
			System.out.println("后："+format.format(cl.getTime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Pattern pattern = Pattern.compile("null");
		
		String trimStr = "12"+" null"+"111";
		System.out.println(trimStr.trim());
		System.out.println(StringUtils.trimAllWhitespace(trimStr));
		Matcher matcher = pattern.matcher(trimStr);
		if(matcher.find()){
			System.out.println("find  null");
		}
		
		Pattern pattern2 = Pattern.compile("新疆");
		System.out.println(pattern2.matcher("新疆发票").find());
		
		double db = 1.0;
		
		
		
		int k  = 0;
		while(k < 10){
			if(k == 6)
				System.out.println("555");;
			System.out.println("k:"+k);
			k++;
		}
		System.out.println("tttttttt");
	}
}
