package org.pensions.view.pd3;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.pensions.data.reports.GratuityExcelReportGenerator;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.PensionerDTO;
import org.pensions.view.util.NotificationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

public class GratuityReportController implements Initializable, Popoverble{
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML private DatePicker generatedDateFrom;
	@FXML private DatePicker generatedDateTo;

	private PensionerDTO dto;
	private PopOver pop;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@Override
	public void setData(Object object, PopOver pop) {
		this.init((PensionerDTO) object, pop);
	}

	private void init(PensionerDTO userData, PopOver pop) {
		this.dto = userData;
		this.pop = pop;

	}
	/**
	 * generate gratuity report button
	 * @param event
	 */
	@FXML
	public void generateGratuityReportActionhandler(ActionEvent event){
		if(generatedDateFrom.getValue() == null && generatedDateTo.getValue() == null){
			NotificationManager.info("Empty parameters!", "Please fill the text field to generate Excel.");

		}else{
			try {
				
				LocalDate date = generatedDateFrom.getValue();
				LocalDate dateTo = generatedDateTo.getValue();
				String generateFrom = date.toString();
				String generateTo = dateTo.toString();
				GratuityExcelReportGenerator report = new GratuityExcelReportGenerator();
				report.generateExcelSheet(generateFrom , generateTo);
				
				closeButtonHandler(event);
				
			}catch(Exception e) {
				NotificationManager.error("hgudhgad", "sdjfksjfdkjshdf");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Exception Dialog");
				alert.setHeaderText("Look, an Exception Dialog");
				alert.setContentText("Could not find file blabla.txt!");

				// Create expandable Exception.
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				String exceptionText = sw.toString();

				Label label = new Label("The exception stacktrace was:");

				TextArea textArea = new TextArea(exceptionText);
				textArea.setEditable(false);
				textArea.setWrapText(true);

				textArea.setMaxWidth(Double.MAX_VALUE);
				textArea.setMaxHeight(Double.MAX_VALUE);
				GridPane.setVgrow(textArea, Priority.ALWAYS);
				GridPane.setHgrow(textArea, Priority.ALWAYS);

				GridPane expContent = new GridPane();
				expContent.setMaxWidth(Double.MAX_VALUE);
				expContent.add(label, 0, 0);
				expContent.add(textArea, 0, 1);

				// Set expandable Exception into the dialog pane.
				alert.getDialogPane().setExpandableContent(expContent);

				alert.showAndWait();
			}
		}
	}


	@FXML
	public void closeButtonHandler(ActionEvent e1){
		pop.hide(new Duration(500));
	}


}
