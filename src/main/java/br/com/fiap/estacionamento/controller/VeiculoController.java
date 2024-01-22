package br.com.fiap.estacionamento.controller;

import br.com.fiap.estacionamento.model.Veiculo;
import br.com.fiap.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> obterTodos() throws Exception {
        return veiculoService.listarVeiculos();
    }

    @GetMapping("/{id}")
    public Veiculo obterPorId( @PathVariable("id") String id) throws Exception {
        return veiculoService.buscarVeiculoPorCodigo(id);
    }

    @PostMapping
    public Veiculo cadastrar(@RequestBody Veiculo veiculo) throws Exception {
        return veiculoService.cadastrarVeiculo(veiculo);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable("id") String id) throws Exception {
        return veiculoService.deletarVeiculo(veiculoService.buscarVeiculoPorCodigo(id));
    }



}
