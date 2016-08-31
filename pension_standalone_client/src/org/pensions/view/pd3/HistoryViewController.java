package org.pensions.view.pd3;


import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.pensions.data.dao.GratuityDAO;
import org.pensions.data.dao.HistoryDAO;
import org.pensions.data.dao.PensionerDAO;
import org.pensions.data.dao.PensionsDAO;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.GratuityListDTO;
import org.pensions.model.GratuityLogDTO;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionLogDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.model.lists.GratuityLogList;
import org.pensions.model.lists.History;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryViewController implements Initializable, Popoverble{

	/**
	 * Labels
	 */
	@FXML private Label lblPensionerName;
	@FXML private Label lblDesignation;
	@FXML private Label lblNic;
	@FXML private Label lblpensionNumber;

	/**
	 * Log table
	 */
	@FXML private TableView<PensionLogDTO> tableLog;
	@FXML private TableColumn<PensionLogDTO, String> colTimestamp;
	@FXML private TableColumn<PensionLogDTO, String> colState;
	@FXML private TableColumn<PensionLogDTO, String> colEmployee;

	@FXML private MenuItem menuViewHistory;

	private PensionerDTO dto;
	private PopOver pop;

	public void init(PensionerDTO dto, PopOver pop) {
		// TODO Auto-generated method stub

		this.dto = dto;
		this.pop = pop;
		Hetoes activePension = dto.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);
		dto.getPensions().add(activePension);

		if(activePension != null) { //there is a pension which is activated
			PensionDTO pension = new PensionsDAO(dto.getId()).getPension(Long.valueOf(activePension.getUrl()));

			lblPensionerName.setText(dto.getName());
			lblNic.setText(dto.getNic());
			lblDesignation.setText(dto.getDesignation());
			lblpensionNumber.setText(String.valueOf(pension.getId()));

			History history = new HistoryDAO(dto.getId(),pension.getId()).getHistory();
			for(PensionLogDTO log : history.getHistory()) {
				tableLog.getItems().add(log);
			}
		}
		

	}

	@FXML public void closeButtonActionHandler(ActionEvent event) {
		pop.hide(new Duration(500));
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colEmployee.setCellValueFactory(new PropertyValueFactory<PensionLogDTO, String>("ResponsibleEmployee"));
		colState.setCellValueFactory(new PropertyValueFactory<PensionLogDTO, String>("State"));
		colTimestamp.setCellValueFactory(new PropertyValueFactory<PensionLogDTO, String>("Timestamp"));
	}

	@Override
	public void setData(Object object, PopOver pop) {
		this.init((PensionerDTO) object, pop);
	}
	
	private void checkDateRange(){
		
		Hetoes activePension = dto.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);
		dto.getPensions().add(activePension);
		PensionDTO pension = new PensionsDAO(dto.getId()).getPension(Long.valueOf(activePension.getUrl()));
		History history = new HistoryDAO(dto.getId(),pension.getId()).getHistory();
		for(PensionLogDTO log : history.getHistory()) {
			
			List<String> timeStamp = Arrays.asList(log.getTimestamp().split(" "));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date dateFrom = sdf.parse("2016-07-01");
				Date dateTo = sdf.parse("2016-07-27");
				Date date1 = sdf.parse(timeStamp.get(0));
				
				if((date1.compareTo(dateFrom)>0) && ((date1.compareTo(dateTo))<=0)){
					System.out.println(timeStamp.get(0));
					
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
    
}
