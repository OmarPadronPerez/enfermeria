/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.util.Date;

/**
 *
 * @author sistemas
 */

public class CONSULTA {
    Date fecha;
    String sintomas;
    String diagnistico;
    String signos_Vitales;
    String medicamento;
    boolean hospital;

    public CONSULTA(String sintomas, String diagnistico, String signos_Vitales, String medicamento, boolean hospital) {
        this.sintomas = sintomas;
        this.diagnistico = diagnistico;
        this.signos_Vitales = signos_Vitales;
        this.medicamento = medicamento;
        this.hospital = hospital;
    }
    
    
    public CONSULTA(Date fecha, String sintomas, String diagnistico, String signos_Vitales, String medicamento, boolean hospital) {
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.diagnistico = diagnistico;
        this.signos_Vitales = signos_Vitales;
        this.medicamento = medicamento;
        this.hospital = hospital;
    }

    public boolean isHospital() {
        return hospital;
    }

    public void setHospital(boolean hospital) {
        this.hospital = hospital;
    }

    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnistico() {
        return diagnistico;
    }

    public void setDiagnistico(String diagnistico) {
        this.diagnistico = diagnistico;
    }

    public String getSignos_Vitales() {
        return signos_Vitales;
    }

    public void setSignos_Vitales(String signos_Vitales) {
        this.signos_Vitales = signos_Vitales;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }
    
    
    
}
