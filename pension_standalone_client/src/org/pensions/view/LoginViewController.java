package org.pensions.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

import org.pensions.controllers.AuthenticationController;
import org.pensions.data.dao.sqlite.SqliteDAO;
import org.pensions.model.UserCredential;
import org.pensions.session.UserSession;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class LoginViewController implements Initializable{	

    @FXML public ImageView userDummyImage;
    @FXML public TextField txtUserName;
    @FXML public PasswordField txtPassword;

    @FXML public void exitBtnActionHandler(MouseEvent e) {
        Platform.exit();
    }

    @FXML public void loginButtonActionHandler(ActionEvent e) throws Exception{
        Stage stage = UserSession.INSTANCE.getStage();
        AuthenticationController controller = new AuthenticationController();

        UserCredential credential = new UserCredential();
        credential.setUsername(txtUserName.getText());
        credential.setPassword(txtPassword.getText());

        if(controller.login(credential)) {
            //updateSqlite();
            ((Stage)((Node)e.getSource()).getScene().getWindow()).close();

            Parent root = FXMLLoader.load(getClass().getResource("/org/pensions/view/RootLayout.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }


    @FXML
    private void enterKeyPressed(KeyEvent event) throws IOException{
		/*
		if (event.getCode() == KeyCode.ENTER) {
			System.out.println("test");
			login(event);
		}
*/
    }

    @FXML public void closeBtnActionHandler() {
        Platform.exit();
    }

    public void login(Event e) throws IOException{
        Stage stage = new Stage();
        AuthenticationController controller = new AuthenticationController();

        UserCredential credential = new UserCredential();
        credential.setUsername(txtUserName.getText());
        credential.setPassword(txtPassword.getText());
        try {
            if(controller.login(credential)) {
                updateSqlite();
                ((Stage)((Node)e.getSource()).getScene().getWindow()).close();

                Parent root = FXMLLoader.load(getClass().getResource("/org/pensions/view/RootLayout.fxml"));
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setAlwaysOnTop(false);
                stage.show();
            }
        }catch(Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("Look, an Exception Dialog");
            alert.setContentText("Could not find file blabla.txt!");

            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
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

    private void updateSqlite() {
		/*Task<Void> updateTask = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				new SqliteDAO().updateSqlite();
				return null;
			}
		};

		Thread th = new Thread(updateTask);
        th.setDaemon(true);
        th.start();*/
        new SqliteDAO().updateSqlite();
    }



    public void initialize(URL location, ResourceBundle resources) {

    }

}