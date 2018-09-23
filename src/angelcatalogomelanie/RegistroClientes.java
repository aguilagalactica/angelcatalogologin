/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angelcatalogomelanie;

import com.mysql.jdbc.*;
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
public class RegistroClientes extends javax.swing.JFrame {

    private javax.swing.JButton next;
    private javax.swing.JButton salir;
    private javax.swing.JTextField nombre;

    String n, sp, sql = "",filtro;
    private TableRowSorter trsfiltro;
    private Object acciones;

    /**
     * Creates new form RegistroClientes
     */
    public RegistroClientes() 
    {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/img/42349585.jpg")).getImage());
        setLocationRelativeTo(null);
       conectarBDclientes();
        limpiar();
        bloquear();
        mostrarClientes();
    }
public void limpiar() 
{
        txtNombre.setText("");
        txtCantidad.setText("");
}
   public void bloquear() 
   {
        txtNombre.setEnabled(false);
        txtCantidad.setEnabled(false);
        jspArticulos.setEnabled(false);
        btnSiguiente.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
   }
   public void desbloquear() 
   {
        txtNombre.setEnabled(true);
        txtCantidad.setEnabled(true);
        jspArticulos.setEnabled(true);
        btnSiguiente.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);

    }
    private void mostrarClientes() 
    {
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Conectar.getTabla("select * from clientes");
        model.setColumnIdentifiers(new Object[]{"nombre", "cantidad"});
        try {
            while (rs.next()) {
                // añade los resultado a al modelo de tabla
                model.addRow(new Object[]{rs.getString("nombre"), rs.getString("cantidad")});
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

public void GuardarDatos()
{     
    n = txtNombre.getText();
        sp = jspArticulos.getValue().toString();

        sql = "INSERT INTO clientes(nombre,cantidad) VALUES (?,?)";
        try {
             Conectar cc = new Conectar();
     java.sql.Connection cn = (java.sql.Connection) cc.conexion();
     java.sql.PreparedStatement pst = cn.prepareStatement(sql);
           if (tblClientes.equals(null) )
{
    JOptionPane.showMessageDialog(null, " FAVOR DE INTRODUCIR DATOS PARA GUARDAR" , "REGISTROS VACIOS", JOptionPane.ERROR_MESSAGE);
 
}
else
{
             pst.setString(1, n);
            pst.setString(2, sp);
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
public void SiguienteTabla()
{
    if (tblClientes.getRowCount() == 0 )
{
    JOptionPane.showMessageDialog(null, "CAMPOS VACIOS, FAVOR DE INGRESAR SU NOMBRE Y CANTIDAD DE JOYAS" , "", JOptionPane.ERROR_MESSAGE);
}
else
{
   TablasJoyas tj = new TablasJoyas();
        tj.setVisible(true);
        this.show(false);
   
}
}
public void NuevosDatos()
{
     btnGuardar.setEnabled(false);
     btnModificar.setEnabled(false);
     btnEliminar.setEnabled(false);
        jspArticulos.setEnabled(true);
        txtNombre.setEnabled(true);
        limpiar();
        txtNombre.requestFocus();
}
public void MostrarDatosTablas()
{
   DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        txtNombre.setText(model.getValueAt(tblClientes.getSelectedRow(), 0) + "");
        txtCantidad.setText(model.getValueAt(tblClientes.getSelectedRow(), 1) + "");
        txtCantidad.setEnabled(true);
        txtNombre.setEnabled(true); 
}
public void EliminarDatosTablasClientes()
{
    int fila=tblClientes.getSelectedRow();
   String dao=(String)tblClientes.getValueAt(fila,0);
        try
        {
             if (tblClientes.getRowCount()!=0)
{ 
    Conectar cc = new Conectar();
     java.sql.Connection cn = (java.sql.Connection) cc.conexion();
    java.sql.PreparedStatement pst = cn.prepareStatement(sql="DELETE from clientes where nombre='"+dao+"'");
pst.executeUpdate();
 
 JOptionPane.showMessageDialog(null, "DATOS ELIMINADOS CORRECTAMENTE"); 
   
}
else
{
   JOptionPane.showMessageDialog(null, "CAMPOS VACIOS" , "NO HAY REGISTROS QUE ELIMINAR", JOptionPane.ERROR_MESSAGE);
   
}
           
        }
         catch (SQLException ex) 
        {
            Logger.getLogger(RegistroClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
       mostrarClientes();
}
private void ModificarDatosTablasClientes() 
{
    try
{
     if (tblClientes.equals(null) )
{ 
    JOptionPane.showMessageDialog(null, "NO SE PUEDEN MODIFICAR LOS CAMPOS SELECCIONADOS" , "REGISTROS VACIOS", JOptionPane.ERROR_MESSAGE);

}
  else
{
    Conectar cc = new Conectar();
     java.sql.Connection cn = (java.sql.Connection) cc.conexion();
     java.sql.PreparedStatement pst = cn.prepareStatement(sql="UPDATE clientes set nombre='"+txtNombre.getText()+"', cantidad='"+txtCantidad.getText()+"' where nombre='"+txtNombre.getText()+"'");
pst.executeUpdate();
   
 JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CORRECTAMENTE"); 
}      
}
catch  (SQLException ex) 
        {
          Logger.getLogger(RegistroClientes.class.getName()).log(Level.SEVERE, null, ex);   
        }
    mostrarClientes();
}

public void MostrarClientesSeleccionados()
{
    filtro = txtNombre.getText();
trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre.getText(), 0));
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
        btnSalir = new javax.swing.JButton();
        lblImg = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jspArticulos = new javax.swing.JSpinner();
        btnNuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        lblPresentacion1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnSesion = new javax.swing.JButton();

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
        getContentPane().add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 298, -1, -1));

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(511, 298, -1, -1));

        lblImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/joyas.jpg"))); // NOI18N
        getContentPane().add(lblImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 260, 150));

        lblNombre.setText("NOMBRE:");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 28));

        lblCantidad.setText("CANTIDAD:");
        getContentPane().add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 170, -1));
        getContentPane().add(jspArticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 80, -1));

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 298, -1, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "CANTIDAD"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 46, 280, 234));

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 298, -1, -1));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 298, -1, -1));

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 298, -1, -1));
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 60, -1));

        lblPresentacion1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPresentacion1.setText("CLIENTES");
        getContentPane().add(lblPresentacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 11, 120, -1));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 270, -1));

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        btnSesion.setText("SESION");
        btnSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSesionActionPerformed(evt);
            }
        });
        getContentPane().add(btnSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
       SiguienteTabla();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       NuevosDatos();
       
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        txtNombre.transferFocus();
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       GuardarDatos();
      
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
MostrarDatosTablas();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       EliminarDatosTablasClientes();
      
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       ModificarDatosTablasClientes();
     
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
      // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
   MostrarClientesSeleccionados();       // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesionActionPerformed
 IniciarSesion is= new IniciarSesion();
 is.setVisible(true);
 this.show(false);      
    }//GEN-LAST:event_btnSesionActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSesion;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jspArticulos;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPresentacion;
    private javax.swing.JLabel lblPresentacion1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

   

    
}
