package VISUAL;

import CLASES.TRABAJADOR;
import SQL.SQLRegistros;
import static auxiliar.auxiliares.calcularIMC;
import java.util.Date;
import javax.swing.JCheckBox;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;


public class NUEVOREGISTRO extends javax.swing.JFrame {
    
    public NUEVOREGISTRO() {
        initComponents();
        bgActividadFisica.add(rbActividadSi);
        bgActividadFisica.add(rbActividadNo);
        bgAlcohol.add(rbAlcoholSi);
        bgAlcohol.add(rbAlcoholNo);
        bgFumar.add(rbFumaSi);
        bgFumar.add(rbFumaNo);
        bgDrogas.add(rbDrogasSi);
        bgDrogas.add(rbDrogasNo);
    }
    
    public TRABAJADOR crearObjeto(){
        TRABAJADOR t=null;
        if(validarDatosEmpleado()){
            t=new TRABAJADOR();
            //datos de empleado
            t.setId(Integer.parseInt(txtId.getText()));
            t.setNombre(txtNombre.getText().toUpperCase());
            t.setApellido1(txtApellido1.getText().toUpperCase());
            t.setApellido2(txtApellido2.getText().toUpperCase());
            t.setFechaIngreso(dcIngreso.getDate());
            t.setArea(txtArea.getText().toUpperCase().toUpperCase());
            t.setActivo(!cbBaja.isSelected());
            if(!cbBaja.isSelected()){
                t.setFechaBaja(null);
            }else{
                t.setFechaBaja(dcBaja.getDate());
            }
            //datos generales
            t.setSexo(cbSexo.getSelectedItem().toString().toUpperCase());
            t.setTelefono(txtTelefono.getText());
            t.setLugNaci(txtLugNacimiento.getText().toUpperCase());
            t.setFecNaci(dcNacimiento.getDate());
            t.setEscolaridad(cbEscolaridad.getSelectedItem().toString());
            t.setEstadoCivil(cbCivil.getSelectedItem().toString());
            //datos contaminantes
            t.setRuido(cbRuido.isSelected());
            t.setPolvo(cbPolvo.isSelected());
            t.setGases(cbGases.isSelected());
            t.setBiologico(cbBiologicos.isSelected());
            t.setHumo(cbHumo.isSelected());
            t.setVapores(bcVapores.isSelected());
            t.setSolventes(cbSolventes.isSelected());
            t.setMetales(cbMetales.isSelected());
            t.setTemperaturas(cbTemperaturas.isSelected());
            t.setMovimiento(cbMovimientos.isSelected());
            t.setPosturas(cbPosturas.isSelected());
            t.setCargas(cbCarga.isSelected());
            //antecedentes Heredofamiliares
            t.setRespiratorio(antecedentes(cbRespiratorias1,cbRespiratorias2));
            t.setRespiratorioObser(txtRespiratorias.getText().toUpperCase());
            t.setHematologico(antecedentes(cbHematologicos1,cbHematologicos2));
            t.setHematologicoObser(txtHematologicos.getText().toUpperCase());
            t.setEnfSexuales(antecedentes(cbSexual1,cbSexual2));
            t.setEnfSexualesObser(txtSexual.getText().toUpperCase());
            t.setNeurologico(antecedentes(cbNeurologico1,cbNeurologico2));
            t.setNeurologicoObser(txtNeurologico.getText().toUpperCase());
            t.setQuirurgicas(antecedentes(cbQuirurgicas1,cbQuirurgicas2));
            t.setQuirurgicasObs(txtQuirurgicas.getText().toUpperCase());
            t.setTransmitibles(antecedentes(cbTransmitibles1,cbTransmitibles2));
            t.setTransmitiblesObser(txtTransmitibles.getText().toUpperCase());
            t.setPsiquiatrica(antecedentes(cbPsiquiatricas1,cbPsiquiatricas2));
            t.setPsiquiatricaObser(txtPsiquiatricas.getText().toUpperCase());
            t.setDigestiva(antecedentes(cbDigestivas1,cbDigestivas2));
            t.setDigestivaObser(txtDigestivas.getText().toUpperCase());
            t.setAutoinmune(antecedentes(cbAutoinmunes1,cbAutoinmunes2));
            t.setAutoinmuneObser(txtAutoinmunes.getText().toUpperCase());
            t.setRenal(antecedentes(cbRenales1,cbRenales2));
            t.setRenalObser(txtRenales.getText().toUpperCase());
            t.setOncologico(antecedentes(cbOncologicos1,cbOncologicos2));
            t.setOncologicoObser(txtOncologicos.getText().toUpperCase());
            //Habitos
            t.setActividad(rbActividadSi.isSelected());
            t.setAlcohol(rbAlcoholSi.isSelected());
            t.setFumar(rbFumaSi.isSelected());
            t.setDrogas(rbDrogasSi.isSelected());
            if(!rbDrogasSi.isSelected()){
                t.setTipoDorga(null);
            }else{
                t.setTipoDorga(txtDrogasTipo.getText().toUpperCase());
            }

            //evaluacion medica
            t.setClinico(txtExamenClinico.getText().toUpperCase());
            t.setTalla(validarFloat(txtTalla));
            t.setPeso(validarFloat(txtPeso));
            t.setFc(validarFloat(txtFr));
            t.setFr(validarFloat(txtFc));
            t.setpAbdominal(validarFloat(txtpAbd));
            t.setOi(validarFloat(txtOi));
            t.setOd(validarFloat(txtOd));

            t.setSnellen(txtSnellen.getText().toUpperCase());
            //exploracion fisica
            t.setPielCabello(txtCabello.getText().toUpperCase());
            t.setOjosAnexos(txtOjos.getText().toUpperCase());
            t.setAparatoRespiratorio(txtRespiratorio.getText().toUpperCase());
            t.setCardiovascular(txtCardio.getText().toUpperCase());
            t.setDigestivo(txtDigestivo.getText().toUpperCase());
            t.setGenitourinario(txtgenito.getText().toUpperCase());
            t.setMusculo(txtMusculo.getText().toUpperCase());
            t.setSisNervioso(txtSisNervioso.getText());
            t.setValoracioMedica(txtValoracion.getText().toUpperCase());
            t.setVacunacion(txtVacucnacion.getText().toUpperCase());
            t.setMedicacion(txtMedicacion.getText().toUpperCase());
            t.setContactos(txtContactos.getText().toUpperCase());
        }
        return t;
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
        public boolean validarDatosEmpleado(){
            String error="";
            txtApellido1.setText(txtApellido1.getText().replaceAll("^\\s*", ""));
            txtApellido2.setText(txtApellido2.getText().replaceAll("^\\s*", ""));
            txtNombre.setText(txtNombre.getText().replaceAll("^\\s*", ""));
            txtId.setText(txtId.getText().replaceAll("^\\s*", ""));
            txtLugNacimiento.setText(txtLugNacimiento.getText().replaceAll("^\\s*", ""));
            txtTelefono.setText(txtTelefono.getText().replaceAll("^\\s*", ""));
            txtArea.setText(txtArea.getText().replaceAll("^\\s*", ""));
            
            try{
                int id=Integer.valueOf(txtId.getText());
            }catch(NumberFormatException e){
                error+="\nId no valido";
            }
            if(txtApellido1.getText().length()<1)
                error+="\nApellido 1 no valido";
            if(txtApellido2.getText().length()<1)
                error+="\nApellido 2 no valido";
            if(txtNombre.getText().length()<1)
                error+="\nNombre no valido";
            try{
                double tel=Double.valueOf(txtTelefono.getText());
            }catch(NumberFormatException e){
                error+="\ntelefono no valido";
            }
            if(txtTelefono.getText().length()==10)
                error+="\nTelefono a 10 digitos";
            if(dcNacimiento.getDate()==null)
                error+="\nFecha de nacimiento no valida";
            
            if(dcIngreso.getDate()==null)
                error+="\nFecha de ingreso no valida";
            if(txtLugNacimiento.getText().length()<1)
                error+="\nLugar de nacimiento no valido";
            if(txtArea.getText().length()<1)
                error+="\nArea de trabajo no valida";
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

        bgActividadFisica = new javax.swing.ButtonGroup();
        bgAlcohol = new javax.swing.ButtonGroup();
        bgFumar = new javax.swing.ButtonGroup();
        bgDrogas = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtApellido1 = new javax.swing.JTextField();
        txtApellido2 = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dcNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtLugNacimiento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbCivil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbEscolaridad = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
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
        jLabel16 = new javax.swing.JLabel();
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
        txtSisNervioso = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        jLabel61 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel1.setText("Nombre");

        txtApellido1.setToolTipText("");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Fecha de nacimiento");

        dcNacimiento.setDateFormatString("dd/MM/yyyy");

        jLabel4.setText("Sexo");

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hombre", "Mujer" }));

        jLabel5.setText("Lugar de nacimiento");

        txtLugNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLugNacimientoActionPerformed(evt);
            }
        });

        jLabel7.setText("Estado civil");

        cbCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soltero", "Casado", "Divorciado", "Union Libre" }));

        jLabel6.setText("Escolaridad");

        cbEscolaridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primaria", "Secundaria", "Bachillerato", "Tecnico", "Profesional" }));

        jLabel2.setText("Telefono");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("DESCRIPCIÓN DEL CARGO");

        jLabel9.setText("Fecha de ingreso");

        dcIngreso.setDateFormatString("dd/MM/yyyy");

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

        jLabel16.setText("TRABAJADOR");

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

        txtFc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFcActionPerformed(evt);
            }
        });

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

        txtCardio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCardioActionPerformed(evt);
            }
        });

        jLabel53.setText("Aparato digestivo ");

        jLabel54.setText("Aparato genitourinario ");

        jLabel55.setText("Sistema musculo- esquelético ");

        jLabel56.setText("Sistema nervioso");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("VALORACIÓN MEDICA");

        txtVacucnacion.setColumns(20);
        txtVacucnacion.setRows(2);
        jScrollPane2.setViewportView(txtVacucnacion);

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

        jLabel61.setText("No. de empleado");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel62.setText("Primer apellido");

        jLabel63.setText("Segundo apellido");

        jLabel64.setText("M");

        jLabel65.setText("KG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                                            .addComponent(cbRenales1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                                            .addComponent(cbRenales2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(cbOncologicos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGap(15, 15, 15)))
                                                .addGap(17, 17, 17)))
                                        .addGap(90, 90, 90)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(txtApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId))
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
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(645, 645, 645))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(675, 675, 675))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addComponent(jLabel62)
                                        .addGap(134, 134, 134)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel63)
                                                .addGap(144, 144, 144)
                                                .addComponent(jLabel1))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cbEscolaridad, 0, 162, Short.MAX_VALUE)
                                                    .addComponent(txtLugNacimiento)))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dcNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbCivil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel6)))
                                .addGap(80, 80, 80)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTelefono)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addComponent(txtSisNervioso))
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
                                            .addComponent(txtFr, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
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
                                            .addComponent(txtIMC, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
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
                                        .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbBaja)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4)))
                        .addGap(27, 27, 27))
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
                                .addGap(231, 231, 231))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido2)
                    .addComponent(jLabel61)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dcNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtLugNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbEscolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
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
                    .addComponent(jLabel16)
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
                    .addComponent(txtSisNervioso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel58)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SQLRegistros mod=new SQLRegistros();
        TRABAJADOR  t=crearObjeto();
        boolean continuar=false;
        if(t!=null){
            continuar=mod.NuevoEmpleado(t);
            if(continuar){
                continuar=mod.guardar_datos_medicos(t);
                if(continuar){
                    showMessageDialog(null, "Guardado");
                    this.dispose();
                }else{
                    showMessageDialog(null, "Falla al guardar");
                }
            }else{
                showMessageDialog(null, "empleado ya existente");
            }
        }
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyReleased
        llamarIMC();
    }//GEN-LAST:event_txtPesoKeyReleased

    private void txtPesoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtPesoPropertyChange

    }//GEN-LAST:event_txtPesoPropertyChange

    private void txtTallaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyReleased
        llamarIMC();
    }//GEN-LAST:event_txtTallaKeyReleased

    private void txtTallaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTallaPropertyChange

    }//GEN-LAST:event_txtTallaPropertyChange

    private void txtDrogasTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDrogasTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDrogasTipoActionPerformed

    private void rbDrogasSiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbDrogasSiItemStateChanged
        txtDrogasTipo.setEnabled(rbDrogasSi.isSelected());
    }//GEN-LAST:event_rbDrogasSiItemStateChanged

    private void cbBajaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbBajaItemStateChanged
        dcBaja.setEnabled(cbBaja.isSelected());
    }//GEN-LAST:event_cbBajaItemStateChanged

    private void txtOdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOdActionPerformed

    private void txtCabelloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCabelloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCabelloActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtLugNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLugNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLugNacimientoActionPerformed

    private void txtFcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFcActionPerformed

    private void txtCardioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCardioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCardioActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox bcVapores;
    private javax.swing.ButtonGroup bgActividadFisica;
    private javax.swing.ButtonGroup bgAlcohol;
    private javax.swing.ButtonGroup bgDrogas;
    private javax.swing.ButtonGroup bgFumar;
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
    private com.toedter.calendar.JDateChooser dcIngreso;
    private com.toedter.calendar.JDateChooser dcNacimiento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
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
    private javax.swing.JRadioButton rbActividadNo;
    private javax.swing.JRadioButton rbActividadSi;
    private javax.swing.JRadioButton rbAlcoholNo;
    private javax.swing.JRadioButton rbAlcoholSi;
    private javax.swing.JRadioButton rbDrogasNo;
    private javax.swing.JRadioButton rbDrogasSi;
    private javax.swing.JRadioButton rbFumaNo;
    private javax.swing.JRadioButton rbFumaSi;
    private javax.swing.JTextField txtApellido1;
    private javax.swing.JTextField txtApellido2;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtAutoinmunes;
    private javax.swing.JTextField txtCabello;
    private javax.swing.JTextField txtCardio;
    private javax.swing.JTextArea txtContactos;
    private javax.swing.JTextField txtDigestivas;
    private javax.swing.JTextField txtDigestivo;
    private javax.swing.JTextField txtDrogasTipo;
    private javax.swing.JTextArea txtExamenClinico;
    private javax.swing.JTextField txtFc;
    private javax.swing.JTextField txtFr;
    private javax.swing.JTextField txtHematologicos;
    private javax.swing.JTextField txtIMC;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLugNacimiento;
    private javax.swing.JTextArea txtMedicacion;
    private javax.swing.JTextField txtMusculo;
    private javax.swing.JTextField txtNeurologico;
    private javax.swing.JTextField txtNombre;
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
    private javax.swing.JTextField txtSisNervioso;
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
