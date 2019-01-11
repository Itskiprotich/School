/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentList;

import Model.BookList;
import Model.StudeeList;
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
public class StudentListController implements Initializable {

    @FXML
    private StackPane mamaYao;
    @FXML
    private AnchorPane babaYao;
    @FXML
    private TableView<StudeeList> bookListTable;
    @FXML
    private TableColumn<StudeeList, String> Bn;
    @FXML
    private TableColumn<StudeeList, String> Bt;
    @FXML
    private TableColumn<StudeeList, String> Ba;
    @FXML
    private TableColumn<StudeeList, String> Bp;
    

    ObservableList<StudeeList> list = FXCollections.observableArrayList();
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
        Bt.setCellValueFactory(new PropertyValueFactory<>("name"));
        Ba.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Bp.setCellValueFactory(new PropertyValueFactory<>("email"));
       
    }

    private void loadBooks() {
        list.clear();
        String sql = "select * from students";
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, "root", "");
            statement = (Statement) conn.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String title = resultSet.getString("name");
                String author = resultSet.getString("phone");
                String publisher = resultSet.getString("email");
                list.add(new StudeeList(number, title, author, publisher));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        bookListTable.setItems(list);
    }   
    
}
