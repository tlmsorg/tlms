import com.test.genericity.Genericity;

/**
 * 泛型测试
 * @author tom
 *
 */
public class GenericityTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Genericity<Integer> gt = new Genericity<Integer>();
		gt.setData(1);
		System.out.println(gt.getData());
	}

}
