package br.com.fiap.estacionamento.service;

import br.com.fiap.estacionamento.model.ReservaVaga;

public interface VagaService {

    public ReservaVaga reservarPeriodoDeEstacionamento(String tipoPeriodo, String duracao, ReservaVaga reservaVaga) throws Exception;

    public ReservaVaga finalizarPeriodoDeEstacionamento(String idEstacionamento, String idCondutor, String idVaga) throws Exception;

}
