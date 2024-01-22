package br.com.fiap.estacionamento.model;

import lombok.Data;

@Data
public class Pix implements FormaPagamento {

        private String chavePix;
        @Override
        public String realizarPagamento(Double valor) {
            return "Pagamento realizado com PIX no valor de R$" + valor;
        }

}
