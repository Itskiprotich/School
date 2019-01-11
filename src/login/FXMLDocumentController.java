/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


import Database.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author Keeprawteach
 */
public class FXMLDocumentController implements Initializable {

    String username, password;
    @FXML
    private JFXTextField user_username;
    @FXML
    private JFXPasswordField user_password;
    @FXML
    private JFXButton user_cancel;
    @FXML
    private JFXButton user_login;

    ConfigFile config;
    
    Database database;
    
    public static final String ICON_IMAGE_LOC = "/resources/cheshire-cat.ico";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        config = ConfigFile.getPreferences();
        database=new Database();
        database.checkConnection();
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void loginAction(ActionEvent event) {
        username = user_username.getText();
        password = user_password.getText();
        if (username.isEmpty() && password.isEmpty()) {
            user_username.getStyleClass().add("wrong-credentials");
            user_password.getStyleClass().add("wrong-credentials");
        } else if (username.isEmpty()) {
            user_username.getStyleClass().add("wrong-credentials");
        } else if (password.isEmpty()) {
            user_password.getStyleClass().add("wrong-credentials");
        } else {
            checkUser(username, password);
        }
    }

    private void checkUser(String username, String password) {
        String pass = DigestUtils.shaHex(password);
        if (username.equalsIgnoreCase(config.getUsername()) && pass.equalsIgnoreCase(config.getPassword())) {
            closeStage();
            openMain();
        } else {
            user_username.getStyleClass().add("wrong-credentials");
            user_password.getStyleClass().add("wrong-credentials");
        }
    }

    private void openMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Main/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Library Management System");
            stage.getIcons().add(new Image(ICON_IMAGE_LOC));
            stage.setScene(new Scene(parent));
            stage.show();
            
        }
        catch (IOException ex) {
            
        }
        
    }

    private void closeStage() {
        ((Stage) user_username.getScene().getWindow()).close();
        
    }

}
