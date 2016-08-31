package org.pensions.view.pd3;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.controlsfx.control.PopOver;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.enumarations.DialogType;
import org.pensions.enumarations.EmployeeRole;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionerDTO;
import org.pensions.model.internal.ViewPensionsTableItem;
import org.pensions.model.lists.PensionList;
import org.pensions.session.UserSession;
import org.pensions.view.RootLayoutController;
import org.pensions.view.util.NotificationManager;
import org.pensions.view.util.PopupUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Dinesh Liyanage on 5/25/2016.
 */
public class ViewPensionsController implements Initializable, Popoverble{

    @FXML private TableView<ViewPensionsTableItem> table;


    @FXML private TableColumn<ViewPensionsTableItem, String> colType;
    @FXML private TableColumn<ViewPensionsTableItem, Long> colPensionNumber;
    @FXML private TableColumn<ViewPensionsTableItem, String> colOffice;
    @FXML private TableColumn<ViewPensionsTableItem, String> colCircular;
    @FXML private TableColumn<ViewPensionsTableItem, String> colState;

    @FXML private Region backDrop;
    @FXML private ProgressIndicator progressIndicator;
    @FXML private AnchorPane prograssIndicatorContainer;

    @FXML private Button btnClose;
    @FXML private Button btnMore;

    private PensionerDTO dto;
    private PopOver pop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colType.setCellValueFactory(new PropertyValueFactory<ViewPensionsTableItem, String>("Type"));
        colPensionNumber.setCellValueFactory(new PropertyValueFactory<ViewPensionsTableItem, Long>("PensionNumber"));
        colOffice.setCellValueFactory(new PropertyValueFactory<ViewPensionsTableItem, String>("Office"));
        colCircular.setCellValueFactory(new PropertyValueFactory<ViewPensionsTableItem, String>("Circular"));
        colState.setCellValueFactory(new PropertyValueFactory<ViewPensionsTableItem, String>("State"));

        Tooltip closeBtnToolTip = new Tooltip("close pensions view window");
        Tooltip moreBtnToolTip = new Tooltip("view more information of the selected pension");

        btnClose.setTooltip(closeBtnToolTip);
        btnMore.setTooltip(moreBtnToolTip);

        table.setFixedCellSize(30);
    }

    private void init(PensionerDTO dto, PopOver pop) {
        this.dto = new PensionerDAO().getPensioner(dto.getId());
        this.pop = pop;

        Task<PensionList> loadPensionsTask = new Task<PensionList>() {
            @Override
            protected PensionList call() throws Exception {
                return new PensionsDAO(dto.getId()).getAllPensions();
            }
        };

        progressIndicator.progressProperty().bind(loadPensionsTask.progressProperty());
        progressIndicator.visibleProperty().bind(loadPensionsTask.runningProperty());
        prograssIndicatorContainer.visibleProperty().bind(loadPensionsTask.runningProperty());
        backDrop.visibleProperty().bind(loadPensionsTask.runningProperty());
        table.setPlaceholder(new Label("Loading..."));

        loadPensionsTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent t) {
                        populateTable(loadPensionsTask.getValue());
                    }
                });

        new Thread(loadPensionsTask).start();
    }

    private void populateTable(PensionList list) {
        table.getItems().clear();

        if(!list.getPensions().isEmpty()) {
            list.getPensions().stream()
                    .forEach(e -> {
                        ViewPensionsTableItem item = new ViewPensionsTableItem();
                        item.setCircular(e.getCircular());
                        item.setOffice(e.getPensionPoint().getRel());
                        item.setPensionNumber(e.getId());
                        item.setType(dto.getPensionerType());
                        item.setState(e.getState().getRel());

                        table.getItems().add(item);
                    });
        }else {
            NotificationManager.info("No pensions available!", "No pension details available for the pensioner");
            pop.hide(new Duration(500));
        }
    }

    /*****************************************************************************************/
    @FXML public void close(ActionEvent e) {
        pop.hide(new Duration(500));
    }

    @FXML public void btnMoreActionHandler(ActionEvent e) throws IOException {

        if(table.getSelectionModel().getSelectedItem() == null) {
            NotificationManager.info("No pension selected!", "Please select a pension to continue");
        }else {
            setActivePension();

            if((UserSession.INSTANCE.getRole()).equals(EmployeeRole.POSTAL.getRole()) && table.getSelectionModel().getSelectedItem().getState().equals("Inserted")){

                PopupUtil<PensionerDTO> pu = new PopupUtil<>(dto,
                        PensionerDTO.class,
                        DialogType.RECEIVE_PENSION_APPLICATION);

                try {
                    pu.show(UserSession.INSTANCE.getStage());
                } catch (Exception e1) {
                    System.out.println("From: " + e1.getMessage());
                }
                close(e);

            }else{

                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("org/pensions/view/pd3/PD3.fxml"));
                pop.hide(new Duration(1000));
                RootLayoutController.getInstance().setAnchoredPane((AnchorPane) loader.load());
                PD3Controller controller = (PD3Controller) loader.getController();
                controller.setPensioner(dto);

            }
        }
    }

    /*************************************************
     *          Context menu action handlers
     *************************************************/
    @FXML public void viewHistory(ActionEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem() != null) {
            setActivePension();
            PopupUtil<PensionerDTO> pu = new PopupUtil<>(dto,
                    PensionerDTO.class,
                    DialogType.VIEW_HISTORY);

            try {
                pu.show(UserSession.INSTANCE.getStage());
            } catch (Exception e) {
                NotificationManager.error("Unexpected error", "Something went wrong while trying to load the history of the pension. please try again or contact system administrator");
            }
        }else {
            NotificationManager.info("Please select a pension", "Please select a pension to view the history");
        }
    }

    /*************************************************
     *                   Private methods
     *************************************************/
    private void setActivePension(){
        String pensionNumber = String.valueOf(table.getSelectionModel().getSelectedItem().getPensionNumber());
        dto.getPensions().add(new Hetoes("ACTIVE_PENSION", pensionNumber));
    }
    
    @Override
    public void setData(Object object, PopOver pop) {
        init((PensionerDTO) object, pop);
    }
}
