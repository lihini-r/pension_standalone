package org.pensions.view.pd3;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ServicePeriodDialogController implements Initializable{

	@FXML public DatePicker dateTrainedServiceFrom;
	@FXML public DatePicker dateTrainedServiceTo;
	
	@FXML public DatePicker dateCasualFrom;
	@FXML public DatePicker dateCasualTo;
	
	@FXML public DatePicker datePermanantFrom;
	@FXML public DatePicker datePermanantlTo;

	@FXML public TextField txtTrainedYears;
	@FXML public TextField txtTrainedMonths;
	@FXML public TextField txtTrainedDays;

	@FXML public TextField txtCasualYears;
	@FXML public TextField txtCasualMonths;
	@FXML public TextField txtCasualDays;
	
	@FXML public TextField txtPermanantYears;
	@FXML public TextField txtPermanantMonths;
	@FXML public TextField txtPermanantDays;
	
	private ChangeListener<Boolean> trainedPeriodCalculateListener;
	private ChangeListener<Boolean> casualCalculateListener;
	private ChangeListener<Boolean> permanantCalculateListener;
	
	/****1st field****/
	final private BooleanProperty dateTrainedServiceFromFilledProperty = new SimpleBooleanProperty(false);
	final private BooleanProperty dateTrainedServiceToFilledProperty = new SimpleBooleanProperty(false);
	final private BooleanProperty dateTrainedCalculateProperty = new SimpleBooleanProperty(false);
	/*****2nd field *****/
	final private BooleanProperty dateCasualFromFilledProperty = new SimpleBooleanProperty(false);
	final private BooleanProperty dateCasualToFilledProperty = new SimpleBooleanProperty(false);
	final private BooleanProperty dateCasualCalculateProperty = new SimpleBooleanProperty(false);
	/*****3rd field *****/
	final private BooleanProperty datePermanantFromFilledProperty = new SimpleBooleanProperty(false);
	final private BooleanProperty datePermanantToFilledProperty = new SimpleBooleanProperty(false);
	final private BooleanProperty datePermanantCalculateProperty = new SimpleBooleanProperty(false);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hookUpEvents();
		
		
	}

	/*****************************************************************/

	@FXML public void close(ActionEvent e) {
		dateTrainedCalculateProperty.removeListener(trainedPeriodCalculateListener);
		((Node)e.getSource()).getScene().getWindow().hide();
		
	}


	private Period getPeriod(LocalDate from, LocalDate to) {
		return Period.between(from, to);
	}

	/******************************************************************/

	
	/***************Event hookups for date pickers *********************/
	private void hookUpEvents() {
		
		/**********Trained period*****************/
		dateTrainedServiceFrom.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(
					ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				if(!newValue.equals(null)) {
					dateTrainedServiceFromFilledProperty.set(true);
				}else {
					dateTrainedServiceFromFilledProperty.set(false);
				}
				
			}
		});
		
		dateTrainedServiceTo.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(
					ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				if(!newValue.equals(null)) {
					dateTrainedServiceToFilledProperty.set(true);
				}else {
					dateTrainedServiceToFilledProperty.set(false);
				}
			}
		});
		
		trainedPeriodCalculateListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(newValue) {
					Period p = getPeriod(dateTrainedServiceFrom.getValue(), dateTrainedServiceTo.getValue());
					txtTrainedDays.setText(String.valueOf(p.getDays()));
					txtTrainedMonths.setText(String.valueOf(p.getMonths()));
					txtTrainedYears.setText(String.valueOf(p.getYears()));
					dateTrainedServiceFromFilledProperty.set(false);
					dateTrainedServiceToFilledProperty.set(false);
				}
			}
			
		};
		
		dateTrainedCalculateProperty.bind(dateTrainedServiceFromFilledProperty.and(dateTrainedServiceToFilledProperty));
		dateTrainedCalculateProperty.addListener(trainedPeriodCalculateListener);
		
		/**************Casual ******************/
		dateCasualFrom.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(
					ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				if(!newValue.equals(null)) {
					dateCasualFromFilledProperty.set(true);
				}else {
					dateCasualFromFilledProperty.set(false);
				}
				
			}
		});
		
		dateCasualTo.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(
					ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				if(!newValue.equals(null)) {
					dateCasualToFilledProperty.set(true);
				}else {
					dateCasualToFilledProperty.set(false);
				}
			}
		});
		
		casualCalculateListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(newValue) {
					Period p = getPeriod(dateCasualFrom.getValue(), dateCasualTo.getValue());
					txtCasualDays.setText(String.valueOf(p.getDays()));
					txtCasualMonths.setText(String.valueOf(p.getMonths()));
					txtCasualYears.setText(String.valueOf(p.getYears()));
					dateCasualFromFilledProperty.set(false);
					dateCasualToFilledProperty.set(false);
				}
			}
			
		};
		
		dateCasualCalculateProperty.bind(dateCasualFromFilledProperty.and(dateCasualToFilledProperty));
		dateCasualCalculateProperty.addListener(casualCalculateListener);
		
		/*********************Permanant****************************************/
		
		datePermanantFrom.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(
					ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				if(!newValue.equals(null)) {
					datePermanantFromFilledProperty.set(true);
				}else {
					datePermanantFromFilledProperty.set(false);
				}
				
			}
		});
		
		datePermanantlTo.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(
					ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
				if(!newValue.equals(null)) {
					datePermanantToFilledProperty.set(true);
				}else {
					datePermanantToFilledProperty.set(false);
				}
			}
		});
		
		permanantCalculateListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if(newValue) {
					Period p = getPeriod(datePermanantFrom.getValue(), datePermanantlTo.getValue());
					txtPermanantDays.setText(String.valueOf(p.getDays()));
					txtPermanantMonths.setText(String.valueOf(p.getMonths()));
					txtPermanantYears.setText(String.valueOf(p.getYears()));
					datePermanantFromFilledProperty.set(false);
					datePermanantToFilledProperty.set(false);
				}
			}
			
		};
		
		datePermanantCalculateProperty.bind(datePermanantFromFilledProperty.and(datePermanantToFilledProperty));
		dateTrainedCalculateProperty.addListener(permanantCalculateListener);
	}
	
	
	/********************************************************************/
}
