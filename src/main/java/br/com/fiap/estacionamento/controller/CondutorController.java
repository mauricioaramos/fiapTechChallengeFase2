package br.com.fiap.estacionamento.controller;

import br.com.fiap.estacionamento.model.*;
import br.com.fiap.estacionamento.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/condutor")
public class CondutorController {

    @Autowired
    CondutorService condutorService;
    @GetMapping
    public List<Condutor> obterTodos() throws Exception {
        return condutorService.listarCondutores();
    }

    @GetMapping("/{id}")
    public Condutor obterPorId( @PathVariable("id") String id) throws Exception {
        return condutorService.buscarCondutorPorCodigo(id);
    }

    @PostMapping
    public Condutor cadastrar(@RequestBody Condutor condutor) throws Exception {
        return condutorService.cadastrarCondutor(condutor);
    }

    @PutMapping("/{id}/vincularVeiculo")
    public Condutor vincularVeiculo(@PathVariable("id") String id, @RequestBody Veiculo veiculo) throws Exception {
        return condutorService.vincularVeiculo(id, veiculo);
    }

    @PutMapping("/{id}/cadastrarPix")
    public Condutor cadastrarPix(@PathVariable("id") String id, @RequestBody Pix pix) throws Exception {
        return condutorService.cadastrarFormaPagamento(id, pix);
    }
    @PutMapping("/{id}/cadastrarCartao")
    public Condutor cadastrarCartao(@PathVariable("id") String id, @RequestBody Cartao cartao) throws Exception {
        return condutorService.cadastrarFormaPagamento(id, cartao);
    }
}
