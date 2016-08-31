package org.pensions.view;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;
import org.pensions.session.UserSession;

public class StickeyNoteController implements Initializable{
	
	@FXML private TextArea textArea;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UserSession session = UserSession.INSTANCE;
		
		if(session.getNote() != null) {
			textArea.setText(session.getNote());
		}
		
		textArea.setOnKeyReleased(e -> {
			session.setNote(textArea.getText());
		});
		textArea.setWrapText(true);
	}
	
	/***********************************************************
	 * 			Button listners
	 * @return 
	 * 		
	 ************************************************************/

	
	/**
	 * Close button action handler
	 * @param e
	 */
	@FXML public void colseButtonActionHandler(ActionEvent e) {
		((Node)e.getSource()).getScene().getWindow().hide();
	}
	
	/**
	 * Copy button action handler
	 * @param e
	 */
	@FXML public void copyBtnactionHandler(ActionEvent e) {
		StringSelection stringSelection = new StringSelection(textArea.getText());
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		Notifications.create()
		.text("Text copied to clipboard...")
		.darkStyle()
		.hideAfter(Duration.millis(1500))
		.showInformation();
	}
	
	
	/**
	 * Clear button action handler
	 */
	@FXML public void clearBtnActionHandler() {
		UserSession.INSTANCE.setNote("");
		textArea.clear();
	}
}
