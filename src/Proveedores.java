
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Basan
 */
public class Proveedores {
    int       IdProveedor;
    String    Nombre;
    String    Apellido;
    String    Correo;
    String    Celular;

    public Proveedores() {
      IdProveedor = 0;
      Nombre = "";
      Apellido = "";
      Correo = "";
      Celular = "";
    }

    public void setIdProveedor(int ValIdProveedor) {
        IdProveedor = ValIdProveedor;
    }

    public void setNombre(String ValNombre) {
        Nombre = ValNombre;
    }

    public void setApellido(String ValApellido) {
        Apellido = ValApellido;
    }

    public void setCorreo(String ValCorreo) {
        Correo = ValCorreo;
    }

    public void setCelular(String ValCelular) {
        Celular = ValCelular;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getCelular() {
        return Celular;
    }

    

    public void setInsertarProvedores(int valIProveedor, String valNombre, String valApellido, String valCorreo, String valCelular) throws SQLException{
        Conectar conectar1 = new Conectar();
        Connection connection1 = conectar1.conexion();
        String sql = "";
        sql = "INSERT INTO proveedores "
                + "(IdProveedor,"
                + " NombreProveedor, "
                + "ApellidoProveedor, "
                + "CorreoProveedor, "
                + "CelularProveedor) "
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = connection1.prepareStatement(sql);
                pst.setString(1, Integer.toString(valIProveedor));
                pst.setString(2, valNombre);
                pst.setString(3, valApellido);
                pst.setString(4, valCorreo);
                pst.setString(5, valCelular);
                int n = pst.executeUpdate();
                pst.close();
        if (n>0)
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
        }
        catch (SQLException ex) {
            Logger.getLogger(NuevoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    public void setActualizarProveedor(int valorId, String valorNombre,String valorApellido, String valorCorreo, String valorCelular){
        Conectar cn = new Conectar();
        Connection con = cn.conexion(); 
       
        String sql = "UPDATE proveedores "+
                "SET NombreProveedor = ?,"+
                "ApellidoProveedor = ?, "+
                "CorreoProveedor = ?, "+
                "CelularProveedor = ? "+
                "WHERE IdProveedor = ?";
       
        
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, valorNombre);
            pst.setString(2, valorApellido);
            pst.setString(3, valorCorreo);
            pst.setString(4, valorCelular);
            pst.setString(5, Integer.toString(valorId));
            int n = pst.executeUpdate();
            pst.close();
            if(n > 0)
            JOptionPane.showMessageDialog(null, "Actualización Exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(NuevoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setEliminarProveedor(int valorId){
        Conectar cn = new Conectar();
        Connection con = cn.conexion();
        String sql = "DELETE FROM proveedores WHERE IdProveedor = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(valorId));
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(NuevoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
   
    
    
    
   
}
