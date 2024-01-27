package br.com.fiap.estacionamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ReservaVaga {
    @Id
    private String id;
    private String codigoEstacionamento;
    private String numeroVaga;
    private String codigoCondutor;
    private String nomeCondutor;
    private String placa;
    private Double valorHora;
    private String tipoPagamento;
    private String funcaoCartao;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String tempoPermanencia;
    private Double valorTotal;
    private String statusPagamento;
    private String tipoPeriodo;
    private String duracao;
    private String statusReserva;

}
