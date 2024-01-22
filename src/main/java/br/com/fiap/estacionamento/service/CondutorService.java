package br.com.fiap.estacionamento.service;

import br.com.fiap.estacionamento.model.Condutor;
import br.com.fiap.estacionamento.model.FormaPagamento;
import br.com.fiap.estacionamento.model.Pix;
import br.com.fiap.estacionamento.model.Veiculo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CondutorService {

    public Condutor cadastrarCondutor(Condutor condutor) throws Exception;

    public String atualizarCondutor(Condutor condutor) throws Exception;

    public String deletarCondutor(Condutor condutor) throws Exception;

    public Condutor buscarCondutorPorCodigo(String codigo) throws Exception;

    public List<Condutor> listarCondutores() throws Exception;
    public Condutor vincularVeiculo(String codigoCondutor, Veiculo veiculo) throws Exception;
    public Condutor cadastrarFormaPagamento(String codigoCondutor, FormaPagamento veiculo) throws Exception;

}
