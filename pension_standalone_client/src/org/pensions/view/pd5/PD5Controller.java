package org.pensions.view.pd5;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.glassfish.hk2.api.AOPProxyCtl;
import org.pensions.data.dao.EmployeeDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PercentageDAO;
import org.pensions.data.dao.sqlite.SqliteDAO;
import org.pensions.enumarations.DependentType;
import org.pensions.enumarations.MaritalStatus;
import org.pensions.enumarations.Salutation;
import org.pensions.enumarations.ServiceDomain;
import org.pensions.model.DeceasedPensionerDTO;
import org.pensions.model.DependentDTO;
import org.pensions.model.GratuityDTO;
import org.pensions.model.GratuityPaymentDTO;
import org.pensions.model.Percentage;
import org.pensions.model.SpouseDTO;
import org.pensions.util.DateUtil;
import org.pensions.util.RadioUtil;
import org.pensions.view.pd5.models.Dependents;
import org.pensions.view.pd5.models.Proportions;
import org.pensions.view.pd5.models.Spouse;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class PD5Controller implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}/*

	@FXML
	private TabPane tabPane;

	@FXML
	private AnchorPane basicTab;
	@FXML
	private AnchorPane gratuityTab;
	@FXML
	private AnchorPane spouseTab;
	@FXML
	private AnchorPane dependentTab;
	@FXML
	private AnchorPane proportionalTab;

	@FXML
	private GridPane basicNavigator;

	private GridPane selectedGrid;

	@FXML
	private TextField empName;
	@FXML
	private TextField empAddress;
	@FXML
	private TextField empNic;
	@FXML
	private TextField wnopNo;
	@FXML
	private TextField serviceYears;
	@FXML
	private TextField serviceMonths;
	@FXML
	private TextField serviceDays;
	@FXML
	private TextField annualSalary;
	@FXML
	private TextField allowances;
	@FXML
	private TextField grossSalary;
	@FXML
	private TextField noPayYears;
	@FXML
	private TextField noPayMonths;
	@FXML
	private TextField noPayDays;
	@FXML
	private TextField setOffYears;
	@FXML
	private TextField setOffMonths;
	@FXML
	private TextField setOffDays;
	@FXML
	private TextField netNopayYears;
	@FXML
	private TextField netNopayMonths;
	@FXML
	private TextField netNopayDays;
	@FXML
	private TextField greaterReduced;
	@FXML
	private TextField greaterUnreduced;
	@FXML
	private TextField lessReduced;
	@FXML
	private TextField lessUnreduced;
	@FXML
	private TextField deductLessReduced;
	@FXML
	private TextField deductLessUnreduced;
	@FXML
	private TextField deductNopayReduced;
	@FXML
	private TextField deductNopayUnreduced;
	@FXML
	private TextField netPecentageReduced;
	@FXML
	private TextField netPecentageUnreduced;
	@FXML
	private TextField gratuityAmount;
	@FXML
	private TextField grossAnnualSal;
	@FXML
	private TextField deathGratuity;
	@FXML
	private TextField govDeductions;
	@FXML
	private TextField netGratuity;
	@FXML
	private TextField spouseName;
	@FXML
	private TextField spouseAddress;
	@FXML
	private TextField spouseContactNo;
	@FXML
	private TextField spouseAccNo;
	@FXML
	private TextField spouseNic;
	@FXML
	private TextField dependName;
	@FXML
	private TextField dependAddress;
	@FXML
	private TextField dependAccNo;
	@FXML
	private TextField dependentNic;

	@FXML
	private DatePicker deceasedDate;
	@FXML
	private DatePicker spouseDob;
	@FXML
	private DatePicker dependentDob;

	@FXML
	private ToggleGroup radioPensionable;
	@FXML
	private ToggleGroup radioPermanent;
	@FXML
	private ToggleGroup radioCentralPrivincial;

	@FXML
	private ComboBox<String> empSalutaion;
	@FXML
	private ComboBox<String> spouseBank;
	@FXML
	private ComboBox<String> spouseBranch;
	@FXML
	private ComboBox<String> dependMarital;
	@FXML
	private ComboBox<String> dependType;
	@FXML
	private ComboBox<String> dependBank;
	@FXML
	private ComboBox<String> dependBranch;
	@FXML
	private ComboBox<String> officeName;
	@FXML
	private ComboBox<String> designation;

	@FXML 
	private ToggleGroup permanantPension;
	@FXML 
	private ToggleGroup permanantPost;

	@FXML
	private Button basicNext;
	@FXML
	private Button gratuityNext;
	@FXML
	private Button spouseNext;
	@FXML
	private Button dependAdd;
	@FXML
	private Button dependNext;
	@FXML
	private Button verifyState;
	@FXML
	private Button rejectState;
	@FXML
	private Button submit;

	@FXML
	private CheckBox checkNoPay;
	@FXML
	private CheckBox checkUnmarried;
	@FXML
	private CheckBox checkDependent;
	@FXML
	private CheckBox checkCertified;

	@FXML
	private GridPane noPayGrid;
	@FXML
	private GridPane spouseGrid;
	@FXML
	private GridPane dependentGrid;
	@FXML
	private GridPane verifyRejBtnGrid;
	@FXML
	private GridPane submitBtnGrid;

	@FXML
	private TableView<Dependents> dependentsTable;
	@FXML
	private TableView<Proportions> proportionsTable;

	@FXML
	private TableColumn<String,String> propName;

	@FXML
	private Label nicValidation;
	@FXML
	private Label gratuityProportions;
	@FXML
	private Label spouseNicValidation;
	@FXML
	private Label dependentNicValidation;

	private long pensionerNo;

	private ObservableList<Dependents> dependentsForTbl = FXCollections.observableArrayList();
	private ObservableList<Proportions> proportionsForTbl = FXCollections.observableArrayList();

	//////////////////////////Dependents Table //////////////////////////////
	@FXML private TableColumn<Dependents, String> tblClDependent_name;
	@FXML private TableColumn<Dependents, String> tblClDependent_dob;
	@FXML private TableColumn<Dependents, String> tblClDependent_maritalStatus;
	@FXML private TableColumn<Dependents, String> tblClDependent_type;
	@FXML private TableColumn<Dependents, String> tblClDependent_address;
	@FXML private TableColumn<Dependents, String> tblClDependent_bank;
	@FXML private TableColumn<Dependents, String> tblClDependent_branch;
	@FXML private TableColumn<Dependents, String> tblClDependent_nicno;
	@FXML private TableColumn<Dependents, String> tblClDependent_accountNo;
	---------------------------------------------------------------------

	//////////////////////////Proportions Table //////////////////////////////
	@FXML private TableColumn<Proportions, String> tblClProportions_name;
	@FXML private TableColumn<Proportions, String> tblClProportions_bank;
	@FXML private TableColumn<Proportions, String> tblClProportions_branch;
	@FXML private TableColumn<Proportions, String> tblClProportions_accno;
	@FXML private TableColumn<Proportions, String> tblClProportions_amount;
	---------------------------------------------------------------------

	////////////////////////// Form Navigation Tab //////////////////////////
	private void setActive(MouseEvent e) {
		if(selectedGrid != null){
			selectedGrid.getStyleClass().remove("formNavActive");
		}
		((GridPane) e.getSource()).getStyleClass().add("formNavActive");
		selectedGrid = (GridPane) e.getSource();
	}

	@FXML
	public void formWizardBasicBtnClicked(MouseEvent e) {
		basicTab.setVisible(true);
		gratuityTab.setVisible(false);
		spouseTab.setVisible(false);
		dependentTab.setVisible(false);
		proportionalTab.setVisible(false);
		setActive(e);
	}

	@FXML
	public void formWizardGratuityBtnClicked(MouseEvent e) {
		
		if(!basicNext.isDisable()) {
			basicTab.setVisible(false);
			gratuityTab.setVisible(true);
			spouseTab.setVisible(false);
			dependentTab.setVisible(false);
			proportionalTab.setVisible(false);
			setActive(e);
		}
		
	}

	@FXML
	public void formWizardSpouseBtnClicked(MouseEvent e) {
		
		if(!gratuityNext.isDisable()) {
			basicTab.setVisible(false);
			gratuityTab.setVisible(false);
			spouseTab.setVisible(true);
			dependentTab.setVisible(false);
			proportionalTab.setVisible(false);
			setActive(e);
		}
		
	}

	@FXML
	public void formWizardDependentBtnClicked(MouseEvent e) {
		
		if(!spouseNext.isDisable()) {
			basicTab.setVisible(false);
			gratuityTab.setVisible(false);
			spouseTab.setVisible(false);
			dependentTab.setVisible(true);
			proportionalTab.setVisible(false);
			setActive(e);
		}
		
	}

	@FXML
	public void formWizardProportionalBtnClicked(MouseEvent e) {
		
		if(!dependNext.isDisable()) {
			basicTab.setVisible(false);
			gratuityTab.setVisible(false);
			spouseTab.setVisible(false);
			dependentTab.setVisible(false);
			proportionalTab.setVisible(true);
			setActive(e);
		}
		
	}
	---------------------------------------------------------------------

	private DeceasedPensionerDTO pensioner = new DeceasedPensionerDTO();

	private SpouseDTO spouse = new SpouseDTO();

	private GratuityDTO gratuity = new GratuityDTO();

	private int currentSelectedDependentIndex = -1;
	private double assignReducedPercentage = 0.0;
	private double assignUnReducedPercentage = 0.0;

	public PD5Controller() {

	}


	@FXML private void nextTab() {
		tabPane.getSelectionModel().selectNext();
		tabPane.getSelectionModel().getSelectedItem().setDisable(false);

	}


	@FXML private void setValidity() {
		String nic = empNic.getText();
		if(!nic.matches("[0-9]{9}[vVxX]"))
		{
			nicValidation.setText("Invalid NIC");
			nicValidation.setVisible(true);
		}
		else
		{
			nicValidation.setVisible(false);
			if(officeName.getSelectionModel().getSelectedItem() != null && empName.getText() != null && empAddress.getText() != null 
					&& empNic.getText() != null && designation.getSelectionModel().getSelectedItem() != null && 
					deceasedDate.getValue() != null && wnopNo.getText() != null 
					&& (radioPensionable.getSelectedToggle().isSelected()) 
					&& (radioPermanent.getSelectedToggle().isSelected()) 
					&& (radioCentralPrivincial.getSelectedToggle().isSelected()))
			{

				basicNext.setDisable(false);

			}
		}
	}

	@FXML
	private void setValidity_Gratuity(){
		if(netGratuity.getText() != null)
		{
			gratuityNext.setDisable(false);

		}
	}

	@FXML
	private void setSpouseValidity() {
		String spousenic = spouseNic.getText();
		if(!spousenic.matches("[0-9]{9}[vVxX]"))
		{
			spouseNicValidation.setText("Invalid NIC");
			spouseNicValidation.setVisible(true);
		}
		else {
			if(spouseName.getText() != null && spouseDob.getValue() != null && spouseAddress.getText() != null && spouseContactNo.getText() != null && spouseBank.getSelectionModel().isEmpty() == false && spouseBranch.getSelectionModel().isEmpty() == false && spouseAccNo.getText() != null && spouseNic.getText() != null)
			{
				spouseNext.setDisable(false);
			}
		}
	}

	@FXML 
	private void setDependentValidity() {

		if(dependName.getText() != null && dependAddress.getText() != null && dependAccNo.getText() != null) {
			dependAdd.setDisable(false);
		} else {
			dependAdd.setDisable(true);
		}
	}

	//////////////////// Check box disable/enable click events /////////////////////
	@FXML
	private void disableSpouse() {
		if(checkUnmarried.isSelected() == true)
		{
			spouseGrid.setDisable(true);
			spouseNext.setDisable(false);
		}
		else
		{
			spouseGrid.setDisable(false);
			spouseNext.setDisable(true);
		}
	}

	@FXML
	private void disableDependent() {
		if(checkDependent.isSelected() == true)
		{
			dependentGrid.setDisable(true);
			dependentsTable.setDisable(true);
			dependAdd.setDisable(true);
			dependNext.setDisable(false);
		}
		else
		{
			dependentGrid.setDisable(false);
			dependentsTable.setDisable(false);
			dependAdd.setDisable(false);
			dependNext.setDisable(true);
		}
	}
	-------------------------------------------------------------------------------

	@FXML
	private void addDependent() {
		Dependents d = new Dependents();
		SqliteDAO sdao = new SqliteDAO();
		
		d.setName(dependName.getText()); 
		d.setDob(dependentDob.getValue().toString());
		d.setMaritalStatus(dependMarital.getValue().equals("Married") ? true : false); 
		d.setRelation(dependType.getValue());
		d.setAddress(dependAddress.getText());
		d.setNic(dependentNic.getText());
	//	d.setBranchId(sdao.getBranchIdFromName(dependBranch.getSelectionModel().getSelectedItem(), dependBank.getSelectionModel().getSelectedItem()));
		d.setAccountNumber(dependAccNo.getText());
	//	d.setBranch(dependBranch.getValue());
		d.setBank(dependBank.getValue());

		boolean insert = false;

		for(Dependents dep : dependentsForTbl) {
			if(dep.getNic().equals(d.getNic())) {
				insert = false;
				break;
			}else {
				insert = true;
			}
		}

		if(insert || currentSelectedDependentIndex == -1) {
			dependentsForTbl.add(d);
			dependentsTable.setEditable(true);
			dependentsTable.setItems(dependentsForTbl);
		} else {
			dependentsForTbl.remove(currentSelectedDependentIndex);
			dependentsForTbl.add(currentSelectedDependentIndex, d);
			dependentsTable.setItems(dependentsForTbl);
			currentSelectedDependentIndex = -1;
		}
		dependNext.setDisable(false);
	}

	private double setPrecisition(double value) {
		return Double.parseDouble(new DecimalFormat("0.00").format(value));
	}

	*//**
	 * @get reduced unreduced percentages
	 * @return
	 *//*
	public Percentage getReducedUnreducedPercentages() {
		PercentageDAO dao = new PercentageDAO();
		Percentage p = null;

		p = dao.getPercentage(Integer.valueOf(serviceYears.getText()), Integer.parseInt(serviceMonths.getText()), Double.valueOf(grossSalary.getText()));

		return p;
	}

	
	 * @Calculation for deductable percentage for each 6 months 1%
	 * @return type double
	 
	public double calculateDeductablePercentage(org.pensions.model.Period p) {
		double deductablePercentage = 0.0;

		if(p.getMonths() >= 0 && p.getMonths() < 6) {
			deductablePercentage = (25-p.getYears())*2;
		} else if(p.getMonths() >= 6) {
			deductablePercentage = (25-(p.getYears()+0.5))*2;
		} else {
			deductablePercentage = 0.0;
		}

		return setPrecisition(deductablePercentage);
	}

	
	 * @Calculation for deductable percentage foe each no pay months 0.2%
	 * @return type double
	 
	public double calculateDeductablePercentage_NoPayMonths() {
		double deductablePercentage = 0.0;
		int years = Integer.parseInt(netNopayYears.getText());
		int months = Integer.parseInt(netNopayMonths.getText());
		int days = Integer.parseInt(netNopayDays.getText());

		if(Integer.parseInt(netNopayDays.getText()) > 0) {
			days = 1;
		}

		deductablePercentage = (years*12 + months + days)*0.2;
		return setPrecisition(deductablePercentage);
	}

	
	 * @Calculate for net percentage
	 * @return type double
	 
	public double[] calculateNetPercentage() {

		org.pensions.model.Period servicePeriod = new org.pensions.model.Period();
		servicePeriod.setYears(Integer.parseInt(serviceYears.getText()));
		servicePeriod.setMonths(Integer.parseInt(serviceMonths.getText()));
		servicePeriod.setDays(Integer.parseInt(serviceDays.getText()));

		double netPercentageReduced = 0.0;
		double netPercentageUnreduced = 0.0;

		if(Integer.parseInt(serviceYears.getText()) >= 25) {
			netPercentageReduced = assignReducedPercentage - calculateDeductablePercentage_NoPayMonths();
			netPercentageUnreduced = assignUnReducedPercentage - calculateDeductablePercentage_NoPayMonths();
		} else {
			netPercentageReduced = assignReducedPercentage - calculateDeductablePercentage_NoPayMonths() - calculateDeductablePercentage(servicePeriod);
			netPercentageUnreduced = assignUnReducedPercentage - calculateDeductablePercentage_NoPayMonths() - calculateDeductablePercentage(servicePeriod);
		}

		return new double[] {netPercentageReduced,netPercentageUnreduced};
	}

	
	 * @Calculation for net gratuity amount
	 * @return type double
	 
	public double calculateNetGratuity() {
		double netGratuity = 0.0;

		netGratuity = (calculateGrossAnnualSalary() > calculateGrossGratuityAmount()) ? calculateGrossAnnualSalary() : calculateGrossGratuityAmount() - Double.parseDouble(govDeductions.getText());

		return setPrecisition(netGratuity);
	}

	
	 * @Calculation for gross annual salary
	 * @return type double
	 
	public double calculateGrossAnnualSalary() {
		double grossAnnualSalary = 0.0;

		if(annualSalary.getText().isEmpty() == false && allowances.getText().isEmpty() == false){
			grossAnnualSalary = Double.parseDouble(annualSalary.getText()) + Double.parseDouble(allowances.getText());
		} else {
			grossAnnualSalary = 0.00;
		}

		return setPrecisition(grossAnnualSalary);
	}

	
	 * @Calculationh for gross gratuity amount
	 * @return type double
	 
	public double calculateGrossGratuityAmount() {

		double grossGratuity = 0.0;
		double[] percentages = calculateNetPercentage();
		grossGratuity = calculateGrossAnnualSalary() * (percentages[1])/100*2;
		return setPrecisition(grossGratuity);

	}

	*//**
	 * Get highest amount for death gratuity
	 * @return
	 *//*
	public double getHighestAmountForDeathGratuity() {
		double deathGratuity = 0.0;

		if(Double.parseDouble(gratuityAmount.getText()) > Double.parseDouble(grossAnnualSal.getText())) {
			deathGratuity = Double.parseDouble(gratuityAmount.getText());
		} else {
			deathGratuity = Double.parseDouble(grossAnnualSal.getText());
		}

		return setPrecisition(deathGratuity);
	}
	
	*//**
	 * Calculate proportional details
	 *//*
	@FXML
	private void calculateProportions() {

		Spouse sp = new Spouse();
		sp.setBank(spouseBank.getSelectionModel().getSelectedItem());
	//	sp.setBranch(spouseBranch.getSelectionModel().getSelectedItem());
		sp.setAccountNumber(spouseAccNo.getText());
		sp.setName(spouseName.getText());

		proportionsTable.getItems().clear();

		double gratAmount = calculateNetGratuity();
		double percentage = 100;

		gratuityProportions.setText(String.valueOf(gratAmount));

		List<Dependents> parents = new ArrayList<Dependents>();
		List<Dependents> children = new ArrayList<Dependents>();
		List<Dependents> siblings = new ArrayList<Dependents>();
		List<Dependents> dayakaSabawa = new ArrayList<Dependents>();

		

		//Get dependents into list
		if(dependentsTable.getItems().size() > 0) {

			for(Dependents d : dependentsTable.getItems()) {

				if(d.getRelation().equals(DependentType.PARENT.getType()))
				{
					parents.add(d);
				}
				if(d.getRelation().equals(DependentType.CHILD.getType()))
				{
					children.add(d);
				}
				if(d.getRelation().equals(DependentType.SIBLING.getType()))
				{
					siblings.add(d);
				}
				if(d.getRelation().equals(DependentType.DAYAKA_SABHAWA.getType()))
				{
					dayakaSabawa.add(d);
				}
			}

		}

		if(!checkUnmarried.isSelected()) {

			if(dependentsTable.getItems().size() > 0) {

				Proportions payment = new Proportions();
				double spouseAmount = gratAmount * percentage/(2*100);

				payment.setAccountNumber(sp.getAccountNumber());
		//		payment.setBranchId(sp.getBranchId());
				payment.setPercentage(percentage/(2));
				payment.setAmount(spouseAmount);
				payment.setDependentId(sp.getId());
				payment.setBankName(sp.getBank());
		//		payment.setBranchName(sp.getBranch());
				payment.setDependentName(sp.getName());

				proportionsForTbl.add(payment);

				for(int i=0;i<children.size();i++) 
				{
					payment = new Proportions();
					Dependents d = children.get(i);
					double dependentAmount = gratAmount * percentage/(2*children.size()*100);

					payment.setAccountNumber(d.getAccountNumber());
		//			payment.setBranchId(d.getBranchId());
					payment.setPercentage(percentage/(2*children.size()));
					payment.setAmount(dependentAmount);
//					payment.setDependentId(d.getId());
					payment.setDependentName(d.getName());
					payment.setBankName(d.getBank());
			//		payment.setBranchName(d.getBranch());

					proportionsForTbl.add(payment);
				}

			} else {
				
				Proportions payment = new Proportions();
				double spouseAmount = gratAmount * percentage/(100);

				payment.setAccountNumber(sp.getAccountNumber());
		//		payment.setBranchId(sp.getBranchId());
				payment.setPercentage(percentage);
				payment.setAmount(spouseAmount);
				payment.setDependentId(sp.getId());
				payment.setBankName(sp.getBank());
		//		payment.setBranchName(sp.getBranch());
				payment.setDependentName(sp.getName());

				proportionsForTbl.add(payment);
				
			}

		} else if(checkUnmarried.isSelected()) {

			if(dependentsTable.getItems().size() > 0) {

					for(int i=0;i<parents.size();i++) {
						Proportions payment = new Proportions();
						Dependents d = parents.get(i);
						double parentAmount = gratAmount * percentage/(parents.size() * 100);

						payment.setAccountNumber(d.getAccountNumber());
			//			payment.setBranchId(d.getBranchId());
						payment.setPercentage(percentage/(parents.size()));
						payment.setAmount(parentAmount);
						payment.setDependentId(d.getId());
						payment.setDependentName(d.getName());
						payment.setBankName(d.getBank());
			//			payment.setBranchName(d.getBranch());

						proportionsForTbl.add(payment);
					}

					for(int i=0;i<children.size();i++) {
						Proportions payment = new Proportions();
						Dependents d = children.get(i);
						double childrenAmount = gratAmount * percentage/(children.size() * 100);

						payment.setAccountNumber(d.getAccountNumber());
		//				payment.setBranchId(d.getBranchId());
						payment.setPercentage(percentage/(children.size()));
						payment.setAmount(childrenAmount);
						payment.setDependentId(d.getId());
						payment.setDependentName(d.getName());
						payment.setBankName(d.getBank());
		//				payment.setBranchName(d.getBranch());

						proportionsForTbl.add(payment);
					}
					
					for(int i=0;i<siblings.size();i++) {
						Dependents d = siblings.get(i);
						Proportions payment = new Proportions();
						double siblingsAmount = gratAmount * percentage/(siblings.size() * 100);
						//gratAmount = gratAmount - amount;

						payment.setAccountNumber(d.getAccountNumber());
	//					payment.setBranchId(d.getBranchId());
						payment.setPercentage(percentage/(siblings.size()));
						payment.setAmount(siblingsAmount);
						payment.setDependentId(d.getId());
						payment.setDependentName(d.getName());
						payment.setBankName(d.getBank());
			//			payment.setBranchName(d.getBranch());

						proportionsForTbl.add(payment);
					}
					
					for(int i=0;i<dayakaSabawa.size();i++) {
						Dependents d = dayakaSabawa.get(i);
						Proportions payment = new Proportions();
						double dayakasabawaAmount = gratAmount * percentage/(dayakaSabawa.size() * 100);
						
						payment.setAccountNumber(d.getAccountNumber());
			//			payment.setBranchId(d.getBranchId());
						payment.setPercentage(percentage/(dayakaSabawa.size()));
						payment.setAmount(dayakasabawaAmount);
						payment.setDependentId(d.getId());
						payment.setDependentName(d.getName());
						payment.setBankName(d.getBank());
	//					payment.setBranchName(d.getBranch());

						proportionsForTbl.add(payment);
						
					}

			}

		}
		
		proportionsTable.setItems(proportionsForTbl);

	}

	
	 * @Set dependents into dependents object
	 * @return type object
	 
	private Dependents modelToDependents(DependentDTO dto) {
		Dependents d = new Dependents();
		SqliteDAO dao = new SqliteDAO();

		d.setName(dto.getName());
		d.setDob(dto.getDob());
		d.setAccountNumber(dto.getAccountNumber());
		d.setAddress(dto.getAddress());
	//	d.setBank(dao.getBankFromId(dto.getBranchId()));
	//	d.setBranch(dao.getBranchFromId(dto.getBranchId()));
	//	d.setMaritalStatus(dto.isMaritalStatus());
	//	d.setNic(dto.getNic());
		d.setRelation(dto.getRelation());

		return d;

	}

	
	 *@Method for calculation no pay leaves
	 
	public void setNoPayLeaves() {
		Period noPay = Period.of(Integer.parseInt(noPayYears.getText()),Integer.parseInt(noPayMonths.getText()),Integer.parseInt(noPayDays.getText()));
		Period setOff = Period.of(Integer.parseInt(setOffYears.getText()),Integer.parseInt(setOffMonths.getText()),Integer.parseInt(setOffDays.getText()));
		Period netNoPay = noPay.minus(setOff);

		netNopayYears.setText(String.valueOf(netNoPay.getYears()));
		netNopayMonths.setText(String.valueOf(netNoPay.getMonths()));
		netNopayDays.setText(String.valueOf(netNoPay.getDays()));
	}

	
	 * @Set dependent object to field
	 
	private void setDependentObjectToField(Dependents dep) {
		dependName.setText(dep.getName());
		dependentDob.setValue(DateUtil.stringToLocalDate(dep.getDob()));
//		dependMarital.setValue(dep.getMarital());
		dependType.setValue(dep.getRelation());
		dependentNic.setText(dep.getNic());
		dependAddress.setText(dep.getAddress());
		dependAccNo.setText(dep.getAccountNumber());
		dependBank.setValue(dep.getBank());
		//dependBranch.setValue(dep.getBranch());
	}

	@FXML
	public void editDependentField() {
		currentSelectedDependentIndex = dependentsTable.getSelectionModel().getSelectedIndex();
		Dependents d = dependentsTable.getItems().get(currentSelectedDependentIndex);
		setDependentObjectToField(d);
	}

	@FXML
	public void rejectBtnActionHandler() {
		EmployeeDAO dao = new EmployeeDAO();
		dao.changePensionerState(false, pensionerNo);
	}

	@FXML
	public void acceptBtnActionHandler() {
		EmployeeDAO dao = new EmployeeDAO();
		dao.changePensionerState(true, pensionerNo);
	}

	
	 * @Getter method for deceased pensioner
	 * @return deceased pensioner object
	 
	private DeceasedPensionerDTO getDeceasedPensionerSAVE() {

		DeceasedPensionerDTO dto = new DeceasedPensionerDTO();
		org.pensions.model.Period p = new org.pensions.model.Period();
		org.pensions.model.Period noPayPeriod = new org.pensions.model.Period();
		GratuityDTO gdto = new GratuityDTO();
		SpouseDTO sdto = new SpouseDTO();
		SqliteDAO sdao = new SqliteDAO();

		dto.setOfficeName(officeName.getSelectionModel().getSelectedItem());
		dto.setSalutation(empSalutaion.getSelectionModel().getSelectedItem());
		dto.setName(empName.getText());
		dto.setAddress(empAddress.getText());
		dto.setNic(empNic.getText());
		dto.setDesignation(designation.getSelectionModel().getSelectedItem());
		dto.setDateOfDeceased(DateUtil.dateToString(deceasedDate.getValue()));
		dto.setDateOfRetired(DateUtil.dateToString(deceasedDate.getValue()));
	//	dto.setState(100);


		if(RadioUtil.getTextOf(radioPensionable.getSelectedToggle()).equals("Yes"))
		{
			dto.setPensionable(true);
		}
		else if(RadioUtil.getTextOf(radioPensionable.getSelectedToggle()).equals("No"))
		{
			dto.setPensionable(false);
		}
		if(RadioUtil.getTextOf(radioPermanent.getSelectedToggle()).equals("Yes"))
		{
			dto.setPermanant(true);
		}
		else if(RadioUtil.getTextOf(radioPermanent.getSelectedToggle()).equals("No"))
		{
			dto.setPensionable(false);
		}
		if(RadioUtil.getTextOf(radioCentralPrivincial.getSelectedToggle()).equals("Provincial"))
		{
			dto.setServiceDomain("Provincial");
		}
		else if(RadioUtil.getTextOf(radioCentralPrivincial.getSelectedToggle()).equals("Central"))
		{
			dto.setServiceDomain("Central");
		}

//		dto.setWopNumber(wnopNo.getText());
//		p.setYears(Integer.parseInt(serviceYears.getText()));
//		p.setMonths(Integer.parseInt(serviceMonths.getText()));
//		p.setDays(Integer.parseInt(serviceDays.getText()));
//		dto.setServicePeriod(p);
//
//		//gratuity
//		gdto.setAllowance(Double.parseDouble(allowances.getText()));
//		gdto.setCircular("9/98");
//		gdto.setConsolidatedSalary(Double.parseDouble(annualSalary.getText()));
//		gdto.setGovDeduction(Double.parseDouble(govDeductions.getText()));
//		gdto.setGratuityAmount(Double.parseDouble(netGratuity.getText()));
//		gdto.setPayments(null);
//
//		noPayPeriod.setYears(Integer.parseInt(noPayYears.getText()));
//		noPayPeriod.setMonths(Integer.parseInt(noPayMonths.getText()));
//		noPayPeriod.setDays(Integer.parseInt(noPayDays.getText()));
//		gdto.setTotalNoPay(noPayPeriod);
//		dto.setGratuity(gdto);

		List<DependentDTO> ldto = new ArrayList<>();

		//spouse
		sdto.setAccountNumber(spouseAccNo.getText());
		sdto.setAddress(spouseAddress.getText());
	//	sdto.setBranchId(sdao.getBranchIdFromName(spouseBranch.getSelectionModel().getSelectedItem(),spouseBank.getSelectionModel().getSelectedItem()));
		sdto.setContactNumber(spouseContactNo.getText());
		sdto.setDob(DateUtil.dateToString(spouseDob.getValue()));
		sdto.setMaritalStatus(true); // There should be add fields for select spouse marriage state
		sdto.setMerrageDate("2015-05-01"); // There should be add fields for select spouse marriage date
		sdto.setName(spouseName.getText());
		sdto.setNic(spouseNic.getText());
		sdto.setRelation(DependentType.SPOUSE.getType());
//		sdto.setType(null);

		ldto.add(sdto);

		//dependent
		for(Dependents d : dependentsTable.getItems()) {
			DependentDTO ddto = new DependentDTO();

			ddto.setAccountNumber(d.getAccountNumber());
			ddto.setAddress(d.getAddress());
		//	ddto.setBranchId(d.getBranchId());
//			ddto.setDob(d.getDob());
//			ddto.setMaritalStatus(d.getMarital().equals("Married") ? true : false);
//			ddto.setName(d.getName());
//			ddto.setNic(d.getNic());
//			ddto.setRelation(d.getRelation());
//			ddto.setType(d.getType());

			ldto.add(ddto);
		}

//		dto.setDependents(ldto);

		return dto;

	}

	
	 * @Setter method for deceased pensioner
	 
	public void setDeceasedPensioner(DeceasedPensionerDTO dto) {

		verifyRejBtnGrid.setVisible(true);
		submitBtnGrid.setVisible(false);

		pensionerNo = dto.getId();

		officeName.setValue(dto.getOfficeName());
		empSalutaion.getSelectionModel().select(dto.getSalutation());
		empName.setText(dto.getName());
		empAddress.setText(dto.getAddress());
		empNic.setText(dto.getNic());
		designation.setValue(dto.getDesignation());
		deceasedDate.setValue(DateUtil.stringToLocalDate(dto.getDateOfDeceased()));
		radioPensionable.selectToggle(dto.isPensionable() ? radioPensionable.getToggles().get(1) : radioPensionable.getToggles().get(0));
		radioPermanent.selectToggle(dto.isPermanant() ? radioPermanent.getToggles().get(0) : radioPermanent.getToggles().get(1));
		radioCentralPrivincial.selectToggle(dto.getServiceDomain().equals(ServiceDomain.PROVINTIAL.getDomain()) ? radioCentralPrivincial.getToggles().get(1) : radioCentralPrivincial.getToggles().get(0));
		wnopNo.setText(dto.getWopNumber());
		serviceYears.setText(String.valueOf(dto.getServicePeriod().getYears()));
		serviceMonths.setText(String.valueOf(dto.getServicePeriod().getMonths()));
		serviceDays.setText(String.valueOf(dto.getServicePeriod().getDays()));
		annualSalary.setText(String.valueOf(dto.getGratuity().getConsolidatedSalary()));
		allowances.setText(String.valueOf(dto.getGratuity().getAllowance()));
		grossSalary.setText(String.valueOf(dto.getGratuity().getConsolidatedSalary()+dto.getGratuity().getAllowance()));
		noPayYears.setText(String.valueOf(dto.getGratuity().getTotalNoPay().getYears()));
		noPayMonths.setText(String.valueOf(dto.getGratuity().getTotalNoPay().getMonths()));
		noPayDays.setText(String.valueOf(dto.getGratuity().getTotalNoPay().getDays()));
		setOffYears.setText("1");
		setOffMonths.setText("0");
		setOffDays.setText("0");

		if(dto.getGratuity().getTotalNoPay().getYears() > 0) {
			netNopayYears.setText(String.valueOf(dto.getGratuity().getTotalNoPay().getYears()-1));
		} else {
			netNopayYears.setText("0");
		}

		if(dto.getGratuity().getTotalNoPay().getMonths() > 0) {
			netNopayMonths.setText(String.valueOf(dto.getGratuity().getTotalNoPay().getMonths()-0));
		} else {
			netNopayMonths.setText("0");
		}

		if(dto.getGratuity().getTotalNoPay().getDays() > 0) {
			netNopayDays.setText(String.valueOf(dto.getGratuity().getTotalNoPay().getDays()-0));
		} else {
			netNopayDays.setText("0");
		}

		PercentageDAO dao = new PercentageDAO();
		Percentage percentage = dao.getPercentage(dto.getServicePeriod().getYears(), dto.getServicePeriod().getMonths(), dto.getGratuity().getConsolidatedSalary()+dto.getGratuity().getAllowance());

		if(dto.getServicePeriod().getYears() > 25) {

			greaterReduced.setText(String.valueOf(percentage.getReduced()));
			greaterUnreduced.setText(String.valueOf(percentage.getUnreduced()));
			deductNopayReduced.setText(String.valueOf(calculateDeductablePercentage_NoPayMonths()));
			deductNopayUnreduced.setText(String.valueOf(calculateDeductablePercentage_NoPayMonths()));
			netPecentageReduced.setText(String.valueOf(Double.parseDouble(greaterReduced.getText())-Double.parseDouble(deductNopayReduced.getText())));
			netPecentageUnreduced.setText(String.valueOf(Double.parseDouble(greaterUnreduced.getText())-Double.parseDouble(deductNopayUnreduced.getText())));

			lessReduced.setDisable(true);
			lessUnreduced.setDisable(true);
			deductLessReduced.setDisable(true);
			deductLessUnreduced.setDisable(true);
			greaterReduced.setDisable(false);
			greaterUnreduced.setDisable(false);

		} else {

			lessReduced.setText(String.valueOf(percentage.getReduced()));
			lessUnreduced.setText(String.valueOf(percentage.getUnreduced()));
			deductLessReduced.setText(String.valueOf(calculateDeductablePercentage(dto.getServicePeriod())));
			deductLessUnreduced.setText(String.valueOf(calculateDeductablePercentage(dto.getServicePeriod())));
			deductNopayReduced.setText(String.valueOf(calculateDeductablePercentage_NoPayMonths()));
			deductNopayUnreduced.setText(String.valueOf(calculateDeductablePercentage_NoPayMonths()));
			netPecentageReduced.setText(String.valueOf(Double.parseDouble(lessReduced.getText())-Double.parseDouble(deductLessReduced.getText())-Double.parseDouble(deductNopayReduced.getText())));
			netPecentageUnreduced.setText(String.valueOf(Double.parseDouble(lessUnreduced.getText())-Double.parseDouble(deductLessUnreduced.getText())-Double.parseDouble(deductNopayUnreduced.getText())));

			greaterReduced.setDisable(true);
			greaterUnreduced.setDisable(true);
		}

		//Assign values into reduced un-reduced variables
		assignReducedPercentage = getReducedUnreducedPercentages().getReduced();
		assignUnReducedPercentage = getReducedUnreducedPercentages().getUnreduced();
		gratuityAmount.setText(String.valueOf(calculateGrossGratuityAmount()));
		grossAnnualSal.setText(String.valueOf(calculateGrossAnnualSalary()));

		if(Double.parseDouble(gratuityAmount.getText()) >= Double.parseDouble(grossAnnualSal.getText()))
		{
			deathGratuity.setText(gratuityAmount.getText());
		}
		else
		{
			deathGratuity.setText(grossAnnualSal.getText());
		} 

		govDeductions.setText(String.valueOf(dto.getGratuity().getGovDeduction()));
		netGratuity.setText(String.valueOf(calculateNetGratuity()));

		List<DependentDTO> depList = dto.getDependents();

		//spouse table

		for(DependentDTO dep_dto : depList)
		{
			Dependents d = modelToDependents(dep_dto);

			if(dep_dto instanceof SpouseDTO) {
				spouseName.setText(d.getName());
				spouseDob.setValue(DateUtil.stringToLocalDate(d.getDob()));
				spouseAddress.setText(d.getAddress());
				spouseContactNo.setText(((SpouseDTO) dep_dto).getContactNumber());
				spouseBank.setValue(d.getBank());
				spouseBranch.setValue(d.getBranch());
				spouseAccNo.setText(d.getAccountNumber());
				spouseNic.setText(d.getNic());
			} else {
				dependentsForTbl.add(d);
			}
		}
		dependentsTable.setItems(dependentsForTbl);
		calculateProportions();
	}

	
	 * @FXML method for add deceased pensioner
	 
	@FXML
	private void insertDeceasedPensioner() {

		PensionerDAO dao = new PensionerDAO();
		long pensionerNo = dao.addPensioner(getDeceasedPensionerSAVE());

		if(pensionerNo != -1){
			Notifications.create()
			.title("Pensioner Information Registration Successfull")
			.text("Pensioner Id is "+pensionerNo)
			.showInformation();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		gratuityTab.setVisible(false);
		spouseTab.setVisible(false);
		dependentTab.setVisible(false);
		proportionalTab.setVisible(false);
		verifyRejBtnGrid.setVisible(false);
		verifyRejBtnGrid.setDisable(true);
		submitBtnGrid.setDisable(true);
		selectedGrid = basicNavigator;
		basicNavigator.getStyleClass().add("formNavActive");
		
		basicNext.setDisable(true);
		gratuityNext.setDisable(true);
		spouseNext.setDisable(true);
		dependNext.setDisable(true);



		//AutoCompletionTextFieldBinding<String> auto = new AutoCompletionTextFieldBinding<>(empName, new SuggestionProvider<String>.create("Ha","Kani","Din"));

		gratuityTab.setDisable(true);
		spouseTab.setDisable(true);
		dependentTab.setDisable(true);
		proportionalTab.setDisable(true);

		nicValidation.setVisible(false);
		spouseNicValidation.setVisible(false);
		dependentNicValidation.setVisible(false);

		basicNext.setDisable(true);	
		gratuityNext.setDisable(true);
		spouseNext.setDisable(true);
		dependNext.setDisable(true);
		dependAdd.setDisable(true);

		//Set dependent marital state
		for(MaritalStatus m : MaritalStatus.values()) {
			dependMarital.getItems().add(m.getStatus());
		}
		dependMarital.getSelectionModel().select(0);

		//Set dependent type into combo box
		for(DependentType d : DependentType.values()) {
			dependType.getItems().add(d.getType());
		}
		dependType.getSelectionModel().select(0);

		//Set salutation into combo box
		for(Salutation s : Salutation.values()) {
			empSalutaion.getItems().add(s.getSalutation());
		}
		empSalutaion.getSelectionModel().select(0);

		SqliteDAO s = new SqliteDAO();
		for(int i=0;i<s.getBank().size();i++) {
			dependBank.getItems().add(s.getBank().get(i));
			spouseBank.getItems().add(s.getBank().get(i));
		}

		for(int l=0;l<s.getOffice().size();l++) {
			officeName.getItems().add(s.getOffice().get(l));
		}

		for(int k=0;k<s.getDesignation().size();k++){
			designation.getItems().add(s.getDesignation().get(k));
		}


		AutoCompletionBinding<String> bindAutoCompletion = TextFields.bindAutoCompletion(empName, designation.getItems());

		dependBank.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				dependBranch.getItems().clear();
				for(int k=0;k<s.getBranchesAccordingBankName(dependBank.getSelectionModel().getSelectedItem()).size();k++) {
					dependBranch.getItems().add(s.getBranchesAccordingBankName(dependBank.getSelectionModel().getSelectedItem()).get(k));
				}
				dependBranch.getSelectionModel().select(0);
			}
		});

		spouseBank.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				spouseBranch.getItems().clear();
				for(int m=0;m<s.getBranchesAccordingBankName(spouseBank.getSelectionModel().getSelectedItem()).size();m++) {
					spouseBranch.getItems().add(s.getBranchesAccordingBankName(spouseBank.getSelectionModel().getSelectedItem()).get(m));
				}
				spouseBranch.getSelectionModel().select(0);
			}
		});

		//Initialize dependents table
		tblClDependent_accountNo.setCellValueFactory(new PropertyValueFactory<Dependents,String>("AccountNumber"));
		tblClDependent_address.setCellValueFactory(new PropertyValueFactory<Dependents,String>("Address"));
		tblClDependent_bank.setCellValueFactory(new PropertyValueFactory<Dependents,String>("Bank"));
		tblClDependent_branch.setCellValueFactory(new PropertyValueFactory<Dependents,String>("Branch"));
		tblClDependent_dob.setCellValueFactory(new PropertyValueFactory<Dependents,String>("Dob"));
		tblClDependent_maritalStatus.setCellValueFactory(new PropertyValueFactory<Dependents,String>("MaritalStatus"));
		tblClDependent_name.setCellValueFactory(new PropertyValueFactory<Dependents,String>("Name"));
		tblClDependent_nicno.setCellValueFactory(new PropertyValueFactory<Dependents,String>("Nic"));
		tblClDependent_type.setCellValueFactory(new PropertyValueFactory<Dependents,String>("Relation"));

		//Initialize proportions table
		tblClProportions_accno.setCellValueFactory(new PropertyValueFactory<Proportions,String>("accountNumber"));
		tblClProportions_amount.setCellValueFactory(new PropertyValueFactory<Proportions,String>("amount"));
		tblClProportions_bank.setCellValueFactory(new PropertyValueFactory<Proportions,String>("bankName"));
		tblClProportions_branch.setCellValueFactory(new PropertyValueFactory<Proportions,String>("branchName"));
		tblClProportions_name.setCellValueFactory(new PropertyValueFactory<Proportions,String>("dependentName"));


		allowances.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (newPropertyValue) {
				}
				else {

					grossAnnualSal.setText(String.valueOf(calculateGrossAnnualSalary()));
					grossSalary.setText(String.valueOf(calculateGrossAnnualSalary()));


					//Assign values into reduced un-reduced variables
					assignReducedPercentage = getReducedUnreducedPercentages().getReduced();
					assignUnReducedPercentage = getReducedUnreducedPercentages().getUnreduced();

					if(Integer.parseInt(serviceYears.getText()) >= 25) {
						greaterReduced.setText(String.valueOf(assignReducedPercentage));
						greaterUnreduced.setText(String.valueOf(assignUnReducedPercentage));
					} else {
						org.pensions.model.Period servicePeriod = new org.pensions.model.Period();
						servicePeriod.setYears(Integer.parseInt(serviceYears.getText()));
						servicePeriod.setMonths(Integer.parseInt(serviceMonths.getText()));
						servicePeriod.setDays(Integer.parseInt(serviceDays.getText()));

						lessReduced.setText(String.valueOf(assignReducedPercentage));
						lessUnreduced.setText(String.valueOf(assignUnReducedPercentage));
						deductLessReduced.setText(String.valueOf(calculateDeductablePercentage(servicePeriod)));
						deductLessUnreduced.setText(String.valueOf(calculateDeductablePercentage(servicePeriod)));
					}

					double[] percentages = calculateNetPercentage();
					netPecentageReduced.setText(String.valueOf(percentages[0]));
					netPecentageUnreduced.setText(String.valueOf(percentages[1]));
					gratuityAmount.setText(String.valueOf(calculateGrossGratuityAmount()));
					deathGratuity.setText(String.valueOf((calculateGrossAnnualSalary() > calculateGrossGratuityAmount()) ? calculateGrossAnnualSalary() : calculateGrossGratuityAmount()));
					netGratuity.setText(String.valueOf(calculateNetGratuity()));
				}
			}
		});

		setOffDays.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (newPropertyValue) {
				}
				else {
					setNoPayLeaves();
					double[] percentages = calculateNetPercentage();
					netPecentageReduced.setText(String.valueOf(percentages[0]));
					netPecentageUnreduced.setText(String.valueOf(percentages[1]));
					deductNopayReduced.setText(String.valueOf(calculateDeductablePercentage_NoPayMonths()));
					deductNopayUnreduced.setText(String.valueOf(calculateDeductablePercentage_NoPayMonths()));
					gratuityAmount.setText(String.valueOf(calculateGrossGratuityAmount()));
					deathGratuity.setText(String.valueOf((calculateGrossAnnualSalary() > calculateGrossGratuityAmount()) ? calculateGrossAnnualSalary() : calculateGrossGratuityAmount()));
					netGratuity.setText(String.valueOf(calculateNetGratuity()));
				}
			}
		});

		dependAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				addDependent();
				calculateProportions();
			}
		});

		verifyState.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				acceptBtnActionHandler();
			}
		});

		rejectState.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				rejectBtnActionHandler();
			}
		});

		serviceDays.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (newPropertyValue) {
				}
				else {

					if(Integer.parseInt(serviceYears.getText()) >= 31){
						checkNoPay.setSelected(true);
					} else {
						checkNoPay.setSelected(false);
					}

					if(Integer.parseInt(serviceYears.getText()) >= 25) {
						greaterReduced.setDisable(false);
						greaterUnreduced.setDisable(false);
						lessReduced.setDisable(true);
						lessUnreduced.setDisable(true);
						deductLessReduced.setDisable(true);
						deductLessUnreduced.setDisable(true);
					} else {
						greaterReduced.setDisable(true);
						greaterUnreduced.setDisable(true);
						lessReduced.setDisable(false);
						lessUnreduced.setDisable(false);
						deductLessReduced.setDisable(false);
						deductLessUnreduced.setDisable(false);
					}
				}
			}
		});

		checkNoPay.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(newValue) {
					noPayGrid.setDisable(true);
					deductNopayReduced.setDisable(true);
					deductNopayUnreduced.setDisable(true);
				} else {
					noPayGrid.setDisable(false);
					deductNopayReduced.setDisable(false);
					deductNopayUnreduced.setDisable(false);
				}
			}
		});

		checkDependent.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(newValue) {
					dependentGrid.setDisable(true);
				} else {
					dependentGrid.setDisable(false);
				}
			}
		});

		checkUnmarried.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(newValue) {
					spouseGrid.setDisable(true);
				} else {
					spouseGrid.setDisable(false);
				}
			}
		});

		checkCertified.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(newValue) {
					submitBtnGrid.setDisable(false);
					verifyRejBtnGrid.setDisable(false);
				} else {
					submitBtnGrid.setDisable(true);
					verifyRejBtnGrid.setDisable(true);
				}
			}
		});

	}
*/}
