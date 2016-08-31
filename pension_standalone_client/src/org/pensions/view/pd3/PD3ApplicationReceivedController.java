package org.pensions.view.pd3;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.text.AbstractDocument.Content;

import org.omg.PortableServer.ServantRetentionPolicyValue;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.model.PensionerDTO;
import org.pensions.model.PensionerOverviewDTO;
import org.pensions.model.lists.PensionerOverviewList;
import org.pensions.model.lists.PensionersList;
import org.pensions.view.RootLayoutController;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PD3ApplicationReceivedController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
 
	@FXML
	private TableView<PensionerDTO> rec_application_tbl;

	@FXML
	private TableColumn<PensionerDTO, Long> ref_no_col;

	@FXML
	private TableColumn<PensionerDTO, String> nic_col;

	@FXML
	private TableColumn<PensionerDTO, String> name_col;

	@FXML
	private TableColumn<PensionerDTO, String> designation_col;

	@FXML
	private TableColumn<PensionerDTO, String> ret_date_col;

	@FXML
	private TableColumn<PensionerDTO, String> office_col;

	@FXML
	private TableColumn<PensionerDTO, String> contact_no_col;

	@FXML
	private TableColumn<PensionerDTO, String> status_col;

	@FXML
	void initialize() {
		System.out.println("called");
		assert rec_application_tbl != null : "fx:id=\"rec_application_tbl\" was not injected: check your FXML file 'PD3ApplicationReceived.fxml'.";
		assert ref_no_col != null : "fx:id=\"ref_no_col\" was not injected: check your FXML file 'PD3ApplicationReceived.fxml'.";
		assert nic_col != null : "fx:id=\"nic_col\" was not injected: check your FXML file 'PD3ApplicationReceived.fxml'.";
		assert name_col != null : "fx:id=\"name_col\" was not injected: check your FXML file 'PD3ApplicationReceived.fxml'.";
		assert designation_col != null : "fx:id=\"designation_col\" was not injected: check your FXML file 'PD3ApplicationReceived.fxml'.";
		assert ret_date_col != null : "fx:id=\"ret_date_col\" was not injected: check your FXML file 'PD3ApplicationReceived.fxml'.";
		assert office_col != null : "fx:id=\"office_col\" was not injected: check your FXML file 'PD3ApplicationReceived.fxml'.";
		assert contact_no_col != null : "fx:id=\"contact_no_col\" was not injected: check your FXML file 'PD3ApplicationReceived.fxml'.";
		assert status_col != null : "fx:id=\"status_col\" was not injected: check your FXML file 'PD3ApplicationReceived.fxml'.";

		ref_no_col.setCellValueFactory(new PropertyValueFactory<PensionerDTO, Long>("id"));
		nic_col.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("nic"));
		name_col.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("name"));
		designation_col.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("designation"));
		ret_date_col.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("dateOfRetired"));
		office_col.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("lastOccupiedPlace"));
		contact_no_col.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("mobileNumber"));

		loadPensionerList();
	}

	
	
	private void loadPensionerList(){

		//ref_no_col.setCellValueFactory(arg0);
		PensionerDAO pensionerdao = new PensionerDAO();
		PensionersList pensionerslist = pensionerdao.getPensionersList("pending");
		rec_application_tbl.getItems().clear();
		///rec_application_tbl.getItems().addAll(pensionerslist.getPensioners());

	}


	@FXML
	void onMouseClicked(MouseEvent event) throws IOException {

		System.out.println(event.isPrimaryButtonDown());
		if(event.getButton() == MouseButton.SECONDARY)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Confirmation");
			alert.setContentText("Confirm Receive Application");
			
			ButtonType confirmButton = new ButtonType("Confirm");
			ButtonType detailButton = new ButtonType("View Details");
			ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(confirmButton, detailButton, cancelButton);

			Optional<ButtonType> result = alert.showAndWait();
			
			
			if (result.get() == confirmButton){
				//Confirm
				long refNo = rec_application_tbl.getSelectionModel().getSelectedItem().getId();
				confirmReceive(refNo);
				loadPensionerList();
			} else if (result.get() == detailButton) {
				//View details
				
			//	Parent detailView = FXMLLoader.load(getClass().getResource("PD3.fxml"));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PD3.fxml"));
				Parent detailView = loader.load();
				
				RootLayoutController.getInstance().rootContainer.getChildren().clear();
			    RootLayoutController.getInstance().rootContainer.getChildren().add(detailView); 
			    RootLayoutController.getInstance().setLoaded("PD3");
			    PD3Controller controller  = loader.getController();
			    controller.loaded = "Pending Receive";
			    PensionerDTO pensioner = new PensionerDAO().getPensioner(rec_application_tbl.getSelectionModel().getSelectedItem().getId());
			    controller.setPensioner(pensioner);
			} else if (result.get() == cancelButton) {
				loadPensionerList();
			}
			
		}
		
	}
	@FXML
	void onKeyPressed(KeyEvent event){
			
		System.out.println(event.getCode().toString());
		if(event.getCode().toString().equals("F5"))
		{
			long refNo = rec_application_tbl.getSelectionModel().getSelectedItem().getId();
			confirmReceive(refNo);
		}

	}
	private void confirmReceive(long refNo){
		/************ confirm request to receive application***********/
		try{

			
				/**** Confirm****/
				PensionerDAO pensionerdao = new PensionerDAO();
				pensionerdao.updatePensionerStatus(true, refNo);
				loadPensionerList();
			
		}catch(Exception e)
		{
			/**** No row selected****/
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please select a row to confirm");
			alert.showAndWait();
		}
	}



}

