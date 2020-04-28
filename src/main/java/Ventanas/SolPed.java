/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Operaciones.Conector;
import Operaciones.Excel;
import Operaciones.Generales;
import static Ventanas.ListadoIngreso.TablaListado;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Videla Araya
 */
public class SolPed extends javax.swing.JInternalFrame {

    Buscar_solped buscarsolped;
    Conector conector = new Conector();
    String normal;
    String urgente;
    int item = 1;
    boolean fmrbuscasolped = false;
    DefaultTableModel modeloTablaSP = new DefaultTableModel();

    /**
     * Creates new form SolPed
     */
    public SolPed() {
        initComponents();
        this.spFecha.getDateEditor().setEnabled(false);
        modeloTablaSP.addColumn("Item");
        modeloTablaSP.addColumn("Código");
        modeloTablaSP.addColumn("Cantidad");
        modeloTablaSP.addColumn("Unidad");
        modeloTablaSP.addColumn("Descripción");
        modeloTablaSP.addColumn("Solicita");
        modeloTablaSP.addColumn("Prioridad");

    }

    public boolean camposVacios() {
        boolean r = this.spFecha.getDate() == null;
        r |= this.txtspNumero.getText().isEmpty();
        r |= this.txtspCodigo.getText().isEmpty();
        r |= this.txtspDescripcion.getText().isEmpty();
        r |= this.txtspUnidad.getText().isEmpty();
        r |= this.txtspCantidad.getText().isEmpty();
        return r;
    }

    public boolean codigoRepetido(String codigo) {
        if (spTabla != null && spTabla.getModel() != null) {
            for (int i = 0; i < modeloTablaSP.getRowCount(); i++) {
                if (codigo.equals(modeloTablaSP.getValueAt(i, 1))) {
                    spTabla.changeSelection(i, 1, false, false);
                    return true;
                }
            }
        }

        return false;
    }

    public void guardarSolped() {

        Conector conn = new Conector();
        conn.connect();
        ResultSet rs = null;
        int Id_material;
        String plantilla = "dd-MM-yyyy";
        Date fecha = spFecha.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(plantilla);
        String Fecha = simpleDateFormat.format(fecha);
        int Num_solicitud = Integer.parseInt(txtspNumero.getText());
        int Id_solicitante = Escritorio.idData[1];
        try {
            for (int i = 0; i < spTabla.getRowCount(); i++) {
                String codigo = (String) spTabla.getValueAt(i, 1);
                rs = conn.buscarId(codigo);
                Id_material = rs.getInt("Id");
                PreparedStatement stm = conn.connect.prepareStatement("INSERT INTO Solped (Num_solicitud, Fecha, Id_material, Cantidad, Cargo, Prioridad, Id_solicitante) VALUES (?,?,?,?,?,?,?)");
                stm.setInt(1, Num_solicitud);//guardamos nº solicitud
                stm.setString(2, Fecha);//guardamos fecha
                stm.setInt(3, Id_material);//guardamos id_material
                stm.setInt(4, Integer.parseInt(spTabla.getValueAt(i, 3).toString()));// guardamos catidad
                stm.setString(5, spTabla.getValueAt(i, 5).toString());// guardamos cargo
                stm.setString(6, spTabla.getValueAt(i, 6).toString());// guardamos prioridad
                stm.setInt(7, Id_solicitante);//guardamos id_usuario
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListadoIngreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Se agregaron lo datos correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);

    }

    public void generarSolped(String solicitante, String fecha, int numerosp) {

        Object oData[][] = new Object[spTabla.getRowCount()][9];

        String pnormal = "";
        String purgente = "";

        Excel excel = new Excel();
        for (int i = 0; i < spTabla.getRowCount(); i++) {

            oData[i][0] = (String) spTabla.getValueAt(i, 1);
            oData[i][2] = (String) spTabla.getValueAt(i, 2);
            oData[i][1] = (int) spTabla.getValueAt(i, 3);
            oData[i][3] = (String) spTabla.getValueAt(i, 4);
            oData[i][4] = (String) spTabla.getValueAt(i, 5);
            oData[i][5] = 0;
            oData[i][6] = 0;
            String pr = (String) spTabla.getValueAt(i, 6);
            if (pr == "Normal") {
                pnormal = "X";
                purgente = "";
            } else {
                pnormal = "";
                purgente = "X";
            }
            oData[i][7] = pnormal;
            oData[i][8] = purgente;
        }
        excel.GenerarSolPed(numerosp, solicitante, fecha, oData);

        JOptionPane.showMessageDialog(null, "Se creo la Solicitud de Pedidos Nº " + numerosp, "Mensaje informativo", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Prioridad = new javax.swing.ButtonGroup();
        TablaSPpopupMenu = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbSolicitado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        spFecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtspNumero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtspCodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtspDescripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtspUnidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtspCantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        check1 = new javax.swing.JCheckBox();
        check2 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cbxArea = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnGenerar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        spTabla = new javax.swing.JTable();

        jMenuItem1.setText("Editar");
        TablaSPpopupMenu.add(jMenuItem1);

        jMenuItem3.setText("Eliminar");
        TablaSPpopupMenu.add(jMenuItem3);

        jMenuItem4.setText("Mostrar");
        TablaSPpopupMenu.add(jMenuItem4);

        setTitle("Solicitud de pedidos");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/orbit.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(399, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Solicitado por");

        cbSolicitado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ISAIAS YAÑEZ", "WILFREDO ROBLEDO", "ADOLFO QUEZADA", " " }));

        jLabel3.setText("Fecha");

        jLabel4.setText("Solicitud de pedido nº");

        txtspNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtspNumeroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtspNumeroKeyTyped(evt);
            }
        });

        jLabel5.setText("Código");

        txtspCodigo.setNextFocusableComponent(txtspDescripcion);

        jLabel6.setText("Descripcion");

        txtspDescripcion.setNextFocusableComponent(txtspUnidad);
        txtspDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtspDescripcionFocusGained(evt);
            }
        });

        jLabel7.setText("Unidad");

        jLabel8.setText("Cantidad");

        txtspCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtspCantidadKeyTyped(evt);
            }
        });

        jLabel10.setText("Prioridad:");

        Prioridad.add(check1);
        check1.setSelected(true);
        check1.setText("Normal");

        Prioridad.add(check2);
        check2.setText("Urgente");
        check2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar2.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel11.setText("Area que solicita");

        cbxArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Operaciones", "HSEC", "Mantencion", "Administrativo", "Bodega", "Otro" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtspDescripcion))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(37, 37, 37)
                                .addComponent(txtspUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtspCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(223, 223, 223)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(check1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(36, 36, 36)
                                        .addComponent(txtspCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(spFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtspNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbxArea, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSolicitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addComponent(spFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtspNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtspCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbxArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtspDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtspUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtspCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(check1)
                    .addComponent(check2)
                    .addComponent(jLabel7))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregar.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AgregarNormal.png"))); // NOI18N
        btnAgregar.setBorder(null);
        btnAgregar.setRolloverEnabled(true);
        btnAgregar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/AgregarRoll.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnGenerar.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SolpedNormal.png"))); // NOI18N
        btnGenerar.setBorderPainted(false);
        btnGenerar.setRolloverEnabled(true);
        btnGenerar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/SolpedRoll.png"))); // NOI18N
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CerrarNormal.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setOpaque(true);
        jButton4.setRolloverEnabled(true);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/CerrarRoll.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Limpiar.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setRolloverEnabled(true);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/LimpiarRoll.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnGenerar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(59, 59, 59)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        spTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Item", "Código", "Unidad", "Cantidad", "Detalle", "Cargo", "Prioridad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        spTabla.setComponentPopupMenu(TablaSPpopupMenu);
        jScrollPane1.setViewportView(spTabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void check2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (Escritorio.bsolped == false) {
            Escritorio.bsolped = true;
            buscarsolped = new Buscar_solped();
            Escritorio.Escritorio.add(buscarsolped);
            buscarsolped.toFront();
            buscarsolped.setVisible(true);
            buscarsolped.setLocation(820, 0);
        } else {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtspDescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtspDescripcionFocusGained
        // TODO add your handling code here:
        Conector con = new Conector();
        con.connect();
        ResultSet rs = null;
        int Id;
        int caso = 0;
        String codigo = this.txtspCodigo.getText();
        String sql = "SELECT Id, Descripcion, Unidad FROM Maestro WHERE Codigo ='" + codigo + "'";
        rs = con.buscar(sql);
        try {
            if (!rs.next()) {
                caso = 1;
                this.txtspDescripcion.setText("CODIGO NO ARROJA RESULTADOS!!!");
                this.txtspCodigo.requestFocus();
            } else {
                caso = 2;
                Id = rs.getInt("Id");
                Escritorio.idData[0] = Id;
                this.txtspDescripcion.setText(rs.getString("Descripcion"));
                this.txtspUnidad.setText(rs.getString("Unidad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtspDescripcionFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Codigo boton limpiar todo
        //conector.limpiarFormulario(this.getContentPane().getComponents());
        Generales generales = new Generales();
        generales.limpiarFormulario(this.getContentPane().getComponents());
        modeloTablaSP.setRowCount(0);
        item = 1;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (camposVacios()) {
            JOptionPane.showMessageDialog(null, "No pueden quedar campos vacios", "Alerta", JOptionPane.INFORMATION_MESSAGE);

        } else {
            String prioridad = "";
            String codigo = txtspCodigo.getText();
            String unidad = txtspUnidad.getText();
            int cantidad = Integer.parseInt(txtspCantidad.getText());
            String detalle = txtspDescripcion.getText();
            String cargo = (cbxArea.getSelectedItem()).toString();
            if (check1.isSelected()) {
                prioridad = "Normal";
            } else {
                prioridad = "Urgente";
            }
            if (codigoRepetido(codigo)) {
                JOptionPane.showMessageDialog(null, "Código repetido", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                txtspCodigo.requestFocus();
                spTabla.clearSelection();
            } else {
                modeloTablaSP.addRow(new Object[]{item, codigo, unidad, cantidad, detalle, cargo, prioridad});
                spTabla.setModel(modeloTablaSP);
                item++;
                txtspCodigo.setText("");
                cbxArea.setSelectedIndex(0);
                txtspUnidad.setText("");
                txtspCantidad.setText("");
                txtspDescripcion.setText("");
                txtspCodigo.requestFocus();
            }
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Codigo boton salir

        Escritorio.FormularioIniciado = false;
        Escritorio.nocode = false;
        Escritorio.Escritorio.removeAll();
        Escritorio.Escritorio.updateUI();
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        // Codigo boton generar solped
        if (modeloTablaSP.getRowCount() > 0) {
            String solicitante = (String) cbSolicitado.getSelectedItem();
            String plantilla = "dd-MM-yyyy";
            Date Fecha = spFecha.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(plantilla);
            String fecha = simpleDateFormat.format(Fecha);
            int numerosp = (Integer.parseInt(txtspNumero.getText()));
            generarSolped(solicitante, fecha, numerosp);
            guardarSolped();
            modeloTablaSP.setRowCount(0);
            item = 1;

        } else {
            JOptionPane.showMessageDialog(null, "No hay datos para crear la SolPed", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_btnGenerarActionPerformed

    private void txtspNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtspNumeroKeyPressed


    }//GEN-LAST:event_txtspNumeroKeyPressed

    private void txtspNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtspNumeroKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9'))
            evt.consume();
    }//GEN-LAST:event_txtspNumeroKeyTyped

    private void txtspCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtspCantidadKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9'))
            evt.consume();
    }//GEN-LAST:event_txtspCantidadKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Prioridad;
    private javax.swing.JPopupMenu TablaSPpopupMenu;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JComboBox<String> cbSolicitado;
    private javax.swing.JComboBox<String> cbxArea;
    public static javax.swing.JCheckBox check1;
    public static javax.swing.JCheckBox check2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser spFecha;
    private javax.swing.JTable spTabla;
    private javax.swing.JTextField txtspCantidad;
    public static javax.swing.JTextField txtspCodigo;
    public static javax.swing.JTextField txtspDescripcion;
    private javax.swing.JTextField txtspNumero;
    private javax.swing.JTextField txtspUnidad;
    // End of variables declaration//GEN-END:variables
}
