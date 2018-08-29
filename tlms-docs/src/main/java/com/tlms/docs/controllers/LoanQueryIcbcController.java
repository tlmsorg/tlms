package com.tlms.docs.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value="/loanqueryicbc")
public class LoanQueryIcbcController
{
	
	
	/**
	 * 上传工行账单文件
	 * 160068
	 * 2018年8月2日 下午3:31:56
	 * @param file
	 * @param operater
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequestMapping(value="/impIcbcBillFile",method=RequestMethod.POST)
	@ResponseBody
	public Object impIcbcBillFile(MultipartFile file,String operater) throws Exception {
		InputStream is = file.getInputStream();
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getName());
		File fileToSave = new File("H:\\"+file.getOriginalFilename());
		if(fileToSave.exists())
			fileToSave.deleteOnExit();
		Boolean isCreateSuccess = fileToSave.createNewFile();
		System.out.println("文件创建状态："+isCreateSuccess);
		FileOutputStream fos = new FileOutputStream(fileToSave);
		byte[] buf = new byte[1024];
		int length = 0;
		while((length = is.read(buf)) > 0) {
			fos.write(buf, 0, length);
			fos.flush();
		}
		fos.close();
		is.close();
		
		return null;
	}
	
}
