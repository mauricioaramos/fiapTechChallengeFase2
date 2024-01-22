package br.com.fiap.estacionamento.service;

import br.com.fiap.estacionamento.model.Veiculo;
import br.com.fiap.estacionamento.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    public Veiculo cadastrarVeiculo(Veiculo veiculo) throws Exception;

    public String atualizarVeiculo(Veiculo veiculo) throws Exception;

    public String deletarVeiculo(Veiculo veiculo) throws Exception;

    public Veiculo buscarVeiculoPorCodigo(String codigo) throws Exception;

    public List<Veiculo> listarVeiculos() throws Exception;
}
