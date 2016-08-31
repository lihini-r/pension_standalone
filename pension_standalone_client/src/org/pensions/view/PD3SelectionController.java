package org.pensions.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.controlsfx.control.PopOver;
import org.pensions.enumarations.DialogType;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.PensionerDTO;
import org.pensions.model.UserCredential;
import org.pensions.session.UserSession;
import org.pensions.view.pd3.AllGratuitiesController;
import org.pensions.view.pd3.GenerateFinalReportController;
import org.pensions.view.pd3.SearchController;
import org.pensions.view.util.NotificationManager;
import org.pensions.view.util.PopupUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PD3SelectionController implements Initializable, Popoverble{
	private PensionerDTO dto;
	private PopOver pop;
	
	@FXML private Label lblRegularPensionerDTO;
	@FXML private Label lblGratuityDTO;
	@FXML private Label lblGenerateReports;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void init(PensionerDTO userdata, PopOver pop) {
		// TODO Auto-generated method stub
		this.dto = userdata;
		this.pop = pop;
	}
	@FXML public void regularPensionerActionHandler(MouseEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/Search.fxml"));
		(lblRegularPensionerDTO.getScene().getWindow()).hide();
		RootLayoutController.getInstance().setAnchoredPane((AnchorPane) loader.load());
		org.pensions.view.pd3.SearchController controller = (SearchController) loader.getController();
	}
	@FXML public void gratuityActionHandler(MouseEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/ViewAllGratuities.fxml"));
		(lblRegularPensionerDTO.getScene().getWindow()).hide();
		RootLayoutController.getInstance().setAnchoredPane((AnchorPane) loader.load());
		org.pensions.view.pd3.AllGratuitiesController controller = (AllGratuitiesController) loader.getController();
	}
	@FXML public void generateReportActionActionHandler(MouseEvent e) throws IOException {
		PopupUtil<UserCredential> pu = new PopupUtil<>(DialogType.REPORT_SELECTION,592,321);

		try {
			pu.show(UserSession.INSTANCE.getStage());
		} catch (Exception ee) {
			NotificationManager.info("Error", "Error occured");
			ee.printStackTrace();
		}
	}
	
	@Override
	public void setData(Object object, PopOver pop) {
		this.init((PensionerDTO) object, pop);
	}
}
