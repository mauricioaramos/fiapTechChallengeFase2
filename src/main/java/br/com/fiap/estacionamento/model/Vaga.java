package br.com.fiap.estacionamento.model;

import lombok.Data;

@Data
public class Vaga {
    String numero;
    String status = "Livre";
}
