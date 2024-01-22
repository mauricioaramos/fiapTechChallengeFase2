package br.com.fiap.estacionamento.repository;

import br.com.fiap.estacionamento.model.Condutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CondutorRepository extends MongoRepository<Condutor, String> {




}
