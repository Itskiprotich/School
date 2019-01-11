/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddStudent;

import Alerts.AlertDisplay;
import Database.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Keeprawteach
 */
public class AddStudentController implements Initializable {

    @FXML
    private StackPane mamaYao;
    @FXML
    private AnchorPane babaYao;
    @FXML
    private JFXTextField student_no;
    @FXML
    private JFXTextField student_name;
    @FXML
    private JFXTextField student_phone;
    @FXML
    private JFXTextField student_email;
    @FXML
    private JFXButton cancelStudent;
    @FXML
    private JFXButton saveStudent;

    String Student_Number, Student_Name, Student_Phone, Student_Email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancelStudent(ActionEvent event) {
        ((Stage) cancelStudent.getScene().getWindow()).close();
    }

    @FXML
    private void saveStudent(ActionEvent event) {

        Student_Number = student_no.getText().toString();
        Student_Name = student_name.getText().toString();
        Student_Phone = student_phone.getText().toString();
        Student_Email = student_email.getText().toString();
        if (Student_Number.isEmpty()) {
            student_no.getStyleClass().add("wrong-credentials");
            student_no.requestFocus();
            return;

        }
        if (Student_Name.isEmpty()) {
            student_name.getStyleClass().add("wrong-credentials");
            student_name.requestFocus();
            return;

        }
        if (Student_Phone.isEmpty()) {
            student_phone.getStyleClass().add("wrong-credentials");
            student_phone.requestFocus();
            return;

        }
        if (Student_Email.isEmpty()) {
            student_email.getStyleClass().add("wrong-credentials");
            student_email.requestFocus();
            return;
        } else {
            addStudentNow(Student_Number, Student_Name, Student_Phone, Student_Email);
        }
    }

    private void addStudentNow(String Student_Number, String Student_Name, String Student_Phone, String Student_Email) {
        Database database = new Database();
        boolean jeff = database.addStudent(Student_Number, Student_Name, Student_Phone, Student_Email);
        if (jeff == true) {
            AlertDisplay.showMaterialDialog(mamaYao, babaYao, new ArrayList<>(), "New Student added", Student_Name + " has been registered");
            clearEntries();
        } else {
            AlertDisplay.showMaterialDialog(mamaYao, babaYao, new ArrayList<>(), "Failed to add Student", Student_Name + " cannot be added");
        }
    }

    private void clearEntries() {
        student_no.clear();
        student_name.clear();
        student_phone.clear();
        student_email.clear();
    }

}
