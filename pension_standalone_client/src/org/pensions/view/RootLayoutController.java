package org.pensions.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.controlsfx.control.PopOver;
import org.pensions.enumarations.DialogType;
import org.pensions.enumarations.WindowType;
import org.pensions.model.PensionerDTO;
import org.pensions.model.UserCredential;
import org.pensions.session.UserSession;
import org.pensions.testing.TestAlert;
import org.pensions.view.pd5.PD5Controller;
import org.pensions.view.util.NotificationManager;
import org.pensions.view.util.PopupUtil;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class RootLayoutController implements Initializable{

	private static RootLayoutController instance = null;

	public String loaded;

	/*******************========================******************/
	@FXML private Label lblUsername;
	@FXML private Label generateReport;
	/*******************========================******************/

	/********************Headers *****************/
	@FXML private AnchorPane pd5Header;
	@FXML private AnchorPane defaultHeader;

	@FXML private StackPane headerStack;

	@FXML public AnchorPane rootContainer;

	/************Side navigation ************/

	@FXML private GridPane sideNavGrid;

	/****************************************/
	


	public void initialize(URL location, ResourceBundle resources) {
		if (instance == null) {
			instance = this;
		}
		lblUsername.setText(UserSession.INSTANCE.getUserName());
		loaded = "home";
		setHeader("defaultHeader");

		Predicate<Node> anchorFilter = new Predicate<Node>() {

			@Override
			public boolean test(Node node) {
				if(node instanceof AnchorPane) return true;
				else return false;
			}
		};
		
		/*if(UserSession.INSTANCE.getRole().equals(EmployeeRole.DATA_ENTRY.getRole())){
			generateReport.setVisible(false);
		}*/
		

	}

	/********************@FXML Methods***********************/

	private void setHeader(String header) {
		FadeTransition ft = new FadeTransition(Duration.seconds(1));
		ft.setFromValue(0.4);
		ft.setToValue(1.0);
		ft.setCycleCount(3);
		ft.setAutoReverse(true);

		Predicate<Node> predicate = new Predicate<Node>() {

			@Override
			public boolean test(Node t) {
				if(t instanceof AnchorPane) return true;
				else return false;
			}
		};
		headerStack.getChildren().stream()
		.filter(predicate)
		.forEach(a -> {
			if(a.getId().equals(header)) {
				//ft.setNode(a);
				//ft.play();
				a.setVisible(true);
			}else {
				a.setVisible(false);
			}
		});
	}

	@FXML public void test(MouseEvent e) {


	}
	
	/**
	 * display dashboard button
	 * @param e
	 * @throws IOException
	 */
	@FXML public void dashboardButtonActionHandler(MouseEvent e) throws IOException {
		setHeader("pd5Header");
		setNavBarActive((Label)e.getSource());
		rootContainer.getChildren().clear();
		loaded = "";
		setNavBarActive((Label)e.getSource());
		setHeader(null);
		if(!loaded.equals("dashboard")) {
			
			FXMLLoader loader  = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/dashboard/DashboardView.fxml"));
			AnchorPane root = loader.load();
			setAnchoredPane(root);
		
			loaded = "pd3Ar";
		}
		
	}
	
	/**
	 * pd3 selection pop over button
	 * @param e
	 * @throws IOException
	 */
	@FXML public void pd3SelectionActionHandler(MouseEvent e) throws IOException {

		setHeader("pd5Header");
		setNavBarActive((Label)e.getSource());
		rootContainer.getChildren().clear();
		loaded = "";
		setNavBarActive((Label)e.getSource());
		setHeader(null);
		if(!loaded.equals("pd3Ar")) {

			PopupUtil<UserCredential> pu = new PopupUtil<>(DialogType.PD3_SELECTION,362,220);

			try {
				pu.show(UserSession.INSTANCE.getStage());
			} catch (Exception ee) {
				NotificationManager.info("Error", "Error occured");
				ee.printStackTrace();
			}

			loaded = "surcharges";
		}
	}

	@FXML public void pd3ButtonActionHandler(MouseEvent e) throws IOException {
		setHeader("pd5Header");
		setNavBarActive((Label)e.getSource());
		rootContainer.getChildren().clear();
		loaded = "";
		setNavBarActive((Label)e.getSource());
		setHeader(null);
		if(!loaded.equals("pd3Ar")) {
			
			FXMLLoader loader  = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/Search.fxml"));
			AnchorPane root = loader.load();
			setAnchoredPane(root);
		
			loaded = "pd3Ar";
		}
	}
	/**
	 * generate excel sheet button
	 * @param e
	 * @throws IOException
	 */
	
	@FXML public void GenerateExcelButtonActionHandler(MouseEvent e) throws IOException {
		setHeader("pd5Header");
		setNavBarActive((Label)e.getSource());
		rootContainer.getChildren().clear();
		loaded = "";
		setNavBarActive((Label)e.getSource());
		setHeader(null);
		if(!loaded.equals("generateExcel")) {
			
			FXMLLoader loader  = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/GenerateFinalReportView.fxml"));
			AnchorPane root = loader.load();
			setAnchoredPane(root);
		
			loaded = "pd3Ar";
		}
		
	}
	
	/**
	 * view gratuities
	 */
	@FXML public void viewGratuitiesButtonActionHandler(MouseEvent e) throws IOException {
		setHeader("pd5Header");
		setNavBarActive((Label)e.getSource());
		rootContainer.getChildren().clear();
		loaded = "";
		setNavBarActive((Label)e.getSource());
		setHeader(null);
		if(!loaded.equals("gratuities")) {
			
			FXMLLoader loader  = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/ViewAllGratuities.fxml"));
			AnchorPane root = loader.load();
			setAnchoredPane(root);
		
			loaded = "pd3Ar";
		}
		
	}
	/**
	 * generate gratuity report
	 * @param e
	 * @throws IOException
	 */
	@FXML public void generateGratuityReportButtonActionHandler(MouseEvent e) throws IOException {
		setHeader("pd5Header");
		setNavBarActive((Label)e.getSource());
		rootContainer.getChildren().clear();
		loaded = "";
		setNavBarActive((Label)e.getSource());
		setHeader(null);
		if(!loaded.equals("gratuityReport")) {
			
			FXMLLoader loader  = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/GenerateFinalGratuityReportView.fxml"));
			AnchorPane root = loader.load();
			setAnchoredPane(root);
		
			loaded = "pd3Ar";
		}
		
	}

	/**
	 * @PD5
	 * @throws IOException
	 */

	/*******************PD5 Buttons ***********************
	 * 
	 * 
	 ********************************************************/
	@FXML public void pd5ButtonActionHandler(MouseEvent e) throws IOException {
		setHeader("pd5Header");
		setNavBarActive((Label)e.getSource());
		rootContainer.getChildren().clear();
		loaded = "";
	}

	

	public void setLoaded(String loaded) {
		this.loaded = loaded;
	}

	@FXML public void pd5InsertActionHandler(MouseEvent e) {
		try {
			if(!loaded.equals("pd5")) {

				AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("org/pensions/view/pd5/PD5.fxml"));
				setAnchoredPane(root);
				loaded = "pd5";
				setSubNavHighlight((Label) e.getSource());
			}
		}catch(Exception ex) {
			TestAlert.showAlert(ex);
		}
	}

	@FXML public void pd5verificationButtonActionHandler(MouseEvent e) throws IOException {
		if(!loaded.equals("pd5verification")) {
			AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("org/pensions/view/PendingVerficiation.fxml"));
			setAnchoredPane(root);
			loaded = "pd5varification";
			setSubNavHighlight((Label) e.getSource());
		}
	}

	@FXML public void pd5RejectionButtonActionHandler(MouseEvent e) throws IOException {
		if(!loaded.equals("pd5Rejection")) {
			AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("org/pensions/view/RejectionList.fxml"));
			setAnchoredPane(root);
			loaded = "pd5Rejection";
			setSubNavHighlight((Label) e.getSource());
		}
	}
	
	@FXML public void pd5PaymentsButtonActionHandler(MouseEvent e) throws IOException {
		if(!loaded.equals("pd5Payments")) {
			AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("org/pensions/view/pd5/PD5Payment.fxml"));
			setAnchoredPane(root);
			loaded = "pd5Payment";
			setSubNavHighlight((Label) e.getSource());
		}
	}

	/********************************************************/
	public void setAnchoredPane(AnchorPane root) {
		rootContainer.getChildren().clear();

		AnchorPane.setTopAnchor(root, 0.0);
		AnchorPane.setBottomAnchor(root, 0.0);
		AnchorPane.setLeftAnchor(root, 0.0);
		AnchorPane.setRightAnchor(root, 0.0);

		rootContainer.getChildren().add(root);
	}

	public static RootLayoutController getInstance() {
		return instance;
	}

	public void loadUpdateWindow(WindowType type, PensionerDTO pensioner) {
		FXMLLoader fxmlLoader;

		switch (type) {
		case PD5:
			AnchorPane root = null;
			try {
				fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(WindowType.PD5.getUrl()));
				root = fxmlLoader.load();
				PD5Controller controller = fxmlLoader.<PD5Controller>getController();
		//		controller.setDeceasedPensioner((DeceasedPensionerDTO) pensioner);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setAnchoredPane(root);
			loaded = "pd5";
		default:
			break;
		}
	}

	@FXML public void logOutbtnHandler(MouseEvent e) throws IOException {
		/*UserSession session = UserSession.INSTANCE;
		session.setNote(null);
		session.setSession(null, null, null);

		((Stage)(((Node)e.getSource()).getScene()).getWindow()).hide();

		Stage loginStage = new Stage();
		loginStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/org/pensions/view/LoginView.fxml"))));

		loginStage.initStyle(StageStyle.UNDECORATED);
		loginStage.setAlwaysOnTop(true);
		loginStage.centerOnScreen();
		loginStage.show();*/

		Platform.exit();
	}


	/**************************************************************
	 * 					SticketNote
	 * @throws IOException 
	 **************************************************************/

	@FXML public void stickeyNoteIconClicked(MouseEvent e) throws IOException {
		PopOver pop = new PopOver();
		pop.setAutoHide(false);
		pop.setCornerRadius(0);
		pop.setDetached(true);
		pop.setHeaderAlwaysVisible(true);
		pop.setTitle("STICKEY NOTE");
		pop.setOpacity(1);
		Parent root = FXMLLoader.load(getClass().getResource("/org/pensions/view/StickeyNote.fxml"));
		pop.setContentNode(root);
		pop.setAutoHide(true);
		pop.show(((Label)e.getSource()));
	}


	/***************************************************************
	 * 
	 * Visualization effects
	 **************************************************************/
	//Main nav bar highlighting effect
	private void setNavBarActive(Label selectedNav) {
		Predicate<Node> isLabel = new Predicate<Node>() {

			@Override
			public boolean test(Node t) {
				return t instanceof Label;
			}
		};
		Task<Void> setNavBarActiveTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				sideNavGrid.getChildren()
				.stream()
				.filter(isLabel).forEach(l -> {
					if(l == selectedNav) {
						if(!((Label)l).getStyleClass().contains("main_nav_selected"))
							((Label)l).getStyleClass().add("main_nav_selected");
					}else {
						if(((Label)l).getStyleClass().contains("main_nav_selected"))
							((Label)l).getStyleClass().remove("main_nav_selected");
					}
				});
				return null;
			}
		};

		Thread th = new Thread(setNavBarActiveTask);
		th.setDaemon(true);
		th.run();
	}

	private void setSubNavHighlight(Label label) {

		HBox parent = (HBox)label.getParent();

		parent.getChildren()
		.stream()
		.forEach(l -> {
			if(l == label) {
				if(!l.getStyleClass().contains("sub_nav_selected")) l.getStyleClass().add("sub_nav_selected");
			}else {
				if(l.getStyleClass().contains("sub_nav_selected")) l.getStyleClass().remove("sub_nav_selected");
			}
		});
	}
}

