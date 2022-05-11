package VISUAL;

import CLASES.CONSULTA;
import CLASES.TRABAJADOR;
import SQL.SQLRegistros;
import static javax.swing.JOptionPane.showMessageDialog;
import org.apache.commons.dbcp2.BasicDataSource;
import static auxiliar.auxiliares.calcularIMC;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PRINCIPAL extends javax.swing.JFrame {
    
    public static BasicDataSource dataSource = null;
    public static POOLCONEXIONES conexiones;
    TRABAJADOR trabajador=null;
    TableColumnModel columnModel = null;
    SimpleDateFormat fSal = new SimpleDateFormat("dd/MM/YYYY");
    public static CellRenderConsultas colorFilar = new CellRenderConsultas();
    DefaultTableModel dftm = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            if (columna == jTable1.getColumnCount()) {
                return true;
            }
            return false;
        }
    };
    
    public PRINCIPAL() {
        initComponents();
        
        this.getContentPane().setBackground( Color.WHITE );
        this.PlOGO.setBackgroung("IMAGENES/LOGOZURICH.png");       
        butBuscar.setBackgroung("IMAGENES/ICONOS/lupa.png");
        conexiones = new POOLCONEXIONES();
        dataSource = conexiones.hacerpool();
        if(dataSource==null){
            this.dispose();
        }
        crearTabla();
        Calendar c2 = new GregorianCalendar();
        dcFecha_Consulta.setDate(c2.getTime());
    }
    @Override
        public Image getIconImage() {
            Image retValue = Toolkit.getDefaultToolkit().
            getImage(ClassLoader.getSystemResource("IMAGENES/logoEnfermeria.png"));
            return retValue;
        }
    
    public void crearTabla() {
        columnModel = jTable1.getColumnModel();
        jTable1.setModel(dftm);
        dftm.setColumnIdentifiers(new Object[]{"Fecha","sintomas","Signos Vitales","Medicamento suministrado","Diagnostico","Hos"});
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(202);
        columnModel.getColumn(2).setPreferredWidth(105);
        columnModel.getColumn(3).setPreferredWidth(230);
        columnModel.getColumn(4).setPreferredWidth(257);
        columnModel.getColumn(5).setPreferredWidth(36);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setDefaultRenderer(jTable1.getColumnClass(0), colorFilar);
        
    }
    
    void borrarTabla() {
        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            this.dftm.removeRow(i);
            i--;
        }
    }
    public void actualizarObjeto(){
        //datos de empleado
        trabajador.setArea(txtArea.getText().toUpperCase());
        trabajador.setActivo(!cbBaja.isSelected());
        if(!cbBaja.isSelected()){
            trabajador.setFechaBaja(null);
        }else{
            trabajador.setFechaBaja(dcBaja.getDate());
        }
        //datos generales
        trabajador.setTelefono(txtTelefono.getText());
        trabajador.setEscolaridad(cbEscolaridad.getSelectedItem().toString());
        trabajador.setEstadoCivil(cbCivil.getSelectedItem().toString());
        //datos contaminantes
        trabajador.setRuido(cbRuido.isSelected());
        trabajador.setPolvo(cbPolvo.isSelected());
        trabajador.setGases(cbGases.isSelected());
        trabajador.setBiologico(cbBiologicos.isSelected());
        trabajador.setHumo(cbHumo.isSelected());
        trabajador.setVapores(bcVapores.isSelected());
        trabajador.setSolventes(cbSolventes.isSelected());
        trabajador.setMetales(cbMetales.isSelected());
        trabajador.setTemperaturas(cbTemperaturas.isSelected());
        trabajador.setMovimiento(cbMovimientos.isSelected());
        trabajador.setPosturas(cbPosturas.isSelected());
        trabajador.setCargas(cbCarga.isSelected());
        //antecedentes Heredofamiliares
        trabajador.setRespiratorio(antecedentes(cbRespiratorias1,cbRespiratorias2));
        trabajador.setRespiratorioObser(txtRespiratorias.getText().toUpperCase());
        trabajador.setHematologico(antecedentes(cbHematologicos1,cbHematologicos2));
        trabajador.setHematologicoObser(txtHematologicos.getText().toUpperCase());
        trabajador.setEnfSexuales(antecedentes(cbSexual1,cbSexual2));
        trabajador.setEnfSexualesObser(txtSexual.getText().toUpperCase());
        trabajador.setNeurologico(antecedentes(cbNeurologico1,cbNeurologico2));
        trabajador.setNeurologicoObser(txtNeurologico.getText().toUpperCase());
        trabajador.setQuirurgicas(antecedentes(cbQuirurgicas1,cbQuirurgicas2));
        trabajador.setQuirurgicasObs(txtQuirurgicas.getText().toUpperCase());
        trabajador.setTransmitibles(antecedentes(cbTransmitibles1,cbTransmitibles2));
        trabajador.setTransmitiblesObser(txtTransmitibles.getText().toUpperCase());
        trabajador.setPsiquiatrica(antecedentes(cbPsiquiatricas1,cbPsiquiatricas2));
        trabajador.setPsiquiatricaObser(txtPsiquiatricas.getText().toUpperCase());
        trabajador.setDigestiva(antecedentes(cbDigestivas1,cbDigestivas2));
        trabajador.setDigestivaObser(txtDigestivas.getText().toUpperCase());
        trabajador.setAutoinmune(antecedentes(cbAutoinmunes1,cbAutoinmunes2));
        trabajador.setAutoinmuneObser(txtAutoinmunes.getText().toUpperCase());
        trabajador.setRenal(antecedentes(cbRenales1,cbRenales2));
        trabajador.setRenalObser(txtRenales.getText().toUpperCase());
        trabajador.setOncologico(antecedentes(cbOncologicos1,cbOncologicos2));
        trabajador.setOncologicoObser(txtOncologicos.getText().toUpperCase());
        //Habitos
        trabajador.setActividad(rbActividadSi.isSelected());
        trabajador.setAlcohol(rbAlcoholSi.isSelected());
        trabajador.setFumar(rbFumaSi.isSelected());
        trabajador.setDrogas(rbDrogasSi.isSelected());
        if(!rbDrogasSi.isSelected()){
            trabajador.setTipoDorga(null);
        }else{
            trabajador.setTipoDorga(txtDrogasTipo.getText().toUpperCase());
        }
        
        //evaluacion medica
        trabajador.setClinico(txtExamenClinico.getText().toUpperCase());
        trabajador.setTalla(validarFloat(txtTalla));
        trabajador.setPeso(validarFloat(txtPeso));
        trabajador.setFc(validarFloat(txtFr));
        trabajador.setFr(validarFloat(txtFc));
        trabajador.setpAbdominal(validarFloat(txtpAbd));
        trabajador.setOi(validarFloat(txtOi));
        trabajador.setOd(validarFloat(txtOd));
        
        trabajador.setSnellen(txtSnellen.getText().toUpperCase());
        //exploracion fisica
        trabajador.setPielCabello(txtCabello.getText().toUpperCase());
        trabajador.setOjosAnexos(txtOjos.getText().toUpperCase());
        trabajador.setAparatoRespiratorio(txtRespiratorio.getText().toUpperCase());
        trabajador.setCardiovascular(txtCardio.getText().toUpperCase());
        trabajador.setDigestivo(txtDigestivo.getText().toUpperCase());
        trabajador.setGenitourinario(txtgenito.getText().toUpperCase());
        trabajador.setMusculo(txtMusculo.getText().toUpperCase());
        
        trabajador.setValoracioMedica(txtValoracion.getText().toUpperCase());
        trabajador.setVacunacion(txtVacucnacion.getText().toUpperCase());
        trabajador.setMedicacion(txtMedicacion.getText().toUpperCase());
        trabajador.setContactos(txtContactos.getText().toUpperCase());
        
    }
    
    
        public int antecedentes(JCheckBox cb1,JCheckBox cb2){
            int retorno=0;
            if(cb1.isSelected())retorno=1;
            
            if(cb2.isSelected())retorno=2;
            
            if(cb1.isSelected()&&cb2.isSelected()) retorno=3;
            return retorno;
        }
        
        public float llamarIMC(){
            float imc=-1;
            if(txtTalla.getText().length()>0&&txtPeso.getText().length()>0){
                try{
                    float talla=Float.parseFloat(txtTalla.getText());
                    float peso=Float.parseFloat(txtPeso.getText());
                    if(talla>0&&peso>0){
                        imc=calcularIMC(talla,peso);
                        txtIMC.setText(String.valueOf(imc));
                    }
                }catch(NumberFormatException e){
                    System.out.println(e);
                }
            }
            return imc;
        }
        
        public float validarFloat(JTextField txt){
            float retorno=-1;
            try{
                txt.setText(txt.getText().replaceAll("^\\s*", ""));
                if(txt.getText().length()>0) 
                    retorno=Float.parseFloat(txt.getText());
                else retorno=0;
                
            }catch(NumberFormatException e){
                System.out.println(e);
                retorno=-1;
            }
            return retorno;
        }
        
        public boolean validarDatosConsulta(){
            txtSintomas.setText(txtSintomas.getText().replaceAll("^\\s*", ""));
            txtDiagnostico.setText(txtDiagnostico.getText().replaceAll("^\\s*", ""));
            if(txtSintomas.getText().length()>0&&
                    txtDiagnostico.getText().length()>0)
                return true;
            
            else return false;
        }
        
    
    
    public void llenarCampos(TRABAJADOR t){
        txtNombre.setText(t.getNombre()+" "+t.getApellido1()+" "+t.getApellido2());
        txtArea2.setText(t.getArea());        
        dcNacimiento.setDate(t.getFecNaci());
        txtLugNacimiento.setText(t.getLugNaci());
        cbSexo.setSelectedItem(t.getSexo());
        txtTelefono.setText(t.getTelefono());
        cbEscolaridad.setSelectedItem(t.getEscolaridad());
        cbCivil.setSelectedItem(t.getEstadoCivil());
        dcIngreso.setDate(t.getFechaIngreso());
        txtArea.setText(t.getArea());
        cbBaja.setSelected(!t.isActivo());
        dcBaja.setEnabled(false);
        
        if(!t.isActivo()) dcBaja.setDate(t.getFechaBaja());
        else dcBaja.setDate(null);
        cbRuido.setSelected(t.isRuido());
        cbPolvo.setSelected(t.isPolvo());
        cbGases.setSelected(t.isGases());
        cbHumo.setSelected(t.isHumo());
        bcVapores.setSelected(t.isVapores());
        cbSolventes.setSelected(t.isSolventes());
        cbMetales.setSelected(t.isMetales());
        cbTemperaturas.setSelected(t.isTemperaturas());
        cbMovimientos.setSelected(t.isMovimiento());
        cbPosturas.setSelected(t.isPosturas());
        cbCarga.setSelected(t.isCargas());
        //ANTECEDENTES HEREDOFAMILIARES
        txtRespiratorias.setText(t.getRespiratorioObser());
        if(t.getRespiratorio()==1||t.getRespiratorio()==3)
            cbRespiratorias1.setSelected(true);
        if(t.getRespiratorio()==2||t.getRespiratorio()==3)
            cbRespiratorias2.setSelected(true);
        
        txtHematologicos.setText(t.getHematologicoObser());
        if(t.getHematologico()==1||t.getHematologico()==3)
            cbHematologicos1.setSelected(true);
        if(t.getHematologico()==2||t.getHematologico()==3)
            cbHematologicos2.setSelected(true);
        
        txtSexual.setText(t.getEnfSexualesObser());
        if(t.getEnfSexuales()==1||t.getEnfSexuales()==3)
            cbSexual1.setSelected(true);
        if(t.getEnfSexuales()==2||t.getEnfSexuales()==3)
            cbSexual2.setSelected(true);
        
        txtNeurologico.setText(t.getNeurologicoObser());
        if(t.getNeurologico()==1||t.getNeurologico()==3)
            cbNeurologico1.setSelected(true);
        if(t.getNeurologico()==2||t.getNeurologico()==3)
            cbNeurologico2.setSelected(true);
        
        txtQuirurgicas.setText(t.getQuirurgicasObs());
        if(t.getQuirurgicas()==1||t.getQuirurgicas()==3)
            cbQuirurgicas1.setSelected(true);
        if(t.getQuirurgicas()==2||t.getQuirurgicas()==3)
            cbQuirurgicas2.setSelected(true);
        
        txtTransmitibles.setText(t.getTransmitiblesObser());
        if(t.getTransmitibles()==1||t.getTransmitibles()==3)
            cbTransmitibles1.setSelected(true);
        if(t.getTransmitibles()==2||t.getTransmitibles()==3)
            cbTransmitibles2.setSelected(true);
        
        txtPsiquiatricas.setText(t.getPsiquiatricaObser());
        if(t.getPsiquiatrica()==1||t.getPsiquiatrica()==3)
            cbPsiquiatricas1.setSelected(true);
        if(t.getPsiquiatrica()==2||t.getPsiquiatrica()==3)
            cbPsiquiatricas2.setSelected(true);
        
        txtDigestivas.setText(t.getDigestivaObser());
        if(t.getDigestiva()==1||t.getDigestiva()==3)
            cbDigestivas1.setSelected(true);
        if(t.getDigestiva()==2||t.getDigestiva()==3)
            cbDigestivas2.setSelected(true);
        
        txtAutoinmunes.setText(t.getAutoinmuneObser());
        if(t.getAutoinmune()==1||t.getAutoinmune()==3)
            cbAutoinmunes1.setSelected(true);
        if(t.getAutoinmune()==2||t.getAutoinmune()==3)
            cbAutoinmunes2.setSelected(true);
        
        txtRenales.setText(t.getRenalObser());
        if(t.getRenal()==1||t.getRenal()==3)
            cbRenales1.setSelected(true);
        if(t.getRenal()==2||t.getRenal()==3)
            cbRenales2.setSelected(true);
        
        txtOncologicos.setText(t.getOncologicoObser());
        if(t.getOncologico()==1||t.getOncologico()==3)
            cbOncologicos1.setSelected(true);
        if(t.getOncologico()==2||t.getOncologico()==3)
            cbOncologicos2.setSelected(true);
        
        //HISTORIA DE HÁBITOS
        if(t.isActividad()){
            rbActividadSi.setSelected(true);
            rbActividadNo.setSelected(false);
        }else{
            rbActividadSi.setSelected(false);
            rbActividadNo.setSelected(true);
        }
        if(t.isActividad()){
            rbActividadSi.setSelected(true);
            rbActividadNo.setSelected(false);
        }else{
            rbActividadSi.setSelected(false);
            rbActividadNo.setSelected(true);
        }
        
        if(t.isAlcohol()){
            rbAlcoholSi.setSelected(true);
            rbAlcoholNo.setSelected(false);
        }else{
            rbAlcoholSi.setSelected(false);
            rbAlcoholNo.setSelected(true);
        }
        
        if(t.isFumar()){
            rbFumaSi.setSelected(true);
            rbFumaNo.setSelected(false);
        }else{
            rbFumaSi.setSelected(false);
            rbFumaNo.setSelected(true);
        }
        
        if(t.isDrogas()){
            rbDrogasSi.setSelected(true);
            rbDrogasNo.setSelected(false);
            txtDrogasTipo.setText(t.getTipoDorga());
        }else{
            rbDrogasSi.setSelected(false);
            rbDrogasNo.setSelected(true);
        }
        //EVALUACIÓN MÉDICA
        txtExamenClinico.setText(t.getClinico());
        txtTalla.setText(String.valueOf(t.getTalla()));
        txtPeso.setText(String.valueOf(t.getPeso()));
        llamarIMC();
        txtFr.setText(String.valueOf(t.getFr()));
        txtFc.setText(String.valueOf(t.getFc()));
        txtpAbd.setText(String.valueOf(t.getpAbdominal()));
        txtSnellen.setText(t.getSnellen());
        txtOi.setText(String.valueOf(t.getOi()));
        txtOd.setText(String.valueOf(t.getOd()));
        //EXPLORACION FISICA
        txtCabello.setText(t.getPielCabello());
        txtOjos.setText(t.getOjosAnexos());
        txtRespiratorio.setText(t.getAparatoRespiratorio());
        txtCardio.setText(t.getCardiovascular());
        txtDigestivo.setText(t.getDigestivo());
        txtgenito.setText(t.getGenitourinario());
        txtMusculo.setText(t.getMusculo());
        txtNervioso.setText(t.getSisNervioso());
        //
        txtValoracion.setText(t.getValoracioMedica());
        txtVacucnacion.setText(t.getVacunacion());
        txtMedicacion.setText(t.getMedicacion());
        txtContactos.setText(t.getContactos());
        llenarTabla();
        
    }
    
    public CONSULTA crear_consulta(){
        String sintomas=txtSintomas.getText();
        String signos_vitales=txtSignos_vitales.getText();
        String medicamento=txtMedicamento.getText();
        String diagnostico=txtDiagnostico.getText();
        boolean hospita=cbHospitalización.isSelected();
        CONSULTA c=new CONSULTA(sintomas,diagnostico,signos_vitales,medicamento,hospita);
        return c;
    }
    public void llenarTabla(){
        borrarTabla();
        ArrayList<CONSULTA> lista =trabajador.getConsultas();
        for(CONSULTA c:lista){
            String h;
            
            if(c.isHospital())h="si";
                    else h="no";
            
            dftm.addRow(new Object[]{fSal.format(c.getFecha()),c.getSintomas(),c.getSignos_Vitales()
            ,c.getMedicamento(),c.getDiagnistico(),h});
        }
    }
    
    //borra todos los campos 
    public void borrarCampos(){
        txtNoEmpleado.setText("");
        trabajador=null;
        txtNombre.setText("-");
        txtArea2.setText("-");
        
        dcNacimiento.setDate(null);
        txtLugNacimiento.setText("");
        txtTelefono.setText("");
        cbEscolaridad.setSelectedItem("");
        cbCivil.setSelectedItem("");
        dcIngreso.setDate(null);
        txtArea.setText("");
        cbBaja.setSelected(false);
        dcBaja.setEnabled(false);
         dcBaja.setDate(null);
         
        cbRuido.setSelected(false);
        cbPolvo.setSelected(false);
        cbGases.setSelected(false);
        cbHumo.setSelected(false);
        bcVapores.setSelected(false);
        cbSolventes.setSelected(false);
        cbMetales.setSelected(false);
        cbTemperaturas.setSelected(false);
        cbMovimientos.setSelected(false);
        cbPosturas.setSelected(false);
        cbCarga.setSelected(false);
        //ANTECEDENTES HEREDOFAMILIARES
        txtRespiratorias.setText("");
            cbRespiratorias1.setSelected(false);
            cbRespiratorias2.setSelected(false);
        
        txtHematologicos.setText("");
            cbHematologicos1.setSelected(false);
            cbHematologicos2.setSelected(false);
        
        txtSexual.setText("");
            cbSexual1.setSelected(false);
            cbSexual2.setSelected(false);
        
        txtNeurologico.setText("");
            cbNeurologico1.setSelected(false);
            cbNeurologico2.setSelected(false);
        
        txtQuirurgicas.setText("");
            cbQuirurgicas1.setSelected(false);
            cbQuirurgicas2.setSelected(false);
        
        txtTransmitibles.setText("");
            cbTransmitibles1.setSelected(false);
            cbTransmitibles2.setSelected(false);
        
        txtPsiquiatricas.setText("");
            cbPsiquiatricas1.setSelected(false);
            cbPsiquiatricas2.setSelected(false);
        
        txtDigestivas.setText("");
            cbDigestivas1.setSelected(false);
            cbDigestivas2.setSelected(false);
        
        txtAutoinmunes.setText("");
            cbAutoinmunes1.setSelected(false);
            cbAutoinmunes2.setSelected(false);
        
        txtRenales.setText("");
            cbRenales1.setSelected(false);
            cbRenales2.setSelected(false);
        
        txtOncologicos.setText("");
            cbOncologicos1.setSelected(false);
            cbOncologicos2.setSelected(false);
        
        //HISTORIA DE HÁBITOS
            rbActividadSi.setSelected(false);
            rbActividadNo.setSelected(true);
            
            rbActividadSi.setSelected(false);
            rbActividadNo.setSelected(true);
                
            rbAlcoholSi.setSelected(false);
            rbAlcoholNo.setSelected(true);
        
            rbFumaSi.setSelected(false);
            rbFumaNo.setSelected(true);
        
            txtDrogasTipo.setText("");
            rbDrogasSi.setSelected(false);
            rbDrogasNo.setSelected(true);
        
        //EVALUACIÓN MÉDICA
        txtExamenClinico.setText("");
        txtTalla.setText("");
        txtPeso.setText("");
        txtIMC.setText("");
        txtFr.setText("");
        txtFc.setText("");
        txtpAbd.setText("");
        txtSnellen.setText("");
        txtOi.setText("");
        txtOd.setText("");
        //EXPLORACION FISICA
        txtCabello.setText("");
        txtOjos.setText("");
        txtRespiratorio.setText("");
        txtCardio.setText("");
        txtDigestivo.setText("");
        txtgenito.setText("");
        txtMusculo.setText("");
        txtNervioso.setText("");
        //
        txtValoracion.setText("");
        txtVacucnacion.setText("");
        txtMedicacion.setText("");
        txtContactos.setText("");
        borrarConsulta();
        
    }
    
    public void borrarConsulta(){
        txtSintomas.setText("");
        txtSignos_vitales.setText("");
        txtMedicamento.setText("");
        txtDiagnostico.setText("");
        borrarTabla();
    }
    
    public void buscarEmpleadoID(){
        try{
            int a=Integer.valueOf(txtNoEmpleado.getText());
            if(a>0){
                SQLRegistros mod=new SQLRegistros();
                trabajador=mod.buscarEmpleadoID(a);
                trabajador=mod.buscar_datos_medicos(trabajador);
                trabajador.setConsultas(mod.obtenerConsultas(trabajador.getId()));
                if(trabajador.getId()<1){
                    showMessageDialog(null, "Trabajador no valido");
                }else{
                    llenarCampos(trabajador);
                }
            }
        }catch(Exception e){
            
        }
    }
    public boolean validaciones(){
        String error="";
        if(cbBaja.isSelected()){
             if(dcBaja.getDate()==null)
                error+="\nFecha de baja no valida";
        }
        
        if(error.length()==0){return true;}
            else {
                showMessageDialog(null, "Error "+error);
                return false;
            }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTitulo = new javax.swing.JPanel();
        PlOGO = new IMAGENES.IMAGEN();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        panel1 = new java.awt.Panel();
        jLabel5 = new javax.swing.JLabel();
        dcFecha_Consulta = new com.toedter.calendar.JDateChooser();
        cbHospitalización = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSintomas = new javax.swing.JTextArea();
        jLabel61 = new javax.swing.JLabel();
        txtSignos_vitales = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtMedicamento = new javax.swing.JTextField();
        jlabel63 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtDiagnostico = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        panel2 = new java.awt.Panel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dcNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtLugNacimiento = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        cbCivil = new javax.swing.JComboBox<>();
        jLabel67 = new javax.swing.JLabel();
        cbEscolaridad = new javax.swing.JComboBox<>();
        jLabel68 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        dcIngreso = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        cbBaja = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        dcBaja = new com.toedter.calendar.JDateChooser();
        cbRuido = new javax.swing.JCheckBox();
        cbPolvo = new javax.swing.JCheckBox();
        cbGases = new javax.swing.JCheckBox();
        cbBiologicos = new javax.swing.JCheckBox();
        cbHumo = new javax.swing.JCheckBox();
        bcVapores = new javax.swing.JCheckBox();
        cbSolventes = new javax.swing.JCheckBox();
        cbMetales = new javax.swing.JCheckBox();
        cbTemperaturas = new javax.swing.JCheckBox();
        cbMovimientos = new javax.swing.JCheckBox();
        cbPosturas = new javax.swing.JCheckBox();
        cbCarga = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        cbRespiratorias1 = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbRespiratorias2 = new javax.swing.JCheckBox();
        txtRespiratorias = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cbHematologicos1 = new javax.swing.JCheckBox();
        cbHematologicos2 = new javax.swing.JCheckBox();
        txtHematologicos = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        cbSexual1 = new javax.swing.JCheckBox();
        cbSexual2 = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        cbNeurologico1 = new javax.swing.JCheckBox();
        cbNeurologico2 = new javax.swing.JCheckBox();
        jLabel22 = new javax.swing.JLabel();
        cbQuirurgicas1 = new javax.swing.JCheckBox();
        cbQuirurgicas2 = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        cbTransmitibles1 = new javax.swing.JCheckBox();
        cbTransmitibles2 = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        cbPsiquiatricas1 = new javax.swing.JCheckBox();
        cbPsiquiatricas2 = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        cbDigestivas1 = new javax.swing.JCheckBox();
        cbDigestivas2 = new javax.swing.JCheckBox();
        jLabel26 = new javax.swing.JLabel();
        cbAutoinmunes1 = new javax.swing.JCheckBox();
        cbAutoinmunes2 = new javax.swing.JCheckBox();
        jLabel27 = new javax.swing.JLabel();
        cbRenales1 = new javax.swing.JCheckBox();
        cbRenales2 = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        cbOncologicos1 = new javax.swing.JCheckBox();
        cbOncologicos2 = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
        rbActividadSi = new javax.swing.JRadioButton();
        rbActividadNo = new javax.swing.JRadioButton();
        jLabel31 = new javax.swing.JLabel();
        rbAlcoholSi = new javax.swing.JRadioButton();
        rbAlcoholNo = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        rbFumaSi = new javax.swing.JRadioButton();
        rbFumaNo = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        rbDrogasSi = new javax.swing.JRadioButton();
        rbDrogasNo = new javax.swing.JRadioButton();
        jLabel34 = new javax.swing.JLabel();
        txtDrogasTipo = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtExamenClinico = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSnellen = new javax.swing.JTextArea();
        jLabel39 = new javax.swing.JLabel();
        txtTalla = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtIMC = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtpAbd = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtFc = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtFr = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtOd = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtOi = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel49 = new javax.swing.JLabel();
        txtCabello = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtOjos = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtRespiratorio = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtCardio = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtDigestivo = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtgenito = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtMusculo = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtNervioso = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtVacucnacion = new javax.swing.JTextArea();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtValoracion = new javax.swing.JTextArea();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtMedicacion = new javax.swing.JTextArea();
        jLabel59 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtContactos = new javax.swing.JTextArea();
        jLabel60 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txtSexual = new javax.swing.JTextField();
        txtNeurologico = new javax.swing.JTextField();
        txtQuirurgicas = new javax.swing.JTextField();
        txtTransmitibles = new javax.swing.JTextField();
        txtPsiquiatricas = new javax.swing.JTextField();
        txtDigestivas = new javax.swing.JTextField();
        txtAutoinmunes = new javax.swing.JTextField();
        txtRenales = new javax.swing.JTextField();
        txtOncologicos = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtNoEmpleado = new javax.swing.JTextField();
        butBuscar = new IMAGENES.IMAGEN();
        txtNombre = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtArea2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setResizable(false);

        jTitulo.setBackground(new java.awt.Color(255, 255, 255));

        PlOGO.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Aharoni", 1, 24)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("ZURICH MUEBLES ");
        jTextField1.setBorder(null);
        jTextField1.setFocusable(false);

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Aharoni", 1, 24)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("ENFERMERIA");
        jTextField2.setBorder(null);
        jTextField2.setFocusable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jTituloLayout = new javax.swing.GroupLayout(jTitulo);
        jTitulo.setLayout(jTituloLayout);
        jTituloLayout.setHorizontalGroup(
            jTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTituloLayout.createSequentialGroup()
                .addComponent(PlOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2))
                .addContainerGap())
        );
        jTituloLayout.setVerticalGroup(
            jTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PlOGO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jTituloLayout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Fecha");

        dcFecha_Consulta.setDateFormatString("dd MMM yyy");
        dcFecha_Consulta.setEnabled(false);
        dcFecha_Consulta.setFocusable(false);

        cbHospitalización.setText("Hospitalización");
        cbHospitalización.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHospitalizaciónActionPerformed(evt);
            }
        });

        jLabel4.setText("Sintomas");

        txtSintomas.setColumns(20);
        txtSintomas.setRows(2);
        jScrollPane1.setViewportView(txtSintomas);

        jLabel61.setText("Signos Vitales");

        jLabel62.setText("Medicamento");

        jlabel63.setText("Diagnostico");

        txtDiagnostico.setColumns(20);
        txtDiagnostico.setRows(2);
        jScrollPane10.setViewportView(txtDiagnostico);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setEnabled(false);
        jScrollPane11.setViewportView(jTable1);

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(dcFecha_Consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(cbHospitalización)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 415, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane1)))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(txtSignos_vitales, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel62)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMedicamento))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane10)))))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcFecha_Consulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbHospitalización, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addGap(9, 9, 9)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(txtSignos_vitales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(txtMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10)
                    .addComponent(jlabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Consulta", panel1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel6.setText("Fecha de nacimiento");

        dcNacimiento.setDateFormatString("dd/MM/yyyy");
        dcNacimiento.setEnabled(false);
        dcNacimiento.setOpaque(false);

        jLabel7.setText("Sexo");

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hombre", "Mujer" }));
        cbSexo.setEnabled(false);

        jLabel8.setText("Lugar de nacimiento");

        txtLugNacimiento.setEditable(false);

        jLabel66.setText("Estado civil");

        cbCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soltero", "Casado", "Divorciado", "Union Libre" }));

        jLabel67.setText("Escolaridad");

        cbEscolaridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primaria", "Secundaria", "Bachillerato", "Tecnico", "Profesional" }));

        jLabel68.setText("Telefono");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setText("DESCRIPCIÓN DEL CARGO");

        jLabel9.setText("Fecha de ingreso");

        dcIngreso.setDateFormatString("dd/MM/yyyy");
        dcIngreso.setEnabled(false);

        jLabel10.setText("Area");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("EXPOSICIÓN A FACTORES CONTAMINANTES");

        cbBaja.setText("Baja");
        cbBaja.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbBajaItemStateChanged(evt);
            }
        });

        jLabel12.setText("Fecha de baja");

        dcBaja.setDateFormatString("dd/MM/yyyy");
        dcBaja.setEnabled(false);

        cbRuido.setText("Ruido");

        cbPolvo.setText("Polvo");

        cbGases.setText("Gases");

        cbBiologicos.setText("Biológicos");

        cbHumo.setText("Humo");

        bcVapores.setText("Vapores");

        cbSolventes.setText("Solventes");

        cbMetales.setText("Metales pesados");

        cbTemperaturas.setText("Temperaturas");

        cbMovimientos.setText("Movimientos repetitivos");

        cbPosturas.setText("Posturas");

        cbCarga.setText("Cargas");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("ANTECEDENTES HEREDOFAMILIARES");

        jLabel14.setText("RESPIRATORIAS ");

        jLabel15.setText("ANTECEDENTES PATOLOGICOS ");

        jLabel70.setText("TRABAJADOR");

        jLabel17.setText("FAMILIAR");

        jLabel18.setText("OBSERVACIONES");

        jLabel19.setText("ENFERMEDADES HEMATOLOGICAS ");

        jLabel20.setText("DE TRANSMISION SEXUAL ");

        jLabel21.setText("NEUROLOGICAS");

        jLabel22.setText("INTERVENCIONES QUIRURGICAS ");

        jLabel23.setText("ENFERMEDADES NO TRANSMISIBLES ");

        jLabel24.setText("ENFERMEDADES PSIQUÍATRICAS");

        jLabel25.setText("ENFERMEDAES DIGESTIVAS ");

        jLabel26.setText("ENFERMEDADES AUTOINMUNES ");

        jLabel27.setText("ENFERMEDADES RENALES ");

        jLabel28.setText("ENFERMEDADES ONCOLOGICAS ");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("HISTORIA DE HÁBITOS");

        jLabel30.setText("¿Realiza actividad física periódica?");

        rbActividadSi.setText("Si");

        rbActividadNo.setSelected(true);
        rbActividadNo.setText("No");

        jLabel31.setText("¿Ingiere bebidas alcohólicas?");

        rbAlcoholSi.setText("Si");

        rbAlcoholNo.setSelected(true);
        rbAlcoholNo.setText("No");

        jLabel32.setText("¿Fuma actualmente?");

        rbFumaSi.setText("Si");

        rbFumaNo.setSelected(true);
        rbFumaNo.setText("No");

        jLabel33.setText("¿Alguna vez ha usado mariguana, heroína, cocaína,");

        jLabel36.setText("barbitúricos,  anfetaminas o cualquier otro tipo de droga psicoactiva?");

        rbDrogasSi.setText("Si");
        rbDrogasSi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbDrogasSiItemStateChanged(evt);
            }
        });

        rbDrogasNo.setSelected(true);
        rbDrogasNo.setText("No");

        jLabel34.setText("Tipo");

        txtDrogasTipo.setEnabled(false);
        txtDrogasTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDrogasTipoActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("EVALUACIÓN MÉDICA");

        jLabel37.setText("Examen clínico ");

        jLabel38.setText("Test SNELLEN  ");

        txtExamenClinico.setColumns(20);
        txtExamenClinico.setRows(2);
        jScrollPane3.setViewportView(txtExamenClinico);

        txtSnellen.setColumns(20);
        txtSnellen.setRows(2);
        jScrollPane4.setViewportView(txtSnellen);

        jLabel39.setText("Altura");

        txtTalla.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTallaPropertyChange(evt);
            }
        });
        txtTalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTallaKeyReleased(evt);
            }
        });

        jLabel40.setText("Peso");

        txtPeso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtPesoPropertyChange(evt);
            }
        });
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKeyReleased(evt);
            }
        });

        jLabel41.setText("IMC");

        txtIMC.setEditable(false);

        jLabel42.setText("Pe. Abd");

        jLabel43.setText("-");

        jLabel44.setText("FC");

        jLabel45.setText("FR");

        jLabel46.setText("OD");

        txtOd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOdActionPerformed(evt);
            }
        });

        jLabel47.setText("OI");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("EXPLORACION FISICA");

        jLabel49.setText("Piel/Cabello");

        txtCabello.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCabelloActionPerformed(evt);
            }
        });

        jLabel50.setText("Ojos y anexos ");

        jLabel51.setText("Aparato respiratorio ");

        jLabel52.setText("Aparato Cardiovascular ");

        jLabel53.setText("Aparato digestivo ");

        jLabel54.setText("Aparato genitourinario ");

        jLabel55.setText("Sistema musculo- esquelético ");

        jLabel56.setText("Sistema nervioso");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("VALORACIÓN MEDICA");

        txtVacucnacion.setColumns(20);
        txtVacucnacion.setRows(2);
        jScrollPane9.setViewportView(txtVacucnacion);

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("HISTORIAL DE VACUNACIÓN");

        txtValoracion.setColumns(20);
        txtValoracion.setRows(2);
        txtValoracion.setTabSize(6);
        jScrollPane5.setViewportView(txtValoracion);

        txtMedicacion.setColumns(20);
        txtMedicacion.setRows(2);
        jScrollPane6.setViewportView(txtMedicacion);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("MEDICACIÓN");

        txtContactos.setColumns(20);
        txtContactos.setRows(2);
        jScrollPane7.setViewportView(txtContactos);

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("CONTACTOS DE EMERGENCIA");

        jLabel64.setText("M");

        jLabel65.setText("KG");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addGap(665, 665, 665))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(695, 695, 695))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(99, 99, 99)
                                .addComponent(rbFumaSi)
                                .addGap(18, 18, 18)
                                .addComponent(rbFumaNo))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel30)
                                    .addGap(34, 34, 34)
                                    .addComponent(rbActividadSi)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbActividadNo))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbAlcoholSi)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbAlcoholNo))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel36)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDrogasTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rbDrogasSi)
                                .addGap(42, 42, 42)
                                .addComponent(rbDrogasNo)
                                .addGap(231, 231, 231))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtLugNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(dcNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel68)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel67)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbEscolaridad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel66)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbCivil, 0, 119, Short.MAX_VALUE)
                                    .addComponent(cbSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardar)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                        .addGap(68, 68, 68))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(139, 139, 139))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(42, 42, 42))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(62, 62, 62))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(86, 86, 86))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(65, 65, 65))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(144, 144, 144))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(94, 94, 94))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(59, 59, 59))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(52, 52, 52)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbHematologicos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbRespiratorias1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbSexual1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbNeurologico1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbQuirurgicas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbTransmitibles1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbPsiquiatricas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbDigestivas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbAutoinmunes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(cbRenales1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                                            .addComponent(cbOncologicos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGap(15, 15, 15)))
                                                .addGap(26, 26, 26)))
                                        .addGap(76, 76, 76)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbHematologicos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbRespiratorias2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbSexual2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbNeurologico2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbQuirurgicas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbTransmitibles2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbPsiquiatricas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbDigestivas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbAutoinmunes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(cbRenales2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                                            .addComponent(cbOncologicos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGap(15, 15, 15)))
                                                .addGap(17, 17, 17)))
                                        .addGap(90, 90, 90)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                                .addGap(140, 140, 140))
                                            .addComponent(txtRespiratorias)
                                            .addComponent(txtHematologicos)
                                            .addComponent(txtSexual)
                                            .addComponent(txtNeurologico)
                                            .addComponent(txtQuirurgicas)
                                            .addComponent(txtTransmitibles)
                                            .addComponent(txtPsiquiatricas)
                                            .addComponent(txtDigestivas)
                                            .addComponent(txtAutoinmunes)
                                            .addComponent(txtRenales)
                                            .addComponent(txtOncologicos)))
                                    .addComponent(jSeparator3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator11))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator9))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(cbRuido)
                                    .addComponent(cbBiologicos)
                                    .addComponent(cbGases)
                                    .addComponent(cbPolvo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbHumo)
                                            .addComponent(bcVapores)
                                            .addComponent(cbMetales)
                                            .addComponent(cbSolventes))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbCarga)
                                            .addComponent(cbPosturas)
                                            .addComponent(cbMovimientos)
                                            .addComponent(cbTemperaturas)))
                                    .addComponent(jSeparator2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel69)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(213, 213, 213)
                                .addComponent(jSeparator8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(425, 425, 425)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel56)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNervioso))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel55)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMusculo))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel54)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtgenito))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel53)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDigestivo))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator6))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator5))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel39)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel45)
                                                .addGap(26, 26, 26)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtFr)
                                            .addComponent(txtTalla))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel44)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel64)
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel40)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel65)
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel41))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtFc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel42)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtIMC)
                                            .addComponent(txtpAbd)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtOi, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel46)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtOd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel49)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtCabello, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel50)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtOjos, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel51)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtRespiratorio))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel52)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCardio))))
                                    .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(dcIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbBaja)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4)))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dcNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel68)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar))
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtLugNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEscolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dcIngreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(dcBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbRuido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPolvo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGases)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBiologicos))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbHumo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcVapores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSolventes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMetales))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbTemperaturas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMovimientos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPosturas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCarga)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel70)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRespiratorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbRespiratorias2)
                        .addComponent(cbRespiratorias1)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHematologicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbHematologicos1)
                        .addComponent(cbHematologicos2)
                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSexual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbSexual2)
                        .addComponent(cbSexual1)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNeurologico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbNeurologico1)
                        .addComponent(cbNeurologico2)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuirurgicas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbQuirurgicas1)
                        .addComponent(cbQuirurgicas2)
                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTransmitibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbTransmitibles1)
                        .addComponent(cbTransmitibles2)
                        .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPsiquiatricas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbPsiquiatricas1)
                        .addComponent(cbPsiquiatricas2)
                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDigestivas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbDigestivas1)
                        .addComponent(cbDigestivas2)
                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAutoinmunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbAutoinmunes1)
                        .addComponent(cbAutoinmunes2)
                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRenales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbRenales1)
                    .addComponent(cbRenales2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbOncologicos2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbOncologicos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOncologicos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel30)
                    .addComponent(rbActividadSi)
                    .addComponent(rbActividadNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbDrogasSi)
                            .addComponent(rbDrogasNo))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDrogasTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(rbAlcoholSi)
                            .addComponent(rbAlcoholNo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(rbFumaNo)
                            .addComponent(rbFumaSi))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel37)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel65)
                            .addComponent(jLabel41)
                            .addComponent(txtIMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(txtOi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)
                            .addComponent(txtOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel44)
                    .addComponent(txtFc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(txtpAbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel48)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtCabello, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(txtDigestivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtOjos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(txtgenito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtRespiratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(txtMusculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(txtCardio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(txtNervioso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel58)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel60)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Información", panel2);

        jLabel16.setText("Numero de empleado");

        txtNoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoEmpleadoKeyReleased(evt);
            }
        });

        butBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butBuscarMouseClicked(evt);
            }
        });

        txtNombre.setText("-");

        jButton2.setText("Nuevo");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtArea2.setText("-");

        jButton1.setText("Limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(butBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtNoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(butBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre)
                        .addComponent(txtArea2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        NUEVOREGISTRO ventanaNueva= new NUEVOREGISTRO();
        ventanaNueva.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventanaNueva.setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void butBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butBuscarMouseClicked
        buscarEmpleadoID();
        
    }//GEN-LAST:event_butBuscarMouseClicked

    private void cbBajaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbBajaItemStateChanged
        dcBaja.setEnabled(cbBaja.isSelected());
    }//GEN-LAST:event_cbBajaItemStateChanged

    private void rbDrogasSiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbDrogasSiItemStateChanged
        txtDrogasTipo.setEnabled(rbDrogasSi.isSelected());
    }//GEN-LAST:event_rbDrogasSiItemStateChanged

    private void txtDrogasTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDrogasTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDrogasTipoActionPerformed

    private void txtTallaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTallaPropertyChange

    }//GEN-LAST:event_txtTallaPropertyChange

    private void txtTallaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyReleased
        llamarIMC();
    }//GEN-LAST:event_txtTallaKeyReleased

    private void txtPesoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtPesoPropertyChange

    }//GEN-LAST:event_txtPesoPropertyChange

    private void txtPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyReleased
        llamarIMC();
    }//GEN-LAST:event_txtPesoKeyReleased

    private void txtOdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOdActionPerformed

    private void txtCabelloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCabelloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCabelloActionPerformed

    private void txtNoEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoEmpleadoKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            buscarEmpleadoID();
        }
    }//GEN-LAST:event_txtNoEmpleadoKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(validaciones()){
            actualizarObjeto();
            SQLRegistros mod=new SQLRegistros();
            if(mod.actualizar_empleado(trabajador))
                if(mod.actualizar_datos_medicos(trabajador)){
                    showMessageDialog(null, "Guardado");
                }else{
                    showMessageDialog(null, "falla al guardar");
                }
        }
        
        
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        borrarCampos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbHospitalizaciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHospitalizaciónActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHospitalizaciónActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /*for(int x=0;x<jTable1.getColumnCount();x++){
        System.out.println(x+" "+jTable1.getColumn(jTable1.getColumnName(x)).getWidth());
        }*/
        CONSULTA c =crear_consulta();
        SQLRegistros mod=new SQLRegistros();
        if(trabajador!=null){
            if(validarDatosConsulta()){
                if(mod.guardarConsulta(trabajador.getId(), c)){
                    showMessageDialog(null, "Guardado");
                    borrarCampos();
                }else{
                    showMessageDialog(null, "falla al guardar");
                }
            
            }else{
                showMessageDialog(null, "Es necesario síntomas y diagnóstico");
            }
        }else{
                showMessageDialog(null, "Es necesario insertar un empleado");
            }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    
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
            java.util.logging.Logger.getLogger(PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PRINCIPAL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private IMAGENES.IMAGEN PlOGO;
    private javax.swing.JCheckBox bcVapores;
    private javax.swing.JButton btnGuardar;
    private IMAGENES.IMAGEN butBuscar;
    private javax.swing.JCheckBox cbAutoinmunes1;
    private javax.swing.JCheckBox cbAutoinmunes2;
    private javax.swing.JCheckBox cbBaja;
    private javax.swing.JCheckBox cbBiologicos;
    private javax.swing.JCheckBox cbCarga;
    private javax.swing.JComboBox<String> cbCivil;
    private javax.swing.JCheckBox cbDigestivas1;
    private javax.swing.JCheckBox cbDigestivas2;
    private javax.swing.JComboBox<String> cbEscolaridad;
    private javax.swing.JCheckBox cbGases;
    private javax.swing.JCheckBox cbHematologicos1;
    private javax.swing.JCheckBox cbHematologicos2;
    private javax.swing.JCheckBox cbHospitalización;
    private javax.swing.JCheckBox cbHumo;
    private javax.swing.JCheckBox cbMetales;
    private javax.swing.JCheckBox cbMovimientos;
    private javax.swing.JCheckBox cbNeurologico1;
    private javax.swing.JCheckBox cbNeurologico2;
    private javax.swing.JCheckBox cbOncologicos1;
    private javax.swing.JCheckBox cbOncologicos2;
    private javax.swing.JCheckBox cbPolvo;
    private javax.swing.JCheckBox cbPosturas;
    private javax.swing.JCheckBox cbPsiquiatricas1;
    private javax.swing.JCheckBox cbPsiquiatricas2;
    private javax.swing.JCheckBox cbQuirurgicas1;
    private javax.swing.JCheckBox cbQuirurgicas2;
    private javax.swing.JCheckBox cbRenales1;
    private javax.swing.JCheckBox cbRenales2;
    private javax.swing.JCheckBox cbRespiratorias1;
    private javax.swing.JCheckBox cbRespiratorias2;
    private javax.swing.JCheckBox cbRuido;
    private javax.swing.JComboBox<String> cbSexo;
    private javax.swing.JCheckBox cbSexual1;
    private javax.swing.JCheckBox cbSexual2;
    private javax.swing.JCheckBox cbSolventes;
    private javax.swing.JCheckBox cbTemperaturas;
    private javax.swing.JCheckBox cbTransmitibles1;
    private javax.swing.JCheckBox cbTransmitibles2;
    private com.toedter.calendar.JDateChooser dcBaja;
    private com.toedter.calendar.JDateChooser dcFecha_Consulta;
    private com.toedter.calendar.JDateChooser dcIngreso;
    private com.toedter.calendar.JDateChooser dcNacimiento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel jTitulo;
    private javax.swing.JLabel jlabel63;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JRadioButton rbActividadNo;
    private javax.swing.JRadioButton rbActividadSi;
    private javax.swing.JRadioButton rbAlcoholNo;
    private javax.swing.JRadioButton rbAlcoholSi;
    private javax.swing.JRadioButton rbDrogasNo;
    private javax.swing.JRadioButton rbDrogasSi;
    private javax.swing.JRadioButton rbFumaNo;
    private javax.swing.JRadioButton rbFumaSi;
    private javax.swing.JTextField txtArea;
    private javax.swing.JLabel txtArea2;
    private javax.swing.JTextField txtAutoinmunes;
    private javax.swing.JTextField txtCabello;
    private javax.swing.JTextField txtCardio;
    private javax.swing.JTextArea txtContactos;
    private javax.swing.JTextArea txtDiagnostico;
    private javax.swing.JTextField txtDigestivas;
    private javax.swing.JTextField txtDigestivo;
    private javax.swing.JTextField txtDrogasTipo;
    private javax.swing.JTextArea txtExamenClinico;
    private javax.swing.JTextField txtFc;
    private javax.swing.JTextField txtFr;
    private javax.swing.JTextField txtHematologicos;
    private javax.swing.JTextField txtIMC;
    private javax.swing.JTextField txtLugNacimiento;
    private javax.swing.JTextArea txtMedicacion;
    private javax.swing.JTextField txtMedicamento;
    private javax.swing.JTextField txtMusculo;
    private javax.swing.JTextField txtNervioso;
    private javax.swing.JTextField txtNeurologico;
    private javax.swing.JTextField txtNoEmpleado;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JTextField txtOd;
    private javax.swing.JTextField txtOi;
    private javax.swing.JTextField txtOjos;
    private javax.swing.JTextField txtOncologicos;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtPsiquiatricas;
    private javax.swing.JTextField txtQuirurgicas;
    private javax.swing.JTextField txtRenales;
    private javax.swing.JTextField txtRespiratorias;
    private javax.swing.JTextField txtRespiratorio;
    private javax.swing.JTextField txtSexual;
    private javax.swing.JTextField txtSignos_vitales;
    private javax.swing.JTextArea txtSintomas;
    private javax.swing.JTextArea txtSnellen;
    private javax.swing.JTextField txtTalla;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTransmitibles;
    private javax.swing.JTextArea txtVacucnacion;
    private javax.swing.JTextArea txtValoracion;
    private javax.swing.JTextField txtgenito;
    private javax.swing.JTextField txtpAbd;
    // End of variables declaration//GEN-END:variables
}
