import org.springframework.util.Assert;

public class AssertTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String str = "1";
			Assert.isNull("2".equals(str), "请输入用户名密码");
		} catch (Exception e) {
			
			System.out.println(e);
		}
	}	

}
