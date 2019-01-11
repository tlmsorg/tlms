package com.tlms.docs.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
 
/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class CheckBoxFlatten {
 
    public static final String SRC = "d:\\1-融资租赁合同-print.pdf";
    public static final String DEST = "d:\\checkboxes_flat.pdf";
 
    public static void main(String[] args) throws DocumentException, IOException {
    	 File file = new File(DEST);
         file.getParentFile().mkdirs();
         new CheckBoxFlatten().manipulatePdf(SRC, DEST);
    }
       
    
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
    	PdfReader reader = new PdfReader(src);
        /*
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.setFormFlattening(false);
        stamper.close();
        
*/
    	BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
//    	bf = BaseFont.createFont("d:\\微软vista雅黑.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//    	bf = BaseFont.createFont("d:\\微软vista雅黑.ttf",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
    	bf = BaseFont.createFont("d:\\msyh.ttf",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
    	
        File file = new File("d:\\1-融资租赁合同-print7.pdf");
		if(file.exists()) {
			file.createNewFile();
		}
			
		OutputStream os = new FileOutputStream(file);
		PdfStamper pdfStamper = new PdfStamper(reader, os);

        AcroFields fields = pdfStamper.getAcroFields();
		Map<String, Item> map = fields.getFields();
		for(Map.Entry<String, Item> entry:map.entrySet()){
			fields.setFieldProperty(entry.getKey(), "textfont", bf, null);
//			fields.setFieldProperty(entry.getKey(), "textsize", new Float(6), null); 
			System.out.println(entry.getKey()+"|"+fields.getField(entry.getKey()));
		}
		
		fields.setField("name1", "重庆融庆融庆融庆融庆融资朱令`11");
		fields.setField("name2", "重庆融资朱令`22");
		pdfStamper.setFormFlattening(true);
		pdfStamper.close();
    }
}
