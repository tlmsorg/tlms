package com.test.formatesqlfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.alibaba.fastjson.JSONObject;

/**
 * @author tom
 * 提取powerdesigner所导出的sql文件中包含alter的行
 */
public class FormatSqlFile {
	/**
	 * tom 2016年11月2日
	 */
	public static void main(String[] args) {
		try {
			InputStream is = new FileInputStream(new File("crebas.sql"));
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(reader);
			String rowStr = "";
			JSONObject json = new JSONObject();
			int index = 0;
			StringBuffer sb = new StringBuffer();
			OutputStream os = new FileOutputStream(new File("d:\\out_crebas.sql"));
			
			while((rowStr = br.readLine()) != null){
				try {
					if("alter".equals(rowStr.trim().substring(0, 5))){
//						System.out.println(rowStr);
						
						os.write((rowStr+"\n").getBytes());
						sb.append(rowStr);
					}
				} catch (Exception e) {
				}
			}
			os.close();
			is.close();
			System.out.println("powerdesigner导出sql文件，提取alter行完成！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
