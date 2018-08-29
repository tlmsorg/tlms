package com.tlms.docs.excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class ExcelParse {

	/**
	 * 字符串转日期 tom 2016年11月7日
	 * 
	 * @param date
	 * @param formateStr
	 * @return
	 */
	public static Date formateString2Date(String date, String formateStr) {
		SimpleDateFormat formate = new SimpleDateFormat(formateStr);
		Date dateRet = null;
		try {
			dateRet = formate.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateRet;
	}

	
	public static String getCellValue(HSSFRow row,int colIndex) {
		String cellValue = "";
		HSSFCell cell = row.getCell(colIndex);
		switch(cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if(HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
				cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			}else {
				cellValue = cell.getNumericCellValue() + "";
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = cell.getBooleanCellValue()+ "";
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			cellValue = "单元格格式错误";
			break;
		default:
			cellValue = "单元格格式未知";
			break;
		}
		
		return cellValue;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(ExcelParse.class.getResource(""));
		File file = new File("d:\\test.xls");
		FileInputStream fis = new FileInputStream(file);

		POIFSFileSystem pfs = new POIFSFileSystem(fis);
		HSSFWorkbook workbook = new HSSFWorkbook(pfs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int colNum = 24;
		int rowNum = sheet.getLastRowNum();
		List<IcbcBill> icbcBillList = new ArrayList<IcbcBill>();
		for (int i = 1; i <= rowNum; i++) {
			System.out.println("**************当前行："+i+"*****************");
			HSSFRow row  = sheet.getRow(i);
			int colIndex = 0;
			IcbcBill icbcBill = new IcbcBill();
			icbcBill.setId(System.currentTimeMillis()+"");
			icbcBill.setIdIcbc(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setContractNo(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setIdNo(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setName(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setMobile(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setHomeTel(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setUnitTel(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setIcbcBranchNo(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setFinanceAmount(Double.parseDouble(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setFinanceAvg(Double.parseDouble(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setServiceFee(Double.parseDouble(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setServiceFeeAvg(Double.parseDouble(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setPeriodTotal(Integer.parseInt(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setPeriodRemain(Integer.parseInt(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setInstallmentDate(ExcelParse.formateString2Date(ExcelParse.getCellValue(row, colIndex++), "yyyy-MM-dd"));
			icbcBill.setBillDay(Integer.parseInt(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setFeeYestoday(Double.parseDouble(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setFeeBestRepay(Double.parseDouble(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setOverdueTimes(Integer.parseInt(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setLastOverdueDate(ExcelParse.formateString2Date(ExcelParse.getCellValue(row, colIndex++), "yyyy-MM-dd"));
			icbcBill.setOverdueTimesContinue(Integer.parseInt(ExcelParse.getCellValue(row, colIndex++)));
			icbcBill.setOrgNo(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setTransOrgName(ExcelParse.getCellValue(row, colIndex++));
			icbcBill.setDataDate(ExcelParse.formateString2Date(ExcelParse.getCellValue(row, colIndex++), "yyyyMMdd"));
			icbcBill.setOverdueDays(Integer.parseInt(ExcelParse.getCellValue(row, colIndex++)));
			String overdueDaysBaseDate = ExcelParse.getCellValue(row, colIndex++);
			if(overdueDaysBaseDate != null && !"".equals(overdueDaysBaseDate.trim()))
				icbcBill.setOverdueDaysBasedate(ExcelParse.formateString2Date(overdueDaysBaseDate, "yyyy-MM-dd"));
			icbcBillList.add(icbcBill);
		}
		System.out.println(icbcBillList.size());
		System.out.println("**************工行账单文件解析完成*****************");
	}
}
