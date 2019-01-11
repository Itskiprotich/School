/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package issuedList;

import Model.BookList;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Keeprawteach
 */
public class IssuedListController implements Initializable {

    @FXML
    private StackPane mamaYao;
    @FXML
    private AnchorPane babaYao;
    @FXML
    private TableView<BookList> bookListTable;
    @FXML
    private TableColumn<BookList, String> Bn;
    @FXML
    private TableColumn<BookList, String> Bt;
    @FXML
    private TableColumn<BookList, String> Ba;
    @FXML
    private TableColumn<BookList, String> Bp;
    @FXML
    private TableColumn<BookList, Boolean> St;

    ObservableList<BookList> list = FXCollections.observableArrayList();
    Statement statement;
    ResultSet resultSet;
    Connection conn;
    String connection_driver = "org.gjt.mm.mysql.Driver";
    String connection_url = "jdbc:mysql://localhost/school";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
        prepareRows();
        loadBooks();
    }

    private void prepareRows() {
        Bn.setCellValueFactory(new PropertyValueFactory<>("number"));
        Bt.setCellValueFactory(new PropertyValueFactory<>("title"));
        Ba.setCellValueFactory(new PropertyValueFactory<>("author"));
        Bp.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        St.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadBooks() {
        list.clear();
        String sql = "select * from books where status ='Issued' ";
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, "root", "");
            statement = (Statement) conn.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                String status = resultSet.getString("status");
                list.add(new BookList(number, title, author, publisher, status));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        bookListTable.setItems(list);
    }
    
}
