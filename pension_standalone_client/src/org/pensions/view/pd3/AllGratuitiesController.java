package org.pensions.view.pd3;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.pensions.data.dao.GratuityDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.enumarations.DialogType;
import org.pensions.enumarations.EmployeeRole;
import org.pensions.model.GratuityDTO;
import org.pensions.model.GratuityListDTO;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionerDTO;
import org.pensions.model.RegularPensionerDTO;
import org.pensions.model.internal.ViewGratuitiesTableItem;
import org.pensions.model.internal.ViewGratuity;
import org.pensions.model.internal.ViewPensionsTableItem;
import org.pensions.model.lists.GratuityList;
import org.pensions.model.lists.PensionList;
import org.pensions.model.lists.PensionerList;
import org.pensions.session.UserSession;
import org.pensions.view.util.NotificationManager;
import org.pensions.view.util.PopupUtil;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class AllGratuitiesController implements Initializable {
	//GratuityDTO gto;
	PensionerDTO dto;
	//ViewGratuity gratuity;
	
	@FXML private TableView<ViewGratuitiesTableItem> tableGratuities;
	@FXML private TableColumn<ViewGratuitiesTableItem, Long> colPensionNo;
	@FXML private TableColumn<ViewGratuitiesTableItem, String> colName;
	@FXML private TableColumn<ViewGratuitiesTableItem, String> colDesignation;
	@FXML private TableColumn<ViewGratuitiesTableItem, String> colNic;
	@FXML private TableColumn<ViewGratuitiesTableItem, String> colRetireDate;

    
	/**
	 * Log table
	 */
	/**@FXML private TableView<PensionerDTO> tableGratuities;
	@FXML private TableColumn<PensionerDTO, String> colPensionNo;
	@FXML private TableColumn<PensionerDTO, String> colName;
	@FXML private TableColumn<PensionerDTO, String> colDesignation;
	@FXML private TableColumn<PensionerDTO, String> colNic;
	@FXML private TableColumn<PensionerDTO, String> colRetireDate;**/
	
	@FXML private ProgressIndicator progressIndicator;
	
	
	@FXML private MenuItem menuViewHistory;
	private int state = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**colPensionNo.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("Id"));
		colName.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("Name"));
		colDesignation.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("Designation"));
		colNic.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("Nic"));
		colRetireDate.setCellValueFactory(new PropertyValueFactory<PensionerDTO, String>("DateOfRetired"));**/
		
		colPensionNo.setCellValueFactory(new PropertyValueFactory<ViewGratuitiesTableItem, Long>("PensionNumber"));
		colName.setCellValueFactory(new PropertyValueFactory<ViewGratuitiesTableItem, String>("Name"));
		colDesignation.setCellValueFactory(new PropertyValueFactory<ViewGratuitiesTableItem, String>("Designation"));
		colNic.setCellValueFactory(new PropertyValueFactory<ViewGratuitiesTableItem, String>("Nic"));
		colRetireDate.setCellValueFactory(new PropertyValueFactory<ViewGratuitiesTableItem, String>("DateORetired"));
		
		tableGratuities.setFixedCellSize(35);
		
		if( UserSession.INSTANCE.getRole().equals(EmployeeRole.DATA_ENTRY.getRole())){
			state = 200;
		}else if(UserSession.INSTANCE.getRole().equals(EmployeeRole.POSTAL.getRole())){
			state = 100;
		}
		Task<GratuityList> loadPensionsTask = new Task<GratuityList>() {
            @Override
            protected GratuityList call() throws Exception {
                return new GratuityDAO(1).getGratuityList(state);
            }
        };

        progressIndicator.progressProperty().bind(loadPensionsTask.progressProperty());
        progressIndicator.visibleProperty().bind(loadPensionsTask.runningProperty());
        tableGratuities.disableProperty().bind(loadPensionsTask.runningProperty());

        loadPensionsTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent t) {
                        populateTable(loadPensionsTask.getValue());
                    }
                });

        new Thread(loadPensionsTask).start();
		
	}
	
	private void gratuitiesTableLoad(){
		
		PensionerList pensioners = new PensionerDAO().searchPensioners("pending");
		 for(PensionerDTO activepensioner : pensioners.getList()) {
			//tableGratuities.getItems().add(activepensioner);
		 }
		 
	}
	
	@FXML public void acceptGratuities(ActionEvent actionEvent) {
        if(tableGratuities.getSelectionModel().getSelectedItem() != null) {
        	ViewGratuitiesTableItem selectedItem = tableGratuities.getSelectionModel().getSelectedItem();
        	
            PopupUtil<PensionerDTO> pu = new PopupUtil<>(selectedItem,
                    PensionerDTO.class,
                    DialogType.ACCEPT_GRATUITIES);

            try {
                pu.show(UserSession.INSTANCE.getStage());
            } catch (Exception e) {
                System.out.println("From: " + e.getMessage());
            }
           
        }
        
    }
	private void populateTable(GratuityList lto) {

        if(!lto.getList().isEmpty()) {
        	lto.getList().stream()
                    .forEach(e -> {
                    	ViewGratuitiesTableItem item = new ViewGratuitiesTableItem();
                        RegularPensionerDTO regularPensioner = new PensionerDAO().getRegularPensioner(Long.valueOf(e.getPensioner().getUrl()));
                        item.setPensionNumber(Long.valueOf(e.getPensioner().getUrl()));
                        item.setName(regularPensioner.getName());
                        item.setNic(regularPensioner.getNic());
                        item.setDesignation(regularPensioner.getDesignation());
                        item.setDateORetired(regularPensioner.getDateOfRetired());
                        

                        tableGratuities.getItems().add(item);
                    });
        }else {
            NotificationManager.info("No gratuities available!", "No Gratuity details available");
        
        }
    }
	
	
	
}
