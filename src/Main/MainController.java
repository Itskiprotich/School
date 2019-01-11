/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Alerts.AlertDisplay;
import Database.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Keeprawteach
 */
public class MainController implements Initializable {

    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton newBook;
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private JFXTextField bookIDInput;
    @FXML
    private MenuItem menu_new_book;
    @FXML
    private JFXButton newStudent;
    @FXML
    private MenuItem menu_add_student;
    @FXML
    private JFXButton studentList;
    @FXML
    private JFXButton bookList;
    @FXML
    private JFXButton issuedList;
    @FXML
    private MenuItem menu_book_list;
    @FXML
    private MenuItem menu_students_list;
    @FXML
    private MenuItem menu_issued_list;
    @FXML
    private JFXTextField studeesearch;
    @FXML
    private Label bookName;
    @FXML
    private Label bookAuthor;
    @FXML
    private Label studentName;
    @FXML
    private Label studentNumber;
    Statement statement;
    ResultSet resultSet;
    Connection conn;
    String connection_driver = "org.gjt.mm.mysql.Driver";
    String connection_url = "jdbc:mysql://localhost/school";
    @FXML
    private JFXButton issueButton;
    @FXML
    private Label Bookstatus;
    @FXML
    private StackPane mamaYao;
    @FXML
    private AnchorPane babaYao;
    String bookId, studentId, bookStatus;
    @FXML
    private Pane userProfileHere;
    @FXML
    private JFXTextField bookSearchNumber;
    @FXML
    private Pane bookDetailsIssues;
    @FXML
    private Pane daysIssued;

    String book;
    @FXML
    private JFXButton renewBook;
    @FXML
    private Label datehereIssued;
    @FXML
    private Label jinayastudee;
    @FXML
    private Label simuyastudee;
    @FXML
    private Label emailyastudee;
    @FXML
    private Label titleofBook;
    @FXML
    private Label publisherofBook;
    @FXML
    private Label authorofBook;
    @FXML
    private Label usedFor;
    @FXML
    private MenuItem menuSettings;
    @FXML
    private JFXButton settingsButton;
    @FXML
    private MenuItem menu_about;
    @FXML
    private JFXButton notify_button;
    @FXML
    private MenuItem menu_over;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userProfileHere.setVisible(false);
        bookDetailsIssues.setVisible(false);
        daysIssued.setVisible(false);
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        JFXButton yesButton = new JFXButton("Logout");
        JFXButton noButton = new JFXButton("Cancel");
        AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(yesButton, noButton), "Confirm Exit",
                String.format("Are you sure want to Exit ?"));
        yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
            ((Stage) logout.getScene().getWindow()).close();
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Library Management System");
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {

            }
        });

    }

    @FXML
    private void newBook(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/AddBook/addbook.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("New Book");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {

        }
    }

    @FXML
    private void newStudent(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/AddStudent/addStudent.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("New Student");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {

        }
    }

    @FXML
    private void studentList(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/studentList/studentList.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Registered Students");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {

        }
    }

    @FXML
    private void bookdList(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/bookList/bookList.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Available Books");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {

        }
    }

    @FXML
    private void issuedList(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/issuedList/issuedList.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Issued Books");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {

        }
    }

    @FXML
    private void searchBookId(ActionEvent event) {
        String bookId = bookIDInput.getText().toString();
        String sql = "select * from books where number = '" + bookId + "' ";
        Boolean flag = false;
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, "root", "");
            statement = (Statement) conn.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String status = resultSet.getString("status");
                bookName.setText(title);
                bookAuthor.setText("by " + author);
                Bookstatus.setText(status);
                flag = true;

            }

            if (!flag) {
                bookName.setText("NO_SUCH_BOOK_AVAILABLE");
                bookAuthor.setText("");
            } else {
                studeesearch.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void searchStudentId(ActionEvent event) {
        String bookId = studeesearch.getText().toString();
        String sql = "select * from students where number = '" + bookId + "' ";
        Boolean flag = false;
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, "root", "");
            statement = (Statement) conn.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                studentName.setText(name);
                studentNumber.setText(phone);
                flag = true;
            }
            if (!flag) {
                studentName.setText("NO_SUCH_MEMBER_AVAILABLE");
                studentNumber.setText("");
            } else {
                issueButton.requestFocus();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void issuedBookToStudent(ActionEvent event) {
        bookId = bookIDInput.getText().toString();
        studentId = studeesearch.getText().toString();
        bookStatus = Bookstatus.getText().toString();
        if (!bookStatus.equalsIgnoreCase("Available")) {
            AlertDisplay.showMaterialDialog(mamaYao, babaYao, new ArrayList<>(), "Already issued book", "This book is already issued. Cant process issue request");
        } else {
            JFXButton yesButton = new JFXButton("YES, Issued");
            JFXButton noButton = new JFXButton("NO, Cancel");
            noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                JFXButton button = new JFXButton("That's Okay");
                AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(button), "Issue Cancelled", null);
                clearDetails();
            });
            yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {

                Database database = new Database();
                boolean jeff = database.issueBook(bookId, studentId);
                if (jeff == true) {
                    Database d = new Database();
                    boolean japhee = d.updateBook(bookId, "IssueD");
                    if (japhee == true) {
                        JFXButton button = new JFXButton("Done!");
                        AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(button), "Complete", "Book Issue Complete");
                        clearDetails();
                    } else {

                        JFXButton button = new JFXButton("Retry");
                        AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(button), "Can't Issue Book", "Error Issuing Book, please try again...!!");
                        clearDetails();
                    }
                } else {
                    JFXButton button = new JFXButton("Retry");
                    AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(button), "Can't Issue Book", "Error Issuing Book, please try again...!!");
                    clearDetails();
                }
            });

            AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(yesButton, noButton), "Confirm Book Issue",
                    String.format("Are you sure want to issue the book '%s' to '%s' ?", bookName.getText(), studentName.getText()));
        }

    }

    private void clearDetails() {
        bookIDInput.setText("");
        studeesearch.setText("");
        bookName.setText("");
        bookAuthor.setText("");
        Bookstatus.setText("");
        studentName.setText("");
        studentNumber.setText("");
    }

    @FXML
    private void userProfileHere(ActionEvent event) {
        book = bookSearchNumber.getText().toString();
        if (book.isEmpty()) {
            bookSearchNumber.getStyleClass().add("wrong-credentials");
        } else {

            String sql = "select * from issuelist where booknumber = '" + book + "' ";
            Boolean flag = false;
            try {
                Class.forName(connection_driver);
                conn = DriverManager.getConnection(connection_url, "root", "");
                statement = (Statement) conn.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String studentnumber = resultSet.getString("studentnumber");

                    Timestamp mIssueTime = resultSet.getTimestamp("issuedtime");
                    Date dateOfIssue = new Date(mIssueTime.getTime());
                    datehereIssued.setText("" + dateOfIssue);
                    Long timeElapsed = System.currentTimeMillis() - mIssueTime.getTime();
                    Long days = TimeUnit.DAYS.convert(timeElapsed, TimeUnit.MILLISECONDS) + 1;
                    String daysElapsed = String.format("Used %d days", days);
                    usedFor.setText(daysElapsed);

                    searchStudent(studentnumber, book);
                    flag = true;
                }
                if (!flag) {

                    JFXButton button = new JFXButton("Okay.I'll Check");
                    AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(button), "No such Book Exists in Issue Database", null);
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                        bookSearchNumber.setText("");
                        bookSearchNumber.requestFocus();
                    });
                } else {
                    renewBook.requestFocus();

                    userProfileHere.setVisible(true);
                    bookDetailsIssues.setVisible(true);
                    daysIssued.setVisible(true);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
//            

        }

    }

    @FXML
    private void renewBook(ActionEvent event) {
        book = bookSearchNumber.getText().toString();

        JFXButton yesButton = new JFXButton("YES");
        JFXButton noButton = new JFXButton("NO");
        noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                JFXButton button = new JFXButton("That's Okay");
                AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(button), "Cancelled", null);
                clearDetails();
            });
        yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
            Database database=new Database();
            boolean jeff=database.returnBook(book,"Available");
            if(jeff==true){
                JFXButton button = new JFXButton("Fine!");
                AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(button), "Done ", "Book has been submitted");
//                clearDetails();
            }
            });
        

        AlertDisplay.showMaterialDialog(mamaYao, babaYao, Arrays.asList(yesButton, noButton), "Confirm",
                String.format("Are you sure want to return the book '%s' from '%s' ?", titleofBook.getText(), jinayastudee.getText()));
    }

    private void searchStudent(String studentnumber, String book) {

        String sql = "select * from students where number = '" + studentnumber + "' ";
        Boolean flag = false;
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, "root", "");
            statement = (Statement) conn.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                jinayastudee.setText(name);
                simuyastudee.setText(phone);
                emailyastudee.setText(email);
                searchBook(book);
                flag = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void searchBook(String book) {

        String sql = "select * from books where number = '" + book + "' ";
        Boolean flag = false;
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, "root", "");
            statement = (Statement) conn.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");

                titleofBook.setText(title);
                authorofBook.setText(author);
                publisherofBook.setText(publisher);

                flag = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void loadSettingsNow(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/settings/userSettings.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Settings");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {

        }
    }

    @FXML
    private void loadAbout(ActionEvent event) {
        AlertDisplay.showTrayMessage(String.format("Hello %s!", System.getProperty("user.name")), "Thanks for using product from Imeja Developers");
    }

    @FXML
    private void openNotifications(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Notification/notifications.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Settings");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException ex) {

        }
    }

}
