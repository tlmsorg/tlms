import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.tlms.core.util.Utils;

public class Test {

	public void getCurrClassName(){
		System.out.println(this.getClass().getName());
	}
	
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
		new Test().getCurrClassName();
		Test test = new Test();
		try {
			System.out.println("路径："+test.getClass().getClassLoader().getResource("").toURI().getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "d:\\test\\test";
		File file = new File(path);
		System.out.println(file.exists());
		if(!file.exists())
			System.out.println(file.mkdirs());
		else
			System.out.println("方法反反复复");
		
		String colors = "111#222#333";
		String[] cls = colors.split("\\#");
		System.out.println("|"+cls[0]+"|"+cls[1]+"|"+cls[2]+"|");
			
		
		Pattern pattern3 = Pattern.compile("[0-9]*");
		Matcher matcher3 = pattern3.matcher("16456");
		System.out.println(matcher3.matches());
		
		System.out.println(5000%1001);
		File file2 = new File("test.txt");
		try {
			FileOutputStream fos = new FileOutputStream(file2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("File.pathSeparator:"+File.pathSeparator+"|"+File.pathSeparatorChar+"|"+File.separator+"|"+File.separatorChar);
	
		Pattern pattern4 = Pattern.compile("carVin");
		String str4 = "carVin2";
		Matcher matcher4 = pattern4.matcher(str4);
		System.out.println(matcher4.find());
		
		System.out.println(Utils.number2Chn(15230.21));
		
		System.out.println("ttt:"+Math.round(9989*0.001));
		
		String name = "唐";
		System.out.println(name.length());
		
		String nl = null;
		String strNul = String.valueOf(nl);
		System.out.println("strNul:"+nl);
	}
}
