import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		String fileName = "微信图片_20180910094520.png";
		String url = "http://zhuli2018.oss-cn-beijing.aliyuncs.com/resource/20f9451e9dc69ec8/";
		String encodeFileName = URLEncoder.encode(fileName);
		System.out.println(url+encodeFileName);
		
		Map<String, Object> cardTranDataMap = new HashMap<String, Object>();
		cardTranDataMap.put("name", "brighttang");
		cardTranDataMap.put("pass", 12346);
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(cardTranDataMap));
		System.out.println(JSONObject.toJSONString(cardTranDataMap));
		
	}

}
