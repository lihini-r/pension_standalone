package org.pensions.view.pd3;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.enumarations.DialogType;
import org.pensions.enumarations.EmployeeRole;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.model.internal.SearchParams;
import org.pensions.session.UserSession;
import org.pensions.view.util.NotificationManager;
import org.pensions.view.util.PopupUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Dinesh Liyanage on 5/24/2016.
 */
public class SearchController implements Initializable {

    @FXML private TextField txtNic;
    @FXML private TextField txtPensionNumber;
    @FXML private TextField txtRefNumber;
    @FXML private TextField txtName;

    @FXML private ComboBox<String> comboStatus;

    @FXML private TableView<PensionerDTO> table;

    @FXML private TableColumn<PensionerDTO, String> colName;
    @FXML private TableColumn<PensionerDTO, String> colRefNo;
    @FXML private TableColumn<PensionerDTO, String> colNic;
    @FXML private TableColumn<PensionerDTO, String> colRetiredDate;
    @FXML private TableColumn<PensionerDTO, String> colDesignation;
    @FXML private TableColumn<PensionerDTO, String> colWop;

    @FXML private ProgressIndicator progressIndicator;

    @FXML private MenuItem viewpension;
    @FXML private MenuItem received;

    String role = UserSession.INSTANCE.getRole();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    	comboStatus.getItems().add("pending");
    	comboStatus.getItems().add("corrected");
    	if(!role.equals(EmployeeRole.POSTAL.getRole()))comboStatus.getItems().add("rejected");

        colName.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("Name"));
        colRefNo.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("Id"));
        colNic.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("Nic"));
        colRetiredDate.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("DateOfRetired"));
        colDesignation.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("Designation"));
        colWop.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("WOPNumber"));

        table.setFixedCellSize(35);

        //table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
        //table.minHeightProperty().bind(table.prefHeightProperty());
        //table.maxHeightProperty().bind(table.prefHeightProperty());

        if(role.equals(EmployeeRole.POSTAL.getRole())){
        	viewpension.setDisable(true);
        	received.setDisable(false);

        }else{
        	viewpension.setDisable(false);
        	received.setDisable(true);
        }

        comboStatus.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Task<List<PensionerDTO>> loadResultTableTask = new Task<List<PensionerDTO>>() {
	                @Override
	                protected List<PensionerDTO> call() throws Exception {
	                    return new PensionerDAO().searchPensioners(newValue).getList();
	                }
	            };

	            progressIndicator.progressProperty().bind(loadResultTableTask.progressProperty());
	            progressIndicator.visibleProperty().bind(loadResultTableTask.runningProperty());
	            table.disableProperty().bind(loadResultTableTask.runningProperty());

	            loadResultTableTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
	                    new EventHandler<WorkerStateEvent>() {
	                        @Override
	                        public void handle(WorkerStateEvent t) {
                                if(loadResultTableTask.getValue().isEmpty()) {
                                	table.getItems().clear();
                                    NotificationManager.info("No pensions found", "No pensions found for the search criteria");
                                }else {
                                    setItemsToTable(loadResultTableTask.getValue());
                                }
	                        }
	                    });

	            new Thread(loadResultTableTask).start();
			}
		});

        comboStatus.getSelectionModel().select(0);
    }

    /******************************************************************/


    @FXML public void searchBtnActionHandler(ActionEvent e) {
        if(isValid()) {
            SearchParams params = new SearchParams();
            params.setNic(txtNic.getText());
            params.setName(txtName.getText());
            params.setPensionNumber(txtPensionNumber.getText());
            params.setRefNumber(txtRefNumber.getText());

            Task<List<PensionerDTO>> loadResultTableTask = new Task<List<PensionerDTO>>() {
                @Override
                protected List<PensionerDTO> call() throws Exception {
                    return new PensionerDAO().searchPensioners(params).getList();
                }
            };

            progressIndicator.progressProperty().bind(loadResultTableTask.progressProperty());
            progressIndicator.visibleProperty().bind(loadResultTableTask.runningProperty());
            table.disableProperty().bind(loadResultTableTask.runningProperty());

            loadResultTableTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                    new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent t) {
                            if(loadResultTableTask.getValue().isEmpty()) {
                            	table.getItems().clear();
                                NotificationManager.info("No pensions found", "No pensions found for the search criteria");
                            }else {
                                setItemsToTable(loadResultTableTask.getValue());
                            }
                        }
                    });

            new Thread(loadResultTableTask).start();
        }
    }

    @FXML public void test(ActionEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem() != null) {
        	PensionerDTO selectedItem = table.getSelectionModel().getSelectedItem();
            PopupUtil<PensionerDTO> pu = new PopupUtil<>(selectedItem,
                    PensionerDTO.class,
                    DialogType.VIEW_PENSIONS);

            try {
                pu.show(UserSession.INSTANCE.getStage());
            } catch (Exception e) {
                System.out.println("From: " + e.getMessage());
            }
        }
    }

    /******************************************************************/
    private boolean isValid() {
        int count = 0;

        if(txtName.getText().isEmpty()) count++;
        if(txtNic.getText().isEmpty()) count++;
        if(txtPensionNumber.getText().isEmpty()) count++;
        if(txtRefNumber.getText().isEmpty()) count++;

        if(count == 4) {
            NotificationManager.info("Empty search parameters!", "Please fill at least one text field to continue searching");
            return false;
        }

        if(!txtName.getText().isEmpty() && !txtName.getText().matches("^[\\p{L}\\s'.-]+$")) {
            NotificationManager.info("Invalid value!", "Name seems to be invalid");
            return false;
        }

        if(!txtRefNumber.getText().matches("^[0-9]*$")) {
            NotificationManager.info("Invalid value!", "Reference number seems to be invalid");
            return false;
        }

        return true;
    }

    private void setItemsToTable(List<PensionerDTO> list) {
        table.getItems().clear();
        table.getItems().addAll(list);
    }
}
