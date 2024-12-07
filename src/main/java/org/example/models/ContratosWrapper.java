package org.example.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.example.db.Contrato;

import java.util.List;

@JsonRootName("contratos")  // Esto le dará el nombre correcto al contenedor
public class ContratosWrapper {
    private List<Contrato> contratos;

    public ContratosWrapper(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
}
