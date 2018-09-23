package angelcatalogomelanie;
 
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Charlie
 */


public class Conectar 
{
    Connection conect = null;
 
    public Connection conexion()
        {
            try {
                //Cargamos el Driver MySQL
                Class.forName("com.mysql.jdbc.Driver");
                //Class.forName("org.gjt.mm.mysql.Driver");
                conect = DriverManager.getConnection("jdbc:mysql://localhost/angelcatalogo","root","root");
                //System.out.println("conexion establecida");
                //JOptionPane.showMessageDialog(null,"Conectado");
                //Cargamos el Driver Access
                //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                //Conectar en red base
                //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb)";DBQ=//servidor/bd_cw/cw.mdb";
                //Conectar Localmente
                //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb)";DBQ=:/cwnetbeans/cw.mdb";
                //conect = DriverManager.getConnection(strConect,"","");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("error de conexion");
                JOptionPane.showMessageDialog(null,"Error de conexion"+e);
            }
            return conect;
        }
    
    public static ResultSet getTabla(String Consulta)
    {
        Conectar cc=new Conectar();
 com.mysql.jdbc.Connection cn= (com.mysql.jdbc.Connection) cc.conexion();
       
        java.sql.Statement st;
        ResultSet datos=null;
        try
        {
            st=cn.createStatement();
            datos=st.executeQuery(Consulta);            
        }
        catch(Exception e){ System.out.print(e.toString());}
        return datos;
    } 
     
    
}