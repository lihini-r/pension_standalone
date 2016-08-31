/**
 * 
 */
package org.pensions.view.pd5;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import org.pensions.data.dao.PensionerDAO;
import org.pensions.model.PensionerOverviewDTO;

/**
 * @author Kani
 *
 */
public class PD5PaymentController {
	
	@FXML private TextField txt_filterField;
	@FXML private CheckBox check_selectAll;
	@FXML private TableView<PensionerOverviewDTO> tbl_paymentTable;
	@FXML private TableColumn<PensionerOverviewDTO, Boolean> tblCl_checkBox;
	@FXML private TableColumn<PensionerOverviewDTO, Integer> tblCl_pensionNumber;
	@FXML private TableColumn<PensionerOverviewDTO, String> tblCl_name;
	@FXML private TableColumn<PensionerOverviewDTO, String> tblCl_nic;
	@FXML private TableColumn<PensionerOverviewDTO, Double> tblCl_gratuity;
	
	private ObservableList<PensionerOverviewDTO> masterData = FXCollections.observableArrayList();
	
	public PD5PaymentController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void initialize() {
		
		PensionerDAO dao = new PensionerDAO();
		
		for(PensionerOverviewDTO d : dao.getPensioners("pending").getPensionersList()) {
			masterData.add(d);
			System.out.println(tblCl_checkBox.getCellObservableValue(0));
		}
		
		tblCl_checkBox.setCellFactory(new Callback<TableColumn<PensionerOverviewDTO,Boolean>, TableCell<PensionerOverviewDTO,Boolean>>() {
			
			@Override
			public TableCell<PensionerOverviewDTO, Boolean> call(
					TableColumn<PensionerOverviewDTO, Boolean> param) {
				return new CheckBoxCell();
			}
		});
		
		
		
		tblCl_pensionNumber.setCellValueFactory(new PropertyValueFactory<PensionerOverviewDTO,Integer>("id"));
		tblCl_name.setCellValueFactory(new PropertyValueFactory<PensionerOverviewDTO,String>("name"));
		tblCl_nic.setCellValueFactory(new PropertyValueFactory<PensionerOverviewDTO,String>("nic"));
		
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
		
		sortedData.comparatorProperty().bind(tbl_paymentTable.comparatorProperty());
		
		tbl_paymentTable.setItems(sortedData);
		
		check_selectAll.selectedProperty().addListener(e -> {
			for(CheckBox cb : CheckBoxCell.getList()) {
				cb.setSelected(check_selectAll.isSelected());
			}
		});
	}

}

/*
 * @Define the reason button
 */
class CheckBoxCell extends TableCell<PensionerOverviewDTO, Boolean> {
    final CheckBox cellCheckBox = new CheckBox();
    private static List<CheckBox> checkList = new ArrayList<CheckBox>();
    
    public CheckBoxCell() {
    	checkList.add(cellCheckBox);
	}
   // static TableView<PensionerOverviewDTO> table = null;
    
    /*CheckBoxCell(){
		//if(table == null) this.t
    	cellCheckBox.setOnAction(e -> {
        	Alert a = new Alert(AlertType.INFORMATION);
        	PensionerOverviewDTO d = this.getTableView().getItems().get(this.getTableRow().getIndex());
        	a.setContentText(d.getName());
        	
        	a.showAndWait();
        });
    }*/

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
        	setAlignment(Pos.CENTER);
            setGraphic(cellCheckBox);
        }
    }
    
    public static List<CheckBox> getList() {
    	return checkList;
    }
}

