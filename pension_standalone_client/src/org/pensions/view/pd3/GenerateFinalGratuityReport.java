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
import org.pensions.enumarations.EmployeeRole;
import org.pensions.model.GratuityListDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.model.lists.GratuityList;
import org.pensions.model.lists.PensionerList;
import org.pensions.session.UserSession;
import org.pensions.view.util.NotificationManager;
import org.pensions.view.util.PopupUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class GenerateFinalGratuityReport implements Initializable{
	@FXML private Button btngeneratereport;
	@FXML private Button btnretriewreports;
	@FXML private ComboBox<String> combogratuityreport;
	private PensionerDTO dto;
	private static final String ROOT_PATH = "C:\\Users\\Public\\Documents\\Gratuity\\";
	private int state = 0;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addFilesToComboBox("C:\\Users\\Public\\Documents\\Gratuity\\");
	}
	
	@FXML
	public void viewGratuityReportActionhandler(ActionEvent event){
		if( UserSession.INSTANCE.getRole().equals(EmployeeRole.DATA_ENTRY.getRole())){
			state = 200;
		}else if(UserSession.INSTANCE.getRole().equals(EmployeeRole.POSTAL.getRole())){
			state = 100;
		}
		
		GratuityList lto = new GratuityDAO(1).getGratuityList(state);
		if(lto.getList().isEmpty()){
			NotificationManager.info("No gratuities Found", "No gratuities Found.");
		}else {
			
			PopupUtil<PensionerDTO> pu = new PopupUtil<>(dto,
					PensionerDTO.class,
					DialogType.GENERATE_GRATUITY_REPORT);


			try {
				pu.show(UserSession.INSTANCE.getStage());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		 }
	}
	
	@FXML
	public void retriewGratuityReportActionhandler(ActionEvent event){

		String dateValue = combogratuityreport.getValue();
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
				combogratuityreport.getItems().add(fileName);

			}	
			
		}
		else{
			System.out.println("Directory does not exists : " + reportDirPath);
		}
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
