package org.pensions.view.pd3;

import java.awt.Desktop;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import org.pensions.data.dao.GratuityDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.enumarations.DialogType;
import org.pensions.enumarations.EmployeeRole;
import org.pensions.model.PensionerDTO;
import org.pensions.model.lists.GratuityList;
import org.pensions.model.lists.PensionerList;
import org.pensions.session.UserSession;
import org.pensions.view.pd3.GenerateFinalReportController.GenericExtFilter;
import org.pensions.view.util.NotificationManager;
import org.pensions.view.util.PopupUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class GenerateFinalRefundReport implements Initializable{
	@FXML private Button btngeneratereport;
	@FXML private Button btnretriewreports;
	@FXML private ComboBox<String> comborefundreport;
	private PensionerDTO dto;
	private static final String ROOT_PATH = "C:\\Users\\Public\\Documents\\Refund\\";
	private int state = 0;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//addFilesToComboBox("C:\\Users\\Public\\Documents\\Gratuity\\");
	}
	
	@FXML
	public void viewPaymentReportActionhandler(ActionEvent event) throws ParseException{
		PensionerList pensioners = new PensionerDAO().searchPensioners("pending");
		if(pensioners.getPensioners().isEmpty()){
			NotificationManager.info("No pensioners Found", "No pensioners Found.");
		}else {
			////boolean value = isGenerateExcel(ROOT_PATH);	
			//if(value == true){
			//	 NotificationManager.info("File is already excists", "Mounthly pension Report is Already generated");
			//}
			//else if(value == false){
			PopupUtil<PensionerDTO> pu = new PopupUtil<>(dto,
					PensionerDTO.class,
					DialogType.GENERATE_REPORT);


			try {
				pu.show(UserSession.INSTANCE.getStage());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		  //}
		}
	}
	
	@FXML
	public void retriewPaymentReportActionhandler(ActionEvent event){

		String dateValue = comborefundreport.getValue();
		try {
			Desktop.getDesktop().open(new File("C:\\Users\\Public\\Documents\\Refund\\"+dateValue+".xlsx"));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("generate");
	}
	
	/**
	 * add file names to combo box	
	 * @param reportDirPath
	 */
	public void addFilesToComboBox(String reportDirPath){

		GenericExtFilter filter = new GenericExtFilter("xlsx");

		File dir = new File(reportDirPath);

		if(dir.isDirectory()==false){
			System.out.println("Directory does not exists : " + reportDirPath);
			return;
		}

		// list out all the file name and filter by the extension
		String[] list = dir.list(filter);

		if (list.length == 0) {
			System.out.println("no files end with : xlsx");
			return;
		}

		for (String file : list) {
			String fileName = file.substring(0,file.indexOf(".xlsx"));
			comborefundreport.getItems().add(fileName);

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
