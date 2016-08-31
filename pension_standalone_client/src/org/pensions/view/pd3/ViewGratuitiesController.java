package org.pensions.view.pd3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.pensions.data.dao.BankDAO;
import org.pensions.data.dao.GratuityDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.BranchDTO;
import org.pensions.model.GratuityDTO;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionerDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class ViewGratuitiesController implements Initializable, Popoverble{
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label accountNumber;
	
	@FXML
	private Label branch;
	
	@FXML
	private Label gratuityAmount;
	
	@FXML
	private Label netGratuityAmount;
	
	@FXML
	private Label gonernmentDeduction;
	@FXML
	private Label bank;
	
	private PensionerDTO dto;
	private PopOver pop;

	private void init(PensionerDTO userData, PopOver pop) {
		// TODO Auto-generated method stub
		this.dto = userData;
		this.pop = pop;
		
		Hetoes activePension = dto.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);

		GratuityDTO gto = new GratuityDAO(dto.getId()).getGratuity(Long.valueOf(dto.getGratuity().getUrl()));
		PensionDTO pension = new PensionsDAO(dto.getId()).getPension(Long.valueOf(activePension.getUrl()));
		BranchDTO branchDto  = new BankDAO().getBank(Integer.valueOf(gto.getBranch().getUrl()));
		accountNumber.setText(gto.getAccountNumber());
		gratuityAmount.setText(String.valueOf(gto.getGratuityAmount()));
		netGratuityAmount.setText(String.valueOf(gto.getNetGratuityAmount()));
		gonernmentDeduction.setText(String.valueOf(gto.getGovDeduction()));
		branch.setText(branchDto.getName());
		bank.setText(branchDto.getBank().getName());
		
	}
	
	@FXML
	public void closeButtonHandler(ActionEvent e){
		pop.hide(new Duration(500));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@Override
	public void setData(Object object, PopOver pop) {
		this.init((PensionerDTO) object, pop);
	}
}
