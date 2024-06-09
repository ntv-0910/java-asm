package com.cuongdz.javaasm.Model;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Cuongdz
 */
public class MySQLIO {

    private static String DB_URL = "jdbc:mysql://localhost:3306/java_asm";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";

    // Constructor
    public MySQLIO() {

    }

    public MySQLIO(String url, String usn, String pwd) {
        DB_URL = url;
        USER_NAME = usn;
        PASSWORD = pwd;
    }

    // Method
    protected static void showExceptionDialog(Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString(), "Database Communication Error", 0);
    }

    private static Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        // Try connecting to MySQL server
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            // Create connection
            conn = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception ex) {
            showExceptionDialog(ex);

        }
        return conn;
    }

    protected static int execute(String s) {
        int numberRowsAffected = 0;
        Connection conn = null;
        try {
            conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // Cancel if there are no connection
            if (conn == null) {
                return 0;
            }
            // Create statement 
            Statement st = conn.createStatement();
            // Execute statement on MySQL server but did not return records
            numberRowsAffected = st.executeUpdate(s);
            conn.close();

        } catch (SQLException ex) {
            showExceptionDialog(ex);
        }
        return numberRowsAffected;
    }

    protected static ResultSet query(String s) {
        ResultSet rs = null;
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // Cancel if there are no connection
            if (conn == null) {
                return null;
            }
            // Create statement 
            Statement st = conn.createStatement();
            // Execute statement on MySQL server but return records
            rs = st.executeQuery(s);
            
        } catch (Exception ex) {
            showExceptionDialog(ex);
        }
        return rs;
    }
}
