package com.tlms.docs.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.AcroFields.Item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
 
/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class CheckBoxFlatten {
 
    public static final String SRC = "d:\\checkboxes.pdf";
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
        File file = new File("print/1-融资租赁合同-print.pdf");
		if(file.exists())
			file.createNewFile();
		OutputStream os = new FileOutputStream(file);
		PdfStamper pdfStamper = new PdfStamper(reader, os);

        AcroFields fields = pdfStamper.getAcroFields();
		Map<String, Item> map = fields.getFields();
		for(Map.Entry<String, Item> entry:map.entrySet()){
			fields.setFieldProperty(entry.getKey(), "textfont", bf, null);
//			fields.setFieldProperty(entry.getKey(), "textsize", new Float(6), null); 
			System.out.println(entry.getKey()+"|"+fields.getField(entry.getKey()));
		}
		fields.setField("cb0", "Yes");
		pdfStamper.setFormFlattening(true);
		pdfStamper.close();
    }
}
