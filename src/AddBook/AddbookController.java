/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddBook;

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
public class AddbookController implements Initializable {

    @FXML
    private JFXButton cancelBook;
    @FXML
    private JFXButton saveBook;
    @FXML
    private JFXTextField book_no;
    @FXML
    private JFXTextField book_title;
    @FXML
    private JFXTextField book_author;
    @FXML
    private JFXTextField book_publisher;

    String Book_No, Book_Title, Book_Author, Book_Publisher;
    @FXML
    private StackPane mamaYao;
    @FXML
    private AnchorPane babaYao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    @FXML
    private void cancelBook(ActionEvent event) {
        ((Stage) cancelBook.getScene().getWindow()).close();
    }

    @FXML
    private void saveBook(ActionEvent event) {
        Book_No = book_no.getText().toString();
        Book_Title = book_title.getText().toString();
        Book_Author = book_author.getText().toString();
        Book_Publisher = book_publisher.getText().toString();
        if (Book_No.isEmpty()) {
            book_no.getStyleClass().add("wrong-credentials");
            return;

        }
        if (Book_Title.isEmpty()) {
            book_title.getStyleClass().add("wrong-credentials");
            return;

        }
        if (Book_Author.isEmpty()) {
            book_author.getStyleClass().add("wrong-credentials");
            return;

        }
        if (Book_Publisher.isEmpty()) {
            book_publisher.getStyleClass().add("wrong-credentials");
            return;
        } else {
            addBookNow(Book_No, Book_Title, Book_Author, Book_Publisher,"Available");
        }
    }

    private void addBookNow(String Book_No, String Book_Title, String Book_Author, String Book_Publisher, String par) {
        Database database=new Database();
        boolean jeff=database.addBook(Book_No,Book_Title,Book_Author,Book_Publisher,par);
        if (jeff==true) {
        AlertDisplay.showMaterialDialog(mamaYao, babaYao, new ArrayList<>(), "New book added", Book_Title + " has been added");
        clearEntries();
        }else{
            AlertDisplay.showMaterialDialog(mamaYao, babaYao, new ArrayList<>(), "Failed to add book", Book_Title + " cannot be added");
        }
    }

    private void clearEntries() {
        book_no.clear();
        book_author.clear();
        book_publisher.clear();
        book_title.clear();
    }

}
