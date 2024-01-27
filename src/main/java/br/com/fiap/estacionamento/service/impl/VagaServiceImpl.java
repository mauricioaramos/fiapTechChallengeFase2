package br.com.fiap.estacionamento.service.impl;

import br.com.fiap.estacionamento.model.*;
import br.com.fiap.estacionamento.repository.CondutorRepository;
import br.com.fiap.estacionamento.repository.EstacionamentoRepository;
import br.com.fiap.estacionamento.repository.ReservaVagaRepository;
import br.com.fiap.estacionamento.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class VagaServiceImpl implements VagaService {

    private static final long TOLERANCIA_MINUTOS = 5;
    private final MongoTemplate mongoTemplate;
    @Autowired
    EstacionamentoRepository estacionamentoRepository;

    @Autowired
    ReservaVagaRepository reservaVagaRepository;

    @Autowired
    CondutorRepository condutorRepository;

    public VagaServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ReservaVaga reservarPeriodoDeEstacionamento(String tipoPeriodo, String duracao, ReservaVaga reservaVaga) throws Exception {

        Estacionamento estacionamento = estacionamentoRepository.findById(reservaVaga.getCodigoEstacionamento())
                .orElseThrow(() -> new IllegalArgumentException("Estacionamento não encontrado"));

        List<Vaga> vagas = estacionamento.getVagas().stream()
                .filter(vaga -> vaga.getStatus().equalsIgnoreCase("LIVRE")).toList();

        if(vagas.isEmpty()){
            throw new Exception("Não há vagas disponiveis no estacionamento");
        }

        reservaVaga.setTipoPeriodo(tipoPeriodo);

        reservaVaga.setValorHora(estacionamento.getValorHora());
        validarReserva(tipoPeriodo, reservaVaga);

        if(tipoPeriodo.equals("Variavel") ){
            reservaVaga.setDataEntrada(LocalDateTime.now());
        }else{
            reservaVaga.setDataSaida(LocalDateTime.now().plusHours(Integer.parseInt(duracao)));

            reservaVaga.setValorTotal(reservaVaga.getValorHora() * Integer.parseInt(duracao));

            Condutor condutor = condutorRepository.findById(reservaVaga.getCodigoCondutor())
                    .orElseThrow(() -> new IllegalArgumentException("Condutor não encontrado"));

            condutor.setFormaPagamento(obterFormaDePagamentoDaReserva(reservaVaga));

            String statusPagamento = condutor.getFormaPagamento().realizarPagamento(reservaVaga.getValorTotal());
            reservaVaga.setStatusPagamento(statusPagamento);
        }

        reservaVaga.setNumeroVaga(vagas.get(0).getNumero());
        reservaVagaRepository.save(reservaVaga);
        vagas.get(0).setStatus("OCUPADA");

        estacionamentoRepository.save(estacionamento);

        return reservaVaga;
    }

    private static void validarReserva(String tipoPeriodo, ReservaVaga reservaVaga) throws Exception {
        if(reservaVaga.getTipoPagamento().equalsIgnoreCase("PIX") && !tipoPeriodo.equals("Fixo")){
            throw new Exception("A forma de pagamento PIX só e permitida para tipoPeriodo fixo.");
        }
        if(tipoPeriodo.equals("Fixo") && (reservaVaga.getDataEntrada() == null )){
            throw new Exception("Data de entrada não informada");
        }
        /*if(tipoPeriodo.equals("Fixo") && reservaVaga.getDataEntrada().isAfter(reservaVaga.getDataSaida())){
            throw new Exception("Data de entrada não pode ser maior que a data de saida");
        }
        if(tipoPeriodo.equals("Fixo") && reservaVaga.getDataEntrada().isBefore(LocalDateTime.now())){
            throw new Exception("Data de entrada não pode ser menor que a data atual");
        }
        if(tipoPeriodo.equals("Fixo") && reservaVaga.getDataSaida().isBefore(LocalDateTime.now())){
            throw new Exception("Data de saida não pode ser menor que a data atual");
        }
        if(tipoPeriodo.equals("Fixo") && reservaVaga.getDataEntrada().isEqual(reservaVaga.getDataSaida())){
            throw new Exception("Data de entrada não pode ser igual a data de saida");
        }*/
        if(tipoPeriodo.equals("Fixo") && reservaVaga.getValorHora() == null){
            throw new Exception("Valor da hora não informado");
        }
        if(tipoPeriodo.equals("Fixo") && reservaVaga.getValorHora() <= 0){
            throw new Exception("Valor da hora não pode ser menor ou igual a zero");
        }
        if(tipoPeriodo.equals("Fixo") && reservaVaga.getTipoPagamento() == null){
            throw new Exception("Tipo de pagamento não informado");
        }
        if(tipoPeriodo.equals("Fixo") && reservaVaga.getTipoPagamento().equalsIgnoreCase("CARTAO") && reservaVaga.getFuncaoCartao() == null){
            throw new Exception("Função do cartão não informada");
        }
    }

    @Override
    public ReservaVaga finalizarPeriodoDeEstacionamento(String idEstacionamento, String idCondutor, String idVaga) throws Exception {

        Estacionamento estacionamento = estacionamentoRepository.findById(idEstacionamento)
                .orElseThrow(() -> new IllegalArgumentException("Estacionamento não encontrado"));

        List<Vaga> vagas = estacionamento.getVagas().stream()
                .filter(vaga -> vaga.getNumero().equalsIgnoreCase(idVaga)).toList();

        vagas.get(0).setStatus("LIVRE");

        ReservaVaga reservaVaga = findReservaVaga(idEstacionamento, idCondutor, idVaga);

        reservaVaga.setDataSaida(LocalDateTime.now());

        reservaVaga.setValorTotal(calculaValorVariavel(reservaVaga));

        Condutor condutor = condutorRepository.findById(idCondutor)
                .orElseThrow(() -> new IllegalArgumentException("Condutor não encontrado"));

        condutor.setFormaPagamento(obterFormaDePagamentoDaReserva(reservaVaga));

        String statusPagamento = condutor.getFormaPagamento().realizarPagamento(reservaVaga.getValorTotal());
        reservaVaga.setStatusPagamento(statusPagamento);
        reservaVaga.setTempoPermanencia(reservaVaga.getDataEntrada().until(reservaVaga.getDataSaida(), ChronoUnit.MINUTES)+ " minutos");

        reservaVagaRepository.save(reservaVaga);
        estacionamentoRepository.save(estacionamento);

        emitirNotaFiscal(reservaVaga);

        return reservaVaga;
    }

    public void emitirNotaFiscal(ReservaVaga reservaVaga) {
        System.out.println("Nota fiscal emitida");
    }

    @Override
    public List<ReservaVaga> obterReservas() throws Exception {
        return reservaVagaRepository.findAll();
    }

    private ReservaVaga findReservaVaga(String idEstacionamento, String idCondutor, String idVaga) throws Exception {
        Query query = new Query(Criteria.where("codigoEstacionamento").is(idEstacionamento)
                .andOperator(Criteria.where("codigoCondutor").is(idCondutor)
                        .andOperator(Criteria.where("numeroVaga").is(idVaga))));

        ReservaVaga reservaVaga = mongoTemplate.findOne(query, ReservaVaga.class);
        if(reservaVaga == null){
            throw new Exception("Reserva não encontrada");
        }
        return reservaVaga;
    }

    private static FormaPagamento obterFormaDePagamentoDaReserva(ReservaVaga reservaVaga) {
        if(reservaVaga.getTipoPagamento().equalsIgnoreCase("PIX"))
            return new Pix();
        else {
            Cartao cartao = new Cartao();
            if(reservaVaga.getFuncaoCartao().equalsIgnoreCase("DEBITO")) {
                cartao.setFuncaoCartao(new FuncaoDebito());
            }
            else if(reservaVaga.getFuncaoCartao().equalsIgnoreCase("CREDITO")) {
                cartao.setFuncaoCartao(new FuncaoCredito());
            }
            return cartao;
        }
    }

    private Double calculaValorVariavel(ReservaVaga reservaVaga) {
        LocalDateTime dataEntrada = reservaVaga.getDataEntrada();
        LocalDateTime dataSaida = reservaVaga.getDataSaida();

        long diferencaMinutos = dataEntrada.until(dataSaida, ChronoUnit.MINUTES);
        long diferencaEmHora = diferencaMinutos / 60;
        long minutosAMais = diferencaMinutos - (diferencaEmHora * 60);
        if(diferencaMinutos % 60 != 0 && minutosAMais > TOLERANCIA_MINUTOS){
            diferencaEmHora += 1;
        }
        return reservaVaga.getValorHora() * diferencaEmHora;

    }


}
