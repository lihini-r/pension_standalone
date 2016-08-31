package org.pensions.view.pd3;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.pensions.data.reports.ExcelReportGenerator;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.PensionerDTO;
import org.pensions.pensionstatemanager.UpdateStatus;
import org.pensions.session.UserSession;
import org.pensions.view.util.NotificationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.util.Duration;

public class PaymentReportController implements Initializable, Popoverble{
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
	@FXML private DatePicker awardedDate;
	
	private PensionerDTO dto;
	private PopOver pop;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@Override
	public void setData(Object object, PopOver pop) {
		this.init((PensionerDTO) object, pop);
	}
	
	private void init(PensionerDTO userData, PopOver pop) {
		this.dto = userData;
		this.pop = pop;
		
	}
	/**
	 * generate payment report button
	 * @param event
	 */
	@FXML
	public void generateReportActionhandler(ActionEvent event){
		if(awardedDate.getValue() == null){
			NotificationManager.info("Empty parameters!", "Please fill the text field to generate Excel.");
			
		}else{
			LocalDate date = awardedDate.getValue();
			String awardedDate = date.toString();
			ExcelReportGenerator report = new ExcelReportGenerator();
			report.generateExcelSheet(awardedDate);
			closeButtonHandler(event);
		}
	}
	
	
	
	@FXML
	public void closeButtonHandler(ActionEvent e){
		pop.hide(new Duration(500));
	}
	

}
