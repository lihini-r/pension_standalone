package org.pensions.view.dashboard;

import java.net.URL;
import java.util.ResourceBundle;

import org.pensions.data.dao.DashboardDAO;
import org.pensions.data.dao.ExtraPaymentDAO;
import org.pensions.model.DashboardDTO;
import org.pensions.model.ExtraPaymentDTO;
import org.pensions.model.Hetoes;
import org.pensions.model.lists.DashboardList;
import org.pensions.model.lists.ExtraPaymentList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class DasboardController implements Initializable{
	
	@FXML private Label lblPendingCount;
	@FXML private Label lblVerifiedCount;
	@FXML private Label lblRejectCount;
	@FXML private ImageView img;
	@FXML private ImageView imgreject;
	@FXML private ImageView imgg;
	@FXML private ImageView imgsath;
	@FXML private ImageView imgr;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		initializeCount();
	}
	
	private void initializeCount(){
		DashboardList dashboardList = new DashboardDAO().getDashboardCount();
		for(DashboardDTO details : dashboardList.getDetails()) {
			if(details.getRel().equals("pending pension")){
			  lblPendingCount.setText(String.valueOf(details.getUrl()));
			}else if(details.getRel().equals("verified pension")){
				lblVerifiedCount.setText(String.valueOf(details.getUrl()));
			}else if(details.getRel().equals("rejected pension")){
				lblRejectCount.setText(String.valueOf(details.getUrl()));
			}
			
		}	
	}

}
