import java.util.ArrayList;
import java.util.regex.Pattern;

public class Test7 {

	public void run(Class clazz,String[] strArray, String... args) {
		System.out.println(clazz);
		System.out.println(strArray);
		System.out.println(args);
		System.out.println(strArray[0]+"|"+strArray[1]+"|"+strArray[2]);
		System.out.println(args[0]+"|"+args[1]+"|"+args[2]);
	}
	
	public static void main(String[] args) {
		String[] strArray = new String[] {"111","222","333"};
		new Test7().run(ArrayList.class,strArray,"444","555","666");
		System.out.println(6%5);
		
		System.out.println(Pattern.compile("法院执行").matcher("身份证命中法院执行名单").find());
	}

}
