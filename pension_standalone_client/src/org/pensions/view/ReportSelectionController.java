package org.pensions.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.PensionerDTO;
import org.pensions.view.pd3.GenerateFinalDeductionReport;
import org.pensions.view.pd3.GenerateFinalGratuityReport;
import org.pensions.view.pd3.GenerateFinalReportController;
import org.pensions.view.pd3.GratuityReportController;
import org.pensions.view.pd3.SearchController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ReportSelectionController implements Initializable, Popoverble{
	private PensionerDTO dto;
	private PopOver pop;
	
	@FXML private Label lblPaymentReport;
	@FXML private Label lblGratuityReport;
	@FXML private Label lblMonthlySalaryReport;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void init(PensionerDTO userdata, PopOver pop) {
		// TODO Auto-generated method stub
		this.dto = userdata;
		this.pop = pop;
	}
	
	@FXML public void paymentReportActionHandler(MouseEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/GenerateFinalReportView.fxml"));
		(lblPaymentReport.getScene().getWindow()).hide();
		RootLayoutController.getInstance().setAnchoredPane((AnchorPane) loader.load());
		org.pensions.view.pd3.GenerateFinalReportController controller = (GenerateFinalReportController) loader.getController();
	}
	@FXML public void gratuityReportActionHandler(MouseEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/GenerateFinalGratuityReportView.fxml"));
		(lblPaymentReport.getScene().getWindow()).hide();
		RootLayoutController.getInstance().setAnchoredPane((AnchorPane) loader.load());
		org.pensions.view.pd3.GenerateFinalGratuityReport controller = (GenerateFinalGratuityReport) loader.getController();
	}
	@FXML public void deductionReportActionHandler(MouseEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/GenerateDeductionReportView.fxml"));
		(lblPaymentReport.getScene().getWindow()).hide();
		RootLayoutController.getInstance().setAnchoredPane((AnchorPane) loader.load());
		org.pensions.view.pd3.GenerateFinalDeductionReport controller = (GenerateFinalDeductionReport) loader.getController();
	}
	
	
	@Override
	public void setData(Object object, PopOver pop) {
		this.init((PensionerDTO) object, pop);
	}
}
