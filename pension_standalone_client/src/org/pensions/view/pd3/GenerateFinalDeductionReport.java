package org.pensions.view.pd3;

import java.awt.Desktop;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.pensions.data.dao.GratuityDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.enumarations.DialogType;
import org.pensions.model.PensionerDTO;
import org.pensions.model.lists.GratuityList;
import org.pensions.model.lists.PensionerList;
import org.pensions.session.UserSession;
import org.pensions.view.pd3.GenerateFinalGratuityReport.GenericExtFilter;
import org.pensions.view.util.NotificationManager;
import org.pensions.view.util.PopupUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class GenerateFinalDeductionReport implements Initializable{
	@FXML private Button btngeneratereport;
	@FXML private Button btnretriewreports;
	@FXML private ComboBox<String> combodeductionsreport;
	private PensionerDTO dto;
	private static final String ROOT_PATH = "C:\\Users\\Public\\Documents\\Deduction\\";
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addFilesToComboBox("C:\\Users\\Public\\Documents\\Deduction\\");
	}
	@FXML
	public void viewDeductionReportActionhandler(ActionEvent event) throws ParseException{
		
		GratuityList lto = new GratuityDAO(1).getGratuityList(600);
		if(lto.getList().isEmpty()){
			NotificationManager.info("No Gratuities Found", "No Gratuities Found.");
		}else {
			PopupUtil<PensionerDTO> pu = new PopupUtil<>(dto,
					PensionerDTO.class,
					DialogType.GENERATE_DEDUCTION_REPORT);


			try {
				pu.show(UserSession.INSTANCE.getStage());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		  
		}
	}
	
	@FXML
	public void retriewDeductionReportActionhandler(ActionEvent event){

		String dateValue = combodeductionsreport.getValue();
		try {
			Desktop.getDesktop().open(new File(ROOT_PATH + dateValue + ".xlsx"));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * add file names to combo box	
	 * @param reportDirPath
	 */
	public void addFilesToComboBox(String reportDirPath){

		GenericExtFilter filter = new GenericExtFilter("xlsx");

		File dir = new File(reportDirPath);

		if(dir.exists()){
			//System.out.println("Directory does not exists : " + reportDirPath);
			//return;
			String[] list = dir.list(filter);

			if (list.length == 0) {
				System.out.println("no files end with : xlsx");
				return;
			}
			// list out all the file name and filter by the extension
			for (String file : list) {
				String fileName = file.substring(0,file.indexOf(".xlsx"));
				combodeductionsreport.getItems().add(fileName);

			}	
			
		}
		else{
			System.out.println("Directory does not exists : " + reportDirPath);
		}
	}
	/**
	 * 
	 * @param reportDirPath
	 * @throws ParseException 
	 */
	public boolean isGenerateExcel(String reportDirPath) throws ParseException{
		boolean isgenerated = false;
		//String currenttimestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		String currenttimestamp = "2017-09-09";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = sdf.parse(currenttimestamp);
		
		GenericExtFilter filter = new GenericExtFilter("xlsx");

		File dir = new File(reportDirPath);

		if(dir.exists()){
			String currentMonth = new SimpleDateFormat("MM").format(currentDate);
			String currentYear = new SimpleDateFormat("yyyy").format(currentDate);
			
			String[] list = dir.list(filter);

			if (list.length == 0) {
				System.out.println("no files end with : xlsx");
				
			}
			// list out all the file name and filter by the extension
			for (String file : list) {
				String fileName = file.substring(0,file.indexOf(".xlsx"));
				Date generatedDate = sdf.parse(fileName);
				String generatedMounth = new SimpleDateFormat("MM").format(generatedDate);
				String generatedYear = new SimpleDateFormat("yyyy").format(generatedDate);
				if((generatedMounth.equals(currentMonth)) && (generatedYear.equals(currentYear))){
					isgenerated = true;
				}

			}	
			
		}
		else{
			System.out.println("Directory does not exists : " + reportDirPath);
		}
		return isgenerated;
		
	}
	// inner class, generic extension filter
			public class GenericExtFilter implements FilenameFilter {

				private String ext;

				public GenericExtFilter(String ext) {
					this.ext = ext;
				}

				public boolean accept(File dir, String name) {
					return (name.endsWith(ext));
				}
			}

}
