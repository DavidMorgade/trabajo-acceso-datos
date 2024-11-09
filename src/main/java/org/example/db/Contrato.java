package org.example.db;

public class Contrato {
    private String adjudicatario;
    private String objeto_generico;
    private String fecha_adjudicacion;
    private double importe;
    private int proveedores_consultados;

    public Contrato(String adjudicatario, String objeto_generico, String fecha_adjudicacion, double importe, int proveedores_consultados) {
        this.adjudicatario = adjudicatario;
        this.objeto_generico = objeto_generico;
        this.fecha_adjudicacion = fecha_adjudicacion;
        this.importe = importe;
        this.proveedores_consultados = proveedores_consultados;
    }

    public String getAdjudicatario() {
        return adjudicatario;
    }

    public void setAdjudicatario(String adjudicatario) {
        this.adjudicatario = adjudicatario;
    }

    public String getObjeto_generico() {
        return objeto_generico;
    }

    public void setObjeto_generico(String objeto_generico) {
        this.objeto_generico = objeto_generico;
    }

    public String getFecha_adjudicacion() {
        return fecha_adjudicacion;
    }

    public void setFecha_adjudicacion(String fecha_adjudicacion) {
        this.fecha_adjudicacion = fecha_adjudicacion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getProveedores_consultados() {
        return proveedores_consultados;
    }

    public void setProveedores_consultados(int proveedores_consultados) {
        this.proveedores_consultados = proveedores_consultados;
    }
}
