package com.tlms.docs.pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.border.Border;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author tom
 * 核准函测试（动态生成pdf）
 */
public class PdfCreateMain {

	public static final String DEST = "print/simple_table.pdf";
	public static final String DEST_SMALL = "print/small_table.pdf";
	
	/**
	 * 签字日期
	 * tom 2016年10月28日
	 */
	public void createSignDate(PdfPTable table,Font fieldNameFont,Font fieldFont){
		String[] imptTips = new String[]{"出租人签字/盖章","潽金融资租赁有限公司","年 月 日"};
//		PdfPCell cell = new PdfPCell(new Paragraph("111", fieldNameFont));
		
		PdfPTable table2 = new PdfPTable(10);
//		table.setTotalWidth(new float[]{100,100,100,100,100,100,100,100,100,100});
		table2.setWidthPercentage(new Float(10));
		
		PdfPCell innerCell = new PdfPCell(new Paragraph("出租人签字/盖章",fieldNameFont));
		innerCell.setColspan(10);
		innerCell.setFixedHeight(25);
		innerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		innerCell.setBorder(Rectangle.NO_BORDER);
		table2.addCell(innerCell);
		
		PdfPCell innerCell2 = new PdfPCell(new Paragraph("潽金融资租赁有限公司",fieldNameFont));
		innerCell2.setColspan(10);
		innerCell2.setFixedHeight(25);
		innerCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		innerCell2.setBorder(Rectangle.NO_BORDER);
		table2.addCell(innerCell2);
		
		PdfPCell innerCell3 = new PdfPCell(new Paragraph("年           月          日",fieldNameFont));
		innerCell3.setColspan(10);
		innerCell3.setFixedHeight(25);
		innerCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		innerCell3.setBorder(Rectangle.NO_BORDER);
		table2.addCell(innerCell3);
		
		PdfPCell cell = new PdfPCell(table2);
//		Paragraph pgh1 = new Paragraph("111", fieldNameFont);
//		Paragraph pgh2 = new Paragraph("111", fieldNameFont);
//		Paragraph pgh3 = new Paragraph("111", fieldNameFont);
//		cell.addElement(new Phrase("fdsffdsfds"));
		cell.setFixedHeight(75);
		cell.setColspan(10);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		/*for (int i = 0; i < imptTips.length; i++) {
			if(cell == null)
				cell = new PdfPCell(new Paragraph(imptTips[i], fieldNameFont));
//			else 
//				cell.addElement(new Paragraph(imptTips[i], fieldNameFont));
//			Paragraph pgh = new Paragraph(imptTips[i], fieldNameFont);
//			cell.addElement(pgh);	
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setFixedHeight(80);
			cell.setColspan(10);
		}*/
		
		
		/*PdfPCell cell2 = new PdfPCell(new Paragraph("111", fieldNameFont));
//		cell.addElement(new Phrase("fdsffdsfds"));
		cell2.setFixedHeight(20);
		cell2.setColspan(10);
//		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell2);
		
		PdfPCell cell3 = new PdfPCell(new Paragraph("111", fieldNameFont));
//		cell.addElement(new Phrase("fdsffdsfds"));
		cell3.setFixedHeight(20);
		cell3.setColspan(10);
		cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell3);*/
		
	}
	
	/**
	 * 附加条件
	 * tom 2016年10月28日
	 */
	public void createImportantTips(PdfPTable table,Font fieldNameFont,Font fieldFont){
		String[] imptTips = new String[]{"重要提示：","1、如发生承租人所购车辆的净车价、车辆所有人名称变更、承租人或保证人提供虚假信息、不接受调整后融资政策、撤销租赁申请等非潽金原因而导致影响审核结果之情形，则潽金有权视","具体情况终止操作该笔租赁业务。","2、应客户要求，上述租赁申请结果可能发生变更，届时潽金将出具《融资租赁申请核准通知函》，请贵公司以潽金最新出具的版本为准。","3、本通知函不作为贵公司放车的依据，如果承租人不能履行全部租赁措施，则潽金将拒绝放款，请贵公司谨慎确认交付车辆的时间并承担相应的风险。"};
        PdfPCell cell = new PdfPCell();
		for (int i = 0; i < imptTips.length; i++) {
			Paragraph pgh = new Paragraph(imptTips[i], fieldNameFont);
//        	PdfPCell carModel = new PdfPCell(new Paragraph("重要提示：", fieldNameFont));
			cell.addElement(pgh);
		}
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setFixedHeight(80);
		cell.setColspan(10);
        table.addCell(cell);
		
	}
	/**
	 * 附加条件
	 * tom 2016年10月28日
	 */
	public void createAddCondition(PdfPTable table,Font fieldNameFont,Font fieldFont){
		PdfPCell applyInfo = new PdfPCell(new Paragraph("放款附加条件", fieldNameFont));
        applyInfo.setColspan(10);
        applyInfo.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(applyInfo);
        
        PdfPCell carModel = new PdfPCell(new Paragraph("放款条件:见票放款 放款附加条件:增加哥哥为共租人，补充提供身份证复印件。", fieldNameFont));
        carModel.setHorizontalAlignment(Element.ALIGN_LEFT);
        carModel.setFixedHeight(100);
        carModel.setColspan(10);
        table.addCell(carModel);
	}
	
	 public void createSmallPdf(String dest) throws IOException, DocumentException {
//	        Rectangle small = new Rectangle(290,100);
	        Font smallfont = new Font(FontFamily.HELVETICA, 10);
//	        Document document = new Document(small, 5, 5, 5, 5);
	        Document document = new Document();
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
	        document.open();
	        PdfPTable table = new PdfPTable(2);
	        table.setTotalWidth(new float[]{ 160, 120 });
	        table.setLockedWidth(true);
	        PdfContentByte cb = writer.getDirectContent();
	        // first row
	        PdfPCell cell = new PdfPCell(new Phrase("Some text here"));
	        cell.setFixedHeight(30);
//	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(2);
	        table.addCell(cell);
	    /*    // second row
	        cell = new PdfPCell(new Phrase("Some more text", smallfont));
	        cell.setFixedHeight(30);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        Barcode128 code128 = new Barcode128();
	        code128.setCode("14785236987541");
	        code128.setCodeType(Barcode128.CODE128);
	        Image code128Image = code128.createImageWithBarcode(cb, null, null);
	        cell = new PdfPCell(code128Image, true);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setFixedHeight(30);
	        table.addCell(cell);
	        // third row
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("and something else here", smallfont));
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        table.addCell(cell);*/
	        document.add(table);
	        document.close();
	    }
	 
	public void createFinanceInfo(PdfPTable table,Font fieldNameFont,Font fieldFont){
		PdfPCell financeInfo = new PdfPCell(new Paragraph("融资信息", fieldNameFont));
		financeInfo.setHorizontalAlignment(Element.ALIGN_CENTER);
		financeInfo.setColspan(10);
        table.addCell(financeInfo);
		//row1 begin
		String[] row1 = new String[]{"融资项目","裸车价","GPS费用","购置税","服务费","保险费","延保费","过户费","加装费","融资手续费"};
		for (int i = 0; i < row1.length; i++) {
			PdfPCell tempFee = new PdfPCell(new Paragraph(row1[i], fieldNameFont));
			tempFee.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(tempFee);
		}
		//row2 begin
		PdfPCell priceField = new PdfPCell(new Paragraph("价款（元）", fieldNameFont));
		priceField.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(priceField);
        PdfPCell salPriceField = new PdfPCell(new Paragraph("11", fieldFont));
        salPriceField.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(salPriceField);
        PdfPCell fpsFeeField = new PdfPCell(new Paragraph("11", fieldFont));
        fpsFeeField.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(fpsFeeField);
        PdfPCell purchaseTaxField = new PdfPCell(new Paragraph("11", fieldFont));
        purchaseTaxField.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(purchaseTaxField);
        PdfPCell serviceFeeField = new PdfPCell(new Paragraph("11", fieldFont));
        serviceFeeField.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(serviceFeeField);
        PdfPCell insuranceFeeField = new PdfPCell(new Paragraph("11", fieldFont));
        insuranceFeeField.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(insuranceFeeField);
        PdfPCell delayFeeField = new PdfPCell(new Paragraph("11", fieldFont));
        delayFeeField.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(delayFeeField);
        PdfPCell transferFeeField = new PdfPCell(new Paragraph("11", fieldFont));
        transferFeeField.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(transferFeeField);
        PdfPCell addonFeeField = new PdfPCell(new Paragraph("11", fieldFont));
        addonFeeField.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(addonFeeField);
        PdfPCell fiannceFee = new PdfPCell(new Paragraph("11", fieldFont));
        fiannceFee.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(fiannceFee);
      //row3 begin
  		String[] row3 = new String[]{"首付比例/抵押比率","利率","保证金（元）","首付款","融资金额","融资期限","月供款（元）","还款方式","约定还款日"};
  		for (int i = 0; i < row3.length; i++) {
  			PdfPCell tempFee = new PdfPCell(new Paragraph(row3[i], fieldNameFont));
  			tempFee.setHorizontalAlignment(Element.ALIGN_CENTER);
  			if(i == row3.length-1)
  				tempFee.setColspan(2);
  	        table.addCell(tempFee);
  		}
  		//row4 begin
  		PdfPCell initPayPercent = new PdfPCell(new Paragraph("11", fieldFont));
  		initPayPercent.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(initPayPercent);
        PdfPCell rate = new PdfPCell(new Paragraph("11", fieldFont));
        rate.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(rate);
        PdfPCell colatteral = new PdfPCell(new Paragraph("11", fieldFont));
        colatteral.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(colatteral);
        PdfPCell initPayAmount = new PdfPCell(new Paragraph("11", fieldFont));
        initPayAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(initPayAmount);
        PdfPCell financeAmount = new PdfPCell(new Paragraph("11", fieldFont));
        financeAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(financeAmount);
        PdfPCell period = new PdfPCell(new Paragraph("11", fieldFont));
        period.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(period);
        PdfPCell monthRepay = new PdfPCell(new Paragraph("11", fieldFont));//月供
        monthRepay.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(monthRepay);
        PdfPCell repayMode = new PdfPCell(new Paragraph("11", fieldFont));//还款方式
        repayMode.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(repayMode);
        PdfPCell repayDate = new PdfPCell(new Paragraph("11", fieldFont));//还款日期
        repayDate.setHorizontalAlignment(Element.ALIGN_CENTER);
        repayDate.setColspan(2);
        table.addCell(repayDate);
		
	}
	 public void createCarInfo(PdfPTable table,Font fieldNameFont,Font fieldFont){
		
		 PdfPCell applyInfo = new PdfPCell(new Paragraph("车辆信息", fieldNameFont));
        applyInfo.setColspan(10);
        applyInfo.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(applyInfo);
        
        PdfPCell carModel = new PdfPCell(new Paragraph("车型", fieldNameFont));
        carModel.setHorizontalAlignment(Element.ALIGN_CENTER);
        carModel.setColspan(3);
        table.addCell(carModel);
        
        PdfPCell carColor = new PdfPCell(new Paragraph("颜色", fieldNameFont));
        carColor.setHorizontalAlignment(Element.ALIGN_CENTER);
        carColor.setColspan(1);
        table.addCell(carColor);
        
        PdfPCell carVin = new PdfPCell(new Paragraph("车架号", fieldNameFont));
        carVin.setHorizontalAlignment(Element.ALIGN_CENTER);
        carVin.setColspan(2);
        table.addCell(carVin);
        
        PdfPCell carEngineNo = new PdfPCell(new Paragraph("发动机号", fieldNameFont));
        carEngineNo.setHorizontalAlignment(Element.ALIGN_CENTER);
        carEngineNo.setColspan(2);
        table.addCell(carEngineNo);
        
        PdfPCell dealPrice = new PdfPCell(new Paragraph("出租与承租方协议价（元）", fieldNameFont));
        dealPrice.setHorizontalAlignment(Element.ALIGN_CENTER);
        dealPrice.setColspan(2);
        table.addCell(dealPrice);
        
        for (int i = 0; i < 5; i++) {
        	PdfPCell carModelField = new PdfPCell(new Paragraph("福田汽车 伽途ix5 2016款 1.5L智悦型DAM15DL", fieldFont));
        	carModelField.setHorizontalAlignment(Element.ALIGN_LEFT);
        	carModelField.setColspan(3);
            table.addCell(carModelField);
            PdfPCell carColorField = new PdfPCell(new Paragraph("火星红", fieldFont));
            carColorField.setHorizontalAlignment(Element.ALIGN_LEFT);
            carColorField.setColspan(1);
            table.addCell(carColorField);
            PdfPCell carVinField = new PdfPCell(new Paragraph("LVCP2GVA0GU206830", fieldFont));
            carVinField.setHorizontalAlignment(Element.ALIGN_LEFT);
            carVinField.setColspan(2);
            table.addCell(carVinField);
            PdfPCell carEngineNoField = new PdfPCell(new Paragraph("169009806-TCV", fieldFont));
            carEngineNoField.setHorizontalAlignment(Element.ALIGN_LEFT);
            carEngineNoField.setColspan(2);
            table.addCell(carEngineNoField);
            PdfPCell dealPriceField = new PdfPCell(new Paragraph("43900.00", fieldFont));
            dealPriceField.setHorizontalAlignment(Element.ALIGN_LEFT);
            dealPriceField.setColspan(2);
            table.addCell(dealPriceField);
		}
	 }
	 
	 public void createTenantInfo(PdfPTable table,Font fieldNameFont,Font fieldFont){
		 PdfPCell applyInfo = new PdfPCell(new Paragraph("申请人信息", fieldNameFont));
        applyInfo.setColspan(10);
        applyInfo.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(applyInfo);
        
        PdfPCell tenant = new PdfPCell(new Paragraph("承租人:", fieldNameFont));
        tenant.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tenant.setColspan(1);
        table.addCell(tenant);
        PdfPCell tenantField = new PdfPCell(new Paragraph("王自龙", fieldFont));
        tenantField.setHorizontalAlignment(Element.ALIGN_LEFT);
        tenantField.setColspan(2);
        table.addCell(tenantField);
        
        PdfPCell idNo = new PdfPCell(new Paragraph("证件号码：", fieldNameFont));
        idNo.setHorizontalAlignment(Element.ALIGN_RIGHT);
        idNo.setColspan(1);
        table.addCell(idNo);
        PdfPCell idNoField = new PdfPCell(new Paragraph("622424199412084417", fieldFont));
        idNoField.setHorizontalAlignment(Element.ALIGN_LEFT);
        idNoField.setColspan(2);
        table.addCell(idNoField);
        
        PdfPCell mobile = new PdfPCell(new Paragraph("联系电话：", fieldNameFont));
        mobile.setHorizontalAlignment(Element.ALIGN_RIGHT);
        mobile.setColspan(1);
        table.addCell(mobile);
        PdfPCell mobileField = new PdfPCell(new Paragraph("18709513674", fieldFont));
        mobileField.setHorizontalAlignment(Element.ALIGN_LEFT);
        mobileField.setColspan(3);
        table.addCell(mobileField);

        
        PdfPCell colessee = new PdfPCell(new Paragraph("共同借款人：", fieldNameFont));
        colessee.setHorizontalAlignment(Element.ALIGN_RIGHT);
        colessee.setColspan(1);
        table.addCell(colessee);
        PdfPCell colesseeField = new PdfPCell(new Paragraph("王自龙", fieldFont));
        colesseeField.setHorizontalAlignment(Element.ALIGN_LEFT);
        colesseeField.setColspan(2);
        table.addCell(colesseeField);
        
        PdfPCell clIdNo = new PdfPCell(new Paragraph("证件号码：", fieldNameFont));
        clIdNo.setHorizontalAlignment(Element.ALIGN_RIGHT);
        clIdNo.setColspan(1);
        table.addCell(clIdNo);
        PdfPCell cIdNoField = new PdfPCell(new Paragraph("622424199412084417", fieldFont));
        cIdNoField.setHorizontalAlignment(Element.ALIGN_LEFT);
        cIdNoField.setColspan(2);
        table.addCell(cIdNoField);
        
        PdfPCell clMobile = new PdfPCell(new Paragraph("联系电话：", fieldNameFont));
        clMobile.setHorizontalAlignment(Element.ALIGN_RIGHT);
        clMobile.setColspan(1);
        table.addCell(clMobile);
        PdfPCell clMobileField = new PdfPCell(new Paragraph("18709513674", fieldFont));
        clMobileField.setHorizontalAlignment(Element.ALIGN_LEFT);
        clMobileField.setColspan(3);
        table.addCell(clMobileField);
	 }
	
	public void createPdf(){
//		Rectangle small = new Rectangle(1040,100);
//		Document document = new Document(small, 10, 20, 20, 20);
		Document document = new Document();
		PdfWriter pdfWriter = null;
		try {
			BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
//			BaseFont bf =BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//			BaseFont strongBf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(PdfCreateMain.DEST));
			document.open();
			PdfPTable table = new PdfPTable(10);
//			table.setTotalWidth(new float[]{100,100,100,100,100,100,100,100,100,100});
			table.setWidthPercentage(new Float(110));
			Font titleFont = new Font(bf,16,Font.BOLD);
			Font orgFont = new Font(bf,12,Font.BOLD);//经销商字体
			Font welcFont = new Font(bf,10,Font.NORMAL);//首段字体
			Font fieldNameFont = new Font(bf,8,Font.BOLD);//字段名字体
			Font fieldFont = new Font(bf,8,Font.NORMAL);//字段值字体（正文字体）
			Font underLineFont = new Font(bf,10,Font.UNDERLINE);//下划线字体
			
		
			Image logoImage2 = Image.getInstance("print/logo.jpg");
			logoImage2.scalePercent(4);
			logoImage2.setAbsolutePosition(10, 810);
			document.add(logoImage2);
			
			/*Image logoImage = Image.getInstance("print/logo.jpg");
			logoImage.setWidthPercentage(15);
			PdfPCell logoCell = new PdfPCell();
			logoCell.addElement(logoImage);
			logoCell.setColspan(10);
			logoCell.setBorder(Rectangle.NO_BORDER);
			logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(logoCell);*/
			
			Paragraph title = new Paragraph("核准通知函", titleFont);
			PdfPCell titleCell = new PdfPCell(title);
			titleCell.setFixedHeight(30);
			titleCell.setBorder(Rectangle.NO_BORDER);
			titleCell.setColspan(10);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(titleCell);
	        
	        PdfPCell branchCell = new PdfPCell();
	        String branchName = "承租人姓名";
	        Paragraph branchPgh = new Paragraph("尊敬的:"+branchName+":",orgFont);
	        branchCell.setColspan(10);
	        branchCell.setBorder(Rectangle.NO_BORDER);
	        branchCell.addElement(branchPgh);
	        table.addCell(branchCell);
	        
	        PdfPCell welcCell = new PdfPCell();
	        String wel1 = "感谢贵公司提交客户：";
	        String wel2 = "王子龙"+"      ";//承租人
	        String wel3 = "的租赁申请，该项租赁申请，经审已被核准，具体核准日期为：";
	        String wel4 = "2016/10/27 17:10:01";//核准日期
	        String wel5 = "。敬请确认以下信息，如有错误请务必及时告知。本核准函有效期为60日（自核准日次日起算）。十分感谢您的支持！";
//	        Phrase firstPgh = new Phrase();
	        Paragraph firstPgh = new Paragraph();
	        firstPgh.add(new Phrase(wel1,welcFont));
	        firstPgh.add(new Phrase(wel2,underLineFont));
	        firstPgh.add(new Phrase(wel3,welcFont));
	        firstPgh.add(new Phrase(wel4,underLineFont));
	        firstPgh.add(new Phrase(wel5,welcFont));
	        welcCell.setColspan(10);
	        welcCell.setBorder(Rectangle.NO_BORDER);
	        welcCell.addElement(firstPgh);
	        table.addCell(welcCell);
	        
	       this.createTenantInfo(table, fieldNameFont, fieldFont);
	       this.createCarInfo(table, fieldNameFont, fieldFont);
	       this.createFinanceInfo(table, fieldNameFont, fieldFont);
	       this.createAddCondition(table, fieldNameFont, fieldFont);
	       this.createImportantTips(table, fieldNameFont, fieldFont);
	       this.createSignDate(table, fieldNameFont, fieldFont);
	        document.add(table);
			document.close();
			pdfWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * tom 2016年10月28日
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, DocumentException {
		PdfCreateMain pcm = new PdfCreateMain();
		pcm.createPdf();
//		pcm.createSmallPdf(PdfCreateMain.DEST_SMALL);
	}

}
