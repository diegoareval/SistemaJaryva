package com.devs.auxiliar;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author RojeruSan
 */

public class ConexionBD {

    Connection conect = null;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost/hcpeventosbd", "root", ""); 
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión" + e);
        }
        return conect;
    }     
}
