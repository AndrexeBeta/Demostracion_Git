
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
public class Clientes {
    int       IdCliente;
    String    NombreCliente;
    String    ApellidoCliente;
    String    CorreoCliente;
    String    CelularCliente;

    public Clientes() {
      IdCliente = 0;
      NombreCliente = "";
      ApellidoCliente = "";
      CorreoCliente = "";
      CelularCliente = "";
    }

    public void setIdCliente(int ValIdCliente) {
        IdCliente = ValIdCliente;
    }

    public void setNombreCliente(String ValNombreCliente) {
        NombreCliente = ValNombreCliente;
    }

    public void setApellidoCliente(String ValApellidoCliente) {
        ApellidoCliente = ValApellidoCliente;
    }

    public void setCorreoCliente(String ValCorreoCliente) {
        CorreoCliente = ValCorreoCliente;
    }

    public void setCelularCliente(String ValCelularCliente) {
        CelularCliente = ValCelularCliente;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public String getApellidoCliente() {
        return ApellidoCliente;
    }

    public String getCorreoCliente() {
        return CorreoCliente;
    }

    public String getCelularCliente() {
        return CelularCliente;
    }

    public void setInsertarClientes(int valIdCliente, String valNombre, String valApellido, String valCorreo, String valCelular) throws SQLException{
        Conectar conectar1 = new Conectar();
        Connection connection1 = conectar1.conexion();
        String sql = "";
        sql = "INSERT INTO clientes "
                + "(idclientes ,"
                + " NombreCliente, "
                + "ApellidoCliente, "
                + "CorreoCliente, "
                + "CelularCliente) "
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = connection1.prepareStatement(sql);
                pst.setString(1, Integer.toString(valIdCliente));
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
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    public void setActualizarCliente(int valorId, String valorNombre,String valorApellido, String valorCorreo, String valorCelular){
        Conectar cn = new Conectar();
        Connection con = cn.conexion(); 
       
        String sql = "UPDATE clientes "+
                "SET NombreCliente = ?,"+
                "ApellidoCliente = ?, "+
                "CorreoCliente = ?, "+
                "CelularCliente = ? "+
                "WHERE idclientes= ?";
       
        
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, valorNombre);
            pst.setString(2, valorApellido);
            pst.setString(3, valorCorreo);
            pst.setString(4, valorCelular);
            pst.setString(5, Integer.toString(valorId));
            int n = pst.executeUpdate();
            pst.close();
            if(n > 0){
            JOptionPane.showMessageDialog(null, "Actualización Exitosa");}
        } catch (SQLException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setEliminarCliente(int valorId){
        Conectar cn = new Conectar();
        Connection con = cn.conexion();
        String sql = "DELETE FROM clientes WHERE idclientes= ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(valorId));
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
   
    
    
    
   
}
