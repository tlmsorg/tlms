import java.lang.reflect.Field;

public class BeanMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeanReflect bf = new BeanReflect();
		Field[] fields = bf.getClass().getDeclaredFields();
//		bf.getClass().getDeclaredField("")
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
//			field.setAccessible(true);
			System.out.println(field.getName());
		}
	}

}
