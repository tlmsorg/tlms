import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Test6 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("E:\\winimhca.log");
		InputStream is = new FileInputStream(file);
		File fileToSave = new File("D:\\"+file.getName());
		FileOutputStream fos = new FileOutputStream(fileToSave);
		byte[] buf = new byte[1024];
		int length = 0;
		while((length = is.read(buf)) > 0) {
			fos.write(buf, 0, length);
		}
		fos.flush();
		fos.close();
		is.close();
	}

}
