package org.pensions.printmodule;

import java.io.IOException;

import org.pensions.interfaces.Popoverble;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionerDTO;

import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.pensions.model.internal.InterviewLetter;

public class PrintModule {

	/**
	 * print interview application
	 * @param owner
	 * @param dto
	 * @param interviewdate
	 */
	public void start(Stage owner,PensionerDTO dto,String interviewdate){
		BorderPane borderPane = new BorderPane();
		AnchorPane root = null;
		
		try {
			FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("org/pensions/printmodule/Template.fxml")));
			root = loader.load();
			Popoverble controller = (Popoverble) loader.getController();
			controller.setData(new InterviewLetter(interviewdate, dto), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		borderPane.setCenter(root);
		Scene scene = new Scene(borderPane,595,842);
		Stage stage = new Stage();
		stage.setScene(scene);

		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(owner);
		stage.show();


		print(borderPane.getCenter(), stage);
		
	}
	/**
	 * print award
	 * @param owner
	 * @param dto
	 * @param interviewdate
	 */
	public void startPrintCertificate(Stage owner,PensionerDTO dto){
		BorderPane borderPane = new BorderPane();
		AnchorPane root = null;
		
		try {
			FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("org/pensions/printmodule/Sathkara.fxml")));
			root = loader.load();
			TemplateController controller = (TemplateController) loader.getController();
			controller.setPensioner(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		borderPane.setCenter(root);
		Scene scene = new Scene(borderPane,595,842);
		Stage stage = new Stage();
		stage.setScene(scene);

		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(owner);
		stage.show();


		print(borderPane.getCenter(), stage);
		
	}
	
	/** Scales the node based on the standard letter, portrait paper to be printed.
     * @param node The scene node to be printed.
     */
	public void print(final Node node,Stage stage){
		Printer printer = Printer.getDefaultPrinter();

		PrinterJob job = PrinterJob.createPrinterJob();
		PageLayout Layout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT,0.216, 0.256, 0.25, 0.275);
		job.getJobSettings().setPageLayout(Layout);
		
		//((ImageView)((AnchorPane)node).getChildren().get(0)).setOpacity(0);
		PageLayout layout = job.getJobSettings().getPageLayout();
		double scaleX = layout.getPrintableWidth() / node.getBoundsInParent().getWidth();
		double scaleY = layout.getPrintableHeight() / node.getBoundsInParent().getHeight();
		node.getTransforms().add(new Scale(scaleX,scaleY));
		
		boolean printDialog = job.showPrintDialog(stage);
		if(printDialog) {
			if (!job.equals(null)) {
				boolean success = job.printPage(node);
				if (success) {
					job.endJob();
					stage.close();

				}else {
					stage.close();
				}
			}
		}else {
			stage.close();
		}
		
	}
	
	
	
	
}
