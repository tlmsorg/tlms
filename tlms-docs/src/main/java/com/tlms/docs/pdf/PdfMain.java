package com.tlms.docs.pdf;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.hyphenation.TernaryTree;
import com.itextpdf.text.pdf.hyphenation.TernaryTree.Iterator;

public class PdfMain {
	public final String classPath = PdfMain.class.getClassLoader().getResource("").toString();
	public void pdfReader(){
		PdfReader reader;
		try {
//			OutputStream os = new FileOutputStream(new File("d:/pdfWrite.pdf"));
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			reader = new PdfReader(classPath+"/潽金融资租赁有限公司抵押合同_表单.pdf");
			PdfStamper ps = new PdfStamper(reader, new FileOutputStream("d:\\test.pdf"));
			AcroFields fields = ps.getAcroFields();
//			Iterator it = (Iterator) fields.getFields().keySet().iterator();
			Map<String, Item> map = fields.getFields();
			for(Map.Entry<String, Item> entry:map.entrySet()){
				String key = entry.getKey();
				Item item = entry.getValue();
				System.out.println(key);
				System.out.println(item);
			}
			fields.setField("date", "111");
			/*while(it.hasMoreElements()){
				String next = it.nextElement();
				System.out.println(next);
			}*/
			ps.setFormFlattening(true);
			ps.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Document document = new Document(PageSize.A4, 20, 20, 40, 40);
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new File("d:\\itext.pdf")));
			System.out.println();
			String classPath = PdfMain.class.getClassLoader().getResource("").toString();
			BaseFont bf = BaseFont.createFont(classPath+"/MSYH.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//			BaseFont bf =BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);    
//			Font font = new Font(FontFamily.COURIER, 19);
//			Font font = new Font(baseFont, 12,Font.NORMAL,Color.BLACK);
			Font font = new Font(bf, 12);
			document.open();
			PdfPTable pdfTable = new PdfPTable(1);
			PdfPCell pdfCell = new PdfPCell();
//			Paragraph paragraph = new Paragraph();
			//潽金融资租赁有限公司抵押合同
//			 new Paragraph("潽金融资租赁有限公司抵押合同",baseFont)
			Paragraph paragraph = new Paragraph("潽金融资租赁有限公司抵押合同", font);

//			for (int i = 0; i < 10; i++) {
//				Chunk chunk = new Chunk("This is a sentence which is long " + i + ". \n");
//				paragraph.add(chunk);
//			}
			pdfCell.addElement(paragraph);
			float[] width = {500f};
			pdfTable.setTotalWidth(width);
			pdfTable.addCell(pdfCell);
			document.add(pdfTable);
			document.close();
			System.out.println(document.isOpen());
			
			PdfMain pdfMain = new PdfMain();
			pdfMain.pdfReader();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
