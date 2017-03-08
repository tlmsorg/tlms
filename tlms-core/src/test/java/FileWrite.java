import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author tom
 *
 */
public class FileWrite {

	/**
	 * tom 2017年3月6日
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("test.txt");
		String[] strArray = new String[]{"11111111111","22222222222"};
		try {
			FileOutputStream fos = new FileOutputStream(file);
			for (int i = 0; i < strArray.length; i++) {
				byte[] byteArray = strArray[i].getBytes("gbk");
				fos.write(byteArray, 0, byteArray.length);
				byte[] str = "\r\n".getBytes();
				fos.write(str, 0, str.length);
				fos.flush();
			}
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
