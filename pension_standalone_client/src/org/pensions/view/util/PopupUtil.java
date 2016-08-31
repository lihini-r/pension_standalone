package org.pensions.view.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import org.controlsfx.control.PopOver;
import org.pensions.enumarations.DialogType;
import org.pensions.interfaces.Popoverble;
import org.pensions.model.PensionerDTO;
import org.pensions.model.PensionerOverviewDTO;
import org.pensions.printmodule.TemplateController;
import org.pensions.view.pd3.ApprovePensionViewController;
import org.pensions.view.pd3.HistoryViewController;
import org.pensions.view.pd3.ReceivedViewController;
import org.pensions.view.pd3.ViewGratuitiesController;
import org.pensions.view.pd3.ViewPensionsController;

public class PopupUtil<T> {

	private static final String Parent = null;
	private PopOver pop;
	private String url;

	
	public PopupUtil(Object object, Class<T> entityType,DialogType type) {
		init(type.name(),object);
		url = type.getFxmlUrl();
	}
	
	public PopupUtil(DialogType type,int x,int y) {
		subMenuInit(x,y);
		url = type.getFxmlUrl();
	}
	
	private void init(String title, Object object) {
		pop = new PopOver();
		pop.setAutoHide(false);
		pop.setCornerRadius(0);
		pop.setDetached(true);
		pop.setHeaderAlwaysVisible(true);
		pop.setTitle(title.replace("_", " "));
		pop.setOpacity(1);
		
		pop.setUserData(object);
	}
	private void subMenuInit(int x,int y) {
		pop = new PopOver();
		pop.setAutoHide(true);
		pop.setAnchorX(x);
		pop.setAnchorY(y);
		pop.setCornerRadius(0);
		pop.setDetached(false);
		pop.setHeaderAlwaysVisible(false);
		pop.setOpacity(1);
	}

	public void show(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
		pop.setContentNode((Parent) loader.load());
		Popoverble popoverbleController = (Popoverble) loader.getController();
		popoverbleController.setData(pop.getUserData(), pop);
		pop.show(stage);
	}

}
