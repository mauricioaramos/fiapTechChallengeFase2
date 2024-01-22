package br.com.fiap.estacionamento.repository;

import br.com.fiap.estacionamento.model.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
}
