package org.pensions.view.pd3;

import java.net.URL;
import java.util.ResourceBundle;
import org.controlsfx.control.PopOver;
import org.pensions.data.dao.BankDAO;
import org.pensions.data.dao.GratuityDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.BranchDTO;
import org.pensions.model.GratuityDTO;
import org.pensions.model.GratuityListDTO;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.model.RegularPensionerDTO;
import org.pensions.model.internal.ViewGratuitiesTableItem;
import org.pensions.model.lists.GratuityList;
import org.pensions.model.lists.PensionList;
import org.pensions.model.lists.PensionerList;
import org.pensions.pensionstatemanager.UpdateStatus;
import org.pensions.view.util.NotificationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;


public class AcceptGratuitiesViewController implements Initializable, Popoverble{
	
	
	@FXML private Label lblaccountNumber;
	@FXML private Label lblbranch;
	@FXML private Label lblgratuityAmount;
	@FXML private Label lblnetGratuityAmount;
	@FXML private Label lblgonernmentDeduction;
	@FXML private Label lblbank;
	
	
	
	private PensionerDTO dto;
	private ViewGratuitiesTableItem item;
	private PopOver pop;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void init(ViewGratuitiesTableItem userdata, PopOver pop) {
		// TODO Auto-generated method stub
		this.item = userdata;
		this.pop = pop;
		
		//PensionerList pensioners = new PensionerDAO().searchPensioners("pending");
		//GratuityList lto = new GratuityDAO(dto.getId()).getGratuityList(100);
		//for(GratuityListDTO list : lto.getList()){
		//for(PensionerDTO activepensioner : pensioners.getList()) {
		 RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(item.getPensionNumber());	
		 PensionList pensionlist = new PensionsDAO(item.getPensionNumber()).getAllPensions();
		 for(PensionDTO pension : pensionlist.getPensions()) {
		  GratuityDTO gto = new GratuityDAO(item.getPensionNumber()).getGratuity(Long.valueOf(regularPensioner.getGratuity().getUrl()));
		  BranchDTO branchDto  = new BankDAO().getBank(Integer.valueOf(pension.getBranch().getUrl()));
		  lblaccountNumber.setText(gto.getAccountNumber());
		  lblgratuityAmount.setText(String.valueOf(gto.getGratuityAmount()));
		  lblnetGratuityAmount.setText(String.valueOf(gto.getNetGratuityAmount()));
		  lblgonernmentDeduction.setText(String.valueOf(gto.getGovDeduction()));
		  lblbranch.setText(branchDto.getName());
		  lblbank.setText(branchDto.getBank().getName());
		  
		 }
		//}

	}
	@FXML
	public void acceptButtonHandler(ActionEvent e){
		
		
		/************ confirm request to accept gratuity***********/
		try{
			/**** Confirm****/
			
			//RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(dto.getId());
		    //GratuityDTO gto = new GratuityDAO(dto.getId()).getGratuity(Long.valueOf(regularPensioner.getGratuity().getUrl()));
			//GratuityDAO dao = new GratuityDAO(dto.getId());
			//dao.updateGratuityStatus(true, dto.getId());
			
			

		}catch(Exception ex) {
			NotificationManager.info("Error!", "Cannot Accept gratuity");
		}
	}
	
	@Override
	public void setData(Object object, PopOver pop) {
		this.init((ViewGratuitiesTableItem) object, pop);
	}
	
	@FXML
	public void closeButtonHandler(ActionEvent e){
		pop.hide(new Duration(500));
	}

}
