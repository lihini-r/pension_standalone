package org.pensions.data.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.RuntimeErrorException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pensions.data.DbConnManagerPMS;
import org.pensions.data.dao.BankDAO;
import org.pensions.data.dao.GratuityDAO;
import org.pensions.data.dao.PensionPointDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.enumarations.EmployeeRole;
import org.pensions.model.BranchDTO;
import org.pensions.model.GratuityDTO;
import org.pensions.model.GratuityListDTO;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionPointDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.model.RegularPensionerDTO;
import org.pensions.model.lists.GratuityList;
import org.pensions.model.lists.PensionList;
import org.pensions.model.lists.PensionerList;
import org.pensions.session.UserSession;
import org.pensions.view.util.NotificationManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class GratuityExcelReportGenerator {
	private ResultSet rs = null;
	private ResultSet resultSet = null; 
	private static final String ROOT_PATH = "C:\\Users\\Public\\Documents\\";
	private String currenttimestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
	private int state = 0;
	/**
	 * generate xcel
	 * @param datePickerValue
	 */
	public void generateExcelSheet(String datePickerValue1 , String datePickerValue2 ){
		try {
			try {
				
				
				XSSFWorkbook workbook = new XSSFWorkbook();
				
				/**XSSFSheet spreadsheet = workbook.createSheet("BOC");
				XSSFSheet spreadsheet2 = workbook.createSheet("PEOPLES BANK");
				XSSFSheet spreadsheet3 = workbook.createSheet("NSB");
				creatTiltleRow(spreadsheet);
				creatTiltleRow(spreadsheet2);
				creatTiltleRow(spreadsheet3);
				
				int i = 2;
				
				if((UserSession.INSTANCE.getRole().equals(EmployeeRole.DATA_ENTRY.getRole())) ) {
					state = 200;
				}
				else if((UserSession.INSTANCE.getRole().equals(EmployeeRole.POSTAL.getRole())) ){
					state = 100;
				}
				GratuityList lto = new GratuityDAO(1).getGratuityList(state);
				for(GratuityListDTO list : lto.getList()){
					   
					    i = createRow(spreadsheet, i, list ,datePickerValue1,datePickerValue2);
					   
						i = createRowPeoplesBank(spreadsheet2, i, list ,datePickerValue1, datePickerValue2);
						
						i = createRowNSB(spreadsheet3, i, list ,datePickerValue1,datePickerValue2);
			    }**/
				
				 GratuityCategory boc = new GratuityCategory(GratuityCategory.STAT_CIVIL, GratuityCategory.BOC_NAME, GratuityCategory.BOC_CODE, "");
				 GratuityCategory nsb = new GratuityCategory(GratuityCategory.STAT_CIVIL , GratuityCategory.NSB_NAME, GratuityCategory.NSB_CODE, "");
				 GratuityCategory pb = new GratuityCategory(GratuityCategory.STAT_CIVIL , GratuityCategory.PB_NAME, GratuityCategory.PB_CODE, "");
				 
				 //GratuityCategory provincialpb = new GratuityCategory(GratuityCategory.STAT_PROVINCIAL , GratuityCategory.PB_NAME, GratuityCategory.PB_CODE, "");
				// GratuityCategory provincialboc = new GratuityCategory(GratuityCategory.STAT_PROVINCIAL , GratuityCategory.BOC_NAME, GratuityCategory.BOC_CODE, "");
				 //GratuityCategory provincialnsb = new GratuityCategory(GratuityCategory.STAT_PROVINCIAL , GratuityCategory.NSB_NAME, GratuityCategory.NSB_CODE, "");
				 
				 GratuityCategory militarypb = new GratuityCategory(GratuityCategory.STAT_MILITARY , GratuityCategory.PB_NAME, GratuityCategory.PB_CODE, "");
				 GratuityCategory militaryboc = new GratuityCategory(GratuityCategory.STAT_MILITARY , GratuityCategory.BOC_NAME, GratuityCategory.BOC_CODE, "");
				 GratuityCategory militarynsb = new GratuityCategory(GratuityCategory.STAT_MILITARY , GratuityCategory.NSB_NAME, GratuityCategory.NSB_CODE, "");
				 
				 
				 createSpreadSheet(workbook, boc ,datePickerValue1 , datePickerValue2);
				 createSpreadSheet(workbook, nsb , datePickerValue1 , datePickerValue2);
				 createSpreadSheet(workbook, pb , datePickerValue1 , datePickerValue2);
				 
				 //createSpreadSheet(workbook, provincialpb , datePickerValue1 , datePickerValue2);
				 //createSpreadSheet(workbook, provincialboc , datePickerValue1 , datePickerValue2);
				 //createSpreadSheet(workbook, provincialnsb , datePickerValue1 , datePickerValue2);
				 
				 createSpreadSheet(workbook, militarypb , datePickerValue1 , datePickerValue2);
				 createSpreadSheet(workbook, militaryboc , datePickerValue1 , datePickerValue2);
				 createSpreadSheet(workbook, militarynsb , datePickerValue1 , datePickerValue2);
				 
				 
				
				File currentDir = new File(ROOT_PATH);
				String outputDirName = "Gratuity";
			    File outputDir = new File(currentDir.getAbsolutePath() + File.separator + outputDirName);    
			    
			    if(!outputDir.exists()){
			        boolean dirCreated = outputDir.mkdir();
			    }else{
			        System.out.println("Output Directory Already Exists");
			    }


				System.out.println("File created");
				FileOutputStream out = new FileOutputStream(new File(outputDir.getAbsolutePath() + File.separator + currenttimestamp+".xlsx"));
				
				workbook.write(out);
				workbook.close();
				out.close();

				try {
					Desktop.getDesktop().open(new File(outputDir.getAbsolutePath() + File.separator + currenttimestamp+".xlsx"));
				}catch (IOException e) {
					NotificationManager.error("Eoor", e.getMessage());
				}
				NotificationManager.info("Document Created Succefully", "Document Created Succefully.");

			}

			catch(Exception e) {
				NotificationManager.info("Connot Create Document", "Error Occurd while creating Excel sheet");
			}
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Look, an Exception Dialog");
			alert.setContentText("Could not find file blabla.txt!");

			// Create expandable Exception.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			// Set expandable Exception into the dialog pane.
			alert.getDialogPane().setExpandableContent(expContent);
			alert.showAndWait();
		}

	}
	
	
	
/**
 * create new spread sheet	
 * @param workbook
 * @param gratuityCategory
 * @param datePickerValue1
 * @param datePickerValue2
 * @throws ParseException
 */
public void createSpreadSheet(XSSFWorkbook workbook, GratuityCategory gratuityCategory, String datePickerValue1 ,String datePickerValue2) throws ParseException{
		
		XSSFSheet spreadsheet = workbook.createSheet(gratuityCategory.categoryIdNew());
		creatTiltleRowNew(spreadsheet);
		
				
		int i = 3;
		if((UserSession.INSTANCE.getRole().equals(EmployeeRole.DATA_ENTRY.getRole())) ) {
			state = 200;
		}
		else if((UserSession.INSTANCE.getRole().equals(EmployeeRole.POSTAL.getRole())) ){
			state = 100;
		}
		GratuityList lto = new GratuityDAO(1).getGratuityList(state);
		double totalGratuityAmount = 0.0;
		for(GratuityListDTO list : lto.getList()){
		    i = createRow(spreadsheet, i, list, datePickerValue1, datePickerValue2, gratuityCategory, totalGratuityAmount);
		}
		creatTotalRow(spreadsheet,i,totalGratuityAmount);
		creatDateRow(spreadsheet,1 ,datePickerValue1,datePickerValue2,gratuityCategory.categoryIdNew());
		
}
/**
 * create title row
 * @param spreadsheet
 */
private void creatTiltleRowNew(XSSFSheet spreadsheet){
	XSSFRow row=spreadsheet.createRow(2);
	XSSFCell cell;
	
	cell=row.createCell(1);
	cell.setCellValue("PENSION NUMBER");
	cell=row.createCell(2);
	cell.setCellValue("NAME");
	cell=row.createCell(3);
	cell.setCellValue("NIC NUMBER");
	cell=row.createCell(4);
	cell.setCellValue("BRANCH");
	cell=row.createCell(5);
	cell.setCellValue("ACCOUNT NUMBER");
	cell=row.createCell(6);
	cell.setCellValue("GRATUITY AMMOUNT");
	
	
}
/**
 * create total row 
 * @param spreadsheet
 */
private void creatTotalRow(XSSFSheet spreadsheet, int rowIndex ,double totalAmount){
	XSSFRow row=spreadsheet.createRow(rowIndex);
	XSSFCell cell;
	String c = String.valueOf(rowIndex);
	String cellvalue = "SUM(G4:G" + c + ")" ;
	
	cell=row.createCell(1);
	cell.setCellValue("CREATED_BY");
	cell=row.createCell(2);
	cell.setCellValue("Account Head");
	
	cell=row.createCell(5);
	cell.setCellValue("TOTAL VALUE");
	cell=row.createCell(6);
	//cell.setCellValue(totalAmount);
	cell.setCellFormula(cellvalue);
	
	
	
}
/**
 * Display Date range
 * @param spreadsheet
 * @param rowIndex
 * @param totalAmount
 */
private void creatDateRow(XSSFSheet spreadsheet, int rowIndex ,String date1, String date2, String name){
	XSSFRow row=spreadsheet.createRow(rowIndex);
	XSSFCell cell;
	
	cell=row.createCell(1);
	cell.setCellValue("GRATUITY-"+name);
	
	cell=row.createCell(3);
	cell.setCellValue("FROM:");
	cell=row.createCell(4);
	cell.setCellValue(date1);
	
	cell=row.createCell(5);
	cell.setCellValue("TO:");
	cell=row.createCell(6);
	cell.setCellValue(date2);
	
}

/**
 * create row
 * @param spreadsheet
 * @param rowIndex
 * @param lgto
 * @param datePickerValue1
 * @param datePickerValue
 * @param gratuityCategory
 * @return
 * @throws ParseException
 */
private int createRow(XSSFSheet spreadsheet, int rowIndex, GratuityListDTO lgto ,String datePickerValue1, String datePickerValue, GratuityCategory gratuityCategory,double totalGratuityAmount) throws ParseException{
	
	RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(Long.valueOf(lgto.getPensioner().getUrl()));
	PensionList pensionlist = new PensionsDAO(Long.valueOf(lgto.getPensioner().getUrl())).getAllPensions();
	for(PensionDTO pension : pensionlist.getPensions()) {
		GratuityDTO gto = new GratuityDAO(Long.valueOf(lgto.getPensioner().getUrl())).getGratuity(Long.valueOf(regularPensioner.getGratuity().getUrl()));
		BranchDTO branchDto  = new BankDAO().getBank(Integer.valueOf(gto.getBranch().getUrl()));
		PensionPointDTO pensionpoint = new PensionPointDAO().getPensionPoint(pension.getPensionPoint().getUrl());
		
		if((pensionpoint.getStutoryBody().matches(gratuityCategory.getStatutoryId()))){
			if(String.valueOf(branchDto.getBank().getId()).equals(gratuityCategory.getBankCode()) ){
			    
		        XSSFCell cell;
		        XSSFRow row=spreadsheet.createRow(rowIndex);
		        cell=row.createCell(1);
				cell.setCellValue(pension.getId());
				cell=row.createCell(2);
				cell.setCellValue(regularPensioner.getName());
				cell=row.createCell(3);
				cell.setCellValue(regularPensioner.getNic());
				cell=row.createCell(4);
				cell.setCellValue(branchDto.getName());
				cell=row.createCell(5);
				cell.setCellValue(gto.getAccountNumber());
				cell=row.createCell(6);
				cell.setCellValue(gto.getGratuityAmount());
				
				totalGratuityAmount = totalGratuityAmount + (double) gto.getGratuityAmount();
				//System.out.println(totalGratuityAmount);
		        rowIndex++;
		        
		        
			}
		}	
	}
	
	//creatTotalRow(spreadsheet,rowIndex,totalGratuityAmount);
	return rowIndex;
}
	
	

}
