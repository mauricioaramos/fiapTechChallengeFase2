package br.com.fiap.estacionamento.service.impl;

import br.com.fiap.estacionamento.model.Veiculo;
import br.com.fiap.estacionamento.repository.VeiculoRepository;
import br.com.fiap.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VeiculoServiceImpl implements VeiculoService {
    @Autowired
    VeiculoRepository veiculoRepository;

    @Override
    public Veiculo cadastrarVeiculo(Veiculo veiculo) throws Exception {
        return veiculoRepository.save(veiculo);
    }

    @Override
    public String atualizarVeiculo(Veiculo veiculo) throws Exception {
        return null;
    }

    @Override
    public String deletarVeiculo(Veiculo veiculo) throws Exception {
        veiculoRepository.delete(veiculo);
        return "Veiculo deletado com sucesso";
    }

    @Override
    public Veiculo buscarVeiculoPorCodigo(String codigo) throws Exception {
        return veiculoRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Veiculo não encontrado"));
    }

    @Override
    public List<Veiculo> listarVeiculos() throws Exception {
        return veiculoRepository.findAll();
    }



}
