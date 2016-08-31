package org.pensions.view.pd3;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.pensions.data.reports.DeductionsExcelReportGenerator;
import org.pensions.data.reports.GratuityExcelReportGenerator;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.PensionerDTO;
import org.pensions.view.util.NotificationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

public class DeductionReportController implements Initializable, Popoverble{
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML private DatePicker generatedDateFrom;
	@FXML private DatePicker generatedDateTo;

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
	 * generate gratuity report button
	 * @param event
	 */
	@FXML
	public void generateDeductionReportActionhandler(ActionEvent event){
		if(generatedDateFrom.getValue() == null && generatedDateTo.getValue() == null){
			NotificationManager.info("Empty parameters!", "Please fill  text fields to generate Excel.");

		}else{
			try {
				System.out.println("button clicked");
				LocalDate date = generatedDateFrom.getValue();
				LocalDate date2 = generatedDateTo.getValue();
				String datefrom = date.toString();
				String dateto = date2.toString();
				DeductionsExcelReportGenerator report = new DeductionsExcelReportGenerator();
				report.generateExcelSheet(datefrom,dateto);
				closeButtonHandler(event);
				
			}catch(Exception e) {
				NotificationManager.info("Error", "Error");
			}
		}
	}


	@FXML
	public void closeButtonHandler(ActionEvent e){
		pop.hide(new Duration(500));
	}

}
