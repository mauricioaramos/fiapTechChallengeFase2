package br.com.fiap.estacionamento.repository;

import br.com.fiap.estacionamento.model.ReservaVaga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservaVagaRepository extends MongoRepository<ReservaVaga, String> {
    @Query("query with param1, param2, param3")
    Optional<ReservaVaga> findBy(String idEstacionamento, String idCondutor, String idVaga);

    public List<ReservaVaga> findByStatusReserva(String ativa);
}
