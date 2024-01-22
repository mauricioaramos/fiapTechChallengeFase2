package br.com.fiap.estacionamento.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Cartao implements FormaPagamento {

        private String numeroCartao;
        private String nomeTitular;
        private LocalDate dataValidade;
        private String cvv;
        FuncaoCartao funcaoCartao ;

        @Override
        public String realizarPagamento(Double valor) {
            return funcaoCartao.realizarPagamento(valor);
        }

}
