package org.pensions.printmodule;


//import java.awt.List;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import org.controlsfx.control.PopOver;
import org.pensions.data.dao.BankDAO;
import org.pensions.data.dao.DependentDAO;
import org.pensions.data.dao.GratuityDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.enumarations.DependentType;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.BranchDTO;
import org.pensions.model.DependentDTO;
import org.pensions.model.GratuityDTO;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.model.SpouseDTO;
import org.pensions.model.internal.InterviewLetter;
import org.pensions.util.DateUtil;
//import org.pensions.view.pd3.Node;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;



public class TemplateController implements Initializable, Popoverble{
	/** Interview Application print **/

	@FXML
	private Label wnoptxt;
	@FXML
	private Label currentDate;
	@FXML
	private Label date;

	private PensionerDTO dto;
	private PopOver pop;

	/** Sathkara Certificate Print **/
	@FXML private Label currentdateS;
	@FXML private Label indexNumber;
	@FXML private Label pensionNumber;
	@FXML private Label wopNumber;
	@FXML private Label PenFullName;
	@FXML private Label penLastOccupation;
	@FXML private Label penRetirementDate;
	@FXML private Label penDesignation;
	@FXML private Label penNIC;
	@FXML private Label lawNumber;

	@FXML private Label widName;
	@FXML private Label widNIC;
	@FXML private Label widMerrageDate;
	@FXML private Label widDistrict;
	@FXML private Label widMerrageCetNumber;

	@FXML private GridPane dependentGrid;

	@FXML private Label pAccountNumber;
	@FXML private Label pBank;
	@FXML private Label bank;
	@FXML private Label branch;
	@FXML private Label accountNumber;
	@FXML private Label noDDate;
	@FXML private Label pensionStartDate;
	@FXML private Label grativity;
	@FXML private Label redPension;
	@FXML private Label unRedPension;
	@FXML private Label printBtn;
	@FXML private Label sdate;

	@FXML private Label sonname;
	@FXML private Label lblpensionNumber;

	public void init(InterviewLetter letterData, PopOver pop) {
		// TODO Auto-generated method stub
		this.dto = letterData.getPensioner();
		Hetoes activePension = dto.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);

		dto = new PensionerDAO().getPensioner(dto.getId());
		dto.getPensions().add(activePension);
		PensionDTO pension = new PensionsDAO(dto.getId()).getPension(Long.valueOf(activePension.getUrl()));
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
		wnoptxt.setText(dto.getwOPNumber());
		currentDate.setText(timeStamp);
		date.setText(letterData.getInterviewDate());
		lblpensionNumber.setText(String.valueOf(pension.getId()));
		
	}
	/*
	 * print award
	 */
	public void setPensioner(PensionerDTO pensioner) {

		Hetoes activePension = pensioner.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);

		pensioner = new PensionerDAO().getPensioner(pensioner.getId());
		pensioner.getPensions().add(activePension);

		GratuityDTO gto = new GratuityDAO(pensioner.getId()).getGratuity(Long.valueOf(pensioner.getGratuity().getUrl()));

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
		currentdateS.setText(timeStamp);
		indexNumber.setText(String.valueOf(pensioner.getId()));
		wopNumber.setText(String.valueOf(pensioner.getwOPNumber()));
		penNIC.setText(pensioner.getNic());
		penDesignation.setText(pensioner.getDesignation());
		PenFullName.setText(pensioner.getSalutation()+"."+pensioner.getName());
		penLastOccupation.setText(pensioner.getServiceDomain());
		penRetirementDate.setText(pensioner.getDateOfRetired());
		penNIC.setText(pensioner.getNic());
		penDesignation.setText(pensioner.getDesignation());
		grativity.setText(String.valueOf(gto.getGratuityAmount()));


		DependentDAO dependentDAO = new DependentDAO(pensioner.getId());
		List<DependentDTO> dependentList = dependentDAO.getDependents().getList();
		int childCount = 0;
		for (DependentDTO dependent : dependentList) {
			if(DependentType.SPOUSE.getType().equalsIgnoreCase(dependent.getRelation())){
				SpouseDTO spouse = new DependentDAO(pensioner.getId()).getSpouse(dependent.getId());
				widName.setText(spouse.getName());
				widNIC.setText(spouse.getNic());
				widMerrageDate.setText(spouse.getMerrageDate());
			}else
			//if(DependentType.CHILD.getType().equalsIgnoreCase(dependent.getRelation()))
			{

				Label lblName = new Label(dependent.getName());
				lblName.setPrefWidth(1000);

				Label lblNic = new Label(dependent.getNic());
				lblNic.setPrefWidth(1000);
				Label lblDob = new Label(dependent.getDob());
				lblDob.setPrefWidth(1000);
				dependentGrid.add(lblName, 0, childCount);
				dependentGrid.add(lblDob, 1, childCount);
				dependentGrid.add(lblNic, 3, childCount);

				childCount++;
				if(childCount == 3) break;
			}
		}


		if(activePension != null) { //there is a pension which is activated
			PensionDTO pension = new PensionsDAO(pensioner.getId()).getPension(Long.valueOf(activePension.getUrl()));
			pAccountNumber.setText(pension.getAccountNumber());
			pensionNumber.setText(String.valueOf(pension.getId()));
			//accountNumber.setText(pension.getAccountNumber());
			redPension.setText(pension.getNetReducedPercentage().toString());
			unRedPension.setText(pension.getNetUnreducedPercentage().toString());
			BranchDTO branchDto  = new BankDAO().getBank(Integer.valueOf(pension.getBranch().getUrl()));
			bank.setText(branchDto.getBank().getName());
			pBank.setText(branchDto.getBank().getName());
			branch.setText(branchDto.getName());
			noDDate.setText("");
			pensionStartDate.setText(pension.getStartedDate());
		}

	}


	@Override
	public void setData(Object object, PopOver pop) {
		this.init((InterviewLetter) object, pop);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
