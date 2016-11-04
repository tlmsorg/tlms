package com.tlms.docs.pdf;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
 
import java.io.IOException;
 
public class CheckBoxValues {
 
    public static final String SRC = "print/datasheet.pdf";
    public static final String FIELD = "CP_1";
 
    public static void main(String[] args) throws IOException {
        CheckBoxValues app = new CheckBoxValues();
        System.out.println(app.getCheckboxValue(SRC, FIELD));
    }
 
    public String getCheckboxValue(String src, String name) throws IOException {
        PdfReader reader = new PdfReader(SRC);
        AcroFields fields = reader.getAcroFields();
        // CP_1 is the name of a check box field
        String[] values = fields.getAppearanceStates("CP_1");
        StringBuffer sb = new StringBuffer();
        for (String value : values) {
//        	System.out.println(value);
            sb.append(value);
            sb.append('\n');
        }
        
        /*String[] value2 = fields.getListOptionExport("CP_1");
        for (int i = 0; i < value2.length; i++) {
			System.out.println(value2[i]);
		}*/
        return sb.toString();
    }
}
