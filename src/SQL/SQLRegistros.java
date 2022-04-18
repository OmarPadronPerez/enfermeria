package SQL;

import CLASES.TRABAJADOR;
import VISUAL.PRINCIPAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sistemas
 */
public class SQLRegistros {
    SimpleDateFormat fSal = new SimpleDateFormat("yyyy-MM-dd");
    
    public boolean CrearEmpleado(TRABAJADOR p){
        PreparedStatement ps = null;
        String sql = null;
        ResultSet rs = null;
        boolean respuesta=false;
        //quitar posibles espacios en blanco al final del string
        p.setNombre(p.getNombre().replaceAll("^\\s*", ""));
        p.setApellido1(p.getApellido1().replaceAll("^\\s*", ""));
        p.setApellido1(p.getApellido2().replaceAll("^\\s*", ""));
        p.setArea(p.getArea().replaceAll("^\\s*", ""));
        sql="INCERT INTO PERSONAL_ZURICH.EMPLEADO(ID, NOMBRE, APELLIDO1, "
                    + "APELLIDO2,FECHA_INGRESO,AREA,ACTIVO,FECHABAJA) "
                    + "VALUES(?,?,?,?,?,?,?,?);";
        
        try{
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2,p.getNombre());
            ps.setString(3, p.getApellido1());
            ps.setString(4, p.getApellido2());
            ps.setString(5, fSal.format(p.getFechaIngreso()));
            ps.setString(6, p.getArea());
            ps.setBoolean(7, p.isActivo());
            if(!p.isActivo()){
                ps.setString(8, fSal.format(p.getFechaBaja()));
            }
            else{
                ps.setString(8, fSal.format("0000-00-00"));
            }
            
            
            rs=ps.executeQuery();
            respuesta=rs.getBoolean(1);
            //ps.execute();
            ps.close();
            rs.close();
            con.close();
            
        }catch(SQLException e){
            System.out.println("CrearEmpleado()" + sql);
            System.out.println("CrearEmpleado() " + e);
            Logger.getLogger(SQLRegistros.class.getName()).log(Level.SEVERE, null, e);
        }
        if(respuesta){
            return true;
        }
        else{
            return false;
        }
        
    }
}
