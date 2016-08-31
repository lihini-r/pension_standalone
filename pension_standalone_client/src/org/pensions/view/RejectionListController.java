package org.pensions.view;

import java.io.IOException;

import org.pensions.data.dao.PensionerDAO;
import org.pensions.enumarations.WindowType;
import org.pensions.model.PensionerDTO;
import org.pensions.model.PensionerOverviewDTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class RejectionListController {
	
	@FXML private TextField txt_filterField;
	@FXML private TableView<PensionerOverviewDTO> tbl_rejectionTable;
	@FXML private TableColumn<PensionerOverviewDTO, Integer> tblCl_id;
	@FXML private TableColumn<PensionerOverviewDTO, String> tblCl_name;
	@FXML private TableColumn<PensionerOverviewDTO, String> tblCl_nic;
	@FXML private TableColumn<PensionerOverviewDTO, Boolean> tblCl_reason;
	
	private ObservableList<PensionerOverviewDTO> masterData = FXCollections.observableArrayList();
	
	public RejectionListController() {
		
	}
	
	@FXML
	private void initialize() {
		
		PensionerDAO dao = new PensionerDAO();

		for(PensionerOverviewDTO d : dao.getPensioners("rejected").getPensionersList()) {
			masterData.add(d);
		}

		tblCl_id.setCellValueFactory(new PropertyValueFactory<PensionerOverviewDTO,Integer>("id"));
		tblCl_name.setCellValueFactory(new PropertyValueFactory<PensionerOverviewDTO,String>("name"));
		tblCl_nic.setCellValueFactory(new PropertyValueFactory<PensionerOverviewDTO,String>("nic"));
		tblCl_reason.setCellFactory(new Callback<TableColumn<PensionerOverviewDTO,Boolean>, TableCell<PensionerOverviewDTO,Boolean>>() {
			
			@Override
			public TableCell<PensionerOverviewDTO, Boolean> call(
					TableColumn<PensionerOverviewDTO, Boolean> param) {
				return new ButtonCell();
			}
		});
		tblCl_reason.setSortable(false);
		
		FilteredList<PensionerOverviewDTO> filteredData = new FilteredList<>(masterData, p-> true);
		
		txt_filterField.textProperty().addListener((Observable, oldValue, newValue) -> {
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
		
		sortedData.comparatorProperty().bind(tbl_rejectionTable.comparatorProperty());
		
		tbl_rejectionTable.setItems(sortedData);
		
	}
	
	@FXML
	public void openRejectionView() throws IOException
	{
		PensionerOverviewDTO podto = tbl_rejectionTable.getSelectionModel().getSelectedItem();
		
		PensionerDAO dao = new PensionerDAO();
		PensionerDTO dto = dao.getPensioner(podto.getId());
		RootLayoutController.getInstance().loadUpdateWindow(WindowType.PD5, dto);
		System.out.println(dto.getAddress());
	}

}

/*
 * @Define the reason button
 */
class ButtonCell extends TableCell<PensionerOverviewDTO, Boolean> {
    final Button cellButton = new Button("Action");
   // static TableView<PensionerOverviewDTO> table = null;
    
	ButtonCell(){
		//if(table == null) this.t
        cellButton.setOnAction(e -> {
        	Alert a = new Alert(AlertType.INFORMATION);
        	PensionerOverviewDTO d = this.getTableView().getItems().get(this.getTableRow().getIndex());
        	a.setContentText(d.getName());
        	
        	a.showAndWait();
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }
    }
}
