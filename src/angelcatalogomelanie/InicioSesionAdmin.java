/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angelcatalogomelanie;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author franky
 */
public class InicioSesionAdmin extends javax.swing.JFrame {
String Usuario;
String Contraseña;
    /**
     * Creates new form LOGGIN
     */
    public InicioSesionAdmin() {
        initComponents();
    this.setLocationRelativeTo(null);
     setIconImage(new ImageIcon(getClass().getResource("/img/42349585.jpg")).getImage());
    }

    void acceder(String usuario, String pass)
    {
        String cap="";
       String sql="SELECT * FROM usuarios WHERE nombreusujoyas='"+usuario+"' && contrausujoyas='"+pass+"'";
        try {
            Conectar cc = new Conectar();
     java.sql.Connection cn = (java.sql.Connection) cc.conexion();
     java.sql.PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            while(rs.next())
            {
                cap=rs.getString("tipousuario");
            }
            if(cap.equals("admin"))
            {
                  
                    JOptionPane.showMessageDialog(null, "BIENVENIDO");
                      RegistroClientesB rcb = new RegistroClientesB();
                    rcb.setVisible(true); 
                    this.setVisible(false);
            }
            if ((txtUSUARIO.getText().isEmpty()) && (txtPASSWORD.getText().isEmpty())) 
        {
            JOptionPane.showMessageDialog(null, "INGRESE SU NOMBRE DE USUARIO Y CONTRASEÑA");
            InicioSesion is= new InicioSesion();
            is.setVisible(true);
            this.show(false); 
        }
            if((!cap.equals("tipousuario")))
            {
                JOptionPane.showMessageDialog(null, "USTED NO ES ADMIN, FAVOR DE REGRESAR ");
                InicioSesion is= new InicioSesion();
                is.setVisible(true);
                this.show(false); 
         
            }
        } catch (SQLException ex) {
            Logger.getLogger(InicioSesionAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtUSUARIO = new javax.swing.JTextField();
        txtPASSWORD = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        btnENTRAR = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 0));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("CONTRASEÑA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        txtUSUARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUSUARIOActionPerformed(evt);
            }
        });
        getContentPane().add(txtUSUARIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 182, -1));
        getContentPane().add(txtPASSWORD, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 180, -1));

        jLabel1.setText("USUARIO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        btnENTRAR.setText("ENTRAR");
        btnENTRAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnENTRARMouseClicked(evt);
            }
        });
        btnENTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnENTRARActionPerformed(evt);
            }
        });
        getContentPane().add(btnENTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        btnAtras.setText("ATRAS");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descarga.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 200));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/21_50x70_375261961_PROOF.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnENTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnENTRARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnENTRARActionPerformed

    private void btnENTRARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnENTRARMouseClicked
        
        
         String usu = txtUSUARIO.getText();
    String pas = new String(txtPASSWORD.getPassword());
        acceder(Usuario, pas);
    }//GEN-LAST:event_btnENTRARMouseClicked

    private void txtUSUARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUSUARIOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUSUARIOActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
    InicioSesion is= new InicioSesion();
is.setVisible(true);
this.show(false);   
    }//GEN-LAST:event_btnAtrasActionPerformed

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
            java.util.logging.Logger.getLogger(InicioSesionAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesionAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesionAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesionAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesionAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnENTRAR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtPASSWORD;
    private javax.swing.JTextField txtUSUARIO;
    // End of variables declaration//GEN-END:variables
}
