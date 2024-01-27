package br.com.fiap.estacionamento.service;

import br.com.fiap.estacionamento.model.Estacionamento;

import java.util.List;

public interface EstacionamentoService {

    public Estacionamento cadastrarEstacionamento(Estacionamento estacionamento) throws Exception;

    public String atualizarEstacionamento(Estacionamento estacionamento) throws Exception;

    public String deletarEstacionamento(Estacionamento estacionamento) throws Exception;

    public Estacionamento buscarEstacionamentoPorCodigo(String codigo) throws Exception;


    public List<Estacionamento> findAll() throws Exception;
}
