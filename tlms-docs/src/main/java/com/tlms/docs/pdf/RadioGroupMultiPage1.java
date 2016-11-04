package com.tlms.docs.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.BaseField;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfBorderDictionary;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
 
/**
 * @author Bruno Lowagie (iText Software)
 */
public class RadioGroupMultiPage1 {
 
    public static final String DEST = "print/multipage_radiobutton1.pdf";
    /** Possible values of a Choice field. */
    public static final String[] LANGUAGES = { "English", "German", "French", "Spanish", "Dutch" };
 
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
//        new RadioGroupMultiPage1().createPdf(DEST);
        new RadioGroupMultiPage1().createPdf2(DEST);
    }
    
    public void createPdf2(String dest) throws IOException, DocumentException {
    	 Document document = new Document(PageSize.A4, 50, 50, 50, 50);
    	 PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("print/output.pdf"));
    	 document.open();
    	 PdfContentByte cb = writer.getDirectContent();
    	 
    	 
    	 RadioCheckField bt = new RadioCheckField(writer, new Rectangle(100, 100, 200, 200), "myradio", "v1");
    	 bt.setCheckType(RadioCheckField.TYPE_SQUARE);
    	 bt.setBackgroundColor(BaseColor.CYAN);
    	 bt.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
    	 bt.setBorderColor(BaseColor.RED);
    	 bt.setTextColor(BaseColor.BLACK);
    	 bt.setBorderWidth(BaseField.BORDER_WIDTH_THICK);
//    	 bt.setChecked(false);
    	 PdfFormField f1 = bt.getRadioField();
    	 
    	/* bt.setOnValue("v2");
    	 bt.setChecked(true);
    	 bt.setBox(new Rectangle(100, 300, 200, 400));
    	 PdfFormField f2 = bt.getRadioField();
    	 
    	 bt.setChecked(false);
    	 bt.setOnValue("v3");
    	 bt.setBox(new Rectangle(100, 500, 200, 600));
    	 PdfFormField f3 = bt.getRadioField();*/
    	 
    	 PdfFormField top = bt.getRadioGroup(true, false);
    	 top.addKid(f1);
    	 /*top.addKid(f2);
    	 top.addKid(f3);*/
    	 writer.addAnnotation(top);
    	 
    	 bt = new RadioCheckField(writer, new Rectangle(100, 300, 200, 400), "check1", "Yes");
    	 bt.setCheckType(RadioCheckField.TYPE_CHECK);
    	 bt.setBorderWidth(BaseField.BORDER_WIDTH_THIN);
    	 bt.setBorderColor(BaseColor.BLACK);
    	 bt.setBackgroundColor(BaseColor.WHITE);
    	 bt.setChecked(true);
    	 PdfFormField ck = bt.getCheckField();
    	 writer.addAnnotation(ck);
    	 document.close();
    	 
    	 
    	 PdfReader pdfReader = null;
    	 PdfStamper pdfStamper = null;
    	 FileOutputStream os = null;
    	 pdfReader = new PdfReader("print/output.pdf");
    	 os = new FileOutputStream(new File("print/output-print.pdf"));
    	 pdfStamper = new PdfStamper(pdfReader, os);
    	 AcroFields form = pdfStamper.getAcroFields();
//    	 fields.removeField("check1");
    	 System.out.println(form.getFieldItem("check1"));
    	 Map<String,Item> map = form.getFields();
    	 for (Map.Entry<String, Item> entry:map.entrySet()) {
    		 System.out.println(entry.getKey()+"|"+entry.getValue());
    		 Item item = entry.getValue();
		 }
    	 Item item = form.getFieldItem("check1");
//    	 PdfDictionary widget = item.getWidget(0);
//    	 PdfArray rect = widget.getAsArray(PdfName.RECT);
//    	 rect.set(2, new PdfNumber(rect.getAsNumber(2).floatValue() - 10f));
    	 
//    	 form.setField("check1", "Yes");
//    	 fields.setFieldProperty("check1", "", value, inst)
//    	 pdfStamper.setFormFlattening(true);
//    	 pdfStamper.partialFormFlattening("check1");
    	 pdfStamper.close();
    	 pdfReader.close();
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
    	// step 1
        Document document = new Document();
        // step 2
        PdfWriter writer =
            PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        // create a radio field spanning different pages
        PdfFormField radiogroup = PdfFormField.createRadioButton(writer, true);
        radiogroup.setFieldName("language");
        Rectangle rect = new Rectangle(40, 806, 60, 788);
        RadioCheckField radio = new RadioCheckField(writer,rect,"radio","English");
        
      /*  PdfFormField radiofield;
        for (int page = 0; page < LANGUAGES.length; ) {
            radio = new RadioCheckField(writer, rect, null, LANGUAGES[page]);
            radio.setBackgroundColor(new GrayColor(0.8f));
            radio.setCheckType(RadioCheckField.TYPE_SQUARE);
            if(page == 2){
            	radio.setChecked(true);
            }
            radiofield = radio.getRadioField();
            radiofield.setPlaceInPage(++page);
            radiogroup.addKid(radiofield);
        }*/
        
//        writer.addAnnotation(top);
//        writer.addAnnotation(radiogroup);
        // add the content
      /*  for (int i = 0; i < LANGUAGES.length; i++) {
            cb.beginText();
            cb.setFontAndSize(bf, 18);
            cb.showTextAligned(Element.ALIGN_LEFT, LANGUAGES[i], 70, 790, 0);
            cb.endText();
            document.newPage();
        }*/
        // step 5
//        document.close();
        
        
    }
}