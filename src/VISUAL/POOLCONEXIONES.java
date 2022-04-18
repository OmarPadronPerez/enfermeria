package VISUAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;

public class POOLCONEXIONES {

    private String base = null;
    private String user = null;
    private String password = null;
    private String servidor = null;
    private String puerto = null;
    private String url = null;
    int intento = 0;

    public BasicDataSource hacerpool() {
        BasicDataSource basicDataSource = new BasicDataSource();
        boolean respuesta=false;
            do {
                intento++;
                leerArchivo();
                basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
                basicDataSource.setUsername(user);
                basicDataSource.setPassword(password);
                basicDataSource.setUrl(url);
                basicDataSource.setValidationQueryTimeout(3);
                Connection con;
            try {
                con = basicDataSource.getConnection();
                respuesta = validarConeccion(con);
            } catch (SQLException ex) {
                System.out.println("falla de conexion "+ex);
                Logger.getLogger(POOLCONEXIONES.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                System.out.println(intento + " " + servidor + " " + respuesta);
                if (intento > 6) {
                    //mensaje de falla de coneccion
                    System.out.println("falla de conexion");
                    JOptionPane.showMessageDialog(null, "Se perdio la conexión con los servidores", "ERROR", JOptionPane.ERROR_MESSAGE);
                    basicDataSource = null;
                    break;
                }
            } while (respuesta == false);

        intento = 0;
        PRINCIPAL.dataSource=basicDataSource;
        return basicDataSource;
    }

    public void leerArchivo() {
        String ruta = null;
        ruta = (new File("")).getAbsolutePath() + "\\archivos\\conexion.cnx";

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            user = br.readLine();
            password = br.readLine();
            servidor = br.readLine();
            puerto = br.readLine();
            base = br.readLine();
            url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + base;

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public boolean validarConeccion(Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String sql = "select pi() limit 1;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    
}
