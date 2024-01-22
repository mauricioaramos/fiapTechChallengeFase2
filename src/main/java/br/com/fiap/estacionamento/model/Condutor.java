package br.com.fiap.estacionamento.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class Condutor {

    @Id
    private String codigo;

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private List<Veiculo> veiculos = new ArrayList<>();
    private FormaPagamento formaPagamento;
}
