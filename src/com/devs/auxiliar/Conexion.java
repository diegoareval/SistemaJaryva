
package com.devs.auxiliar;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

        
/**
 *
 * @author DEVS
 */
public class Conexion {
   
    private static Connection conn;
    
      public static Connection getConnection(){
          try 
          { 
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcpeventosbd", "root", "");
            
              
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
              
          }
        return conn;
      }
              
      
    
}
