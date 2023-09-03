/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miprimeraaplicacion;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jalav
 */
public class accesoBD {

    private final String host;
    private final String user;
    private final String password;
    private final String BD;

    private Connection conexion;

    public accesoBD(String host, String user, String password, String BD) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.BD = BD;
    }

    public Connection getConexion() {
        return conexion;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getBD() {
        return BD;
    }

    public boolean comprobar() {
        if (conexion == null) {
            return false;
        } else {
            return true;
        }
    }

    public void conectabd() {
        try {
            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            String cadena = "jdbc:mysql://" + getHost() + "/" + getBD();
            conexion = DriverManager.getConnection(cadena, getUser(), getPassword());
            JOptionPane.showMessageDialog(null, "Se ha accedido a la base de datos con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos " + e.toString());
        }
    }

    //CONEXION BD SIN MENSAJE
    public void conectabdcl() {
        try {
            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            String cadena = "jdbc:mysql://" + getHost() + "/" + getBD();
            conexion = DriverManager.getConnection(cadena, getUser(), getPassword());

        } catch (Exception e) {

        }
    }

    public void actualizarbd(String sql) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos " + e);
        }
    }

    ///////////////////////////////PARA CLIENTE///////////////////////////////////////////////////
    public int autoincremte() throws SQLException {
        ResultSet rs;
        int auto = 0;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Selecmax(cod_cli) as num from cliente");
        if (rs.next()) {
            auto = rs.getInt(1) + 1;
        } else {
            auto = 1;
        }
        return auto;
    }

    public void actualizarcl(String ced, String nom, String ape, String direc, String ciud) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate("INSERT INTO `cliente` (`cedula`, `nombre`, `apellido`, `ciudad`, `direccion`) VALUES ('" + ced + "', '" + nom + "', '" + ape + "', '" + ciud + "', '" + direc + "')");
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int clientcod(String a) throws SQLException {
        ResultSet rs;
        int b = 0;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Select cod_cli from cliente where cedula='" + a + "'");
        while (rs.next()) {
            b = rs.getInt(1);
        }
        return b;
    }

    public void actualizarcla(String busc, String ced, String nom, String ape, String direc, String ciud) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate("update cliente SET `cedula` = '" + ced + "', `nombre` = '" + nom + "', `apellido` = '" + ape + "', `ciudad` = '" + ciud + "', `direccion` = '" + direc + "' WHERE (`cod_cli` = '" + clientcod(busc) + "')");
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public void eliminarcl(String ced) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate("Delete from cliente where(cedula='" + ced + "')");
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public ResultSet mostrarbdbc(String a) throws SQLException {
        ResultSet rs;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Select * from cliente where cedula='" + a + "'");
        return rs;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////PARA DISTRIBUIDOR/////////////////////////////////////////////////////////////////////
    public void actualizardis(String ced, String nom, String ciud) throws SQLException {
        try {

            Statement stm = conexion.createStatement();
            stm.executeUpdate("INSERT INTO `distribuidor` (`ruc`, `nombre`, `ciudad`) VALUES ('" + ced + "', '" + nom + "', '" + ciud + "')");
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int discod(String a) throws SQLException {
        ResultSet rs;
        int b = 0;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Select cod_dis from distribuidor where ruc='" + a + "'");
        while (rs.next()) {
            b = rs.getInt(1);
        }
        return b;
    }

    public void actualizardila(String busc, String ced, String nom, String ciud) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate("UPDATE `distribuidor` SET `ruc` = '" + ced + "', `nombre` = '" + nom + "', `ciudad` = '" + ciud + "' WHERE (cod_dis  = '" + discod(busc) + "')");
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public void eliminardis(String ced) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate("Delete from distribuidor where(ruc='" + ced + "')");
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public ResultSet mostrarbddis(String a) throws SQLException {
        ResultSet rs;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Select * from distribuidor where ruc='" + a + "'");
        return rs;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void actualizarpro(String cod, String des, int pre, int stock) throws SQLException {
        try {

            Statement stm = conexion.createStatement();
            stm.executeUpdate("INSERT INTO `producto` (`cod_pro`, `descripcion`, `precio`, `stock`) VALUES ('" + cod + "', '" + des + "', '" + pre + "', '" + stock + "')");
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int procod(String a) throws SQLException {
        ResultSet rs;
        int b = 0;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Select id_pro from producto where cod_pro='" + a + "'");
        while (rs.next()) {
            b = rs.getInt(1);
        }
        return b;
    }

    public void actualizarprodi(String busc, String cod, String des, int pre, int stock) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate("UPDATE `producto` SET `cod_pro` = '" + cod + "', `nombre` = '" + des + "', `ciudad` = '" + pre + "', `stock` = '" + stock + "' WHERE ( id_pro  = '" + procod(busc) + "')");
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public void eliminarpro(String cod) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate("Delete from producto where(cod_pro='" + cod + "')");
            JOptionPane.showMessageDialog(null, "Base de datos actualizada exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public ResultSet mostrarbdpro(String a) throws SQLException {
        ResultSet rs;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Select * from producto where cod_pro='" + a + "'");
        return rs;
    }

    public ResultSet codis() throws SQLException {
        ResultSet rs;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("select distribuidor.nombre from distribuidor join producto on distribuidor.cod_dis;");
        return rs;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ResultSet mostrarfac(String a, String sql, String c) throws SQLException {
        ResultSet rs;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Select " + a + " from " + sql + " " + c);
        return rs;
    }

    public void restarpro(String busc) throws SQLException {
        ResultSet rs;
        float a = 0;
        rs = this.mostrarbdpro(busc);
        try {
            while (rs.next()) {
                a = rs.getFloat("stock") - 1;
            }
            Statement stm = conexion.createStatement();
            stm.executeUpdate("update producto set stock =" + a + "where cod_pro='" + busc + "'");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
    public void sumapro(String busc, int cant) throws SQLException {
        ResultSet rs;
        float a = 0;
        rs = this.mostrarbdpro(busc);
        try {
            while (rs.next()) {
                a = rs.getFloat("stock") + cant;
            }
            Statement stm = conexion.createStatement();
            stm.executeUpdate("update producto set stock =" + a + "where cod_pro='" + busc + "'");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    public void factura(int cli, int pro, double total) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate("insert into factura(cod_cli, id_pro, total) values ('"+cli+"', '"+pro+"', '"+total+"') ");
            JOptionPane.showMessageDialog(null, "Factura guardada exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la factura " + e);
        }
    }
    
     public void facturaconp(int cli, int pro, double total) throws SQLException {
        try {
            Statement stm = conexion.createStatement();
            stm.executeUpdate("insert into compra(cod_dis, id_pro, total) values ('"+cli+"', '"+pro+"', '"+total+"') ");
            JOptionPane.showMessageDialog(null, "Compra guardada exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la factura " + e);
        }
    }

    public ResultSet mostrarbd(String sql) throws SQLException {
        ResultSet rs;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Select * from " + sql);
        return rs;
    }
     public ResultSet mostrar(String sql) throws SQLException {
        ResultSet rs;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery(sql);
        return rs;
    }

    public void cierraBD() throws SQLException {
        conexion.close();
        JOptionPane.showMessageDialog(null, "Base de datos cerrada con exito");
    }

    public boolean existe(String busc) throws SQLException {
        ResultSet rs;
        boolean bs = false;
        Statement stm = conexion.createStatement();
        rs = stm.executeQuery("Select ");
        return false;
    }
}
