package br.com.fiap.estacionamento.service.impl;

import br.com.fiap.estacionamento.model.Estacionamento;
import br.com.fiap.estacionamento.repository.EstacionamentoRepository;
import br.com.fiap.estacionamento.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    @Autowired
    EstacionamentoRepository estacionamentoRepository;

    @Override
    public String atualizarEstacionamento(Estacionamento estacionamento) throws Exception {
        return null;
    }

    @Override
    public String deletarEstacionamento(Estacionamento estacionamento) throws Exception {
        return null;
    }

    @Override
    public Estacionamento buscarEstacionamentoPorCodigo(String codigo) throws Exception {
        return estacionamentoRepository.findById(codigo).orElseThrow(() -> new Exception("Estacionamento não encontrado"));
    }

    @Override
    public List<Estacionamento> findAll() throws Exception {
        return estacionamentoRepository.findAll();
    }


    @Override
    public Estacionamento cadastrarEstacionamento(Estacionamento estacionamento) throws Exception {
        return estacionamentoRepository.save(estacionamento);
    }
}
