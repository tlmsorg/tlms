import java.util.ArrayList;

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
	}

}
