package press3Initialzers_Processors;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class ExcelReadAndWriteData {
	
	//@Test()
	public void testSheet() throws InterruptedException, IOException {
		GenericExcelProcesser genericExcelProcesser = new GenericExcelProcesser("create",
				"D:\\press3Automation\\Configs\\"+System.currentTimeMillis()+".xlsx");
		
		long sheetNAme = System.currentTimeMillis();
		String newSheetName = "agent" + sheetNAme;
		genericExcelProcesser.addSheet(newSheetName);
		
		String[][] profilsData = storeProfilesData.profileData; 
//		genericExcelProcesser.addColumn(newSheetName, "profileName");
//		genericExcelProcesser.addColumn(newSheetName, "CallsCount");
//		genericExcelProcesser.addColumn(newSheetName, "LoginTime");
//		genericExcelProcesser.addColumn(newSheetName, "LogOutTime");
//		
//		genericExcelProcesser.setCellData(newSheetName, "profileName" , 2, "srikanth");
//		genericExcelProcesser.setCellData(newSheetName, "profileName" , 3, "sharan");
//		genericExcelProcesser.setCellData(newSheetName, "profileName" , 4, "surya");
//		genericExcelProcesser.setCellData(newSheetName, "profileName" , 5, "srinu");
//		genericExcelProcesser.setCellData(newSheetName, "profileName" , 6, "koushik");
//		
//		
//		genericExcelProcesser.setCellData(newSheetName, "CallsCount" , 2, "50");
//		genericExcelProcesser.setCellData(newSheetName, "CallsCount" , 3, "50");
//		genericExcelProcesser.setCellData(newSheetName, "CallsCount" , 4, "50");
//		genericExcelProcesser.setCellData(newSheetName, "CallsCount" , 5, "50");
//		genericExcelProcesser.setCellData(newSheetName, "CallsCount" , 6, "50");
//		
		System.out.println("before writing excel");
	    for (int row = 0; row < profilsData.length; row++) {
            for (int col = 0; col < profilsData[row].length; col++) {
            	//profilsData[row][col] = row * col;
            	if(row == 0)
            	{
            		genericExcelProcesser.addColumn(newSheetName, profilsData[row][col]);
            	}
            	else
            	{
            		genericExcelProcesser.setCellData(newSheetName, profilsData[0][col] , row+1,  profilsData[row][col]);
            	}
              //  System.out.print(profilsData[row][col] + "\t");
            }
            //System.out.println();
        }
	    System.out.println("After writing excel");
	}

//	public static void main(String args[]) throws InterruptedException, IOException {
//		ExcelReadAndWriteData data = new ExcelReadAndWriteData();
//		data.testSheet();
//	}

}
