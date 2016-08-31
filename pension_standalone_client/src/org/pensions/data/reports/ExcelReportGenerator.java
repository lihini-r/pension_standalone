package org.pensions.data.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.pensions.data.dao.ExtraPaymentDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.data.dao.VillageOfficeDAO;
import org.pensions.model.BranchDTO;
import org.pensions.model.ExtraPaymentDTO;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.model.RegularPensionerDTO;
import org.pensions.model.VillageOffice;
import org.pensions.model.lists.ExtraPaymentList;
import org.pensions.model.lists.PensionList;
import org.pensions.model.lists.PensionerList;
import org.pensions.view.util.NotificationManager;



public class ExcelReportGenerator {

	    private static final String ROOT_PATH = "C:\\Users\\Public\\Documents\\";
	    
		/**
		 * Generate final excel sheet
		 * @param awardedDate 
		 */
		public void generateExcelSheet(String datePickerValue){

			String currenttimestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 

			try {

				XSSFWorkbook workbook = new XSSFWorkbook(); 
				XSSFSheet spreadsheet1 = workbook.createSheet("After(2014-10-01)");
				XSSFSheet spreadsheet2 = workbook.createSheet("Before(2014-10-01)");
				
				creatTiltleRow(spreadsheet1);
				creatTiltleRow(spreadsheet2);
				int i=2;

				PensionerList pensioners = new PensionerDAO().searchPensioners("pending");
				for(PensionerDTO activepensioner : pensioners.getList()) {
					System.out.println("xcel sheet created");

					i = createRow(spreadsheet1, i, activepensioner ,datePickerValue);
					i = createRowFor2014(spreadsheet2, i, activepensioner ,datePickerValue);

				}
				
				File currentDir = new File(ROOT_PATH);
				String outputDirName = "Payment";
			    File outputDir = new File(currentDir.getAbsolutePath() + File.separator + outputDirName);    
			    
			    if(!outputDir.exists()){
			        boolean dirCreated = outputDir.mkdir();
			    }else{
			        System.out.println("Output Directory Already Exists");
			    }
				
				//FileOutputStream out = new FileOutputStream(new File(ROOT_PATH + currenttimestamp + ".xlsx"));
			    System.out.println("File created");
				FileOutputStream out = new FileOutputStream(new File(outputDir.getAbsolutePath() + File.separator + currenttimestamp+".xlsx"));
				workbook.write(out);
				out.close();
				workbook.close();

				try {
					//Desktop.getDesktop().open(new File(ROOT_PATH + currenttimestamp+".xlsx"));
					Desktop.getDesktop().open(new File(outputDir.getAbsolutePath() + File.separator + currenttimestamp+".xlsx"));
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				NotificationManager.info("Document Created Succefully", "Document Created Succefully.");

			}

			catch(Exception e) {
				NotificationManager.info("Connot Create Document", "Error Occurd");

			}

		}
		

		/**
		 * create row
		 * @param spreadsheet
		 * @param rowIndex
		 * @param activepensioner
		 * @return
		 * @throws ParseException 
		 */
		private int createRow(XSSFSheet spreadsheet, int rowIndex, PensionerDTO dto ,String datePickerValue) throws ParseException{
			
			RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(dto.getId());
			PensionList pensionlist = new PensionsDAO(dto.getId()).getAllPensions();
			for(PensionDTO pension : pensionlist.getPensions()) {
				
				boolean checkRetireDate = isCheckRetireDate(dto);
				if(checkRetireDate == true){
				
				double OTA = getOTA(dto);
				double reducePension = getReducePension(pension);
				double unreducePension = getUnreducePension(pension);
				double SPA = getSpecialAllowances(dto , pension);
				double DSA = getDSA(dto , pension);
				double MAA = getMAA(dto , pension ,datePickerValue);
				List<String> addresses = splitAddress(regularPensioner);
				int DSCODE = getDSCode(regularPensioner);
				int type = getpensionerType(regularPensioner);

				BranchDTO branchDto  = new BankDAO().getBank(Integer.valueOf(pension.getBranch().getUrl()));

				XSSFCell cell;
				XSSFRow row=spreadsheet.createRow(rowIndex);
				cell=row.createCell(1);
				cell.setCellValue(DSCODE);
				cell=row.createCell(2);
				cell.setCellValue(type);
				cell=row.createCell(3);
				cell.setCellValue(pension.getId());
				cell=row.createCell(4);
				cell.setCellValue(dto.getDateOfRetired());
				cell=row.createCell(5);
				cell.setCellValue(dto.getName());
				cell=row.createCell(6);
				cell.setCellValue(dto.getNic());
				cell=row.createCell(7);
				cell.setCellValue(regularPensioner.getDateOfBirth());
				cell=row.createCell(8);
				cell.setCellValue(dto.getWOPNumber());
				cell=row.createCell(9);
				cell.setCellValue(addresses.get(0) == null ? " " : addresses.get(0));
				cell=row.createCell(10);
				cell.setCellValue(addresses.size() < 2 ? " " : addresses.get(1));
				cell=row.createCell(11);
				cell.setCellValue(addresses.size() < 3 ? " " : addresses.get(2));
				cell=row.createCell(12);
				cell.setCellValue(regularPensioner.getVillageOffice().getUrl());
				cell=row.createCell(13);
				cell.setCellValue(regularPensioner.getLandNumber());
				cell=row.createCell(14);
				cell.setCellValue(regularPensioner.getMobileNumber());
				cell=row.createCell(15);
				cell.setCellValue(pension.getAccountNumber());
				cell=row.createCell(16);
				cell.setCellValue(branchDto.getBank().getId());
				cell=row.createCell(17);
				cell.setCellValue(pension.getBranch().getUrl());
				cell=row.createCell(18);
				cell.setCellValue("null");
				cell=row.createCell(19);
				cell.setCellValue(reducePension);
				cell=row.createCell(20);
				cell.setCellValue(unreducePension);
				cell=row.createCell(21);
				cell.setCellValue(MAA);
				cell=row.createCell(22);
				cell.setCellValue("3525");
				cell=row.createCell(23);
				cell.setCellValue(OTA);
				cell=row.createCell(24);
				cell.setCellValue(SPA);
				cell=row.createCell(25);
				cell.setCellValue(DSA);

				rowIndex++;
				
				//updatePensionStatus(dto,pension);
			 }
			}


			return rowIndex;
		}
		
/**
 * create row for pensioners - before retired 2014-10-01		
 * @param spreadsheet
 * @param rowIndex
 * @param dto
 * @param datePickerValue
 * @return
 * @throws ParseException
 */
private int createRowFor2014(XSSFSheet spreadsheet, int rowIndex, PensionerDTO dto ,String datePickerValue) throws ParseException{
			
			RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(dto.getId());
			PensionList pensionlist = new PensionsDAO(dto.getId()).getAllPensions();
			for(PensionDTO pension : pensionlist.getPensions()) {
				
				boolean checkRetireDate = isCheckRetireDate(dto);
				if(checkRetireDate == false){
				
				double OTA = getOTA(dto);
				double reducePension = getReducePension(pension);
				double unreducePension = getUnreducePension(pension);
				double SPA = getSpecialAllowances(dto , pension);
				double DSA = getDSA(dto , pension);
				double MAA = getMAA(dto , pension ,datePickerValue);
				List<String> addresses = splitAddress(regularPensioner);
				int DSCODE = getDSCode(regularPensioner);
				int type = getpensionerType(regularPensioner);

				BranchDTO branchDto  = new BankDAO().getBank(Integer.valueOf(pension.getBranch().getUrl()));

				XSSFCell cell;
				XSSFRow row=spreadsheet.createRow(rowIndex);
				cell=row.createCell(1);
				cell.setCellValue(DSCODE);
				cell=row.createCell(2);
				cell.setCellValue(type);
				cell=row.createCell(3);
				cell.setCellValue(pension.getId());
				cell=row.createCell(4);
				cell.setCellValue(dto.getDateOfRetired());
				cell=row.createCell(5);
				cell.setCellValue(dto.getName());
				cell=row.createCell(6);
				cell.setCellValue(dto.getNic());
				cell=row.createCell(7);
				cell.setCellValue(regularPensioner.getDateOfBirth());
				cell=row.createCell(8);
				cell.setCellValue(dto.getWOPNumber());
				cell=row.createCell(9);
				cell.setCellValue(addresses.get(0) == null ? " " : addresses.get(0));
				cell=row.createCell(10);
				cell.setCellValue(addresses.size() < 2 ? " " : addresses.get(1));
				cell=row.createCell(11);
				cell.setCellValue(addresses.size() < 3 ? " " : addresses.get(2));
				cell=row.createCell(12);
				cell.setCellValue(regularPensioner.getVillageOffice().getUrl());
				cell=row.createCell(13);
				cell.setCellValue(regularPensioner.getLandNumber());
				cell=row.createCell(14);
				cell.setCellValue(regularPensioner.getMobileNumber());
				cell=row.createCell(15);
				cell.setCellValue(pension.getAccountNumber());
				cell=row.createCell(16);
				cell.setCellValue(branchDto.getBank().getId());
				cell=row.createCell(17);
				cell.setCellValue(pension.getBranch().getUrl());
				cell=row.createCell(18);
				cell.setCellValue("null");
				cell=row.createCell(19);
				cell.setCellValue(reducePension);
				cell=row.createCell(20);
				cell.setCellValue(unreducePension);
				cell=row.createCell(21);
				cell.setCellValue(MAA);
				cell=row.createCell(22);
				cell.setCellValue("3525");
				cell=row.createCell(23);
				cell.setCellValue(OTA);
				cell=row.createCell(24);
				cell.setCellValue(SPA);
				cell=row.createCell(25);
				cell.setCellValue(DSA);

				rowIndex++;
				
				//updatePensionStatus(dto,pension);
			 }
			}


			return rowIndex;
		}
		/**
		 * create title of the spread sheet
		 * @param spreadsheet
		 */

		private void creatTiltleRow(XSSFSheet spreadsheet){
			XSSFRow row=spreadsheet.createRow(1);
			XSSFCell cell;
			cell=row.createCell(1);
			cell.setCellValue("DSCODE");
			cell=row.createCell(2);
			cell.setCellValue("PT");
			cell=row.createCell(3);
			cell.setCellValue("PENSION NUMBER");
			cell=row.createCell(4);
			cell.setCellValue("RDATE");
			cell=row.createCell(5);
			cell.setCellValue("NAME");
			cell=row.createCell(6);
			cell.setCellValue("NIC NUMBER");
			cell=row.createCell(7);
			cell.setCellValue("DATE OF BIRTH");
			cell=row.createCell(8);
			cell.setCellValue("WOP NUMBER");
			cell=row.createCell(9);
			cell.setCellValue("ADDRESS 1");
			cell=row.createCell(10);
			cell.setCellValue("ADDRESS 2");
			cell=row.createCell(11);
			cell.setCellValue("ADDRESS 3");
			cell=row.createCell(12);
			cell.setCellValue("GS NUMBER");
			cell=row.createCell(13);
			cell.setCellValue("PHONE NUMBER");
			cell=row.createCell(14);
			cell.setCellValue("MOBILE NUMBER");
			cell=row.createCell(15);
			cell.setCellValue("BAC");
			cell=row.createCell(16);
			cell.setCellValue("BANK NO");
			cell=row.createCell(17);
			cell.setCellValue("BRANCH NO");
			cell=row.createCell(18);
			cell.setCellValue("PNAME");
			cell=row.createCell(19);
			cell.setCellValue("REDUCED");
			cell=row.createCell(20);
			cell.setCellValue("UNREDUCED");
			cell=row.createCell(21);
			cell.setCellValue("MAA");
			cell=row.createCell(22);
			cell.setCellValue("CLA");
			cell=row.createCell(23);
			cell.setCellValue("OTA");
			cell=row.createCell(24);
			cell.setCellValue("SPA");
			cell=row.createCell(25);
			cell.setCellValue("DSA");

		}
		/**
		 * get pensioner type
		 * @param activepensioner
		 * @return
		 */
		private int getpensionerType(PensionerDTO pensioner){
			int type = 0;
			if(pensioner.getPensionerType().equals("civil")){
				type = 1;
			}else{
				type = 3;
			}
			return type;
		}
		/**
		 * get reduced pension 
		 * @param pension
		 * @return
		 */
		private double getReducePension(PensionDTO pension){
			double reduce =0.0;
			reduce = ((pension.getConsolidatedSalary() * pension.getNetReducedPercentage())/100.0)/12.0;
			return Math.round(reduce* 100.0)/ 100.0;

		}
		/**
		 * get unreduced pension
		 * @param pension
		 * @return
		 */
		private double getUnreducePension(PensionDTO pension){
			double unreduce =0.0;
			unreduce = ((pension.getConsolidatedSalary() * pension.getNetUnreducedPercentage())/100.0)/12.0;
			return Math.round(unreduce * 100.0)/ 100.0;


		}
		/**
		 * get OTA 
		 * @param activepensioner
		 * @return
		 * @throws ParseException
		 */
		private double getOTA(PensionerDTO activepensioner) throws ParseException{

			double ota = 0.0;

			String retiredate = activepensioner.getDateOfRetired();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date date1 = sdf.parse(retiredate);
			Date date2 = sdf.parse("2016-01-01");
			Date date2015 = sdf.parse("2015-01-01");

			String year1 = new SimpleDateFormat("yyyy").format(date1);
			System.out.println("year" + year1);

			
			if(date1.compareTo(date2)>0){
				System.out.println("Date1 is after Date2");
				ota = 0.0;
			}
			
			else if((date1.compareTo(date2015)>0) && (date1.compareTo(date2)<=0)){
				System.out.println("Date1 is before");
				ota = 3500.0;
			}else{
				ota = 0.0;
			}
			

			return ota;

		}
		/**
		 * get SPA Amount
		 * @param activepensioner
		 * @param pension
		 * @return
		 */
		private double getSpecialAllowances(PensionerDTO activepensioner , PensionDTO pension){
			double spa = 0.0;

			ExtraPaymentList paymentList = new ExtraPaymentDAO(activepensioner.getId(),pension.getId()).getPayments();
			if(paymentList!=null){
				for(ExtraPaymentDTO exPayment : paymentList.getExtraPayments()) {
					if(exPayment.getType().equals("allowance")) {
						if(exPayment.getDescription().equals("SPA")){
							if(exPayment.isPensionable()){	
							 spa = spa + exPayment.getAmount();
							}
						}
					} 
				}
			}

			return spa;
		}
		/**
		 * get DSA Amount
		 * @param activepensioner
		 * @param pension
		 * @return
		 */
		private double getDSA(PensionerDTO activepensioner , PensionDTO pension){
			double dsa = 0.0;

			ExtraPaymentList paymentList = new ExtraPaymentDAO(activepensioner.getId(),pension.getId()).getPayments();
			if(paymentList!=null){ 
				for(ExtraPaymentDTO exPayment : paymentList.getExtraPayments()) {
					if(exPayment.getType().equals("allowance")) {
						if(exPayment.getDescription().equals("DSA")){
							if(exPayment.isPensionable()){	
							dsa = dsa + exPayment.getAmount();
							}
						}
					} 
				}
			}

			return dsa;
		}
		/**
		 * calculate DSCODE
		 * @param pensioner
		 * @return
		 */
		private int getDSCode(PensionerDTO pensioner){
			int dscode = 0;

			VillageOffice villageoffice = new VillageOfficeDAO().getVillageOffice(Long.valueOf((pensioner.getVillageOffice().getUrl())));
			int dis = villageoffice.getDsOffice().getDistrict().getId();
			long ds = villageoffice.getDsOffice().getId();
			dscode = (dis * 100) + (int)ds ;

			return dscode;
		}
		/**
		 * calculate MAA
		 * @param activepensioner
		 * @param pension
		 * @return
		 * @throws ParseException
		 */
		private double getMAA(PensionerDTO activepensioner , PensionDTO pension, String datePickerValue) throws ParseException{

			double maa = 0.0;

			String retiredate = activepensioner.getDateOfRetired();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


			Date date3 = sdf.parse("2016-09-10");
			Date retireDate = sdf.parse(retiredate);
			Date date4 = sdf.parse("2015-04-10");
			Date awardedDate = sdf.parse(datePickerValue);
			Date lastCheckMonth = sdf.parse("2015-12-31");
			System.out.println("DATEPICKER value->" + awardedDate);

			String yearRetired = new SimpleDateFormat("yyyy").format(retireDate);


			int arriesdateCount = getDateDiff(retireDate);
			double reduce =  getReducePension(pension);
			double dsa = getDSA(activepensioner , pension);
			double spa = getSpecialAllowances(activepensioner , pension);

			if(yearRetired.equals("2016")){
				int arriesMonthcount = getMonthDiff(retireDate,awardedDate);
				double arriesDatePension = ((reduce + dsa + spa + 3500.0)/31.0 ) * (double)arriesdateCount;
				double arriesMounthPension = ((reduce + dsa + spa + 3500.0)/31.0 ) * (double)arriesMonthcount;
				

				maa = arriesDatePension + arriesMounthPension;

			}else if(yearRetired.equals("2015")){
				double cla = 0.0;
				int arries2015 = getMonthDiff(retireDate, lastCheckMonth);
				int arries2016 = getMonths(awardedDate);			
				int longArriesMonthcount = arries2015 + arries2016;
				cla = getClaFor2015(retireDate, date4);

				double arriesDatePension = ((reduce + dsa + spa + cla + 3525.0)/31.0 ) * (double)arriesdateCount;
				double arriesMounthPension = ((reduce + dsa + spa + cla + 3525.0)/31.0 ) * (double)longArriesMonthcount;

				maa = arriesDatePension + arriesMounthPension;


			}
			return Math.round (maa * 100.0) / 100.0;
		}
		/**
		 * split pensioner's address
		 * @param pensioner
		 * @return
		 */
		private List<String> splitAddress(PensionerDTO pensioner){
			return Arrays.asList(pensioner.getAddress().split(","));
		}
		/**
		 * calculate date difference for arries
		 * @param retiredDate
		 * @return
		 */
		private int getDateDiff(Date retiredDate){
			int dateDiff = 0;

			String arriesDate = new SimpleDateFormat("dd").format(retiredDate);
			dateDiff = (31 - Integer.valueOf(arriesDate) + 1);

			System.out.println("retiredate->" + retiredDate);
			System.out.println("arriesdateCount->" + dateDiff);

			return dateDiff;
		}
		/**
		 * calculate month difference for 2015 
		 * @param awardedDate
		 * @return
		 */
		private int getMonths(Date awardedDate){
			int months = 0;
			String pensionMonth = new SimpleDateFormat("MM").format(awardedDate);
			months = Integer.valueOf(pensionMonth);

			return months;
		}
		/**
		 * calculate month difference for 2016
		 * @param retiredDate
		 * @param checkDate
		 * @return
		 */
		private int getMonthDiff(Date retiredDate, Date checkDate){
			int monthDiff = 0;
			String retireMonth = new SimpleDateFormat("MM").format(retiredDate);
			String pensionMonth = new SimpleDateFormat("MM").format(checkDate);
			monthDiff = (Integer.valueOf(pensionMonth) - (Integer.valueOf(retireMonth) + 1));		

			return monthDiff;
		}
		/**
		 * calculate CLA for 2015 retire dates
		 * @param retiredDate
		 * @param checkChangeDate
		 * @return
		 */
		private double getClaFor2015(Date retiredDate, Date checkChangeDate){
			double cla = 0.0;

			if(retiredDate.compareTo(checkChangeDate)>0){
				System.out.println("retierDate is after date3");
				cla = 3500.0;
			}else{
				cla = 2500.0;
				System.out.println("retierDate is before date3");
			}

			return cla;
		}
		/**
		 * update pensioners' status
		 * @param dto
		 */
		private void updatePensionStatus(PensionerDTO pensioner,PensionDTO pension){
			PensionsDAO dao = new PensionsDAO(pensioner.getId());
			dao.updatePensionStatus(true, pension.getId());
		}
		
		private void createDirectory(){
			File currentDir = new File(".");
			System.out.println(currentDir);
			String outputDirName = "tempOut";
		    File outputDir = new File(currentDir.getAbsolutePath() + File.separator + outputDirName);    
		    
		    if(!outputDir.exists()){
		        boolean dirCreated = outputDir.mkdir();
		        System.out.println("Output Directory Created : " + dirCreated);
		    }else{
		        System.out.println("Output Directory Already Exists");
		    }

		}
		private boolean isCheckRetireDate(PensionerDTO dto) throws ParseException{
			boolean isvalue = false;
			String retiredate = dto.getDateOfRetired();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date retireDate = sdf.parse(retiredate);
			Date checkDate = sdf.parse("2014-10-01");
			
			
			if(retireDate.compareTo(checkDate)>0){
				System.out.println("retierDate is after checkDate");
				isvalue = true;
			}
			return isvalue;
		}


	
}
