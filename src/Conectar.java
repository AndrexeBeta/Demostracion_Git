
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import javax.swing.*;
/**
/**
 *
 * @author Basan
 */
public class Conectar {
    Connection conect =null;
/* se declara una variable de tipo Connetion y 
    se inicia en null*/
    
    public Connection conexion(){
      /*  Se define un metodoPublico llamado conexion
         que devolvera un objeto de tipoConnection*/   
      try{
      /* se inicia un bloque try para manejar posibles E
          excepciones que puedan ocurrir al conectarse con la 
          base de datos
          */
          Class.forName("org.gjt.mm.mysql.Driver");
           conect = DriverManager.getConnection("jdbc:mysql://localhost/inverntario2","root","");

      }catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error" +e);
      }
        return conect;
    }
    
    
    
    
}
