
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = new String("ttt");
		String str2 = new String("ttt");
		System.out.println(str1 == str2);
		Object obj = null;
		System.out.println(obj.equals(str2));
		Test2 test2 =new Test2();
		test2.setTest2(null);
	}
}
