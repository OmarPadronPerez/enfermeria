package SQL;

import CLASES.CONSULTA;
import CLASES.TRABAJADOR;
import VISUAL.PRINCIPAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SQLRegistros {
    SimpleDateFormat fSal = new SimpleDateFormat("yyyy-MM-dd");
    
    public boolean NuevoEmpleado(TRABAJADOR p){
        PreparedStatement ps = null;
        String sql = null;
        boolean respuesta=false;
        //quitar posibles espacios en blanco al final del string
        p.setNombre(p.getNombre().replaceAll("^\\s*", ""));
        p.setApellido1(p.getApellido1().replaceAll("^\\s*", ""));
        p.setApellido1(p.getApellido2().replaceAll("^\\s*", ""));
        p.setArea(p.getArea().replaceAll("^\\s*", ""));
        sql="INSERT INTO PERSONAL_ZURICH.EMPLEADO(ID, NOMBRE, APELLIDO1, "
                + "APELLIDO2,FECHA_INGRESO,AREA,ACTIVO,FECHA_BAJA,telefono,"
                + "lugar_nacimiento,SEXO,ESTUDIOS,ESTADO_CIVIL,FECHA_NACIMIENTO) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";        
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
                ps.setNull(8, Types.VARCHAR);
            }
            ps.setString(9, p.getTelefono());
            ps.setString(10, p.getLugNaci());
            ps.setString(11, p.getSexo());
            ps.setString(12, p.getEscolaridad());
            ps.setString(13, p.getEstadoCivil());
            ps.setString(14, fSal.format(p.getFecNaci()));
            ps.execute();
            ps.close();
            con.close();
            respuesta=true;
        }catch(SQLException e){
            System.out.println("CrearEmpleado()" + sql);
            System.out.println("CrearEmpleado() " + e);
            Logger.getLogger(SQLRegistros.class.getName()).log(Level.SEVERE, null, e);
        }
        return respuesta;
    }
    
    public TRABAJADOR buscarEmpleadoID(int id){
        TRABAJADOR t=null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql = "SELECT * FROM personal_zurich.empleado WHERE id ="+id+";";
        
        try{
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            t=new TRABAJADOR();
            if(rs.next()){
                t.setId(rs.getInt("id"));
                t.setNombre(rs.getString("nombre"));
                t.setApellido1(rs.getString("apellido1"));
                t.setApellido2(rs.getString("apellido2"));
                t.setFechaIngreso(rs.getDate("fecha_ingreso"));
                t.setArea(rs.getString("area"));
                t.setTelefono(rs.getString("telefono"));
                t.setFecNaci(rs.getDate("fecha_nacimiento"));
                t.setLugNaci(rs.getString("lugar_nacimiento"));
                t.setSexo(rs.getString("sexo"));
                t.setEscolaridad(rs.getString("estudios"));
                t.setEstadoCivil(rs.getString("estado_civil"));
                t.setActivo(rs.getBoolean("activo"));
                t.setFechaBaja(rs.getDate("fecha_baja"));
            }
            ps.close();
            rs.close();
            con.close();
            
        }catch(SQLException e){
            t=null;
            System.out.println("CrearEmpleado()" + sql);
            System.out.println("CrearEmpleado() " + e);
            Logger.getLogger(SQLRegistros.class.getName()).log(Level.SEVERE, null, e);
        }
        return t;
        
    }
    
    public TRABAJADOR buscar_datos_medicos(TRABAJADOR t){
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql = "SELECT * FROM personal_zurich.datos_medicos WHERE id ="+t.getId()+";";
        try{
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                t.setRuido(rs.getBoolean("ruido"));
                t.setPolvo(rs.getBoolean("polvo"));
                t.setGases(rs.getBoolean("gases"));
                t.setBiologico(rs.getBoolean("biologico"));
                t.setHumo(rs.getBoolean("humo"));
                t.setVapores(rs.getBoolean("vapores"));
                t.setSolventes(rs.getBoolean("solventes"));
                t.setMetales(rs.getBoolean("metales_pesado"));
                t.setTemperaturas(rs.getBoolean("temperaturas_altas"));
                t.setMovimiento(rs.getBoolean("movimientos_repetitivos"));
                t.setPosturas(rs.getBoolean("posturas"));
                t.setCargas(rs.getBoolean("cargas_pesadas"));
                //ANTECEDENTES HEREDOFAMILIARES
                t.setRespiratorio(rs.getInt("enfermedades_respiratorias"));
                t.setRespiratorioObser(rs.getString("enfermedades_respiratorias_observaciones"));
                t.setHematologico(rs.getInt("enfermedades_hematologicas"));
                t.setHematologicoObser(rs.getString("enfermedades_hematologicas_observaciones"));
                t.setEnfSexuales(rs.getInt("enfermedades_sexuales"));
                t.setEnfSexualesObser(rs.getString("enfermedades_sexuales_observaciones"));
                t.setNeurologico(rs.getInt("enfermedades_neurologicas"));
                t.setNeurologicoObser(rs.getString("enfermedades_neurologicas_observaciones"));
                t.setQuirurgicas(rs.getInt("enfermedades_inter_quirurgicas"));
                t.setQuirurgicasObs(rs.getString("enfermedades_inter_quirurgicas_observaciones"));
                t.setTransmitibles(rs.getInt("enfermedades_no_transmitibles"));
                t.setTransmitiblesObser(rs.getString("enfermedades_no_transmitibles_observaciones"));
                t.setPsiquiatrica(rs.getInt("enfermedades_psiquiatricas"));
                t.setPsiquiatricaObser(rs.getString("enfermedades_psiquiatricas_observaciones"));
                t.setDigestiva(rs.getInt("enfermedades_digestivas"));
                t.setDigestivaObser(rs.getString("enfermedades_digestivas_observaciones"));
                t.setAutoinmune(rs.getInt("enfermedades_autoinmunes"));
                t.setAutoinmuneObser(rs.getString("enfermedades_autoinmunes_observaciones"));
                t.setRenal(rs.getInt("enfermedades_renales"));
                t.setRenalObser(rs.getString("enfermedades_renales_observaciones"));
                t.setOncologico(rs.getInt("enfermedades_oncologicas"));
                t.setOncologicoObser(rs.getString("enfermedades_oncologicas_observaciones"));
                //HISTORIA DE HÁBITOS
                t.setActividad(rs.getBoolean("actividad_fisica"));
                t.setAlcohol(rs.getBoolean("alcohol"));
                t.setFumar(rs.getBoolean("fumar"));
                t.setDrogas(rs.getBoolean("drogas"));
                t.setTipoDorga(rs.getString("drogas_tipo"));
                //evaluacion medica
                t.setClinico(rs.getString("examen_clinico"));
                t.setPeso(rs.getFloat("peso"));
                t.setTalla(rs.getFloat("altura"));
                t.setFr(rs.getFloat("fr"));
                t.setFc(rs.getFloat("fc"));
                t.setpAbdominal(rs.getFloat("perimetro_abdominal"));
                t.setSnellen(rs.getString("test_snellen"));
                t.setOd(rs.getFloat("ojo_derecho"));
                t.setOi(rs.getFloat("ojo_izquierdo"));
                //exploracion fisica
                t.setPielCabello(rs.getString("piel_cabello"));
                t.setOjosAnexos(rs.getString("ojos_anexos"));
                t.setAparatoRespiratorio(rs.getString("aparato_respiratorio"));
                t.setCardiovascular(rs.getString("aparato_cardiovascular"));
                t.setDigestivo(rs.getString("aparato_digestivo"));
                t.setGenitourinario(rs.getString("aparato_urinario"));
                t.setMusculo(rs.getString("sistema_muscular"));
                t.setSisNervioso(rs.getString("sistema_nervioso"));
                
                t.setValoracioMedica(rs.getString("valoracion_medica"));
                t.setVacunacion(rs.getString("historial_vacunacion"));
                t.setMedicacion(rs.getString("medicacion"));
                t.setContactos(rs.getString("contactos_emergencia"));
                
            }
            
        }catch(SQLException e){
            System.out.println("buscar_datos_medicos" + sql);
            System.out.println("buscar_datos_medicos " + e);
            Logger.getLogger(SQLRegistros.class.getName()).log(Level.SEVERE, null, e);
            t=null;
        }
        
        return t;
    }
    
    public boolean guardar_datos_medicos(TRABAJADOR p){
        PreparedStatement ps = null;
        String sql = null;
        boolean respuesta=false;
        sql="INSERT INTO personal_zurich.datos_medicos\n values(?";
        for(int i=1;i<61;i++){
            sql+=",?";
        }
        sql+=");";
        try{
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setBoolean(2, p.isRuido());
            ps.setBoolean(3, p.isPolvo());
            ps.setBoolean(4, p.isGases());
            ps.setBoolean(5, p.isBiologico());
            ps.setBoolean(6, p.isHumo());
            ps.setBoolean(7, p.isVapores());
            ps.setBoolean(8, p.isSolventes());
            ps.setBoolean(9, p.isMetales());
            ps.setBoolean(10, p.isTemperaturas());
            ps.setBoolean(11, p.isMovimiento());
            ps.setBoolean(12, p.isPosturas());
            ps.setBoolean(13, p.isCargas());
            //14-15
            ps.setInt(14, p.getRespiratorio());
            if(p.getRespiratorio()!=0) ps.setString(15, p.getRespiratorioObser());
            else ps.setNull(15,Types.VARCHAR);
            //16-17
            ps.setInt(16, p.getHematologico());
            if(p.getHematologico()!=0) ps.setString(17, p.getHematologicoObser());
            else ps.setNull(17,Types.VARCHAR);
            //18-19
            ps.setInt(18, p.getEnfSexuales());
            if(p.getEnfSexuales()!=0) ps.setString(19, p.getEnfSexualesObser());
            else ps.setNull(19,Types.VARCHAR);
            //20-21
            ps.setInt(20, p.getNeurologico());
            if(p.getNeurologico()!=0) ps.setString(21, p.getNeurologicoObser());
            else ps.setNull(21,Types.VARCHAR);
            //22-23
             ps.setInt(22, p.getQuirurgicas());
            if(p.getTransmitibles()!=0) ps.setString(23, p.getQuirurgicasObs());
            else ps.setNull(23,Types.VARCHAR);
            //24-25
            ps.setInt(24, p.getTransmitibles());
            if(p.getTransmitibles()!=0) ps.setString(25, p.getTransmitiblesObser());
            else ps.setNull(25,Types.VARCHAR);
            //26-27
            ps.setInt(26, p.getPsiquiatrica());
            if(p.getTransmitibles()!=0) ps.setString(27, p.getPsiquiatricaObser());
            else ps.setNull(27,Types.VARCHAR);
            //28-29
            ps.setInt(28, p.getDigestiva());
            if(p.getTransmitibles()!=0) ps.setString(29, p.getDigestivaObser());
            else ps.setNull(29,Types.VARCHAR);
            //30-31
            ps.setInt(30, p.getAutoinmune());
            if(p.getTransmitibles()!=0) ps.setString(31, p.getAutoinmuneObser());
            else ps.setNull(31,Types.VARCHAR);
            //32-33
            ps.setInt(32, p.getRenal());
            if(p.getTransmitibles()!=0) ps.setString(33, p.getRenalObser());
            else ps.setNull(33,Types.VARCHAR);
            //34-35
            ps.setInt(34, p.getOncologico());
            if(p.getTransmitibles()!=0) ps.setString(35, p.getOncologicoObser());
            else ps.setNull(35,Types.VARCHAR);
            
            ps.setBoolean(36, p.isActividad());
            ps.setBoolean(37, p.isAlcohol());
            ps.setBoolean(38, p.isFumar());
            ps.setBoolean(39, p.isDrogas());
            ps.setString(40, p.getTipoDorga());
            ps.setString(41, p.getClinico());
            ps.setFloat(42, p.getTalla());
            ps.setFloat(43, p.getPeso());
            ps.setFloat(44, p.getFr());
            ps.setFloat(45, p.getFc());
            ps.setFloat(46, p.getpAbdominal());
            ps.setString(47, p.getSnellen());
            ps.setFloat(48,p.getOi());
            ps.setFloat(49,p.getOd());
            // exploracion fisica
            ps.setString(50, p.getPielCabello());
            ps.setString(51, p.getOjosAnexos());
            ps.setString(52, p.getAparatoRespiratorio());
            ps.setString(53, p.getCardiovascular());
            ps.setString(54, p.getDigestivo());
            ps.setString(55, p.getGenitourinario());
            ps.setString(56, p.getMusculo());
            ps.setString(57, p.getSisNervioso());
            
            
            ps.setString(58, p.getValoracioMedica());
            ps.setString(59, p.getVacunacion());
            ps.setString(60,p.getMedicacion());
            ps.setString(61,p.getContactos());
            
            ps.execute();
            ps.close();
            con.close();
            respuesta=true;
        }catch(SQLException e){
            System.out.println("guardar_datos_medicos() " + sql);
            System.out.println("guardar_datos_medicos() " + e);
            Logger.getLogger(SQLRegistros.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return respuesta;
    }
    
    
    public boolean actualizar_datos_medicos(TRABAJADOR p){
        PreparedStatement ps = null;
        String sql = null;
        boolean respuesta=false;
        sql="UPDATE personal_zurich.datos_medicos SET "
                + "ruido=?,polvo=?,gases=?,biologico=?,humo=?,vapores=?,solventes=?"
                + ",metales_pesado=?,temperaturas_altas=?,movimientos_repetitivos=?"
                + ",posturas=?"
                //ANTECEDENTES HEREDOFAMILIARES
                + "\n,cargas_pesadas=?,enfermedades_respiratorias=?"
                + ",enfermedades_respiratorias_observaciones=?,enfermedades_hematologicas=?"
                + ",enfermedades_hematologicas_observaciones=?,enfermedades_sexuales=?"
                + ",enfermedades_sexuales_observaciones=?,enfermedades_neurologicas=?"
                + ",enfermedades_neurologicas_observaciones=?,enfermedades_inter_quirurgicas=?"
                + ",enfermedades_inter_quirurgicas_observaciones=?,enfermedades_no_transmitibles=?"
                + ",enfermedades_no_transmitibles_observaciones=?,enfermedades_psiquiatricas=?"
                + ",enfermedades_psiquiatricas_observaciones=?,enfermedades_digestivas=?"
                + ",enfermedades_digestivas_observaciones=?,enfermedades_autoinmunes=?"
                + ",enfermedades_autoinmunes_observaciones=?,enfermedades_renales=?"
                + ",enfermedades_renales_observaciones=?,enfermedades_oncologicas=?"
                + ",enfermedades_oncologicas_observaciones=?"
                //HISTORIA DE HÁBITOS
                + "\n,actividad_fisica=?,alcohol=?,fumar=?,drogas=?,drogas_tipo=?"
                //EVALUACIÓN MÉDICA
                + ",examen_clinico=?,altura=?,peso=?,fr=?,fc=?,perimetro_abdominal=?"
                + ",test_snellen=?,ojo_izquierdo=?,ojo_derecho=?"
                //EXPLORACION FISICA
                + "\n,piel_cabello=?,ojos_anexos=?,aparato_respiratorio=?,aparato_cardiovascular=?"
                + ",aparato_digestivo=?,aparato_urinario=?,sistema_muscular=?,sistema_nervioso=?"
                //
                + ",valoracion_medica=?,historial_vacunacion=?,medicacion=?,contactos_emergencia=?"
                + "\nwhere id=?;";
        try{
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setBoolean(1, p.isRuido());
            ps.setBoolean(2, p.isPolvo());
            ps.setBoolean(3, p.isGases());
            ps.setBoolean(4, p.isBiologico());
            ps.setBoolean(5, p.isHumo());
            ps.setBoolean(6, p.isVapores());
            ps.setBoolean(7, p.isSolventes());
            ps.setBoolean(8, p.isMetales());
            ps.setBoolean(9, p.isTemperaturas());
            ps.setBoolean(10, p.isMovimiento());
            ps.setBoolean(11, p.isPosturas());
            ps.setBoolean(12, p.isCargas());
            //13-14
            ps.setInt(13, p.getRespiratorio());
            if(p.getRespiratorio()!=0) ps.setString(14, p.getRespiratorioObser());
            else ps.setNull(14,Types.VARCHAR);
            //15-16
            ps.setInt(15, p.getHematologico());
            if(p.getHematologico()!=0) ps.setString(16, p.getHematologicoObser());
            else ps.setNull(16,Types.VARCHAR);
            //17-18
            ps.setInt(17, p.getEnfSexuales());
            if(p.getEnfSexuales()!=0) ps.setString(18, p.getEnfSexualesObser());
            else ps.setNull(18,Types.VARCHAR);
            //19-20
            ps.setInt(19, p.getNeurologico());
            if(p.getNeurologico()!=0) ps.setString(20, p.getNeurologicoObser());
            else ps.setNull(20,Types.VARCHAR);
            //21-22
             ps.setInt(21, p.getQuirurgicas());
            if(p.getTransmitibles()!=0) ps.setString(22, p.getQuirurgicasObs());
            else ps.setNull(23,Types.VARCHAR);
            //23-24
            ps.setInt(23, p.getTransmitibles());
            if(p.getTransmitibles()!=0) ps.setString(24, p.getTransmitiblesObser());
            else ps.setNull(24,Types.VARCHAR);
            //25-26
            ps.setInt(25, p.getPsiquiatrica());
            if(p.getTransmitibles()!=0) ps.setString(26, p.getPsiquiatricaObser());
            else ps.setNull(26,Types.VARCHAR);
            //27-28
            ps.setInt(27, p.getDigestiva());
            if(p.getTransmitibles()!=0) ps.setString(28, p.getDigestivaObser());
            else ps.setNull(28,Types.VARCHAR);
            //29-30
            ps.setInt(29, p.getAutoinmune());
            if(p.getTransmitibles()!=0) ps.setString(30, p.getAutoinmuneObser());
            else ps.setNull(30,Types.VARCHAR);
            //31-32
            ps.setInt(31, p.getRenal());
            if(p.getTransmitibles()!=0) ps.setString(32, p.getRenalObser());
            else ps.setNull(32,Types.VARCHAR);
            //33-34
            ps.setInt(33, p.getOncologico());
            if(p.getTransmitibles()!=0) ps.setString(34, p.getOncologicoObser());
            else ps.setNull(34,Types.VARCHAR);
            
            ps.setBoolean(35, p.isActividad());
            ps.setBoolean(36, p.isAlcohol());
            ps.setBoolean(37, p.isFumar());
            ps.setBoolean(38, p.isDrogas());
            ps.setString(39, p.getTipoDorga());
            ps.setString(40, p.getClinico());
            ps.setFloat(41, p.getTalla());
            ps.setFloat(42, p.getPeso());
            ps.setFloat(43, p.getFr());
            ps.setFloat(44, p.getFc());
            ps.setFloat(45, p.getpAbdominal());
            ps.setString(46, p.getSnellen());
            ps.setFloat(47,p.getOi());
            ps.setFloat(48,p.getOd());
            // exploracion fisica
            ps.setString(49, p.getPielCabello());
            ps.setString(50, p.getOjosAnexos());
            ps.setString(51, p.getAparatoRespiratorio());
            ps.setString(52, p.getCardiovascular());
            ps.setString(53, p.getDigestivo());
            ps.setString(54, p.getGenitourinario());
            ps.setString(55, p.getMusculo());
            ps.setString(56, p.getSisNervioso());
            
            
            ps.setString(57, p.getValoracioMedica());
            ps.setString(58, p.getVacunacion());
            ps.setString(59,p.getMedicacion());
            ps.setString(60,p.getContactos());
            
            ps.setInt(61, p.getId());
            
            ps.execute();
            ps.close();
            con.close();
            respuesta=true;
        }catch(SQLException e){
            System.out.println("actualizar_datos_medicos " + sql);
            System.out.println("actualizar_datos_medicos " + e);
            Logger.getLogger(SQLRegistros.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return respuesta;
    }
    public boolean guardarConsulta(int id, CONSULTA c){
        PreparedStatement ps = null;
        String sql = null;
        boolean respuesta=false;
        sql="INSERT INTO personal_zurich.consultas_enfermeria"
        + "(id_empleado,sintomas,signos_vitales,medicamento_suministrado,diagnistico,hospital) "
        + "VALUES(?,?,?,?,?,?);";
        try{
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, c.getSintomas());
            ps.setString(3, c.getSignos_Vitales());
            ps.setString(4, c.getMedicamento());
            ps.setString(5, c.getDiagnistico());
            ps.setBoolean(6, c.isHospital());
            
            ps.execute();
            ps.close();
            con.close();
            respuesta=true;            
        }catch(SQLException e){
            System.out.println("guardarConsulta " + sql);
            System.out.println("guardarConsulta " + e);
            Logger.getLogger(SQLRegistros.class.getName()).log(Level.SEVERE, null, e);  
        }
        return respuesta;
    }
    public ArrayList<CONSULTA> obtenerConsultas(int id){
        PreparedStatement ps = null;
        String sql = null;
        ResultSet rs=null;
        ArrayList<CONSULTA> consultas=new ArrayList<>();
        sql="SELECT * FROM personal_zurich.consultas_enfermeria WHERE id_empleado ="+id+";";
        try{
            Connection con = PRINCIPAL.dataSource.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Date fecha=rs.getDate("fecha_consulta");
                String sintomas=rs.getString("sintomas");
                String diagnostico=rs.getString("diagnistico");
                String signosVitales=rs.getString("signos_vitales");
                String medicamento=rs.getString("medicamento_suministrado");
                boolean hospital=rs.getBoolean("hospital");
                CONSULTA c=new CONSULTA(fecha,sintomas,diagnostico,signosVitales,medicamento,hospital);
                consultas.add(c);
            }
            
        }catch(SQLException e){
            System.out.println("guardarConsulta " + sql);
            System.out.println("guardarConsulta " + e);
            Logger.getLogger(SQLRegistros.class.getName()).log(Level.SEVERE, null, e);  
        }
        return consultas;
    }
    
}
