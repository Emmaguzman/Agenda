/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectar;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Emmanuel
 */
public class Singleton_SQLServer {

    private static Connection conn = null;

    public static Connection getConnection() {
        String url = "jdbc:sqlserver://NtBEmm4:1433;databaseName=Agenda";
        String usuario = "Emma";
        String pass = "1234";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            if (conn == null) {
                conn = DriverManager.getConnection(url, usuario, pass);
            }

        } catch (Exception e ) {
            JOptionPane.showMessageDialog(null,"ERROR"+e.getMessage()+"");
            e.printStackTrace();
        }

        return conn;
    }
   

}
 
 
  
    

