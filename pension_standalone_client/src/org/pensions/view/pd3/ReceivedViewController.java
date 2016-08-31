package org.pensions.view.pd3;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionerDTO;
import org.pensions.model.RegularPensionerDTO;
import org.pensions.pensionstatemanager.UpdateStatus;
import org.pensions.view.util.NotificationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceivedViewController implements Initializable, Popoverble{

	@FXML private Label lblName;
	@FXML private Label lblWnopNumber;
	@FXML private Label lnlNic;
	@FXML private Label lblDesignation;
	@FXML private Label lblAddress;
	@FXML private Label lblService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert lblName != null : "fx:id=\"lblName\" was not injected: check your FXML file 'ReceivedView.fxml'.";
		assert lblWnopNumber != null : "fx:id=\"lblWnopNumber\" was not injected: check your FXML file 'ReceivedView.fxml'.";
		assert lnlNic != null : "fx:id=\"lnlNic\" was not injected: check your FXML file 'ReceivedView.fxml'.";
		assert lblDesignation != null : "fx:id=\"lblDesignation\" was not injected: check your FXML file 'ReceivedView.fxml'.";
		assert lblAddress != null : "fx:id=\"lblAddress\" was not injected: check your FXML file 'ReceivedView.fxml'.";
		assert lblService != null : "fx:id=\"lblService\" was not injected: check your FXML file 'ReceivedView.fxml'.";
	}

	@Override
	public void setData(Object object, PopOver pop) {
		this.init((PensionerDTO) object, pop);
	}

	private PopOver pop;
	private PensionerDTO pensioner;

	private void init(PensionerDTO userData, PopOver pop) {
		// TODO Auto-generated method stub
		this.pensioner = userData;
		this.pop = pop;

		Hetoes activePension = pensioner.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);

		pensioner = new PensionerDAO().getPensioner(pensioner.getId());
		pensioner.getPensions().add(activePension);
		RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(pensioner.getId());
		lblName.setText(pensioner.getName());
		lblAddress.setText(regularPensioner.getDateOfRetired());
		lblDesignation.setText(regularPensioner.getMobileNumber());
		lnlNic.setText(pensioner.getNic());
		lblWnopNumber.setText(pensioner.getwOPNumber());
		//lblService.setText(pensioner.getServiceDomain());

	}

	@FXML
	public void receiveButtonHandler(ActionEvent e){
		/************ confirm request to receive application***********/
		try{
			/**** Confirm****/
			UpdateStatus pd = new UpdateStatus();
			pd.updatePensionState(pensioner,true);
			closeButtonActionHandler(e);

		}catch(Exception ex) {
			NotificationManager.info("Error!", "Cannot received Application");
		}
	}

	@FXML public void closeButtonActionHandler(ActionEvent event) {
		pop.hide(new Duration(500));
	}

}
