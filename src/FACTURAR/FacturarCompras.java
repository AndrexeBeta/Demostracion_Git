package FACTURAR;


import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FacturarCompras extends javax.swing.JFrame {

    public static int banderaVenta;
    public static String valor;
    public static int banderaProveedorVenta;
    Double subTotal, Grantotal, SubTotalModificar;

    public FacturarCompras() {
        initComponents();
        getContentPane().setBackground(Color.white);
        setTitle("Factura de Compra");
        setResizable(false);
        setLocationRelativeTo(null);
        limpiar();
        bloquear();
        jTextFieldCodigoBarras.setEnabled(false);
        jButtonBuscarProducto.setEnabled(false);
        //setCargarClientes();
        setCargarFecha();
        setCargarNumeroFactura();
        jTextFieldIdProveedor.requestFocus();
        subTotal = 0.0;
        Grantotal = 0.0;
        banderaVenta = 0;
        banderaProveedorVenta = 0;
    }

    /* public void setCargarClientes (){
       conectar conectar1 = new conectar();
       Connection Connection1 = conectar1.conexion();
       String sql = "SELECT * FROM clientes ORDER BY  idclientes ASC ";      
        try {
                Statement st = Connection1.createStatement();
                ResultSet rs = st.executeQuery(sql);
                jComboBoxClientes.removeAllItems();
                    while (rs.next()){
                        jComboBoxClientes.addItem((rs.getString(1))+" - "+rs.getString(2)+" "+rs.getString(3));
                    }
            } catch (SQLException ex) {
              JOptionPane.showConfirmDialog(null, ex);
            }      
     }*/
    public void setBuscarProveedor() {
        boolean encontro = false;
        int valorIdProveedor = Integer.parseInt(jTextFieldIdProveedor.getText());
        conectar conectar1 = new conectar();
        Connection Connection1 = conectar1.conexion();
            
        String sql = "SELECT * FROM Proveedores WHERE idProveedor = '" + valorIdProveedor + "'";
        try {
            Statement st = Connection1.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                encontro = true;
                jTextFieldCliente.setText(rs.getString(2) + " " + rs.getString(3));
                jTextFieldCodigoBarras.setEnabled(true);
                jButtonBuscarProducto.setEnabled(true);
                jTextFieldCodigoBarras.requestFocus();
            }
            if (encontro == false) {
                //Metodo de selección de confirmación
                int x = JOptionPane.showConfirmDialog(null, "El Cliente con id " + valorIdProveedor + " no está registrado, desea registrarlo");
                if (x == JOptionPane.YES_OPTION) {
                    NuevoProveedorCompra.valor = jTextFieldIdProveedor.getText();
                    NuevoProveedorCompra nc1 = new NuevoProveedorCompra();
                    nc1.setVisible(true);
                    dispose();
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "No es posible facturar si no es cliente del sistema");
                }
                jTextFieldIdProveedor.requestFocus();
            }
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }

    public void setCargarFecha() {
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        // SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        jTextFieldFecha.setText(date.format(now));
        //System.out.println(hour.format(now));
        //System.out.println(now);
    }

    public void setCargarNumeroFactura() {
        int maxNumFactura = 0;
        conectar cn = new conectar();
        Connection con = cn.conexion();
        String sql = "SELECT MAX(idFacturas) FROM facturas";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    maxNumFactura = Integer.valueOf(rs.getString(1).toString());
                } else {
                    maxNumFactura = 0;
                }
            }
            maxNumFactura = maxNumFactura + 1;
            jTextFieldNumeroFactura.setText(String.valueOf(maxNumFactura));
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }

    public void debloquear() {
        //jButtonNuevo.setEnabled(false);
        // jButtonBuscarProducto.setEnabled(true);
        jTextFieldCodigoBarras.setEnabled(true);
        jTextFieldIdProducto.setEnabled(true);
        jTextFieldDescripcionProducto.setEnabled(true);
        jTextFieldValorUnitario.setEnabled(true);
        jTextFieldClaveProducto.setEnabled(true);
        jTextFieldCantidad.setEnabled(true);
        jTextFieldTotalVenta.setEnabled(true);
        jButtonAdicionar.setEnabled(true);
    }

    public void bloquear() {
        // jButtonBuscarProducto.setEnabled(false);
        jTextFieldCodigoBarras.setEnabled(true);
        jTextFieldIdProducto.setEnabled(false);
        jTextFieldDescripcionProducto.setEnabled(false);
        jTextFieldValorUnitario.setEnabled(false);
        jTextFieldClaveProducto.setEnabled(false);
        jTextFieldCantidad.setEnabled(false);
        jTextFieldTotalVenta.setEnabled(false);
        jButtonAdicionar.setEnabled(false);
    }

    public void limpiar() {
        //jButtonNuevo.setEnabled(true);
        jTextFieldCodigoBarras.setText("");
        jTextFieldIdProducto.setText("");
        jTextFieldDescripcionProducto.setText("");
        jTextFieldValorUnitario.setText("");
        jTextFieldCantidad.setText("");
        jTextFieldClaveProducto.setText("");
        jTextFieldTotalVenta.setText("");

    }

    public void setGuardar() {
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
        conectar conectar1 = new conectar();
        Connection connection1 = conectar1.conexion();
        String validProducto, valCantidad, valUni, valTotal;
        String sql = "";
        String sql2 = "";
        String sql3 = "";
        //---------------inicia ventas
        sql = "INSERT INTO ventas (idVentas, FechaVenta, MontoVenta,clientes_idclientes) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = connection1.prepareStatement(sql);
            pst.setString(1, jTextFieldNumeroFactura.getText());
            pst.setString(2, jTextFieldFecha.getText());
            pst.setString(3, jTextFieldGranTotal.getText());
            pst.setString(4, jTextFieldIdProveedor.getText());

            int n = pst.executeUpdate();
            /*if(n>0)
                    JOptionPane.showMessageDialog(null, "registro exitoso ventas");*/
        } catch (SQLException ex) {
            Logger.getLogger(FacturarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        //------------------termina ventas
        //---------------inicia facturas
        sql = "INSERT INTO facturas (idFacturas, FechaFactura, MontoFactura,Ventas_idVentas,clientes_idclientes) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = connection1.prepareStatement(sql);
            pst.setString(1, jTextFieldNumeroFactura.getText());
            pst.setString(2, jTextFieldFecha.getText());
            pst.setString(3, jTextFieldGranTotal.getText());
            pst.setString(4, jTextFieldNumeroFactura.getText());
            pst.setString(5, jTextFieldIdProveedor.getText());

            int n = pst.executeUpdate();
            /* if(n>0)
                    JOptionPane.showMessageDialog(null, "registro exitoso Facturas");*/
        } catch (SQLException ex) {
            Logger.getLogger(FacturarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        //------------------termina facturas
        //-------------------inicio detalleventas
        int col = jTableVentas.getRowCount();
        for (int i = 0; i < col; i++) {
            validProducto = model.getValueAt(i, 1).toString();
            valCantidad = model.getValueAt(i, 4).toString();
            valUni = model.getValueAt(i, 5).toString();
            valTotal = model.getValueAt(i, 6).toString();
            sql = "INSERT INTO detalleventas (Ventas_idVentas,Productos_idProductos,CantidadProducto,ValorUnitario, subTotal) VALUES (?,?,?,?,?)";
            try {
                PreparedStatement pst = connection1.prepareStatement(sql);
                pst.setString(1, jTextFieldNumeroFactura.getText());
                pst.setString(2, validProducto);
                pst.setString(3, valCantidad);
                pst.setString(4, valUni);
                pst.setString(5, valTotal);

                int n = pst.executeUpdate();
                /* if(n>0)
                    JOptionPane.showMessageDialog(null, "registro exitoso detalle");*/
            } catch (SQLException ex) {
                Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //.............. Eliminar las filas de la tabla
        int numfilas = jTableVentas.getRowCount();
        if (numfilas >= 1) {
            for (int i = numfilas - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        Grantotal = 0.0;
        jTextFieldGranTotal.setText("");
        setCargarNumeroFactura();
        jTextFieldIdProveedor.setText("");
        jTextFieldCliente.setText("");
        bloquear();
        JOptionPane.showMessageDialog(null, "Registros guardados con éxito");
        jTextFieldIdProveedor.requestFocus();
        jTextFieldCodigoBarras.setEnabled(false);
    }

    public void adicionar() {
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
        if (!jTextFieldDescripcionProducto.getText().equals("")) {
            model.addRow(new Object[]{jTextFieldCodigoBarras.getText(),
                jTextFieldIdProducto.getText(), jTextFieldDescripcionProducto.getText(),
                jTextFieldClaveProducto.getText(), jTextFieldCantidad.getText(), jTextFieldValorUnitario.getText(),
                jTextFieldTotalVenta.getText()
            });
            subTotal = Double.parseDouble(jTextFieldTotalVenta.getText());
            Grantotal = Grantotal + subTotal;
            jTextFieldGranTotal.setText(Double.toString(Grantotal));
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione Productos para adicionarlos a la factura");
        }

    }

    public void eliminarTodo() {
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
        int numfilas = jTableVentas.getRowCount();
        if (numfilas >= 1) {
            for (int i = numfilas - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            Grantotal = 0.0;
            jTextFieldGranTotal.setText(Double.toString(Grantotal));
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos en la factura");
        }
        bloquear();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVentas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldDescripcionProducto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCodigoBarras = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jTextFieldIdProducto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldValorUnitario = new javax.swing.JTextField();
        jTextFieldClaveProducto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTotalVenta = new javax.swing.JTextField();
        jButtonBuscarProducto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldIdProveedor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldGranTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNumeroFactura = new javax.swing.JTextField();
        jTextFieldFecha = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonEliminarArticulo = new javax.swing.JButton();
        jButtonEliminarTodos = new javax.swing.JButton();
        jButtonRegresar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1367, 740));
        getContentPane().setLayout(null);

        jTableVentas.setBackground(new java.awt.Color(255, 255, 255));
        jTableVentas.setForeground(new java.awt.Color(0, 0, 0));
        jTableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Id Producto", "Descripción", "Clave", "Cantidad", "Valor/Unitario", "ValorTotal"
            }
        ));
        jTableVentas.setGridColor(new java.awt.Color(0, 255, 102));
        jTableVentas.setOpaque(false);
        jTableVentas.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jTableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableVentas);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(263, 250, 840, 325);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabel6.setText("Total");

        jLabel1.setText("Descripción");

        jTextFieldDescripcionProducto.setEditable(false);

        jLabel3.setText("Cod Barras");

        jTextFieldCodigoBarras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoBarrasFocusLost(evt);
            }
        });

        jLabel10.setText("Cantidad");

        jTextFieldIdProducto.setEditable(false);

        jLabel8.setText("idProducto");

        jTextFieldValorUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldValorUnitarioFocusLost(evt);
            }
        });

        jTextFieldClaveProducto.setEditable(false);

        jLabel4.setText("Valor/unitario");

        jLabel5.setText("Clave Producto");

        jTextFieldTotalVenta.setEditable(false);
        jTextFieldTotalVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTotalVentaFocusGained(evt);
            }
        });

        jButtonBuscarProducto.setText("Buscar");
        jButtonBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBuscarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel8))
                    .addComponent(jTextFieldIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1)
                        .addGap(118, 118, 118)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldCantidad))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(124, 124, 124)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarProducto))
                .addGap(12, 12, 12))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(263, 170, 840, 66);

        jPanel2.setBackground(new java.awt.Color(241, 1, 30));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(168, 37, 104)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nombre");

        jTextFieldCliente.setEditable(false);
        jTextFieldCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Id Proveeedor");

        jTextFieldIdProveedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIdProveedorFocusLost(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TOTAL");

        jTextFieldGranTotal.setEditable(false);
        jTextFieldGranTotal.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldGranTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldGranTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Factrua de venta N°");

        jTextFieldNumeroFactura.setEditable(false);
        jTextFieldNumeroFactura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldFecha.setEditable(false);
        jTextFieldFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fecha");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldGranTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jTextFieldFecha)
                                .addComponent(jLabel9)
                                .addComponent(jTextFieldNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jTextFieldGranTotal))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(39, 39, 39))))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(270, 50, 1020, 110);

        jPanel3.setBackground(new java.awt.Color(241, 1, 30));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jButtonAdicionar.setBackground(new java.awt.Color(0, 204, 0));
        jButtonAdicionar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdicionar.setText("+");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });
        jButtonAdicionar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonAdicionarKeyPressed(evt);
            }
        });

        jButtonGuardar.setBackground(new java.awt.Color(168, 37, 104));
        jButtonGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonEliminarArticulo.setText("Eliminar Articulo");
        jButtonEliminarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarArticuloActionPerformed(evt);
            }
        });

        jButtonEliminarTodos.setText("Eliminar Todos");
        jButtonEliminarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarTodosActionPerformed(evt);
            }
        });

        jButtonRegresar.setText("Regresar");
        jButtonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButtonAdicionar)
                .addGap(31, 31, 31)
                .addComponent(jButtonGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEliminarArticulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEliminarTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRegresar)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(1110, 170, 180, 401);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo final.jpg"))); // NOI18N
        getContentPane().add(jLabel13);
        jLabel13.setBounds(250, 0, 1070, 720);

        jPanel5.setBackground(new java.awt.Color(241, 1, 30));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo lado_1.png"))); // NOI18N
        jLabel29.setText("jLabel8");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(288, 288, 288))
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 250, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegresarActionPerformed
        // TODO add your handling code here:
        Inicio inicio1 = new Inicio();
        inicio1.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonRegresarActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar();
        limpiar();
        bloquear();
        jTextFieldCodigoBarras.requestFocus();

    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonAdicionarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonAdicionarKeyPressed
        // TODO add your handling code here:
        adicionar();
        limpiar();
        bloquear();
        jTextFieldCodigoBarras.requestFocus();
    }//GEN-LAST:event_jButtonAdicionarKeyPressed

    private void jTableVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVentasMouseClicked
        // TODO add your handling code here:
        //debloquear();
        jButtonAdicionar.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
//           jTextFieldCodigoBarras.setText(model.getValueAt(jTableVentas.getSelectedRow(), 0).toString());
//           jTextFieldIdProducto.setText(model.getValueAt(jTableVentas.getSelectedRow(), 1).toString());
//           jTextFieldDescripcionProducto.setText(model.getValueAt(jTableVentas.getSelectedRow(), 2).toString());
//           jTextFieldClaveProducto.setText(model.getValueAt(jTableVentas.getSelectedRow(), 3).toString());
//           jTextFieldCantidad.setText(model.getValueAt(jTableVentas.getSelectedRow(), 4).toString());
//           jTextFieldValorUnitario.setText(model.getValueAt(jTableVentas.getSelectedRow(), 5).toString());
//           jTextFieldTotalVenta.setText(model.getValueAt(jTableVentas.getSelectedRow(), 6).toString());
        SubTotalModificar = 0.0;
        //SubTotalModificar = Double.parseDouble(jTextFieldTotalVenta.getText());
        SubTotalModificar = Double.parseDouble(model.getValueAt(jTableVentas.getSelectedRow(), 6).toString());
    }//GEN-LAST:event_jTableVentasMouseClicked

    private void jButtonEliminarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarArticuloActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
        if (jTableVentas.getSelectedRow() == -1) {
            if (jTableVentas.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Ingrese productos a la factura");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un artículo para eliminar de la tabla");
            }
        } else {
            //Actualizar la caja de texto GranTotal
            //Grantotal = Grantotal - (Double.parseDouble(jTextFieldTotalVenta.getText()));
            Grantotal = Grantotal - Double.parseDouble(model.getValueAt(jTableVentas.getSelectedRow(), 6).toString());
            jTextFieldGranTotal.setText(Double.toString(Grantotal));
            model.removeRow(jTableVentas.getSelectedRow());
        }
        bloquear();
        limpiar();
        jTextFieldCodigoBarras.requestFocus();
    }//GEN-LAST:event_jButtonEliminarArticuloActionPerformed

    private void jButtonEliminarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarTodosActionPerformed

        // TODO add your handling code here:
        eliminarTodo();
        jTextFieldCodigoBarras.requestFocus();

    }//GEN-LAST:event_jButtonEliminarTodosActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
        if (jTableVentas.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese productos a la factura");
            jTextFieldCodigoBarras.requestFocus();
        } else {
            setGuardar();
        }


    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jTextFieldCodigoBarrasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBarrasFocusLost
        // TODO add your handling code here:
        if (jTextFieldCodigoBarras.getText().equals("")) {

        } else {
            boolean igual = false;
            String registro[] = new String[1];
            String valorCodigoBarras = jTextFieldCodigoBarras.getText();
            String sql = "SELECT * FROM productos WHERE CodigoBarras like '" + valorCodigoBarras + "'";
            conectar conectar1 = new conectar();
            Connection cn = conectar1.conexion();
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    igual = true;
                    registro[0] = rs.getString("CodigoBarras");
                    jTextFieldIdProducto.setText(rs.getString("idProductos"));
                    jTextFieldDescripcionProducto.setText(rs.getString("DescripcionProducto"));
                    jTextFieldClaveProducto.setText(rs.getString("ClaveProducto"));
                    jTextFieldCantidad.requestFocus();
                    debloquear();
                }
                if (igual == false) {
                    JOptionPane.showMessageDialog(null, "¿Producto no existe?");

                    limpiar();
                    bloquear();
                    jTextFieldCodigoBarras.requestFocus();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_jTextFieldCodigoBarrasFocusLost

    private void jTextFieldTotalVentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTotalVentaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalVentaFocusGained

    private void jTextFieldValorUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldValorUnitarioFocusLost
        // TODO add your handling code here:
        double cantidad = Double.parseDouble(jTextFieldCantidad.getText());
        double valorUnitario = Double.parseDouble(jTextFieldValorUnitario.getText());
        double total = cantidad * valorUnitario;
        jButtonAdicionar.requestFocus();
        jTextFieldTotalVenta.setText(Double.toString(total));
    }//GEN-LAST:event_jTextFieldValorUnitarioFocusLost

    private void jTextFieldIdProveedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIdProveedorFocusLost
        // TODO add your handling code here:
        if (jTextFieldIdProveedor.getText().equals("")) {
            jTextFieldIdProveedor.requestFocus();
        } else {
            setBuscarClientes();
        }


    }//GEN-LAST:event_jTextFieldIdProveedorFocusLost

    private void jButtonBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarProductoActionPerformed

        banderaVenta = 1;
        ConsultarProductoCompraVenta obj = new ConsultarProductoCompraVenta();
        obj.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarProductoActionPerformed

    private void jTextFieldClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Inicio in = new Inicio();
        in.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonBuscarProducto;
    private javax.swing.JButton jButtonEliminarArticulo;
    private javax.swing.JButton jButtonEliminarTodos;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableVentas;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldClaveProducto;
    private javax.swing.JTextField jTextFieldCliente;
    public static javax.swing.JTextField jTextFieldCodigoBarras;
    private javax.swing.JTextField jTextFieldDescripcionProducto;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldGranTotal;
    private javax.swing.JTextField jTextFieldIdProducto;
    public static javax.swing.JTextField jTextFieldIdProveedor;
    private javax.swing.JTextField jTextFieldNumeroFactura;
    private javax.swing.JTextField jTextFieldTotalVenta;
    private javax.swing.JTextField jTextFieldValorUnitario;
    // End of variables declaration//GEN-END:variables
}
