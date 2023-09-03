/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miprimeraaplicacion;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author jalav
 */
public class MiprimeraAplicacion {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        interfaz pst = new interfaz();
        pst.setVisible(true);
        //bdconectada bd = new bdconectada();
        //bd.setVisible(true);
        /*ResultSet rs;
        accesoBD bd = new accesoBD("localhost", "root", "unpollito", "caucho");
        bd.conectabd();
        bd.actualizarbd("insert into cliente values('1350189691', 'Julio', 'Alava', 'Portoviejo', 'Los Olivos', 'Masculino')");
        
       
        //bd.conectabd();*/
        

    }

}
