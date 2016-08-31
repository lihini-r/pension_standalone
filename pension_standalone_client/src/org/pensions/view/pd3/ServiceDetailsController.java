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

public class ServiceDetailsController implements Initializable, Popoverble{
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
	@FXML
	private Label lblnoPayPeriod;
	@FXML
	private Label lblservice;
	@FXML
	private Label lblgrade;
	@FXML
	private Label lbldateOfFirstAppoinment;
	@FXML
	private Label lbldateOfPermenentAppoinment;
	@FXML
	private Label lblretirementReason;
	@FXML
	private Label lblsection;
	@FXML
	private Label lbldateOfRetirement;
	@FXML
	private Label lblservicePeriod;
	
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
		
		lblservice.setText(regularPensioner.getServiceDomain());
		lbldateOfFirstAppoinment.setText(regularPensioner.getDateOfPermanantAppoinment());
		lblretirementReason.setText(regularPensioner.getRetiredReason());
		lblsection.setText(regularPensioner.getSectionUnder());
		lbldateOfRetirement.setText(regularPensioner.getDateOfRetired());
		lblgrade.setText(regularPensioner.getGrade());
		lbldateOfPermenentAppoinment.setText(regularPensioner.getDateOfPermanantAppoinment());
		

	}
	
	@FXML
	public void closeButtonHandler(ActionEvent e){
		pop.hide(new Duration(500));
	}

}
