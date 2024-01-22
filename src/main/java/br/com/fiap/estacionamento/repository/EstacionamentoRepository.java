package br.com.fiap.estacionamento.repository;

import br.com.fiap.estacionamento.model.Estacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstacionamentoRepository extends MongoRepository<Estacionamento, String> {
}
