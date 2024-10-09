package com.flamingo.models;

public class FormaPago {
    private String tipoPago; 
    private String numTarjeta; 
    private String fecVencimiento; 
    private String cvv; 
    private String nomTitular; 
    // Constructor
    public FormaPago(String tipoPago, String numTarjeta, String fecVencimiento, String cvv, String nomTitular) {
        this.tipoPago = tipoPago;
        this.numTarjeta = numTarjeta;
        this.fecVencimiento = fecVencimiento;
        this.cvv = cvv;
        this.nomTitular = nomTitular;
    }

    // Getters y Setters
    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(String fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNomTitular() {
        return nomTitular;
    }

    public void setNomTitular(String nomTitular) {
        this.nomTitular = nomTitular;
    }
}
