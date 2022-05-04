package CLASES;

import java.util.ArrayList;
import java.util.Date;

public class TRABAJADOR {
    /*datos de identificaion*/
    int id=-1;
    String apellido1=null;
    String apellido2=null;
    String nombre=null;
    int edad=-1;
    String puesto=null;
    String sexo=null;
    String lugNaci=null;
    Date fecNaci=null;
    String estadoCivil=null;
    String escolaridad=null;
    String telefono=null;
    
    /*Descripcion de cargo*/
    Date fechaIngreso=null;
    String area=null;
    boolean activo=false;
    Date fechaBaja=null;
    
    /*contaminantes*/
    boolean ruido=false;
    boolean polvo=false;
    boolean gases=false;
    boolean biologico=false;
    boolean humo=false;
    boolean vapores=false;
    boolean solventes=false;
    boolean metales=false;
    boolean temperaturas=false;
    boolean movimiento=false;
    boolean posturas=false;
    boolean cargas=false;
    
    /**antecedentes familiares*/ //0-ninguno 1-el 2-familiar 3-los 2
    int respiratorio=-1;
    String respiratorioObser=null;
    int neurologico=-1;
    String neurologicoObser=null;
    int hematologico=-1;
    String hematologicoObser=null;
    int transmitibles=-1;
    String transmitiblesObser=null;
    int psiquiatrica=-1;
    String psiquiatricaObser=null;
    int quirurgicas=-1;
    String quirurgicasObs=null;
    int digestiva=-1;
    String digestivaObser=null;
    int autoinmune=-1;
    String autoinmuneObser=null;
    int renal=-1;
    String renalObser=null;
    int oncologico=-1;
    String oncologicoObser=null;
    int enfSexuales=-1;
    String enfSexualesObser=null;
    
    /**habitos*/
    boolean actividad=false;
    boolean alcohol=false;
    boolean fumar=false;
    boolean drogas=false;
    String tipoDorga=null;
    
    /**EVALUACION MEDICA*/
    String clinico=null;
    String snellen=null;
    float talla=-1;
    float peso=-1;
    float imc=-1;
    float pAbdominal=-1;
    float fc=-1;
    float fr=-1;
    float oi=-1;
    float od=-1;

    
    
    /**Exploración física*/
    String pielCabello=null;
    String ojosAnexos=null;
    String aparatoRespiratorio=null;
    String cardiovascular=null;
    String digestivo=null;
    String genitourinario =null;
    String musculo=null;
    String sisNervioso=null;
    
    String valoracioMedica=null;
    String vacunacion=null;
    String medicacion=null;
    String contactos=null;
    
    ArrayList<CONSULTA> consultas=null;

    public TRABAJADOR() {
    }

    public ArrayList<CONSULTA> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<CONSULTA> consultas) {
        this.consultas = consultas;
    }

    public String getSisNervioso() {
        return sisNervioso;
    }

    public void setSisNervioso(String sisNervioso) {
        this.sisNervioso = sisNervioso;
    }

    public int getEnfSexuales() {
        return enfSexuales;
    }

    public void setEnfSexuales(int enfSexuales) {
        this.enfSexuales = enfSexuales;
    }

    public String getEnfSexualesObser() {
        return enfSexualesObser;
    }

    public void setEnfSexualesObser(String enfSexualesObser) {
        this.enfSexualesObser = enfSexualesObser;
    }

    public float getOi() {
        return oi;
    }

    public void setOi(float oi) {
        this.oi = oi;
    }
    
    public float getOd() {
        return od;
    }

    public void setOd(float od) {
        this.od = od;
    }
    public int getQuirurgicas() {
        return quirurgicas;
    }

    public void setQuirurgicas(int quirurgicas) {
        this.quirurgicas = quirurgicas;
    }

    public String getQuirurgicasObs() {
        return quirurgicasObs;
    }

    public void setQuirurgicasObs(String quirurgicasObs) {
        this.quirurgicasObs = quirurgicasObs;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public Date getFecNaci() {
        return fecNaci;
    }

    public void setFecNaci(Date fecNaci) {
        this.fecNaci = fecNaci;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isRuido() {
        return ruido;
    }

    public void setRuido(boolean ruido) {
        this.ruido = ruido;
    }

    public boolean isPolvo() {
        return polvo;
    }

    public void setPolvo(boolean polvo) {
        this.polvo = polvo;
    }

    public boolean isGases() {
        return gases;
    }

    public void setGases(boolean gases) {
        this.gases = gases;
    }

    public boolean isBiologico() {
        return biologico;
    }

    public void setBiologico(boolean biologico) {
        this.biologico = biologico;
    }

    public boolean isHumo() {
        return humo;
    }

    public void setHumo(boolean humo) {
        this.humo = humo;
    }

    public boolean isVapores() {
        return vapores;
    }

    public void setVapores(boolean vapores) {
        this.vapores = vapores;
    }

    public boolean isSolventes() {
        return solventes;
    }

    public void setSolventes(boolean solventes) {
        this.solventes = solventes;
    }

    public boolean isMetales() {
        return metales;
    }

    public void setMetales(boolean metales) {
        this.metales = metales;
    }

    
    public boolean isMovimiento() {
        return movimiento;
    }

    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
    }

    public boolean isPosturas() {
        return posturas;
    }

    public void setPosturas(boolean posturas) {
        this.posturas = posturas;
    }

    public boolean isCargas() {
        return cargas;
    }

    public void setCargas(boolean cargas) {
        this.cargas = cargas;
    }

    public int getRespiratorio() {
        return respiratorio;
    }

    public void setRespiratorio(int respiratorio) {
        this.respiratorio = respiratorio;
    }

    public String getRespiratorioObser() {
        return respiratorioObser;
    }

    public void setRespiratorioObser(String respiratorioObser) {
        this.respiratorioObser = respiratorioObser;
    }

    public int getNeurologico() {
        return neurologico;
    }

    public void setNeurologico(int neurologico) {
        this.neurologico = neurologico;
    }

    public String getNeurologicoObser() {
        return neurologicoObser;
    }

    public void setNeurologicoObser(String neurologicoObser) {
        this.neurologicoObser = neurologicoObser;
    }

    public int getHematologico() {
        return hematologico;
    }

    public void setHematologico(int hematologico) {
        this.hematologico = hematologico;
    }

    public String getHematologicoObser() {
        return hematologicoObser;
    }

    public void setHematologicoObser(String hematologicoObser) {
        this.hematologicoObser = hematologicoObser;
    }

    public int getTransmitibles() {
        return transmitibles;
    }

    public void setTransmitibles(int transmitibles) {
        this.transmitibles = transmitibles;
    }

    public String getTransmitiblesObser() {
        return transmitiblesObser;
    }

    public void setTransmitiblesObser(String transmitiblesObser) {
        this.transmitiblesObser = transmitiblesObser;
    }

    public int getPsiquiatrica() {
        return psiquiatrica;
    }

    public void setPsiquiatrica(int psiquiatrica) {
        this.psiquiatrica = psiquiatrica;
    }

    public String getPsiquiatricaObser() {
        return psiquiatricaObser;
    }

    public void setPsiquiatricaObser(String psiquiatricaObser) {
        this.psiquiatricaObser = psiquiatricaObser;
    }

    public int getDigestiva() {
        return digestiva;
    }

    public void setDigestiva(int digestiva) {
        this.digestiva = digestiva;
    }

    public String getDigestivaObser() {
        return digestivaObser;
    }

    public void setDigestivaObser(String digestivaObser) {
        this.digestivaObser = digestivaObser;
    }

    public int getAutoinmune() {
        return autoinmune;
    }

    public void setAutoinmune(int autoinmune) {
        this.autoinmune = autoinmune;
    }

    public String getAutoinmuneObser() {
        return autoinmuneObser;
    }

    public void setAutoinmuneObser(String autoinmuneObser) {
        this.autoinmuneObser = autoinmuneObser;
    }

    public int getRenal() {
        return renal;
    }

    public void setRenal(int renal) {
        this.renal = renal;
    }

    public String getRenalObser() {
        return renalObser;
    }

    public void setRenalObser(String renalObser) {
        this.renalObser = renalObser;
    }

    public int getOncologico() {
        return oncologico;
    }

    public void setOncologico(int oncologico) {
        this.oncologico = oncologico;
    }

    public String getOncologicoObser() {
        return oncologicoObser;
    }

    public void setOncologicoObser(String oncologicoObser) {
        this.oncologicoObser = oncologicoObser;
    }

    public boolean isActividad() {
        return actividad;
    }

    public void setActividad(boolean actividad) {
        this.actividad = actividad;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public boolean isFumar() {
        return fumar;
    }

    public void setFumar(boolean fumar) {
        this.fumar = fumar;
    }

    public boolean isDrogas() {
        return drogas;
    }

    public void setDrogas(boolean drogas) {
        this.drogas = drogas;
    }

    public String getTipoDorga() {
        return tipoDorga;
    }

    public void setTipoDorga(String tipoDorga) {
        this.tipoDorga = tipoDorga;
    }

    public String getClinico() {
        return clinico;
    }

    public void setClinico(String clinico) {
        this.clinico = clinico;
    }

    public String getSnellen() {
        return snellen;
    }

    public void setSnellen(String snellen) {
        this.snellen = snellen;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public float getpAbdominal() {
        return pAbdominal;
    }

    public void setpAbdominal(float pAbdominal) {
        this.pAbdominal = pAbdominal;
    }

    public float getFc() {
        return fc;
    }

    public void setFc(float fc) {
        this.fc = fc;
    }

    public float getFr() {
        return fr;
    }

    public void setFr(float fr) {
        this.fr = fr;
    }

    public String getPielCabello() {
        return pielCabello;
    }

    public void setPielCabello(String pielCabello) {
        this.pielCabello = pielCabello;
    }

    public String getOjosAnexos() {
        return ojosAnexos;
    }

    public void setOjosAnexos(String ojosAnexos) {
        this.ojosAnexos = ojosAnexos;
    }

    public String getAparatoRespiratorio() {
        return aparatoRespiratorio;
    }

    public void setAparatoRespiratorio(String aparatoRespiratorio) {
        this.aparatoRespiratorio = aparatoRespiratorio;
    }

    public String getLugNaci() {
        return lugNaci;
    }

    public void setLugNaci(String lugNaci) {
        this.lugNaci = lugNaci;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public boolean isTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(boolean temperaturas) {
        this.temperaturas = temperaturas;
    }

    public String getCardiovascular() {
        return cardiovascular;
    }

    public void setCardiovascular(String cardiovascular) {
        this.cardiovascular = cardiovascular;
    }

    public String getDigestivo() {
        return digestivo;
    }

    public void setDigestivo(String digestivo) {
        this.digestivo = digestivo;
    }

    public String getGenitourinario() {
        return genitourinario;
    }

    public void setGenitourinario(String genitourinario) {
        this.genitourinario = genitourinario;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }
    
    public String getValoracioMedica() {
        return valoracioMedica;
    }

    public void setValoracioMedica(String valoracioMedica) {
        this.valoracioMedica = valoracioMedica;
    }

    public String getVacunacion() {
        return vacunacion;
    }

    public void setVacunacion(String vacunacion) {
        this.vacunacion = vacunacion;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public String getContactos() {
        return contactos;
    }

    public void setContactos(String contactos) {
        this.contactos = contactos;
    }
    
}
