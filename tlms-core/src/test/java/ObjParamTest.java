
public class ObjParamTest {
	
	public void objHaveManyParam(Object... obj){
		for (int i = 0; i < obj.length; i++) {
			System.out.println(obj.getClass().getName());
			System.out.println(obj[i]);
		}
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjParamTest opt = new ObjParamTest();
		opt.objHaveManyParam("111",222,333);
	}

}
