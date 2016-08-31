package org.pensions.data.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pensions.data.dao.BankDAO;
import org.pensions.data.dao.GratuityDAO;
import org.pensions.data.dao.HistoryDAO;
import org.pensions.data.dao.PensionPointDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.model.BranchDTO;
import org.pensions.model.GratuityDTO;
import org.pensions.model.GratuityListDTO;
import org.pensions.model.GratuityLogDTO;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionLogDTO;
import org.pensions.model.PensionPointDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.model.RegularPensionerDTO;
import org.pensions.model.lists.GratuityList;
import org.pensions.model.lists.GratuityLogList;
import org.pensions.model.lists.History;
import org.pensions.model.lists.PensionList;
import org.pensions.model.lists.PensionerList;
import org.pensions.view.util.NotificationManager;

public class DeductionsExcelReportGenerator {
	
	private static final String ROOT_PATH = "C:\\Users\\Public\\Documents\\";
	//private static final String ROOT_PATH = "D:\\";
	private String currenttimestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
	
	/**
	 * generate xcel
	 * @param datePickerValue
	 */
	public void generateExcelSheet(String datePickerValue1 , String datePickerValue2){
		try {
			try {
				
				
				XSSFWorkbook workbook = new XSSFWorkbook();
				
				/**XSSFSheet spreadsheet1 = workbook.createSheet("CENTRAL");
				XSSFSheet spreadsheet2 = workbook.createSheet("PROVINCIAL-BOC");
				XSSFSheet spreadsheet3 = workbook.createSheet("PROVINCIAL-PB");
				XSSFSheet spreadsheet4 = workbook.createSheet("MILITARY-ARMY-BOC");
				
				creatTiltleRow(spreadsheet1);
				creatTiltleRow(spreadsheet2);
				creatTiltleRow(spreadsheet3);
				creatTiltleRowMilitary(spreadsheet4);
				
				int i = 2;
				GratuityList lto = new GratuityDAO(1).getGratuityList(600);
				  for(GratuityListDTO list : lto.getList()){
					    i = createRow(spreadsheet1, i, list ,datePickerValue1,datePickerValue2);
					    i=2;
						i = createRowProvincialBOC(spreadsheet2, i, list ,datePickerValue1, datePickerValue2);
						i=2;
						i = createRowProvincialPeoplesBank(spreadsheet3, i, list ,datePickerValue1,datePickerValue2);
						i=2;
						i = createRowMilitaryArmy(spreadsheet4, i, list ,datePickerValue1,datePickerValue2);
				  }
				  **/
				/**PensionerList pensioners = new PensionerDAO().searchPensioners("pending");
				for(PensionerDTO activepensioner : pensioners.getList()) {
					System.out.println("xcel sheet created");
					i = createRow(spreadsheet1, i, activepensioner ,datePickerValue1,datePickerValue2);
					i = createRowProvincialBOC(spreadsheet2, i, activepensioner ,datePickerValue1,datePickerValue2);
					i = createRowProvincialPeoplesBank(spreadsheet3, i, activepensioner ,datePickerValue1,datePickerValue2);
				}**/
				  
				
				GratuityCategory central = new GratuityCategory(GratuityCategory.STAT_CENTRAL, "", "", "");
				GratuityCategory prvBoc = new GratuityCategory(GratuityCategory.STAT_PROVINCIAL , GratuityCategory.BOC_NAME, GratuityCategory.BOC_CODE, "");
				GratuityCategory prvPb = new GratuityCategory(GratuityCategory.STAT_PROVINCIAL , GratuityCategory.PB_NAME, GratuityCategory.PB_CODE, "");
				//GratuityCategory mltBocArmy = new GratuityCategory(GratuityCategory.STAT_MILITARY, GratuityCategory.BOC_NAME, GratuityCategory.BOC_CODE, GratuityCategory.TYPE_ARMY);
				//GratuityCategory mltBocNavy = new GratuityCategory(GratuityCategory.STAT_MILITARY, GratuityCategory.BOC_NAME, GratuityCategory.BOC_CODE, GratuityCategory.TYPE_NAVY);
				//GratuityCategory mltBocAF = new GratuityCategory(GratuityCategory.STAT_MILITARY, GratuityCategory.BOC_NAME, GratuityCategory.BOC_CODE, GratuityCategory.TYPE_AIRFORCE);
				GratuityCategory mltPbArmy = new GratuityCategory(GratuityCategory.STAT_MILITARY, GratuityCategory.PB_NAME, GratuityCategory.PB_CODE, GratuityCategory.TYPE_ARMY);
				//GratuityCategory mltPbNavy = new GratuityCategory(GratuityCategory.STAT_MILITARY, GratuityCategory.PB_NAME, GratuityCategory.PB_CODE, GratuityCategory.TYPE_NAVY);
				//GratuityCategory mltPbAF = new GratuityCategory(GratuityCategory.STAT_MILITARY, GratuityCategory.PB_NAME, GratuityCategory.PB_CODE, GratuityCategory.TYPE_AIRFORCE);
				
				createSpreadSheet(workbook, central ,datePickerValue1 , datePickerValue2);
				createSpreadSheet(workbook, prvBoc , datePickerValue1 , datePickerValue2);
				createSpreadSheet(workbook, prvPb , datePickerValue1 , datePickerValue2);
				//createSpreadSheet(workbook, mltBocArmy , datePickerValue1 , datePickerValue2);
				//createSpreadSheet(workbook, mltBocNavy , datePickerValue1 , datePickerValue2);
				//createSpreadSheet(workbook, mltBocAF , datePickerValue1 , datePickerValue2);
				createSpreadSheet(workbook, mltPbArmy , datePickerValue1 , datePickerValue2);
				//createSpreadSheet(workbook, mltPbNavy , datePickerValue1 , datePickerValue2);
				//createSpreadSheet(workbook, mltPbAF , datePickerValue1 , datePickerValue2);	
				  
				 
				
				File currentDir = new File(ROOT_PATH);
				String outputDirName = "Deduction";
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
			NotificationManager.info("Connot Create Document", "Error Occurd while creating Excel sheet");
		}

	}
	
/**
 * create separate spreadsheet	
 * @param workbook
 * @param gratuityCategory
 * @param datePickerValue1
 * @param datePickerValue2
 * @throws ParseException
 */
public void createSpreadSheet(XSSFWorkbook workbook, GratuityCategory gratuityCategory, String datePickerValue1 ,String datePickerValue2) throws ParseException{
		
		XSSFSheet spreadsheet = workbook.createSheet(gratuityCategory.categoryIdNew());
		creatTiltleRow(spreadsheet, gratuityCategory.isMilitary());
		
				
		int i = 3;
		GratuityList lto = new GratuityDAO(1).getGratuityList(600);
		double totalDeductionAmount =0.0;
		for(GratuityListDTO list : lto.getList()){
		    i = createRow(spreadsheet, i, list, datePickerValue1, datePickerValue2, gratuityCategory,totalDeductionAmount);
		}
		creatTotalRow(spreadsheet,i,totalDeductionAmount);
		creatDateRow(spreadsheet,1 ,datePickerValue1,datePickerValue2,gratuityCategory.categoryIdNew());
		
}
	
/**
 * create title row	
 * @param spreadsheet
 * @param isMilitary
 */
private void creatTiltleRow(XSSFSheet spreadsheet, boolean isMilitary){
	XSSFRow row=spreadsheet.createRow(2);
	XSSFCell cell;
	
	cell=row.createCell(1);
	cell.setCellValue("PENSION NUMBER");
	cell=row.createCell(2);
	cell.setCellValue("NAME");
	cell=row.createCell(3);
	cell.setCellValue("MIN DPT");
	cell=row.createCell(4);
	cell.setCellValue("BRANCH");
	cell=row.createCell(5);
	cell.setCellValue("ACCOUNT NUMBER");
	cell=row.createCell(6);
	cell.setCellValue("DEDUCTIONS");
	if(isMilitary){
		cell=row.createCell(7);
		cell.setCellValue("W&OP NUMBER");
		cell=row.createCell(8);
		cell.setCellValue("BANK DATE");
	}
}
/**
 * create total deduction value
 * @param spreadsheet
 * @param rowIndex
 * @param totalAmount
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
	cell.setCellFormula(cellvalue);;
	
}
/**
 * display date range
 * @param spreadsheet
 * @param rowIndex
 * @param date1
 * @param date2
 * @param name
 */
private void creatDateRow(XSSFSheet spreadsheet, int rowIndex ,String date1, String date2, String name){
	XSSFRow row=spreadsheet.createRow(rowIndex);
	XSSFCell cell;
	
	cell=row.createCell(1);
	cell.setCellValue("DEDUCTIONS-"+name);
	
	
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
private int createRow(XSSFSheet spreadsheet, int rowIndex, GratuityListDTO lgto ,String datePickerValue1, String datePickerValue, GratuityCategory gratuityCategory,double totalDeductionAmount) throws ParseException{
	//double totalDeductionAmount = 0.0;
	RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(Long.valueOf(lgto.getPensioner().getUrl()));
	PensionList pensionlist = new PensionsDAO(Long.valueOf(lgto.getPensioner().getUrl())).getAllPensions();
	for(PensionDTO pension : pensionlist.getPensions()) {
		GratuityDTO gto = new GratuityDAO(Long.valueOf(lgto.getPensioner().getUrl())).getGratuity(Long.valueOf(regularPensioner.getGratuity().getUrl()));
		BranchDTO branchDto  = new BankDAO().getBank(Integer.valueOf(gto.getBranch().getUrl()));
		PensionPointDTO pensionpoint = new PensionPointDAO().getPensionPoint(pension.getPensionPoint().getUrl());
		
		
		if((!gratuityCategory.isMilitary() && pensionpoint.getStutoryBody().equals(gratuityCategory.getStatutoryBody())) || (gratuityCategory.isMilitary() && pensionpoint.getType().equals(gratuityCategory.getType()))){
			if((!gratuityCategory.isCentral() && String.valueOf(branchDto.getBank().getId()).equals(gratuityCategory.getBankCode())) || gratuityCategory.isCentral()){
			//if((!gratuityCategory.isCentral() && String.valueOf(branchDto.getBank().getId()) != null && gratuityCategory.getBankCode().equals(String.valueOf(branchDto.getBank().getId()))) || gratuityCategory.isCentral()){
			    
		        XSSFCell cell;
		        XSSFRow row=spreadsheet.createRow(rowIndex);
		        cell=row.createCell(1);
		        cell.setCellValue(pension.getId());
		        cell=row.createCell(2);
		        cell.setCellValue(regularPensioner.getName());
		        cell=row.createCell(3);
		        cell.setCellValue(pensionpoint.getName());
		        cell=row.createCell(4);
		        cell.setCellValue(branchDto.getId());
		        cell=row.createCell(5);
		        cell.setCellValue(gto.getAccountNumber());
		        cell=row.createCell(6);
		        cell.setCellValue(gto.getGovDeduction());
				
				if(gratuityCategory.isMilitary()){
					cell=row.createCell(7);
					cell.setCellValue(regularPensioner.getWOPNumber());
					cell=row.createCell(8);
					cell.setCellValue(datePickerValue1);
				}					
                
				totalDeductionAmount = totalDeductionAmount + gto.getGovDeduction();
		        rowIndex++;
			}
		}
		
	}
	//creatTotalRow(spreadsheet,rowIndex,totalDeductionAmount);

	return rowIndex;
}
/**
 * check date range
 * @param lgto
 */
private void checkDateRange(GratuityListDTO lgto){
	boolean isdate = false;
	GratuityLogList history = new GratuityDAO(Long.valueOf(lgto.getPensioner().getUrl())).getgratuityHistory();
	for(GratuityLogDTO log : history.getHistory()) {
		log.setTimestamp("2014-01-12");
		System.out.println(log.getTimestamp());
		
		List<String> timeStamp = Arrays.asList(log.getTimestamp().split(","));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateFrom = sdf.parse("2014-01-12");
			Date dateTo = sdf.parse("2014-01-12");
			Date date1 = sdf.parse(timeStamp.get(0));
			
			if((date1.compareTo(dateFrom)>0) && ((date1.compareTo(dateTo))<=0)){
				isdate = true;
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//return isdate;
	
}
	
	

}
