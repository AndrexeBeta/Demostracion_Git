
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;;

/**
 *
 * @author Luis E Muñoz
 */
public class NuevoCliente extends javax.swing.JFrame {
        int ExisteCliente;
         DefaultTableModel model;
         
    
   public NuevoCliente() {
        initComponents();
        setResizable(false);
        setLocation(0,0);
        setTitle("Nuevo Cliente");
         jButtonEliminarCliente.setEnabled(false);
         jButtonActualizar.setEnabled(false);
        
        setBuscar("");
        jButtonGuardar.setEnabled(false);
        
        
       
     
    }
    
    /// limpiar campos de clliente//
   
    
    //Se obtiene la informacion del campo cliente//
    public void guardarCampo(){
         ExisteCliente=0;
         //Defino las variables para regristar la informacion,me haga la consulta en la base de datos y 
         String []regristro=new String[1];
         String valor=jTextFieldIdCliente.getText();
         String sql=  "SELECT * FROM clientes where idclientes  LIKE'%"+valor+"%'";
         
         // se crea un objeto  con el cual se administra la conexion con la base de datos
         Conectar Conectar1 = new Conectar();
         Connection Connection1= Conectar1.conexion();
         //
          try {
        Statement st = Connection1.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            ExisteCliente = 1;
        }
      
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        if (ExisteCliente == 1) {
            JOptionPane.showMessageDialog(null,"El Cliente ya existe");
            jTextFieldIdCliente.requestFocus();
            jTextFieldIdCliente.setText("");
        }else {
           jButtonGuardar.setEnabled(true); 
            
        }    
  
    }
      
    
    public void setBuscar (String valor){
       String [] encabezados = {"Id","Nombre","Apellido","Correo","Celular"};
       String [] registros = new String[5];
       
       String sql = "SELECT * FROM clientes where CONCAT(idclientes ,'',NombreCliente,'',ApellidoCliente) LIKE'%"+valor+"%'";
       model = new DefaultTableModel(null,encabezados);
       Conectar conectar1 = new Conectar();
       Connection Connection1 = conectar1.conexion();
       
        try {
        Statement st = Connection1.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            registros [0]=rs.getString("idclientes");
            registros [1]=rs.getString("NombreCliente");
            registros [2]=rs.getString("ApellidoCliente");
            registros [3]=rs.getString("CorreoCliente");
            registros [4]=rs.getString("CelularCliente");
            model.addRow(registros);
        }
        jTableVistaClientes.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }jTextFieldIdCliente.setEnabled(false);
       
    }
    
    
    public void vaciarCampo(){
        jTextFieldIdCliente.setEnabled(true);
        jTextFieldIdCliente.setText("");
            jTextFieldNombre.setText("");
            jTextFieldApellido.setText("");
            jTextFieldCorreoCliente.setText("");
            jTextFieldCelularCliente.setText("");
            jTextFieldIdCliente.requestFocus();
    
    }
    
     
       
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelbaseCliente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVistaClientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButtonNCliente = new javax.swing.JButton();
        jButtonEliminarCliente = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabelIdCliente = new javax.swing.JLabel();
        jLabelNombreCliente = new javax.swing.JLabel();
        jLabelApellidoCliente = new javax.swing.JLabel();
        jTextFieldIdCliente = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldApellido = new javax.swing.JTextField();
        jLabelCorreoCliente = new javax.swing.JLabel();
        jTextFieldCorreoCliente = new javax.swing.JTextField();
        jTextFieldCelularCliente = new javax.swing.JTextField();
        jLabelCorreoCliente1 = new javax.swing.JLabel();
        jButtonGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1500, 980));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(null);

        jPanel1.setBackground(java.awt.SystemColor.control);
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1496, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1500, 110);

        jPanelbaseCliente.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Clientes");

        javax.swing.GroupLayout jPanelbaseClienteLayout = new javax.swing.GroupLayout(jPanelbaseCliente);
        jPanelbaseCliente.setLayout(jPanelbaseClienteLayout);
        jPanelbaseClienteLayout.setHorizontalGroup(
            jPanelbaseClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelbaseClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1380, Short.MAX_VALUE))
        );
        jPanelbaseClienteLayout.setVerticalGroup(
            jPanelbaseClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelbaseCliente);
        jPanelbaseCliente.setBounds(0, 220, 1500, 20);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setText("Administrador de Clientes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(474, 474, 474)
                .addComponent(jLabel2)
                .addContainerGap(661, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 240, 1500, 50);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTextFieldBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextFieldBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldBuscarMouseClicked(evt);
            }
        });
        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });
        jTextFieldBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarKeyReleased(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jButton1MouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscar)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jTableVistaClientes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableVistaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableVistaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTableVistaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableVistaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVistaClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableVistaClientes);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 290, 369, 630);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jButtonNCliente.setText("Nuevo Cliente");
        jButtonNCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonNCliente.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButtonNCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNClienteActionPerformed(evt);
            }
        });

        jButtonEliminarCliente.setText("Eliminar");
        jButtonEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarClienteActionPerformed(evt);
            }
        });

        jButtonActualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });
        jButtonActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonActualizarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonActualizar)
                .addContainerGap(750, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNCliente)
                    .addComponent(jButtonEliminarCliente)
                    .addComponent(jButtonActualizar))
                .addContainerGap())
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(400, 300, 1080, 40);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelIdCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelIdCliente.setText("Id ");

        jLabelNombreCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelNombreCliente.setText("Nombre");

        jLabelApellidoCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelApellidoCliente.setText("Apellido");

        jTextFieldIdCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIdClienteFocusLost(evt);
            }
        });
        jTextFieldIdCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdClienteActionPerformed(evt);
            }
        });

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jTextFieldApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldApellidoActionPerformed(evt);
            }
        });

        jLabelCorreoCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCorreoCliente.setText("Correo");

        jTextFieldCorreoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCorreoClienteActionPerformed(evt);
            }
        });

        jTextFieldCelularCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCelularClienteActionPerformed(evt);
            }
        });

        jLabelCorreoCliente1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCorreoCliente1.setText("Celular");

        jButtonGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonGuardar.setText("Agregar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jButtonGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonGuardarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextFieldCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabelCorreoCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(675, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdCliente)
                    .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombreCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelApellidoCliente)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCorreoCliente)
                    .addComponent(jTextFieldCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCorreoCliente1)
                    .addComponent(jTextFieldCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jButtonGuardar)
                .addContainerGap(299, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6);
        jPanel6.setBounds(400, 350, 1080, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jTextFieldBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscarMouseClicked
      
    }//GEN-LAST:event_jTextFieldBuscarMouseClicked

    private void jButtonNClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNClienteActionPerformed
    vaciarCampo();
    jButtonEliminarCliente.setEnabled(false);
         jButtonActualizar.setEnabled(false);
   
    }//GEN-LAST:event_jButtonNClienteActionPerformed

    private void jTextFieldCelularClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCelularClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCelularClienteActionPerformed

    private void jTextFieldCorreoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCorreoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCorreoClienteActionPerformed

    private void jTextFieldApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldApellidoActionPerformed

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jTextFieldIdClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdClienteActionPerformed
       
    }//GEN-LAST:event_jTextFieldIdClienteActionPerformed

    private void jTextFieldIdClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIdClienteFocusLost
        if (jTextFieldIdCliente.getText().equals("")) {
            
        } else {
           guardarCampo();            
        }
    }//GEN-LAST:event_jTextFieldIdClienteFocusLost

    private void jTableVistaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVistaClientesMouseClicked
        jButtonEliminarCliente.setEnabled(true);
        jButtonActualizar.setEnabled(true);
        jTextFieldIdCliente.setEnabled(false);
        
        DefaultTableModel model = (DefaultTableModel) jTableVistaClientes.getModel();
        jTextFieldIdCliente.setText(model.getValueAt(jTableVistaClientes.getSelectedRow(), 0).toString());
        jTextFieldNombre.setText(model.getValueAt(jTableVistaClientes.getSelectedRow(),1).toString());
        jTextFieldApellido.setText(model.getValueAt(jTableVistaClientes.getSelectedRow(),2).toString());
        jTextFieldCorreoCliente.setText(model.getValueAt(jTableVistaClientes.getSelectedRow(),3).toString());
        jTextFieldCelularCliente.setText(model.getValueAt(jTableVistaClientes.getSelectedRow(),4).toString());
    }//GEN-LAST:event_jTableVistaClientesMouseClicked

    private void jButton1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jButton1MouseWheelMoved
       
    }//GEN-LAST:event_jButton1MouseWheelMoved

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        guardarCampo();
        if(ExisteCliente == 0){
            try {
               
                Clientes Clientes1 = new Clientes();
                Clientes1 .setIdCliente(Integer.parseInt(jTextFieldIdCliente.getText()));
                Clientes1.setNombreCliente(jTextFieldNombre.getText());
                Clientes1.setApellidoCliente(jTextFieldApellido.getText());
                Clientes1.setCorreoCliente(jTextFieldCorreoCliente.getText());
                Clientes1.setCelularCliente(jTextFieldCelularCliente.getText());
                Clientes1.setInsertarClientes(Clientes1.getIdCliente(), Clientes1.getNombreCliente(),
                    Clientes1.getApellidoCliente(),Clientes1.getCorreoCliente(),Clientes1.getCelularCliente());
            } catch (SQLException ex) {
                Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            vaciarCampo();
            jButtonGuardar.setEnabled(false);
            setBuscar("");
            
            
        }

    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonGuardarKeyPressed
     
                                           
       

        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarKeyPressed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        // TODO add your handling code here:
       Clientes c = new Clientes();
        c.setIdCliente(Integer.parseInt(jTextFieldIdCliente.getText()));
        c.setNombreCliente(jTextFieldNombre.getText());
        c.setApellidoCliente(jTextFieldApellido.getText());
        c.setCorreoCliente(jTextFieldCorreoCliente.getText());
        c.setCelularCliente(jTextFieldCelularCliente.getText());
        c.setActualizarCliente(c.getIdCliente(), c.getNombreCliente(), c.getApellidoCliente(), c.getCorreoCliente(),c.getCelularCliente());
        setBuscar("");
        vaciarCampo();

        
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonActualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonActualizarKeyPressed
        Clientes c = new Clientes();
        c.setIdCliente(Integer.parseInt(jTextFieldIdCliente.getText()));
        c.setNombreCliente(jTextFieldNombre.getText());
        c.setApellidoCliente(jTextFieldApellido.getText());
        c.setCorreoCliente(jTextFieldCorreoCliente.getText());
        c.setCelularCliente(jTextFieldCelularCliente.getText());
        c.setActualizarCliente(c.getIdCliente(), c.getNombreCliente(), c.getApellidoCliente(), c.getCorreoCliente(),c.getCelularCliente());
        setBuscar("");

        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonActualizarKeyPressed

    private void jButtonEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarClienteActionPerformed
        if (jTableVistaClientes.getSelectedRow()>-1){
            int x = JOptionPane.showConfirmDialog(null, "¿Desea elimiar la información?");
            if (x == JOptionPane.YES_OPTION){
                Clientes c = new Clientes();
                String m = model.getValueAt(jTableVistaClientes.getSelectedRow(), 0).toString();
                c.setIdCliente(Integer.parseInt(m));
                c.setEliminarCliente(c.getIdCliente());
                JOptionPane.showMessageDialog(null,"Registro Eliminado con Éxito" );
                setBuscar("");
                jTextFieldBuscar.requestFocus();
                vaciarCampo();
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un Cliente para eliminar");
           
        }
    }//GEN-LAST:event_jButtonEliminarClienteActionPerformed

    private void jTextFieldBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarKeyReleased
       setBuscar(jTextFieldBuscar.getText());
    }//GEN-LAST:event_jTextFieldBuscarKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IntefaseVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IntefaseVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IntefaseVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IntefaseVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 new NuevoCliente().setVisible(true);
                  
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonEliminarCliente;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelApellidoCliente;
    private javax.swing.JLabel jLabelCorreoCliente;
    private javax.swing.JLabel jLabelCorreoCliente1;
    private javax.swing.JLabel jLabelIdCliente;
    private javax.swing.JLabel jLabelNombreCliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelbaseCliente;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableVistaClientes;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldBuscar;
    private javax.swing.JTextField jTextFieldCelularCliente;
    private javax.swing.JTextField jTextFieldCorreoCliente;
    private javax.swing.JTextField jTextFieldIdCliente;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

    private void setBuscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
