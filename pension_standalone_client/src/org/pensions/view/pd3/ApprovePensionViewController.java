package org.pensions.view.pd3;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.pensionstatemanager.UpdateStatus;
import org.pensions.printmodule.PrintModule;
import org.pensions.session.UserSession;
import org.pensions.view.util.NotificationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ApprovePensionViewController implements Popoverble, Initializable {

	@FXML private Label nameTxt;
	@FXML private Label refNoTxt;
	@FXML private DatePicker InterviewDateTxt;
	@FXML private TextArea remarksTxt;

	private PensionerDTO dto;
	private PopOver pop;


	public void loadData(Long refNo, String name){

	}

	public void saveAndPrintLetter(){

	}
	void sendSMS(){

	}

	void closeForm(){

	}

	private void init(PensionerDTO userData, PopOver pop) {
		this.pop = pop;
		this.dto = userData;
		String id = String.valueOf(dto.getId());
		refNoTxt.setText(id);
		nameTxt.setText(dto.getName());
	}


	/* print interview letter */
	@FXML
	public void confirmButtonHandler(ActionEvent e){
		//int count = 0;

		String wopnumber = refNoTxt.getText();
		String remarks = remarksTxt.getText();
		LocalDate date = InterviewDateTxt.getValue();
		String datevalue = date.toString();

		if((remarksTxt.getText().isEmpty()) || (InterviewDateTxt.getValue() == null)) {
			NotificationManager.info("Empty parameters!", "Please fill all text field to print");

		}
		else{
        	
		/* print application */
			try {
				UpdateStatus pd = new UpdateStatus();
				PrintModule printModule = new PrintModule();
				Stage Owner = UserSession.INSTANCE.getStage();
				closeButtonHandler(e);
				printModule.start(Owner,dto,datevalue);
				pd.updatePensionState(dto,true);


			}catch(NumberFormatException ex) {
				NotificationManager.info("Cannot print!", "cannot print letter.");
			}
		}

	}
	@FXML
	public void closeButtonHandler(ActionEvent e){
		pop.hide(new Duration(1000));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@Override
	public void setData(Object object, PopOver pop) {
		init((PensionerDTO) object, pop);
	}
}
