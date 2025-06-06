

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


public class Productos {
    int IdProducto;
    String Detalle;
    String Clave;
    String CodigoBarras;
    
     
    public Productos (){
    IdProducto = 0;
    Detalle = "";
    Clave = "";
    CodigoBarras = "";
     
    }
            
    public void setIdProducto (int valorIdProducto){
        IdProducto = valorIdProducto;
    }
    public void setDetalle(String valorDetalle){
        Detalle = valorDetalle;
    }
    public void setClave(String valorClave){
        Clave = valorClave;
    }
    public void setCodigoBarras(String valorCodigoBarras){
        CodigoBarras = valorCodigoBarras;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public String getDetalle() {
        return Detalle;
    }

    public String getClave() {
        return Clave;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }
   
    
    public void setInsertarProducto(int valIdProducto, String valDetalle, String valClave, String valCodigoBarras) throws SQLException{
        Conectar conectar1 = new Conectar();
        Connection connection1 = conectar1.conexion();
        String sql = "";
        sql = "INSERT INTO productos (idProductos , DescripcionProducto, ClaveProducto, CodigoBarras) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = connection1.prepareStatement(sql);
                pst.setString(1, Integer.toString(valIdProducto));
                pst.setString(2, valDetalle);
                pst.setString(3, valClave);
                pst.setString(4, valCodigoBarras);
                
                int n = pst.executeUpdate();
                pst.close();
        if (n>0)
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
        }
        catch (SQLException ex) {
            Logger.getLogger(NuevoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void setActualizarProducto(int valIdProducto, String valDetalle, String valClave, String valCodigoBarras){
        Conectar cn = new Conectar();
        Connection con = cn.conexion(); 
       
        String sql = "UPDATE productos "+
                "SET DescripcionProducto = ?,"+
                "ClaveProducto = ?, "+
                "CodigoBarras = ? "+
               
                "WHERE idProductos  = ?";
       
        
        try {
            PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setString(1, valDetalle);
                pst.setString(2, valClave);
                pst.setString(3, valCodigoBarras);
                pst.setString(4, Integer.toString(valIdProducto));
            int n = pst.executeUpdate();
            pst.close();
            if(n > 0)
            JOptionPane.showMessageDialog(null, "Actualización Exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(NuevoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setEliminarProveedor(int valorId){
        Conectar cn = new Conectar();
        Connection con = cn.conexion();
        String sql = "DELETE FROM productos WHERE idProductos  = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(valorId));
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(NuevoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
}
