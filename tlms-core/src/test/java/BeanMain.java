import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BeanMain {

	/**
	 * 递归获取field
	 * @param obj 对象
	 * @param fieldList 所有field列表
	 */
	public void getField(Class obj,List<Field> fieldList){
		Field[] fields = obj.getDeclaredFields();
		if(!obj.getName().equals("java.lang.Object")){
			for (Field field : fields) {
				field.setAccessible(true);
				fieldList.add(field);
			}
			this.getField(obj.getSuperclass(), fieldList);
		}
	}
	/**
	 * 获取对象所有field
	 * @param obj
	 * @return
	 */
	public List<Field> getFieldList(Class obj){
		List<Field> fieldList = new ArrayList<>();
		this.getField(obj, fieldList);
		return fieldList;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeanChildReflect bf = new BeanChildReflect();
		
		bf.setName("tang");
		bf.setAge("11");
		bf.setAddress("重庆");
		bf.setMail("tang@qq.com");
		bf.setSex("男");
		//获取类中所有的属性（包括public、private、proteced、缺省等属性），不能获取父类属性任何属性，对于private属性，必须设置为可以访问：setAccessible(true)
		Field[] cfields = bf.getClass().getDeclaredFields();
//		Field[] cfields = bf.getClass().getFields();//可以获取当前类及所有父类中的public属性，无法获取其他属性
		Class cls = bf.getClass();
//		Class prtCls = cls.getSuperclass();
//		Class pprtCls = prtCls.getSuperclass();
		BeanMain beanMain = new BeanMain();
		List<Field> fieldList = beanMain.getFieldList(cls);
		for (Field field:fieldList) {
			try {
				System.out.println("bf属性值："+field.get(bf));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
