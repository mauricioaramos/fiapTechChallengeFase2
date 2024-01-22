package br.com.fiap.estacionamento.model;

import lombok.Data;


public interface FormaPagamento {
        String realizarPagamento(Double valor);
}
