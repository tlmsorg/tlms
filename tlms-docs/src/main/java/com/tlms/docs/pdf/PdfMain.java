package com.tlms.docs.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Map;

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
import com.tlms.docs.vo.LeaseConstractVo;
import com.tlms.docs.vo.PdfMgc;

public class PdfMain {
	public final String classPath = PdfMain.class.getClassLoader().getResource("").toString();
	
	public void setAcroFields(AcroFields fields,Object obj){
		Class cls = obj.getClass();
		Field[] fieldArray = cls.getDeclaredFields();
		for (Field field:fieldArray) {
			field.setAccessible(true);
			try {
				if(field.get(obj) != null){
					System.out.println("*************"+field.get(obj));
					if(!"select".equals(field.get(obj)))//"select":复选框已选中;"true"：叉形;"其他字符串"：取消选中复选框
						fields.setField(field.getName(), field.get(obj).toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	/**
	 * 打印共同还租承诺 print colessee promise
	 */
	public void printCp(){
		int period = 12;
		PdfReader pdfReader = null;
		OutputStream os = null;
		PdfStamper pdfStamper = null;
		try {
			pdfReader = new PdfReader(classPath+"/pdf/tpl/7-共同还租承诺书-模板.pdf");
			File file = new File("print/7-共同还租承诺书-print.pdf");
			if(file.exists())
				file.createNewFile();
			os = new FileOutputStream(file);
			System.out.println("*******************:"+file.length());
			pdfStamper = new PdfStamper(pdfReader, os);
			BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
//			BaseFont bf = BaseFont.createFont(classPath+"/MSYH.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf,10,Font.NORMAL);
			AcroFields fields = pdfStamper.getAcroFields();
			Map<String, Item> map = fields.getFields();
			for(Map.Entry<String, Item> entry:map.entrySet()){
				fields.setFieldProperty(entry.getKey(), "textfont", bf, null);
				if("contractNo".equals(entry.getKey()))
					fields.setFieldProperty(entry.getKey(), "textsize", new Float(8), null); 
				else{
					fields.setFieldProperty(entry.getKey(), "textsize", new Float(12), null); 
				}
				System.out.println(entry.getKey()+"|"+fields.getField(entry.getKey()));
			}
			for(int i = 0;i < period;i++){
				fields.setField("fill_"+(i*2+1), "2015-01-01");
				fields.setField("fill_"+(i*2+2), "8657");
			}
//			fields.setFieldProperty("contractNo","textsize",new Float(6),null);
			fields.setField("contractNo", " N220160621068");
			fields.setField("colesseeName", "共租人");
			fields.setField("colesseeIdNo", "500383198808181994");
			fields.setField("tenantName", "承租人");
			fields.setField("tenantIdNo", "500383198808181994");
			fields.setField("relation", "妃子");
			
			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
			pdfReader.close();
			System.out.println("*******************:"+file.length());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印还款提醒 print repay  remind
	 */
	public void printRr(){
		int period = 12;
		PdfReader pdfReader = null;
		OutputStream os = null;
		PdfStamper pdfStamper = null;
		
		try {
			pdfReader = new PdfReader(classPath+"/pdf/tpl/6-还款提醒-模板.pdf");
			File file = new File("print/6-还款提醒-print.pdf");
			if(file.exists())
				file.createNewFile();
			os = new FileOutputStream(file);
			pdfStamper = new PdfStamper(pdfReader, os);
//			BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
			BaseFont bf = BaseFont.createFont(classPath+"/MSYH.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf,10,Font.NORMAL);
			AcroFields fields = pdfStamper.getAcroFields();
			Map<String, Item> map = fields.getFields();
			for(Map.Entry<String, Item> entry:map.entrySet()){
				fields.setFieldProperty(entry.getKey(), "textfont", bf, null);
				fields.setFieldProperty(entry.getKey(), "textsize", new Float(8), null); 
				System.out.println(entry.getKey()+"|"+fields.getField(entry.getKey()));
			}
			for(int i = 0;i < period;i++){
				fields.setField("fill_"+(i*2+1), "2015-01-01");
				fields.setField("fill_"+(i*2+2), "8657");
			}
			fields.setField("accountName", "阿布拉多.艾尼瓦尔.萨迪克买买提");
//			fields.setFieldProperty("contractNo","textsize",new Float(6),null);
			fields.setField("contractNo", " N220160621068");
			fields.setField("totalFinanceAmt", "云南南天电子信息产业股份有限公司");
			fields.setField("period", "12");
			fields.setField("firstRepayDate", "2016-12-11");
			fields.setField("firstRepayFee", "8657");
			
			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
			pdfReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印贷款收据print loan receipt
	 */
	public void printLr(){
		PdfReader pdfReader = null;
		OutputStream os = null;
		PdfStamper pdfStamper = null;
		try {
			pdfReader = new PdfReader(classPath+"/pdf/tpl/5-收据-模板.pdf");
			File file = new File("print/5-收据-print.pdf");
			if(file.exists())
				file.createNewFile();
			os = new FileOutputStream(file);
			pdfStamper = new PdfStamper(pdfReader, os);
			BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
			Font font = new Font(bf,10,Font.NORMAL);
			AcroFields fields = pdfStamper.getAcroFields();
			Map<String, Item> map = fields.getFields();
			for(Map.Entry<String, Item> entry:map.entrySet()){
				fields.setFieldProperty(entry.getKey(), "textfont", bf, null);
				fields.setFieldProperty(entry.getKey(), "textsize", new Float(7), null); 
				System.out.println(entry.getKey()+"|"+fields.getField(entry.getKey()));
			}
			fields.setField("contractNo", "GZZJ-HT2013-JS001");
//			fields.setFieldProperty("contractNo","textsize",new Float(6),null);
			fields.setField("totalLoanAmt", "8563");
			fields.setField("branchName", "云南南天电子信息产业股份有限公司");
			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
			pdfReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印抵押车辆清单print mortgage car list
	 */
	public void printMcl(){
		PdfReader pdfReader = null;
		OutputStream os = null;
		PdfStamper pdfStamper = null;
		int carAmt = 20;//抵押车辆数量
		try {
			pdfReader = new PdfReader(classPath+"/pdf/tpl/4-抵押车辆清单-模板.pdf");
			File file = new File("print/4-抵押车辆清单-print.pdf");
			if(file.exists())
				file.createNewFile();
			os = new FileOutputStream(file);
			pdfStamper = new PdfStamper(pdfReader, os);
			BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
			Font font = new Font(bf,10,Font.NORMAL);
			AcroFields fields = pdfStamper.getAcroFields();
			Map<String, Item> map = fields.getFields();
			for(Map.Entry<String, Item> entry:map.entrySet()){
				fields.setFieldProperty(entry.getKey(), "textfont", bf, null);
				fields.setFieldProperty(entry.getKey(), "textsize", new Float(8), null); 
				System.out.println(entry.getKey()+"|"+fields.getField(entry.getKey()));
			}
			fields.setField("contractNo","200810405234");
			for (int i = 0; i < carAmt; i++) {
				fields.setField("fill_"+(6*i+2),"车牌号"+i+1);
				fields.setField("fill_"+(6*i+3),"品牌及车型"+i+1);
				fields.setField("fill_"+(6*i+4),"车架号"+i+1);
				fields.setField("fill_"+(6*i+5), "发动机号"+i+1);
				fields.setField("fill_"+(6*i+6), "车辆颜色"+i+1);
				fields.setField("fill_"+(6*i+7), "生产商"+i+1);
			}
			fields.setField("carAmt", carAmt+"");
			fields.setField("year", "2016");
			fields.setField("month", "10");
			fields.setField("day", "12");
			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
			pdfReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印车辆交接单print car delievery receipt
	 */
	public void printCdr(){
		PdfReader pdfReader = null;
		OutputStream os = null;
		PdfStamper pdfStamper = null;
		try {
			pdfReader = new PdfReader(classPath+"/pdf/tpl/3-车辆交接单-套打-模板-有背景.pdf");
			File file = new File("print/3-车辆交接单-套打-模板-print.pdf");
			if(file.exists())
				file.createNewFile();
			os = new FileOutputStream(file);
			pdfStamper = new PdfStamper(pdfReader, os);
			BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
			Font font = new Font(bf,10,Font.NORMAL);
			AcroFields fields = pdfStamper.getAcroFields();
			Map<String, Item> map = fields.getFields();
			for(Map.Entry<String, Item> entry:map.entrySet()){
				fields.setFieldProperty(entry.getKey(), "textfont", bf, null);
				fields.setFieldProperty(entry.getKey(), "textsize", new Float(8), null); 
				System.out.println(entry.getKey()+"|"+fields.getField(entry.getKey()));
			}
			fields.setField("carStyle","潽金carStyle租赁有限公司");
			fields.setField("plateNo","plateNo");
			fields.setField("carColor","云南carColor电子信息产业股份有限公司");
			fields.setField("carEngineNo","carEngineNo");
			fields.setField("carVin", "carVin");
			fields.setField("dealerName", "dealerName");
			fields.setField("tenantName", "tenantIdNo");
			fields.setField("tenantIdNo", "tenantIdNo");
			fields.setField("tenantPhone", "tenantPhone");
			fields.setField("tenantSign", "tenantSign");
			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
			pdfReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印租赁合同print lease contract
	 */
	public void printLsc(){
		PdfReader pdfReader = null;
		OutputStream os = null;
		PdfStamper pdfStamper = null;
		try {
			pdfReader = new PdfReader(classPath+"/pdf/tpl/1-融资租赁合同-套打-模板.pdf");
//			pdfReader = new PdfReader(classPath+"/pdf/tpl/checkboxes.pdf");
//			os = new FileOutputStream("d:\\潽金融资租赁有限公司抵押合同B-print.pdf");
//			File file = new File(classPath+"/pdf/print/潽金融资租赁有限公司抵押合同B-print.pdf");
			File file = new File("print/1-融资租赁合同-print.pdf");
			if(file.exists())
				file.createNewFile();
			os = new FileOutputStream(file);
			pdfStamper = new PdfStamper(pdfReader, os);
			BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
//			Font font  = new Font(FontFamily.TIMES_ROMAN, 20);
			Font font = new Font(bf,10,Font.NORMAL);
			AcroFields fields = pdfStamper.getAcroFields();
			Map<String, Item> map = fields.getFields();
			for(Map.Entry<String, Item> entry:map.entrySet()){
				fields.setFieldProperty(entry.getKey(), "textfont", bf, null);
				fields.setFieldProperty(entry.getKey(), "textsize", new Float(6), null); 
				System.out.println(entry.getKey()+"|"+fields.getField(entry.getKey()));
			}
			LeaseConstractVo leaseConstract = new LeaseConstractVo();
			leaseConstract.setName1("name1");
			leaseConstract.setPhone1("phone1");
			leaseConstract.setName2("name2");
			leaseConstract.setPhone2("phone2");
			leaseConstract.setIsTenant("");
//			leaseConstract.setField("isGuarantor");
			
			leaseConstract.setIsIdCard1("select");
			leaseConstract.setIsPassport1("unselect");
			leaseConstract.setIsOrgCodeId1("unselect");
			leaseConstract.setIsIdCard2("unselect");
			leaseConstract.setIsPassport2("select");
			leaseConstract.setIsOrgCodeId2("unselect");
			
			leaseConstract.setCtfNo1("ctfNo1");
			leaseConstract.setAddress1("address1");
			leaseConstract.setCtfNo2("ctfNo2");
			leaseConstract.setAddress2("address2");
			
			leaseConstract.setIsSalePrice("select");
			leaseConstract.setIsPurchaseTax("select");
			leaseConstract.setIsGpsFee("select");
			leaseConstract.setIsFinanceFee("select");
			leaseConstract.setIsServiceFee("select");
			leaseConstract.setIsTransferFee("unselect");
			leaseConstract.setIsInsruanceFee("unselect");
			leaseConstract.setIsAddonFee("select");
			leaseConstract.setIsDelayInsuranceFee("select");
			/*
			leaseConstract.setField("salePrice");
			leaseConstract.setField("purchaseTax");
			leaseConstract.setField("gpsFee");
			leaseConstract.setField("financeFee");
			leaseConstract.setField("serviceFee");
			leaseConstract.setField("transferFee");
			leaseConstract.setField("insruanceFee");
			leaseConstract.setField("addonFee");
			leaseConstract.setField("delayInsuranceFee");
			
			leaseConstract.setField("totalCarPrice");
			leaseConstract.setField("addFee");
			leaseConstract.setField("collateral");
			leaseConstract.setField("initPayAmount");
			leaseConstract.setField("totalFianceAmt");
			leaseConstract.setField("totalLoanAmt");
			leaseConstract.setField("monthRent");
			leaseConstract.setField("manageFee");
			leaseConstract.setField("repayDate");
			
			leaseConstract.setField("plateNo1");
			leaseConstract.setField("plateNo2");
			leaseConstract.setField("plateNo3");
			leaseConstract.setField("carBrand1");
			leaseConstract.setField("carBrand2");
			leaseConstract.setField("carBrand3");
			leaseConstract.setField("carVin1");
			leaseConstract.setField("carVin2");
			leaseConstract.setField("carVin3");
			leaseConstract.setField("carEngine1");
			leaseConstract.setField("carEngine2");
			leaseConstract.setField("carEngine3");
			leaseConstract.setField("carColor1");
			leaseConstract.setField("carColor2");
			leaseConstract.setField("carColor3");
			leaseConstract.setField("carManu1");
			leaseConstract.setField("carManu2");
			leaseConstract.setField("carManu3");
			leaseConstract.setField("startYear");
			leaseConstract.setField("startMonth");
			leaseConstract.setField("startDay");
			leaseConstract.setField("endYear");
			leaseConstract.setField("endMonth");
			leaseConstract.setField("endDay");
			leaseConstract.setField("totalMonth");
			
			leaseConstract.setField("loanAcctNo");
			leaseConstract.setField("loadBankName");
			leaseConstract.setField("loadAcctName");
			leaseConstract.setField("repayAcctNo");
			leaseConstract.setField("repayBankName");
			leaseConstract.setField("repayAcctName");*/
			
			this.setAcroFields(fields, leaseConstract);
			System.out.println("222222222222"+fields.getField("name1"));
			
			
			
//			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
			pdfReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印抵押合同B版  print mortgatge contract  B 
	 */
	public void printMgcB(){
		PdfReader pdfReader = null;
		OutputStream os = null;
		PdfStamper pdfStamper = null;
		try {
			pdfReader = new PdfReader(classPath+"/pdf/tpl/2-抵押合同（版本A）-套打-有背景.pdf");
//			os = new FileOutputStream("d:\\潽金融资租赁有限公司抵押合同B-print.pdf");
//			File file = new File(classPath+"/pdf/print/潽金融资租赁有限公司抵押合同B-print.pdf");
			File file = new File("print/2-抵押合同（版本B）-套打-print.pdf");
			if(file.exists())
				file.createNewFile();
			os = new FileOutputStream(file);
			pdfStamper = new PdfStamper(pdfReader, os);
			BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);
			Font font  = new Font(FontFamily.TIMES_ROMAN, 20);
			AcroFields fields = pdfStamper.getAcroFields();
			Map<String, Item> map = fields.getFields();
			for(Map.Entry<String, Item> entry:map.entrySet()){
				System.out.println(entry.getKey());
			}
			PdfMgc pdfMgc = new PdfMgc();
			fields.setField("contactNo1", "200810405234");
//			fields.setFieldProperty("contactNo1", "200810405234", bf, null);
			fields.setField("tenant","ll潽金公司");
			fields.setField("idNo","ll500383198808181994");
			fields.setField("contactNo2","ll200810405234");
			fields.setField("","ll200810405234");
			fields.setField("financeAmt","ll10000");
			fields.setField("financeAmtChn","ll壹万元整");
			fields.setField("pledgerName","ll王二娃");
			fields.setField("pledgerCtftype","ll身份证");
			fields.setField("pledgerCtfNo","ll500383198808181996");
			fields.setField("pledgerPhone","ll18723290701");
			fields.setField("pledgerAddress","ll重庆渝北区");
			
			fields.setField("carBrand", "ll奔驰");
			fields.setField("carEnginNo", "ll奔驰发动机250");
			fields.setField("carModelNo", "ll奔驰ML300");
			fields.setField("carColor", "ll黑色");
			fields.setField("carFrameNo", "ll车架号110");
			fields.setField("carPlateNo", "ll渝A8888");
			
			fields.setField("startYear","2015");
			fields.setField("startMonth","10");
			fields.setField("startDay","21");
			fields.setField("endYear","2016");
			fields.setField("endMonth","11");
			fields.setField("endDay","14");
			
			
			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
			pdfReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void pdfReader(){
		PdfReader reader;
		try {
//			OutputStream os = new FileOutputStream(new File("d:/pdfWrite.pdf"));
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			reader = new PdfReader(classPath+"/潽金融资租赁有限公司抵押合同B-模板.pdf");
			PdfStamper ps = new PdfStamper(reader, new FileOutputStream("d:\\test.pdf"));
			AcroFields fields = ps.getAcroFields();
//			Iterator it = (Iterator) fields.getFields().keySet().iterator();
			Map<String, Item> map = fields.getFields();
			BaseFont bf = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", false);

			for(Map.Entry<String, Item> entry:map.entrySet()){
				String key = entry.getKey();
				Item item = entry.getValue();
//				System.out.println(key);
//				System.out.println(item);
			}
			fields.setField("date", "111");
			fields.setFieldProperty("date", "发多少块结合", bf, null);
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
	public void createPdf() throws DocumentException, IOException{
		Document document = new Document(PageSize.A4, 20, 20, 40, 40);
		PdfWriter.getInstance(document, new FileOutputStream(new File("d:\\itext.pdf")));
		System.out.println();
		String classPath = PdfMain.class.getClassLoader().getResource("").toString();
		BaseFont bf = BaseFont.createFont(classPath+"/MSYH.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//		BaseFont bf =BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);    
//		Font font = new Font(FontFamily.COURIER, 19);
//		Font font = new Font(baseFont, 12,Font.NORMAL,Color.BLACK);
		Font font = new Font(bf, 12);
		document.open();
		PdfPTable pdfTable = new PdfPTable(1);
		PdfPCell pdfCell = new PdfPCell();
//		Paragraph paragraph = new Paragraph();
		//潽金融资租赁有限公司抵押合同
//		 new Paragraph("潽金融资租赁有限公司抵押合同",baseFont)
		Paragraph paragraph = new Paragraph("潽金融资租赁有限公司抵押合同", font);

//		for (int i = 0; i < 10; i++) {
//			Chunk chunk = new Chunk("This is a sentence which is long " + i + ". \n");
//			paragraph.add(chunk);
//		}
		pdfCell.addElement(paragraph);
		float[] width = {500f};
		pdfTable.setTotalWidth(width);
		pdfTable.addCell(pdfCell);
		document.add(pdfTable);
		document.close();
		System.out.println(document.isOpen());
	}
	public static void main(String[] args) {
		try {
			PdfMain pdfMain = new PdfMain();
//			pdfMain.pdfReader();
			pdfMain.printLsc();
		/*	pdfMain.printMgcB();
			pdfMain.printCdr();
			pdfMain.printMcl();
			pdfMain.printLr();
			pdfMain.printRr();
			pdfMain.printCp();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
