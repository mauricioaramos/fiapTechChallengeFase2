package br.com.fiap.estacionamento.controller;

import br.com.fiap.estacionamento.model.Estacionamento;
import br.com.fiap.estacionamento.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    EstacionamentoService estacionamentoService;

    @PostMapping
    public Estacionamento cadastrarEstacionamento(@RequestBody Estacionamento estacionamento) throws Exception {
        return estacionamentoService.cadastrarEstacionamento(estacionamento);
    }
}
