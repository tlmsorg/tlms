package com.tlms.docs.check;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ChinaPayCheck {

	public String leftPad(String src,String padTag,int length) {
		int padLength = length - src.length();
		for (int i = 0; i < padLength; i++) {
			src = padTag + src;
		}
		return src;
	}
	public List<String> readSeccessFile() throws Exception{
		List<String> list = new ArrayList<String>();
		File successFile = new File("C:\\Users\\160068\\Desktop\\数据匹配\\交易成功订单号(刘霞).txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(successFile), "UTF-8"));
		String orderNo = "";
		while((orderNo = br.readLine()) != null) {
//			System.out.println(orderNo);
			list.add(this.leftPad(orderNo, "0", 16));
		}
		return list;
	}
	
	public void readAllRecord(List<String> successList) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\160068\\Desktop\\数据匹配\\银联新一代兼容版-20180508所有交易.xlsx")));
		XSSFSheet sheet = null;
		//银联未成功列表
        List<XSSFRow> failRowList = new ArrayList<XSSFRow>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
            sheet = workbook.getSheetAt(i);
            for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
            	System.out.println("第"+j+"行");
                XSSFRow row = sheet.getRow(j);
                //true:交易失败
                boolean isFail = true;
                if (row != null) {
                    for (int k = 0; k < 14; k++) {// getLastCellNum，是获取最后一个不为空的列是第几个
                        if(k == 1) {
                        	XSSFCell cell = row.getCell(k);
                        	Object cellValue = null;
                        	String cellValueStr = "";
                        	//数字：取整数
                        	if(cell.getCellType() == 0) {
                        		cellValue =  row.getCell(k).getNumericCellValue();
                        		cellValueStr = cellValue+"";
                        		if(cellValueStr.split("\\.").length > 1)
                        			cellValueStr = cellValueStr.split("\\.")[0];
                        	}
                        		
                        	if(cell.getCellType() == 1) {
                        		cellValue =  row.getCell(k).getStringCellValue();
                        		cellValueStr = cellValue+"";
                        	}
                        	
                        	/**
                        	 * 银联成功订单号
                        	 */
                        	for (String orderNo : successList) {
								if(orderNo.equals(cellValueStr)) {
									isFail = false;
									break;
								}
							}
                        }
                    }
                    if(isFail) {
                    	failRowList.add(row);
                    }
                }
//                System.out.println(""); // 读完一行后换行
            }
            System.out.println("读取sheet表：" + workbook.getSheetName(i) + " 完成");
        }
        
        /**
         * 银联未成功：文件写入
         */
//        FileInputStream fis = new FileInputStream(new File("C:\\Users\\160068\\Desktop\\数据匹配\\失败记录.xlsx"));
//        XSSFWorkbook workbook2 = new XSSFWorkbook(fis); // 读取的文件
//        fis.close();
        XSSFWorkbook workbook2 = new XSSFWorkbook();
        XSSFSheet sheet2 = null;
//        int i = workbook.getSheetIndex("xt"); // sheet表名
//        sheet2 = workbook2.getSheetAt(0);
        sheet2 = workbook2.createSheet();
        
        for (int i = 0; i < failRowList.size(); i++) {
        	System.out.println("扫描记录："+i);
        	FileOutputStream fo = new FileOutputStream("C:\\Users\\160068\\Desktop\\数据匹配\\银联未成功记录(基于所有交易).xlsx"); // 输出到文件
        	XSSFRow row = sheet2.createRow(i);
        	XSSFRow failRow = failRowList.get(i);
        	for (int j = 0; j < 15; j++) {
//        		System.out.println(j);
        		failRow.getCell(j);
        		XSSFCell cell = row.createCell(j);
        		XSSFCell failCell = failRow.getCell(j);
//        		cell.setCellValue(failCell.getStringCellValue());
        		//0:数字  1：字符串 
        		/*System.out.println(failCell.getCellType());
        		System.out.println(XSSFCell.CELL_TYPE_NUMERIC);
        		System.out.println(XSSFCell.CELL_TYPE_STRING);
        		System.out.println(XSSFCell.CELL_TYPE_FORMULA);*/
        		if(failCell != null) {
        			if(failCell.getCellType() == 0)
            			cell.setCellValue(failCell.getNumericCellValue());
                	if(failCell.getCellType() == 1)
                		cell.setCellValue(failCell.getStringCellValue());
        		}
        		
        		
			}
//        	row.createCell(0).setCellValue("5555");
        	workbook2.write(fo);
        	fo.flush();
        	fo.close();
		}
       
        
       /* XSSFRow row = sheet.getRow(0); // 获取指定的行对象，无数据则为空，需要创建
        if (row == null) {
            row = sheet.createRow(0); // 该行无数据，创建行对象
        }
 
        Cell cell = row.createCell(1); // 创建指定单元格对象。如本身有数据会替换掉
        cell.setCellValue("tt"); // 设置内容
 
        FileOutputStream fo = new FileOutputStream("E:/temp/t1.xls"); // 输出到文件
        workbook.write(fo);*/
	}
	public static void main(String[] args) throws Exception {
		ChinaPayCheck check = new ChinaPayCheck();
		List<String> successList = check.readSeccessFile();
		check.readAllRecord(successList);
		System.out.println(successList.size());
	}

}
