package org.example.services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.db.Contrato;
import org.example.db.db;
import org.example.models.ContratosWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoService {

    db db = new db();

    public String obtenerContratosComoXml() {
        List<Contrato> contratos = db.getAllContratos();

        XmlMapper xmlMapper = new XmlMapper();


        ContratosWrapper contratosWrapper = new ContratosWrapper(contratos);

        try {
            // El XML será ahora formateado con indentación
            String xml = xmlMapper.writeValueAsString(contratosWrapper);
            return xml;
        } catch (Exception e) {
            return "<error>Error al generar el XML: " + e.getMessage() + "</error>";
        }
    }
}
