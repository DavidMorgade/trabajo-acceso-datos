package org.example.controllers;

import org.example.db.Contrato;
import org.example.db.db;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {
    db db = new db();

    @GetMapping
    public List<Contrato> getContratos() {
        List<Contrato> contratos = db.getAllContratos();
        return contratos;
    }
}
