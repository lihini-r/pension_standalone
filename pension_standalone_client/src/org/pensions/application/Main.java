package org.pensions.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	private double x, y;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/org/pensions/view/LoginView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			addDragListeners(root, primaryStage);
			primaryStage.show();
			//comment form diessh
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	private void addDragListeners(final Node n, Stage primaryStage){

		n.setOnMousePressed((MouseEvent mouseEvent) -> {
			this.x = n.getScene().getWindow().getX() - mouseEvent.getScreenX();
			this.y = n.getScene().getWindow().getY() - mouseEvent.getScreenY();
		});

		n.setOnMouseDragged((MouseEvent mouseEvent) -> {
			primaryStage.setX(mouseEvent.getScreenX() + this.x);
			primaryStage.setY(mouseEvent.getScreenY() + this.y);
		});
	}
}
