/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notification;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Keeprawteach
 */
public class NotificationsController implements Initializable {

    @FXML
    private StackPane mamaYao;
    @FXML
    private AnchorPane babaYao;
    @FXML
    private TableView<?> bookListTable;
    @FXML
    private TableColumn<?, ?> Bn;
    @FXML
    private TableColumn<?, ?> Bt;
    @FXML
    private TableColumn<?, ?> Ba;
    @FXML
    private TableColumn<?, ?> Bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
