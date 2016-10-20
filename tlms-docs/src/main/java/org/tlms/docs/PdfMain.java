package org.tlms.docs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;



public class PdfMain {
	public void fillTemplate(){//利用模板生成pdf
		        //模板路径
//		String templatePath = "pdfFolder/template_demo.pdf";
		String templatePath = "pdfTpl.pdf";
		
		        //生成的新文件路径
		String newPDFPath = "newPdf.pdf";
		PdfReader reader;
		FileOutputStream out;
		ByteArrayOutputStream bos;
		PdfStamper stamper;
		try {
			out = new FileOutputStream(newPDFPath);//输出流
			reader = new PdfReader(templatePath);//读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			
			String[] str = {"123456789","11","1","1994-00-00",
					"130222111133338888"
					,"111"};
			int i = 0;
			java.util.Iterator<String> it = form.getFields().keySet().iterator();
		    while(it.hasNext()){
		    	String name = it.next().toString();
		    	System.out.println(name);
		    	form.setField(name, str[i++]);
		    }
		    stamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
		    stamper.close();
		    
		    Document doc = new Document();
		    PdfCopy copy = new PdfCopy(doc, out);
		    doc.open();
		    PdfImportedPage importPage = copy.getImportedPage(
		    		new PdfReader(bos.toByteArray()), 1);
		    copy.addPage(importPage);
		    doc.close();
			
		} catch (IOException e) {
			System.out.println(1);
		} catch (DocumentException e) {
			System.out.println(2);
		}
	}  
	
	public void pdfTest() throws FileNotFoundException, DocumentException, IOException{
		String str = PdfMain.class.getClassLoader().getResource("").toString();
		PdfReader reader = new PdfReader("emptTpl.pdf"); // 模版文件目录  
		 
	    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(  
	            "d:/fillTemplate.pdf")); // 生成的输出流  
	    // ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	    // PdfStamper ps = new PdfStamper(reader, bos);  
	    AcroFields form = stamper.getAcroFields();  
	    BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
	    Map<String, Item> fieldMap = form.getFields(); // pdf表单相关信息展示  
	    for (Entry<String, Item> entry : fieldMap.entrySet()) {  
	        String name = entry.getKey(); // name就是pdf模版中各个文本域的名字  
	        Item item = (Item) entry.getValue();  
	        System.out.println("[name]:" + name + ", [value]: " + item);  
//	        form.setField(name, "test");
	        form.setFieldProperty(name, "textfont", bf, null);
	    } 
	   
	    form.setField("sex", "男");  
	    form.setField("address", "重庆市渝北区黄山大道中段");  
	    form.setField("age", "100");
	    form.setField("fill_8", "唐亮");
	  
	    stamper.setFormFlattening(true); // 这句不能少  
	    stamper.close();  
	    reader.close();  
	}
	public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
		// TODO Auto-generated method stub
		PdfMain pdfMain = new PdfMain();
//		pdfMain.fillTemplate();
//		pdfMain.pdfTest();
		String path = PdfMain.class.getClassLoader().getResource("").toString();
		File file = new File("print/testtang.txt");
		FileOutputStream fos = new FileOutputStream(file);
		String str = "tttttttttttttttttttt";
		byte[] buf = new byte[1000];
		buf = str.getBytes();
		fos.write(buf);
		fos.flush();
	}

}
