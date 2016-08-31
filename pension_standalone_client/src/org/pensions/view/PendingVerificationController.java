package org.pensions.view;

import java.io.IOException;

import org.pensions.data.dao.PensionerDAO;
import org.pensions.enumarations.WindowType;
import org.pensions.model.PensionerDTO;
import org.pensions.model.PensionerOverviewDTO;
import org.pensions.testing.TestAlert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PendingVerificationController {

	@FXML private TextField filterField;
	@FXML private TableView<PensionerOverviewDTO> verifyTable;
	@FXML private TableColumn<PensionerOverviewDTO, Integer> id;
	@FXML private TableColumn<PensionerOverviewDTO, String> name;
	@FXML private TableColumn<PensionerOverviewDTO, String> nic;

	private ObservableList<PensionerOverviewDTO> masterData = FXCollections.observableArrayList();

	public PendingVerificationController() {

	}

	@FXML
	private void initialize() {

		PensionerDAO dao = new PensionerDAO();

		for(PensionerOverviewDTO d : dao.getPensioners("pending").getPensionersList()) {
			masterData.add(d);
		}

		id.setCellValueFactory(new PropertyValueFactory<PensionerOverviewDTO,Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<PensionerOverviewDTO,String>("name"));
		nic.setCellValueFactory(new PropertyValueFactory<PensionerOverviewDTO,String>("nic"));
		
		FilteredList<PensionerOverviewDTO> filteredData = new FilteredList<>(masterData, p-> true);
		
		filterField.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(PensionerOverviewDTO -> {
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if(PensionerOverviewDTO.getNic().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else {
					return false;
				}
			});
		});
		
		SortedList<PensionerOverviewDTO> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(verifyTable.comparatorProperty());
		
		verifyTable.setItems(sortedData);
	}
	
	@FXML
	public void openVerificationView() throws IOException {
		try {
		PensionerOverviewDTO podto = verifyTable.getSelectionModel().getSelectedItem();
		
		PensionerDAO dao = new PensionerDAO();
		PensionerDTO dto = dao.getPensioner(podto.getId());
		RootLayoutController.getInstance().loadUpdateWindow(WindowType.PD5, dto);
		System.out.println(dto.getAddress());
		}catch(Exception e) {
			TestAlert.showAlert(e);
		}
	//	new PD5Controller().setDeceasedPensioner(dto);
	}
	
	


}
