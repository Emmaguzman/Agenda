/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conectar;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Emmanuel
 */
public class Singelton_JDBC {
    
   private static Connection conn= null;
   
   public static Connection getConnection(){
        String url = "jdbc:derby://localhost:1527/Agenda";
        String usuario ="TP";
        String pass = "agenda";
        try {
          
            
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
