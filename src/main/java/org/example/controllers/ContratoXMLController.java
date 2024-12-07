package org.example.controllers;

import org.example.services.ContratoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/xml/contratos.xml")
public class ContratoXMLController {
    private final ContratoService contratoService;

    public ContratoXMLController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping(produces = "application/xml")
    public ResponseEntity<String> obtenerContratosComoXml() {
        String xml = contratoService.obtenerContratosComoXml();  // Obtén el XML como String desde el servicio
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/xml")  // Asegúrate de que el tipo de contenido es XML
                .body(xml);  // Devuelve el XML como respuesta
    }
}
