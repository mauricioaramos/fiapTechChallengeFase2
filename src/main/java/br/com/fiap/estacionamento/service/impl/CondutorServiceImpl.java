package br.com.fiap.estacionamento.service.impl;

import br.com.fiap.estacionamento.model.Condutor;
import br.com.fiap.estacionamento.model.FormaPagamento;
import br.com.fiap.estacionamento.model.Pix;
import br.com.fiap.estacionamento.model.Veiculo;
import br.com.fiap.estacionamento.repository.CondutorRepository;
import br.com.fiap.estacionamento.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CondutorServiceImpl implements CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    @Override
    public Condutor cadastrarCondutor(Condutor condutor) throws Exception {
        return condutorRepository.save(condutor);
    }

    @Override
    public String atualizarCondutor(Condutor condutor) throws Exception {
        return null;
    }

    @Override
    public String deletarCondutor(Condutor condutor) throws Exception {
        return null;
    }

    @Override
    public Condutor buscarCondutorPorCodigo(String codigo) throws Exception {
        return condutorRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Condutor não encontrado"));
    }

    @Override
    public List<Condutor> listarCondutores() throws Exception {
        return condutorRepository.findAll();
    }

    @Override
    public Condutor vincularVeiculo(String codigoCondutor, Veiculo veiculo) throws Exception {
        return condutorRepository.findById(codigoCondutor)
                .map(condutor -> {
                    condutor.getVeiculos().add(veiculo);
                    return condutorRepository.save(condutor);
                })
                .orElseThrow(() -> new IllegalArgumentException("Condutor não encontrado"));
    }

    @Override
    public Condutor cadastrarFormaPagamento(String codigoCondutor, FormaPagamento formaPagamento) throws Exception {
        return condutorRepository.findById(codigoCondutor)
                .map(condutor -> {
                    condutor.setFormaPagamento(formaPagamento);
                    return condutorRepository.save(condutor);
                })
                .orElseThrow(() -> new IllegalArgumentException("Condutor não encontrado"));
    }

}
