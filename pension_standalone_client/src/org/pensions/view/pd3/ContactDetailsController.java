package org.pensions.view.pd3;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionerDTO;
import org.pensions.model.RegularPensionerDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class ContactDetailsController implements Initializable, Popoverble{
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
	@FXML
	private Label telephone;
	
	@FXML
	private Label mobilephone;
	
	@FXML
	private Label email;
	
	@FXML
	private Label skype;
	
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
		
		Hetoes activePension = dto.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);
		
		RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(dto.getId());
		
		telephone.setText(regularPensioner.getLandNumber());
		mobilephone.setText(regularPensioner.getMobileNumber());
		email.setText(regularPensioner.getEmail());
		skype.setText(regularPensioner.getSkype());
		

	}
	
	@FXML
	public void closeButtonHandler(ActionEvent e){
		pop.hide(new Duration(500));
	}

}
