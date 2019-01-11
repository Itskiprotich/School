/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import login.ConfigFile;

/**
 * FXML Controller class
 *
 * @author Keeprawteach
 */
public class UserSettingsController implements Initializable {

    @FXML
    private AnchorPane babaYao;
    @FXML
    private JFXButton saveBook;
    @FXML
    private JFXTextField no_of_days_allowed;
    @FXML
    private JFXTextField fine_per_day;
    @FXML
    private JFXTextField user_name;
    @FXML
    private JFXPasswordField user_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadInfor();
    }    

    @FXML
    private void saveBook(ActionEvent event) {
        int ndays = Integer.parseInt(no_of_days_allowed.getText());
        float fine = Float.parseFloat(fine_per_day.getText());
        String uname = user_name.getText();
        String pass = user_password.getText();

        ConfigFile preferences = ConfigFile.getPreferences();
        preferences.setnDaysWithoutFine(ndays);
        preferences.setFinePerDay(fine);
        preferences.setUsername(uname);
        preferences.setPassword(pass);

        ConfigFile.writePreferenceToFile(preferences);
    }

    private void loadInfor() {
        
        ConfigFile preferences = ConfigFile.getPreferences();
        no_of_days_allowed.setText(String.valueOf(preferences.getnDaysWithoutFine()));
        fine_per_day.setText(String.valueOf(preferences.getFinePerDay()));
        user_name.setText(String.valueOf(preferences.getUsername()));
        String passHash = String.valueOf(preferences.getPassword());
        user_password.setText(passHash.substring(0, Math.min(passHash.length(), 10)));
        
    }
    
}
