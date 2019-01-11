/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Keeprawteach
 */
public class Database {

    Connection conn;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    ArrayList<String> arrayList = new ArrayList<>();
    String name, type, status, room, description;
    String connection_driver = "org.gjt.mm.mysql.Driver";
    String connection_url = "jdbc:mysql://localhost/school";
    String username = "root";
    String password = "";

    public void checkConnection() {

    }

    public boolean addBook(String Book_No, String Book_Title, String Book_Author, String Book_Publisher, String par) {

        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, username, password);

            String query = " insert into books (number, title, author, publisher, status)"
                    + " values (?, ?, ?, ?, ?)";
            preparedStatement = (PreparedStatement) conn.prepareStatement(query);
            preparedStatement.setString(1, Book_No);
            preparedStatement.setString(2, Book_Title);
            preparedStatement.setString(3, Book_Author);
            preparedStatement.setString(4, Book_Publisher);
            preparedStatement.setString(5, par);
            preparedStatement.execute();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addStudent(String Student_Number, String Student_Name, String Student_Phone, String Student_Email) {

        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, username, password);

            String query = " insert into students (number, name, phone, email)"
                    + " values (?, ?, ?, ?)";
            preparedStatement = (PreparedStatement) conn.prepareStatement(query);
            preparedStatement.setString(1, Student_Number);
            preparedStatement.setString(2, Student_Name);
            preparedStatement.setString(3, Student_Phone);
            preparedStatement.setString(4, Student_Email);
            preparedStatement.execute();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean issueBook(String bookId, String studentId) {
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, username, password);

            String query = "insert into issuelist (booknumber, studentnumber)"
                    + " values (?, ?)";
            preparedStatement = (PreparedStatement) conn.prepareStatement(query);
            preparedStatement.setString(1, bookId);
            preparedStatement.setString(2, studentId);
            preparedStatement.execute();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBook(String bookId, String sues) {
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, username, password);
            String query = "update books set status=? where number=?";
            preparedStatement = (PreparedStatement) conn.prepareStatement(query);
            preparedStatement.setString(1, sues);
            preparedStatement.setString(2, bookId);
            preparedStatement.execute();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnBook(String book, String available) {
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, username, password);
            String query = "update books set status=? where number=?";
            preparedStatement = (PreparedStatement) conn.prepareStatement(query);
            preparedStatement.setString(1, available);
            preparedStatement.setString(2, book);
            preparedStatement.execute();
            conn.close();
            return removeBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean removeBook(String book) {
        try {
            Class.forName(connection_driver);
            conn = DriverManager.getConnection(connection_url, username, password);
            String query = "delete from issuelist  where booknumber=?";
            preparedStatement = (PreparedStatement) conn.prepareStatement(query);
            preparedStatement.setString(1, book);
            preparedStatement.execute();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
