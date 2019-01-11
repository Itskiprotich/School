/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookList;

import Model.BookList;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
public class BookListController implements Initializable {

    @FXML
    private StackPane mamaYao;
    @FXML
    private AnchorPane babaYao;
    @FXML
    private TableView<BookListBook> bookListTable;
    @FXML
    private TableColumn<BookListBook, String> Bn;
    @FXML
    private TableColumn<BookListBook, String> Bt;
    @FXML
    private TableColumn<BookListBook, String> Ba;
    @FXML
    private TableColumn<BookListBook, String> Bp;
    @FXML
    private TableColumn<BookListBook, Boolean> St;

    ObservableList<BookListBook> list = FXCollections.observableArrayList();
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
        String sql = "select * from books";
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
                list.add(new BookListBook(number, title, author, publisher, status));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        bookListTable.setItems(list);
    }

    public static class BookListBook {
        private final SimpleStringProperty Bn;
        private final SimpleStringProperty Bt;
        private final SimpleStringProperty Ba;
        private final SimpleStringProperty Bp;
        private final SimpleStringProperty St;
        public BookListBook(String Bn, String Bt, String Ba, String Bp, String St) {
            this.Bn = new SimpleStringProperty(Bn);
            this.Bt = new SimpleStringProperty(Bt);
            this.Ba = new SimpleStringProperty(Ba);
            this.Bp = new SimpleStringProperty(Bp);
            this.St = new SimpleStringProperty(St);
        }

        public String getBn() {
            return Bn.get();
        }

        public String getBt() {
            return Bt.get();
        }

        public String getBa() {
            return Ba.get();
        }

        public String getBp() {
            return Bp.get();
        }

        public String getSt() {
            return St.get();
        }

    }

}
