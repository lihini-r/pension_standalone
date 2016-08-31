package org.pensions.view.pd3;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.pensions.data.dao.*;
import org.pensions.enumarations.*;
import org.pensions.model.*;
import org.pensions.model.lists.ExtraPaymentList;
import org.pensions.pensionstatemanager.UpdateStatus;
import org.pensions.printmodule.PrintModule;
import org.pensions.session.UserSession;
import org.pensions.util.DateUtil;
import org.pensions.view.RootLayoutController;
import org.pensions.view.util.PopupUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.processing.RoundEnvironment;

/**
 * @author Buddika
 *
 */
/**
 * @author Buddika
 *
 */
public class PD3Controller implements Initializable {

	/*********** Form navigation ******************/
	@FXML
	private GridPane basicInfoNavigator;
	/**********************************************/

	@FXML
	private AnchorPane dependentInfo;
	@FXML
	private AnchorPane basicInfo;
	@FXML
	private AnchorPane PensionInfo;

	/**************** Dependents ******************/
	@FXML
	private ComboBox<String> comboDependentType;
	@FXML
	private ComboBox<String> comboDepSalutation;
	@FXML
	private ToggleGroup radioDependentMaritalStstus;

	@FXML
	private TextField txtDepName;
	@FXML
	private TextField txtDepAddress;
	@FXML
	private TextField txtDepAccountNumber;
	@FXML
	private TextField txtDepNic;
	@FXML
	private TextField txtSpouseContact;

	public String loaded;

	@FXML private DatePicker dateSposeMerried;


	@FXML private TableColumn<DependentDTO, String> colName;
	@FXML private TableColumn<DependentDTO, String> colAddress;
	@FXML private TableColumn<DependentDTO, String> colAccountNumber;
	@FXML private TableColumn<DependentDTO, String> colNicNumber;
	@FXML private TableColumn<DependentDTO, String> colMariatlStatus;
	@FXML private TableColumn<DependentDTO, String> colRelation;

	@FXML private TableView<DependentDTO> tableDependent;

	/********************************************/

	/************** Basic INfo ************************/
	@FXML
	private ComboBox<String> comboMaritalStstus;
	@FXML
	private ComboBox<String> comboServiceDomain;
	@FXML
	private ToggleGroup radioPensionable;
	@FXML
	private ToggleGroup radioPermanant;

	@FXML
	private TextField txtPensionerName;
	@FXML
	private ComboBox<String> comboPensionerSalutation;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtNic;
	@FXML
	private TextField txtDesignation;
	@FXML
	private TextField txtWnopNumber;
	@FXML
	private DatePicker dateRetired;
	@FXML
	private TextField telephone;
	@FXML
	private TextField mobilePhone;
	@FXML
	private TextField email;
	@FXML
	private TextField skypeId;

	/*************************************************/

	/**************** spouse ******************/
	@FXML
	public Separator hlSpouse;
	@FXML
	public GridPane gridSpouse;
	/********************************************/
	/************** Pension INfo ************************/
	@FXML
	private TextField net;
	@FXML
	private TextField accountNumber;
	@FXML
	private TextField circular;
	@FXML
	private TextField consolidatedSalary;
	@FXML
	private TextField netReducedPercentage;
	@FXML
	private TextField netUnreducedPercentage;
	@FXML
	private TextField scale;
	@FXML
	private TextField days;
	@FXML
	private TextField months;
	@FXML
	private TextField years;
	@FXML
	private TextField id;
	@FXML
	private ComboBox<String> bank;
	@FXML
	private ComboBox<String> branch;
	@FXML
	private TextField txtincreamentdate;
	@FXML
	private Label txtincreament;
	@FXML
	private Label txtallowances;
	

	private GridPane selectedGrid;
	@FXML private Label lblReducedPension;
	@FXML private TextField servicemonthxt;
	@FXML private TextField serviceyearxt;
	@FXML private TextField servicedaysxt;
	@FXML private Label lblUnreducedPension;
	@FXML private Label lblReducedPension2020;
	@FXML private Label lblUnReducedPension2020;

	@FXML private Button accept;
	@FXML private Button reject;
	@FXML private Button close;
	/**************table lists ***********************/

	private ObservableList<DependentDTO> dependentsList = FXCollections.observableArrayList();

	/****************objects in used for editiong purpose**************/
	private DependentDTO depndentCurrentLoaded = null;

	private PensionerDTO dto;
	private PensionDTO pdto;

	String role = UserSession.INSTANCE.getRole();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		basicInfo.setVisible(true);
		dependentInfo.setVisible(false);
		PensionInfo.setVisible(false);
		selectedGrid = basicInfoNavigator;
		basicInfoNavigator.getStyleClass().add("formNavActive");
		init();
		initTbales();
		hookUpInitEventListners();

	}

	private void init() {

		// initializing Dependent types combobox
		Stream.of(DependentType.values()).forEach(type -> {
			comboDependentType.getItems().add(type.getType());
		});

		// initializing Marriage status combobox
		Stream.of(MaritalStatus.values()).forEach(state -> {
			comboMaritalStstus.getItems().add(state.getStatus());
		});

		Stream.of(Salutation.values()).forEach(s -> {
			comboPensionerSalutation.getItems().add(s.getSalutation());
			comboDepSalutation.getItems().add(s.getSalutation());
		});

		Stream.of(ServiceDomain.values()).forEach(d -> {
			comboServiceDomain.getItems().add(d.getDomain());
		});

		/*** for testing****/

	}

	private void hookUpInitEventListners() {
		comboMaritalStstus.valueProperty().addListener(
				new ChangeListener<String>() {
					Predicate<String> isSuccessor = (newValue) -> newValue
							.equals(MaritalStatus.MERRIED.getStatus());

					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						if (isSuccessor.test(newValue)) {
							hideSpouseDetails(false);
							if (!comboDependentType.getItems().contains(
									DependentType.SPOUSE.getType()))
								comboDependentType.getItems().add(
										DependentType.SPOUSE.getType());
						} else {
							hideSpouseDetails(true);
							if (comboDependentType.getItems().contains(
									DependentType.SPOUSE.getType()))
								comboDependentType.getItems().remove(
										DependentType.SPOUSE.getType());
						}

					}
				});

		comboDependentType.valueProperty().addListener(
				new ChangeListener<String>() {
					Predicate<String> isSuccessor = (newValue) -> newValue
							.equals(DependentType.SPOUSE.getType());

					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						if (isSuccessor.test(newValue)) {
							hideSpouseDetails(false);
						} else {
							hideSpouseDetails(true);
						}
					}
				});
	}

	/**
	 * initializing tables
	 */
	private void initTbales() {
		colName.setCellValueFactory(new PropertyValueFactory<DependentDTO, String>("Name"));
		colAddress.setCellValueFactory(new PropertyValueFactory<DependentDTO, String>("Address"));
		colAccountNumber.setCellValueFactory(new PropertyValueFactory<DependentDTO, String>("AccountNumber"));
		colMariatlStatus.setCellValueFactory(new PropertyValueFactory<DependentDTO, String>("Merital"));
		colNicNumber.setCellValueFactory(new PropertyValueFactory<DependentDTO, String>("Nic"));
		colRelation.setCellValueFactory(new PropertyValueFactory<DependentDTO, String>("Relation"));

		tableDependent.onMouseClickedProperty().set(e -> {
			depndentCurrentLoaded = tableDependent.getSelectionModel().getSelectedItem();

			comboDependentType.getSelectionModel().select(depndentCurrentLoaded.getRelation());
			//comboDepSalutation.getSelectionModel().select(depndentCurrentLoaded.get);
			txtDepAccountNumber.setText(depndentCurrentLoaded.getAccountNumber());
			txtDepAddress.setText(depndentCurrentLoaded.getAddress());
			txtDepName.setText(depndentCurrentLoaded.getName());
			txtDepNic.setText(depndentCurrentLoaded.getNic());
			radioDependentMaritalStstus.selectToggle(depndentCurrentLoaded.getMaritalStatus() ? radioDependentMaritalStstus.getToggles().get(0) : radioDependentMaritalStstus.getToggles().get(1));			

			if(depndentCurrentLoaded instanceof SpouseDTO) {
				txtSpouseContact.setText(((SpouseDTO) depndentCurrentLoaded).getContactNumber());
				dateSposeMerried.setValue(DateUtil.stringToLocalDate(((SpouseDTO) depndentCurrentLoaded).getMerrageDate()));
			}
		});
	}

	/**
	 * show hide spouse details
	 * @param hide
	 */
	private void hideSpouseDetails(boolean hide) {
		if (hide) {
			if (!gridSpouse.getStyleClass().contains(Css.HIDE.getValue())) {
				hlSpouse.getStyleClass().add(Css.HIDE.getValue());
				gridSpouse.getStyleClass().add(Css.HIDE.getValue());
			}
		} else {
			if (gridSpouse.getStyleClass().contains(Css.HIDE.getValue())) {
				hlSpouse.getStyleClass().remove(Css.HIDE.getValue());
				gridSpouse.getStyleClass().remove(Css.HIDE.getValue());
			}
		}
	}

	/**************** Controlling the form navigation *************************/
	@FXML
	public void formWizardDependentBtnClicked(MouseEvent e) {
		dependentInfo.setVisible(true);
		basicInfo.setVisible(false);
		PensionInfo.setVisible(false);
		setActive(e);
	}

	@FXML
	public void formWizardBasicBtnClicked(MouseEvent e) {
		dependentInfo.setVisible(false);
		basicInfo.setVisible(true);
		PensionInfo.setVisible(false);
		setActive(e);
	}

	@FXML
	public void formWizardPensionBtnClicked(MouseEvent e) {
		dependentInfo.setVisible(false);
		basicInfo.setVisible(false);
		PensionInfo.setVisible(true);
		checkConfirmation();
		setActive(e);
	}

	@FXML
	public void noPayCalculationButtonClicked(ActionEvent e) {
		PensionerOverviewDTO d = new PensionerOverviewDTO();
		PopupUtil<PensionerOverviewDTO> pu = new PopupUtil<>(d,
				PensionerOverviewDTO.class,
				DialogType.SERVICE_PERIOD_CALCULATER);

		try {
			pu.show((Stage) ((Node) e.getSource()).getScene().getWindow());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void setActive(MouseEvent e) {
		if (selectedGrid != null) {
			selectedGrid.getStyleClass().remove("formNavActive");
		}
		((GridPane) e.getSource()).getStyleClass().add("formNavActive");
		selectedGrid = (GridPane) e.getSource();
	}



	public void setPensioner(PensionerDTO pensioner) {
		dto = pensioner;
		Hetoes activePension = pensioner.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);

		pensioner = new PensionerDAO().getPensioner(pensioner.getId());
		pensioner.getPensions().add(activePension);

		comboPensionerSalutation.getSelectionModel().select(pensioner.getSalutation());
		txtPensionerName.setText(pensioner.getName());
		txtAddress.setText(pensioner.getAddress());
		txtDesignation.setText(pensioner.getDesignation());
		txtNic.setText(pensioner.getNic());
		txtWnopNumber.setText(pensioner.getwOPNumber());
		dateRetired.setValue(DateUtil.stringToLocalDate(pensioner.getDateOfRetired()));
		comboServiceDomain.setValue(pensioner.getServiceDomain());
		radioPensionable.selectToggle(pensioner.getPensionable() ? radioPensionable.getToggles().get(0) : radioPensionable.getToggles().get(1));
		radioPermanant.selectToggle(pensioner.getPensionable() ? radioPermanant.getToggles().get(0) : radioPermanant.getToggles().get(1));
		comboServiceDomain.getSelectionModel().select(pensioner.getServiceDomain());
		
		
		

		populateDependentTable(pensioner);
		populatePensionDeialis(pensioner);
	}

	/**
	 * Populate depend table for the pensioner
	 * @param pensioner
	 */
	private void populateDependentTable(PensionerDTO pensioner) {
		DependentDAO dao = new DependentDAO(pensioner.getId());
		List<DependentDTO> list = dao.getDependents().getList();

		if(list.isEmpty()) {
			tableDependent.setPlaceholder(new Label("No dependent details found"));
		}else {
			tableDependent.getItems().setAll(list);
		}
	}
	/**
	 * populate pension Information
	 * @param pensioner
	 */
	private void populatePensionDeialis(PensionerDTO pensioner) {

		Hetoes activePension = pensioner.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);

		if(activePension != null) { //there is a pension which is activated
			PensionDTO pension = new PensionsDAO(pensioner.getId()).getPension(Long.valueOf(activePension.getUrl()));

			id.setText(String.valueOf(pension.getId()));
			accountNumber.setText(pension.getAccountNumber());
			circular.setText(pension.getCircular());
			consolidatedSalary.setText(pension.getConsolidatedSalary().toString());
			netReducedPercentage.setText(pension.getNetReducedPercentage().toString());
			netUnreducedPercentage.setText(pension.getNetUnreducedPercentage().toString());
			scale.setText(pension.getScale());
			days.setText(String.valueOf(pension.getTotalNoPay().getDays()));
			months.setText(String.valueOf(pension.getTotalNoPay().getMonths()));
			years.setText(String.valueOf(pension.getTotalNoPay().getYears()));

			BranchDTO branchDto  = new BankDAO().getBank(Integer.valueOf(pension.getBranch().getUrl()));
			bank.getSelectionModel().select(branchDto.getBank().getName());
			branch.getSelectionModel().select(branchDto.getName());
			
			RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(pensioner.getId());
			servicemonthxt.setText(String.valueOf(regularPensioner.getServicePeriod().getMonths()));
			serviceyearxt.setText(String.valueOf(regularPensioner.getServicePeriod().getYears()));
			servicedaysxt.setText(String.valueOf(regularPensioner.getServicePeriod().getDays()));
			txtincreamentdate.setText(regularPensioner.getNextIncrementDate());
			
			ExtraPaymentList paymentList = new ExtraPaymentDAO(pensioner.getId(),pension.getId()).getPayments();
			Double reducedPension = 0.0;
			Double unReducedPension = 0.0;
			Double reducedPension2020 = 0.0;
			Double UnReducedPension2020 = 0.0;
			Double firstAllowance = 0.0;
			Double secondAllowance = 0.0;
			
			double allowanceTotal = 0.0;
			for(ExtraPaymentDTO exPayment : paymentList.getExtraPayments()) {
			  if(exPayment.isPensionable()){	
				if(exPayment.getType().equals("allowance")) {
					allowanceTotal += exPayment.getAmount();
					
				}
			  } 	
			}
		   txtallowances.setText(String.format("%.2f",allowanceTotal));
		  
		  if(pensioner.getPensionerType().equals("civil")){
			Salary salary = new SalaryDAO().getSalary(pension.getScale(), regularPensioner.getGrade(), pension.getCircular(), String.valueOf(pension.getConsolidatedSalary() / 12.0), regularPensioner.getDateOfRetired(), regularPensioner.getNextIncrementDate());  
			if(salary != null){
			if(salary.getFirstAllowance() > 0){
					firstAllowance = salary.getFirstAllowance();
					secondAllowance = salary.getSecondAllowance();
				
			  if(paymentList.getExtraPayments().isEmpty()) {
				 reducedPension = (((pension.getConsolidatedSalary() + firstAllowance) * pension.getNetReducedPercentage())/100) / 12.0;
				 unReducedPension = (((pension.getConsolidatedSalary() + firstAllowance) * pension.getNetUnreducedPercentage())/100) / 12.0;
				 reducedPension2020 = ((((salary.getBasicSalary()*12.0) + secondAllowance) * pension.getNetReducedPercentage()) / 100.0) / 12.0;
				 UnReducedPension2020 = ((((salary.getBasicSalary()*12.0) + secondAllowance) * pension.getNetUnreducedPercentage()) / 100.0) / 12.0;
				 
				 
			  }else {
				reducedPension = (((pension.getConsolidatedSalary() + firstAllowance + allowanceTotal) * pension.getNetReducedPercentage()) / 100.0) /12.0;
				unReducedPension = (((pension.getConsolidatedSalary()+ firstAllowance + allowanceTotal) * pension.getNetUnreducedPercentage()) / 100.0) /12.0;
				reducedPension2020 = ((((salary.getBasicSalary()*12.0) + secondAllowance + allowanceTotal) * pension.getNetReducedPercentage()) / 100.0) / 12.0;
				UnReducedPension2020 = ((((salary.getBasicSalary()*12.0) + secondAllowance + allowanceTotal) * pension.getNetUnreducedPercentage()) / 100.0) / 12.0;
				
		      }
			}
			
			}
			else if(salary == null){
				System.out.print("null");
				if(paymentList.getExtraPayments().isEmpty()) {
					 reducedPension = (((pension.getConsolidatedSalary()) * pension.getNetReducedPercentage())/100) / 12.0;
					 unReducedPension = (((pension.getConsolidatedSalary()) * pension.getNetUnreducedPercentage())/100) / 12.0;
					 reducedPension2020 = 0.0;
					 UnReducedPension2020 = 0.0;
					 
					 
				  }else {
					reducedPension = (((pension.getConsolidatedSalary() + allowanceTotal) * pension.getNetReducedPercentage()) / 100.0) /12.0;
					unReducedPension = (((pension.getConsolidatedSalary() + allowanceTotal) * pension.getNetUnreducedPercentage()) / 100.0) /12.0;
					reducedPension2020 = 0.0;
					UnReducedPension2020 = 0.0;
					
			     }
			}
		   }
		  else if((pensioner.getPensionerType().equals("milcom")) || (pensioner.getPensionerType().equals("milnoncom"))){
			if(paymentList.getExtraPayments().isEmpty()) {
					reducedPension = (((pension.getConsolidatedSalary()) * pension.getNetReducedPercentage())/100) / 12.0;
					unReducedPension = (((pension.getConsolidatedSalary()) * pension.getNetUnreducedPercentage())/100) / 12.0;
					reducedPension2020 = 0.0;
					UnReducedPension2020 = 0.0;
			}else {
					reducedPension = (((pension.getConsolidatedSalary() + allowanceTotal) * pension.getNetReducedPercentage()) / 100.0) /12.0;
					unReducedPension = (((pension.getConsolidatedSalary()+ allowanceTotal) * pension.getNetUnreducedPercentage()) / 100.0) /12.0;
					reducedPension2020 = 0.0;
					UnReducedPension2020 = 0.0;
			}
		  }
			
			lblReducedPension.setText(String.format("%.2f", reducedPension));
			lblUnreducedPension.setText(String.format("%.2f", unReducedPension));
			lblReducedPension2020.setText(String.format("%.2f",reducedPension2020));
			lblUnReducedPension2020.setText(String.format("%.2f",UnReducedPension2020));
			txtincreament.setText(String.format("%.2f",firstAllowance));
		}
	}

	@FXML
	public void testaaaa() throws UnknownHostException {
		String desktopPath = null;
		try {
			desktopPath =System.getProperty("user.home") + "\\"+"Desktop\\payments";

			desktopPath.replace("\\", "//");
			System.out.println(desktopPath);
			//s = "\"" + desktopPath.replace("\\","\\\\") + "\\\\" +"payments" + "\"";
			//System.out.print(s);
			File f = new File(desktopPath);
			boolean mkdir = f.mkdir();
			System.out.println(mkdir);
		}catch(Exception e) {
			e.printStackTrace();
		}
		String excelFileName = desktopPath +"/payment.xlsx";//name of excel file


		//System.out.println(path_to_desktop);
		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;

		//iterating r number of rows
		int rowCount = 0;
		for (DependentDTO dto : tableDependent.getItems())
		{

			@SuppressWarnings("unchecked")
			XSSFRow row = sheet.createRow(rowCount++);

			//iterating c number of columns
			int colCount = 0;
			for (TableColumn<DependentDTO, ?> tableColumn : tableDependent.getColumns())
			{
				XSSFCell cell = row.createCell(colCount++);

				cell.setCellValue(dto.getName());
			}
		}

		try {
			File file = new File(excelFileName);
			file.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(excelFileName);

			//write this workbook to an Outputstream.
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*****************************************************************************/

	@FXML
	public void acceptButtonActionhandler(ActionEvent e){

		checkAcceptation();


	}
	/***********************Rejection
	 * @throws IOException ********************************/

	@FXML
	void addReject(ActionEvent event) throws IOException {
		if((role.equals(EmployeeRole.DATA_ENTRY.getRole())) ) {
			try {
				FXMLLoader loader  = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/RejectionView.fxml"));
				AnchorPane detailView = loader.load();
				RejectionViewController controller = (RejectionViewController)loader.getController();
				controller.init(dto);
				RootLayoutController.getInstance().rootContainer.getChildren().clear();
				RootLayoutController.getInstance().rootContainer.getChildren().add(detailView); 
				RootLayoutController.getInstance().setLoaded("PD3");

			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}else{
			UpdateStatus pd = new UpdateStatus();
			pd.updatePensionState(dto,false);
		}
	}
	/**
	 * view gratuity details
	 * @param event
	 */
	@FXML
	public void viewGratuitiesActionhandler(ActionEvent event){
		System.out.println("checked");
		PopupUtil<PensionerDTO> pu = new PopupUtil<>(dto,
				PensionerDTO.class,
				DialogType.VIEW_GRATUITIES);


		try {
			pu.show(UserSession.INSTANCE.getStage());
		} catch (Exception ex) {
			System.out.println("From: " + ex.getMessage());
		}
	}
	/**
	 * view contact details
	 * @param event
	 */
	@FXML
	public void viewContactDetailsActionhandler(ActionEvent event){
		System.out.println("checked");
		PopupUtil<PensionerDTO> pu = new PopupUtil<>(dto,
				PensionerDTO.class,
				DialogType.CONTACT_DETAILS);


		try {
			pu.show(UserSession.INSTANCE.getStage());
		} catch (Exception ex) {
			System.out.println("From: " + ex.getMessage());
		}
	}
	/**
	 * view service details
	 * @param event
	 */
	@FXML
	public void viewServiceDetailsActionhandler(ActionEvent event){
		System.out.println("checked");
		PopupUtil<PensionerDTO> pu = new PopupUtil<>(dto,
				PensionerDTO.class,
				DialogType.SERVICE_DETAILS);


		try {
			pu.show(UserSession.INSTANCE.getStage());
		} catch (Exception ex) {
			System.out.println("From: " + ex.getMessage());
		}
	}

	/******************************************************************/

	private void checkConfirmation(){

		Hetoes activePension = dto.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);
		PensionDTO pension = new PensionsDAO(dto.getId()).getPension(Long.valueOf(activePension.getUrl()));
		UserPrivilege userPrivilege = new EmployeeDAO().getPrivilages(); 

		int pensionState = Integer.parseInt(pension.getState().getUrl());

		if(pensionState == userPrivilege.getPendingListState() || 
				pensionState == (userPrivilege.getCorrectintState() - 100) || ((pensionState == userPrivilege.getRejectedListState()) && (role.equals(EmployeeRole.DATA_ENTRY.getRole()))) ) {
			accept.setDisable(false);
			reject.setDisable(false);
		}else {
			accept.setDisable(true);
			reject.setDisable(true);
		}

	}

	/******************************************************************/

	private void checkAcceptation(){
		System.out.println("checked");
		/*
		 * print letter
		 */
		if(role.equals(EmployeeRole.PRINT_LETTER.getRole())) {
			PopupUtil<PensionerDTO> pu = new PopupUtil<>(dto,
					PensionerDTO.class,
					DialogType.APLICATON_CONFIRM);


			try {
				pu.show(UserSession.INSTANCE.getStage());
			} catch (Exception ex) {
				System.out.println("From: " + ex.getMessage());
			}
		}else if(role.equals(EmployeeRole.SATHKARA_OFFICER.getRole())) {
			/*
			 * print certificate
			 */

			try {

				UpdateStatus pd = new UpdateStatus();
				PrintModule printModule = new PrintModule();
				Stage Owner = UserSession.INSTANCE.getStage();
				printModule.startPrintCertificate(Owner,dto);
				pd.updatePensionState(dto,true);

			}catch(NumberFormatException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Please enter a correct details");
				alert.setContentText(ex.getMessage());

				alert.showAndWait();
			}
		}else if(role.equals(EmployeeRole.REGISTRATION_AD.getRole())){
			UpdateStatus pd = new UpdateStatus();
			pd.updatePensionState(dto,true);

		}else if(role.equals(EmployeeRole.DATA_ENTRY.getRole())){
			UpdateStatus pd = new UpdateStatus();
			pd.updatePensionState(dto,true);
		}
	}

	/******************************************************************/

	@FXML
	public void closeButtonHandler(ActionEvent e){
		((Node)e.getSource()).getScene().getWindow().hide();
	}


}
