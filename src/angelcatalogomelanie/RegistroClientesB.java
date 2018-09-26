/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angelcatalogomelanie;

import com.mysql.jdbc.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author FGG
 */
public class RegistroClientesB extends javax.swing.JFrame {

    private javax.swing.JButton next;
    private javax.swing.JButton salir;
    private javax.swing.JTextField nombre;
 private TableRowSorter TrsFiltroCB;
 
    String n, sp, sql = "",filtro;
   DefaultTableModel model;
   ResultSet rs;
    private Object acciones;
int id, num, ctb;
    /**
     * Creates new form RegistroClientes
     */
    public RegistroClientesB() 
    {
        initComponents();
         this.getContentPane().setBackground(Color.magenta);
        setIconImage(new ImageIcon(getClass().getResource("/img/42349585.jpg")).getImage());
        setLocationRelativeTo(null);
       conectarBDclientes();
        limpiar();
       
        mostrarClientes();
    }
public void limpiar() 
{
        txtCantidad.setText("");
       
}
 
    private void mostrarClientes() 
    {
        DefaultTableModel model = new DefaultTableModel();
        
        ResultSet rs = Conectar.getTabla("select * from clientes");
        model.setColumnIdentifiers(new Object[]{"id","nombre", "cantidad"});
        try {
            while (rs.next()) {
                // añade los resultado a al modelo de tabla
                model.addRow(new Object[]{rs.getInt("idcliente"),rs.getString("nombre"), rs.getString("cantidad")});
                    }
            // asigna el modelo a la tabla
            tblClientes.setModel(model);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
public void conectarBDclientes()
     {
         Conectar cc = new Conectar();
       java.sql.Connection cn = (java.sql.Connection) cc.conexion();
     cc.conexion();
     cn=cc.conexion();
     }

public void SiguienteTabla()
{ 
  
     if (txtNombre.getText().isEmpty()||txtCantidad.getText().isEmpty())
          
{
    JOptionPane.showMessageDialog(null, "FAVOR DE INGRESAR SU NOMBRE Y CANTIDAD " , "CAMPOS VACIOS", JOptionPane.ERROR_MESSAGE);
}
else
     {
         TablasJoyasB tjb = new TablasJoyasB();
        tjb.setVisible(true);
        this.show(false);
     }
     
}
public void NuevosDatos()
{        txtCantidad.setEnabled(true);
        limpiar();
        txtCantidad.requestFocus();
}
public void MostrarDatosTablas()
{
   txtCantidad.setEnabled(true); 
       lblId.setText(model.getValueAt(tblClientes.getSelectedRow(), 0) + "");
       txtCantidad.setText(model.getValueAt(tblClientes.getSelectedRow(), 1) + "");
       
       
}
public void MostrarClientesSeleccionados()
{
    txtBuscarB.setEnabled(true);
      
}
public void GuardarDatos()
{     
    
        try {
             n = txtNombre.getText();
       num= Integer.parseInt(txtCantidad.getText());

        sql = "INSERT INTO clientes(nombre,cantidad) VALUES (?,?)";
           if (txtNombre.getText().isEmpty()||txtCantidad.getText().isEmpty())
{
    JOptionPane.showMessageDialog(null, " FAVOR DE INTRODUCIR DATOS PARA GUARDAR" , "REGISTROS VACIOS", JOptionPane.ERROR_MESSAGE);
 
}
else
{
    Conectar cc = new Conectar();
     java.sql.Connection cn = (java.sql.Connection) cc.conexion();
     java.sql.PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1,n);
            pst.setInt(2,num);
            
           
            pst.executeUpdate();
            mostrarClientes();
            JOptionPane.showMessageDialog(null, "DATOS GUARDADOS CORRECTAMENTE");
            
}     
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RegistroClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void filtroclientesB()
{
    int i=1;
    TrsFiltroCB.setRowFilter(RowFilter.regexFilter(txtBuscarB.getText(),i));
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPresentacion = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        btnAtrasTJB = new javax.swing.JButton();
        lblImg = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        lblPresentacion1 = new javax.swing.JLabel();
        txtBuscarB = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnGuardarB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 204));
        setForeground(new java.awt.Color(0, 0, 153));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPresentacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPresentacion.setText("AngelCatalogo");
        getContentPane().add(lblPresentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 11, 120, -1));

        btnSiguiente.setText("IR A JOYAS ");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, -1));

        btnAtrasTJB.setText("ATRAS");
        btnAtrasTJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasTJBActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtrasTJB, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, -1, -1));

        lblImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/joyas.jpg"))); // NOI18N
        getContentPane().add(lblImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 260, 120));

        lblNombre.setText("NOMBRE:");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 28));

        lblCantidad.setText("CANTIDAD:");
        getContentPane().add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 70, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDCLIENTE", "NOMBRE", "CANTIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jScrollPane1.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(0).setResizable(false);
            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblClientes.getColumnModel().getColumn(1).setResizable(false);
            tblClientes.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblClientes.getColumnModel().getColumn(2).setResizable(false);
            tblClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 46, 460, 234));

        lblPresentacion1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPresentacion1.setText("CLIENTES");
        getContentPane().add(lblPresentacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 11, 120, -1));

        txtBuscarB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarBKeyTyped(evt);
            }
        });
        getContentPane().add(txtBuscarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 300, -1));

        jLabel1.setText("id:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));
        getContentPane().add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 60, 20));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 170, -1));

        jButton1.setText("MODIFICAR");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        jButton2.setText("ELIMINAR");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("BUSCAR:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, -1, -1));

        btnGuardarB.setText("GUARDAR");
        btnGuardarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarBActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasTJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasTJBActionPerformed
      InicioSesionAdmin isa = new InicioSesionAdmin();
      isa.setVisible(true);
      this.show(false);
    }//GEN-LAST:event_btnAtrasTJBActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
       SiguienteTabla();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        txtCantidad.transferFocus();
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtBuscarBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarBKeyTyped
txtBuscarB.addKeyListener(new KeyAdapter() 
{
    public void keyReleased(final KeyEvent ke)
    {
        String cadena = (txtBuscarB.getText());
    txtBuscarB.setText(cadena);
    filtroclientesB();
    }
});

TrsFiltroCB = new TableRowSorter(tblClientes.getModel());
tblClientes.setRowSorter(TrsFiltroCB);
    }//GEN-LAST:event_txtBuscarBKeyTyped

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarBActionPerformed
        GuardarDatos();

    }//GEN-LAST:event_btnGuardarBActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroClientesB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroClientesB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroClientesB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroClientesB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroClientesB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtrasTJB;
    private javax.swing.JButton btnGuardarB;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPresentacion;
    private javax.swing.JLabel lblPresentacion1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBuscarB;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

   

    
}
