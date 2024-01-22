package br.com.fiap.estacionamento.model;

public class FuncaoCredito implements FuncaoCartao{

    @Override
    public String realizarPagamento(Double valor) {
        return "Pagamento relazido na funcao "+ getNomeClass() + " no valor de R$ " + valor + " realizado com sucesso!";
    }

    public String getNomeClass() {
        return this.getClass().getSimpleName();
    }
}
