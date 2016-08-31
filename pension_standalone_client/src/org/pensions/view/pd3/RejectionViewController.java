/**
 * Sample Skeleton for 'RejectionView.fxml' Controller Class
 */

package org.pensions.view.pd3;

import java.util.ArrayList;
import java.util.List;

import org.pensions.data.dao.RejectionDAO;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionerDTO;
import org.pensions.model.RejectionDTO;
import org.pensions.model.RejectionReasonDTO;
import org.pensions.model.lists.RejectionReasonList;
import org.pensions.pensionstatemanager.UpdateStatus;
import org.pensions.view.util.NotificationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RejectionViewController {
	
	@FXML // fx:id="refNoTxt"
	private TextField refNoTxt; // Value injected by FXMLLoader

	@FXML // fx:id="nameTxt"
	private TextField nameTxt; // Value injected by FXMLLoader

	@FXML // fx:id="reasonsTable"
	private TableView<RejectionReasonDTO> reasonsTable; // Value injected by FXMLLoader

	@FXML // fx:id="addBtn"
	private Button addBtn; // Value injected by FXMLLoader

	@FXML // fx:id="clearBtn"
	private Button clearBtn; // Value injected by FXMLLoader

	@FXML // fx:id="addedReasonsList"
	private ListView<String> addedReasonsList; // Value injected by FXMLLoader

	@FXML // fx:id="customReasonTxt"
	private TextArea customReasonTxt; // Value injected by FXMLLoader

	@FXML // fx:id="confirmBtn"
	private Button confirmBtn; // Value injected by FXMLLoader

	@FXML // fx:id="removeBtn"
	private Button removeBtn; // Value injected by FXMLLoader


	private PensionerDTO dto;
	private List<Long>currentReasonIds = new  ArrayList<>();


	@FXML private TableColumn<RejectionReasonDTO, Integer> indexColumn;
	@FXML private TableColumn<RejectionReasonDTO, String> reasonColumn;


	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert refNoTxt != null : "fx:id=\"refNoTxt\" was not injected: check your FXML file 'RejectionView.fxml'.";
		assert nameTxt != null : "fx:id=\"nameTxt\" was not injected: check your FXML file 'RejectionView.fxml'.";
		assert reasonsTable != null : "fx:id=\"reasonsTable\" was not injected: check your FXML file 'RejectionView.fxml'.";
		assert indexColumn != null : "fx:id=\"indexColumn\" was not injected: check your FXML file 'RejectionView.fxml'.";
		assert reasonColumn != null : "fx:id=\"reasonColumn\" was not injected: check your FXML file 'RejectionView.fxml'.";
		assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'RejectionView.fxml'.";
		assert addedReasonsList != null : "fx:id=\"addedReasonsList\" was not injected: check your FXML file 'RejectionView.fxml'.";
		assert customReasonTxt != null : "fx:id=\"customReasonTxt\" was not injected: check your FXML file 'RejectionView.fxml'.";
		assert confirmBtn != null : "fx:id=\"confirmBtn\" was not injected: check your FXML file 'RejectionView.fxml'.";

		indexColumn.setCellValueFactory(new PropertyValueFactory<RejectionReasonDTO, Integer>("Id"));
		reasonColumn.setCellValueFactory(new PropertyValueFactory<RejectionReasonDTO, String>("Message"));

		loadReasons();
	}

	private void loadReasons() {
		RejectionReasonList reasonList = new RejectionDAO().getReasons("PD3");
		reasonsTable.getItems().addAll(reasonList.getReasons());
	}

	
	/********** add to reason list***********/
	@FXML
	public void addReason(ActionEvent event){
		RejectionReasonDTO selectedReason = reasonsTable.getSelectionModel().getSelectedItem();
		addedReasonsList.getItems().add(selectedReason.getMessage());
		currentReasonIds.add(selectedReason.getId());
	}
	
	
	/************ remove the select value listview****************/
	@FXML
	public void removeReason(ActionEvent event){
		addedReasonsList.getItems().remove(addedReasonsList.getSelectionModel().getSelectedIndex());
		
		String selectedReason = addedReasonsList.getSelectionModel().getSelectedItem();
		for(RejectionReasonDTO reason : reasonsTable.getItems()){
			if(reason.getMessage() == selectedReason){
				currentReasonIds.remove(reason.getId());
			}
		}
		//ToDo
	}

	/************Save & Confirm Rejection*****************/
	public void confirm(ActionEvent event){
		RejectionDTO rejection = new RejectionDTO();
		
		Hetoes pension = dto.getPensions().stream()
				.filter(e -> e.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);
		
		rejection.setPension(pension);
		
		for(long selectedid : currentReasonIds) {
			RejectionReasonDTO reason = new RejectionReasonDTO();
			reason.setId(selectedid);
			reason.setType("regular");
			
			rejection.getReasons().add(reason);
		}
		
		if(!customReasonTxt.getText().isEmpty()) {
			RejectionReasonDTO customReason = new RejectionReasonDTO();
			customReason.setType("custom");
			customReason.setMessage(customReasonTxt.getText());
			
			rejection.getReasons().add(customReason);
		}
		
		RejectionDAO dao = new RejectionDAO();
		dao.addRejection(rejection);
		
		UpdateStatus pd = new UpdateStatus();
		pd.updatePensionState(dto,false);
		 
		currentReasonIds.clear();
		
		clearform();
	}

	/***** lord the value of text filed  reference number & name*******/
	
	public void init(PensionerDTO dto) {
		this.dto = dto;
		refNoTxt.setText(String.valueOf(dto.getId()));
		nameTxt.setText(dto.getName());
	}
	@FXML
	public void clearform(){
		addedReasonsList.getItems().clear();
		customReasonTxt.clear();
		
		//NotificationManager.info("Successfully Rejected", "Successfully Rejected");
	}
}
