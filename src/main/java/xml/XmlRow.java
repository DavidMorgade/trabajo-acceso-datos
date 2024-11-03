package xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Row")
public class XmlRow {
    private String nif;
    private String adjudicatario;
    private String objetoGenerico;
    private String objeto;
    private String fechaAdjudicacion;
    private String importe;
    private String proveedoresConsultados;
    private String tipoDeContrato;

    // Getters and setters

    @XmlElement(name = "Cell")
    public void setCells(List<Cell> cells) {
        this.nif = cells.get(0).getData();
        this.adjudicatario = cells.get(1).getData();
        this.objetoGenerico = cells.get(2).getData();
        this.objeto = cells.get(3).getData();
        this.fechaAdjudicacion = cells.get(4).getData();
        this.importe = cells.get(5).getData();
        this.proveedoresConsultados = cells.get(6).getData();
        this.tipoDeContrato = cells.get(7).getData();
    }

    // Getters and setters for each field
}