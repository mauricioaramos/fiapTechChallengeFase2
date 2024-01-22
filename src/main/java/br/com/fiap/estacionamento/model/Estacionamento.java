package br.com.fiap.estacionamento.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;
@Data
@Document
public class Estacionamento {

    @Id
    private String id;
    private String endereco;
    private List<Vaga> vagas;
    private Double valorHora;

}
