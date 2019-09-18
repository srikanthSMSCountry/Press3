package press3Initialzers_Processors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;

public class GenericExcelProcesser {

	public String Path = "";
	public FileInputStream file;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public String[] coloumnArrays;

	public FileOutputStream fileOut = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public GenericExcelProcesser(String ExcelFilePath) throws IOException {

		this.Path = ExcelFilePath;
		this.file = new FileInputStream(this.Path);
		this.workbook = new XSSFWorkbook(this.file);
		this.sheet = this.workbook.getSheetAt(0);
	}

	public GenericExcelProcesser(String action, String ExcelFilePath) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();

		fileOut = new FileOutputStream(ExcelFilePath);

		workbook.write(fileOut);

		this.Path = ExcelFilePath;
		this.file = new FileInputStream(this.Path);
		this.workbook = new XSSFWorkbook(this.file);
		// this.sheet1 = this.workbook.getSheetAt(0);
	}

	public String[] getColoumnsArray() {
		coloumnArrays = new String[readColsCount()];

		Row row = this.sheet.getRow(0);
		int colsCount = row.getPhysicalNumberOfCells();

		for (int colIndex = 0; colIndex < colsCount; colIndex++) {
			Cell cell = row.getCell(colIndex);

			System.out.println(cell.getStringCellValue());
			System.out.println(colIndex);
			coloumnArrays[colIndex] = cell.getStringCellValue();

		}
		System.out.println("read Data From Excel");
		return coloumnArrays;

	}

	public String readcellValue(int rowIndex, int coloumnIndex) {
		String CellData = "";
		Row row = this.sheet.getRow(rowIndex);

		Cell cell = row.getCell(coloumnIndex);
		if (cell != null) {
			CellData = cell.getStringCellValue();
		}

		return CellData;
	}

	public int readRowsCount() {
		int rowCount = this.sheet.getLastRowNum();
		return rowCount;
	}

	public int readColsCount() {
		Row row = this.sheet.getRow(0);
		int colsCount = row.getPhysicalNumberOfCells();
		return colsCount;
	}

	public String formatCell(int rowIndex, int coloumnIndex) {

		DataFormatter formatter = new DataFormatter();
		String val = formatter.formatCellValue(sheet.getRow(rowIndex).getCell(coloumnIndex));

		return val;
	}

	public boolean addSheet(String sheetname) {

		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);
			fileOut = new FileOutputStream(Path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeSheet(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(this.Path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		try {
			file = new FileInputStream(this.Path);
			workbook = new XSSFWorkbook(file);
			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;

			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);

			fileOut = new FileOutputStream(Path);

			workbook.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addColumn(String sheetName, String colName) {

		try {

			file = new FileInputStream(this.Path);
			workbook = new XSSFWorkbook(file);
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);

			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(colName);
			cell.setCellStyle(style);

			fileOut = new FileOutputStream(Path);
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

}
