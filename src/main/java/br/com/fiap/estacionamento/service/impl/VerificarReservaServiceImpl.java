package br.com.fiap.estacionamento.service.impl;

import br.com.fiap.estacionamento.model.ReservaVaga;
import br.com.fiap.estacionamento.repository.ReservaVagaRepository;
import br.com.fiap.estacionamento.service.VagaService;
import br.com.fiap.estacionamento.service.VerificarReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class VerificarReservaServiceImpl implements VerificarReservaService {

    @Autowired
    ReservaVagaRepository reservaVagaRepository;
    @Autowired
    VagaService vagaService;

    @Override
    public void verificarReserva() throws Exception {
        this.verificarSeReservaEstaProximoDoVencimento(5);
        this.verificarSeReservaPeriodoFixoEstaVencidaEFinalizaPeriodoEstacinamento();
    }

    public void quantoTempoFaltaParaADataDeSaida(LocalDateTime dataSaida){
        LocalDateTime dataAtual = LocalDateTime.now();
        long duracaoEmMinutos = dataAtual.until(dataSaida, ChronoUnit.MINUTES);
        System.out.println("Faltam " + duracaoEmMinutos + " minutos para a data de saida");
    }
    private void verificarSeReservaEstaProximoDoVencimento(Integer minutosAntesDeVencer){
        List<ReservaVaga>  reservaAtiva = reservaVagaRepository.findByStatusReserva("ATIVA");
        LocalDateTime dataAtual = LocalDateTime.now();
        reservaAtiva.forEach(reservaVaga -> {
            LocalDateTime dataSaidaEstimada = reservaVaga.getDataEntrada().plusHours(Integer.parseInt(reservaVaga.getDuracao()));

            long duracaoEmMinutos = dataAtual.until(dataSaidaEstimada, ChronoUnit.MINUTES);

            //long duracaoExtimadaEmMinutos = dataSaidaEstimada.until(LocalDateTime.now(), ChronoUnit.MINUTES);

            //long duracaoAtualEmMinutos = reservaVaga.getDataEntrada().until(LocalDateTime.now(), ChronoUnit.MINUTES);

            if( duracaoEmMinutos <= minutosAntesDeVencer && duracaoEmMinutos > 0){
                enviarNotificacao(reservaVaga.getTipoPeriodo());
                reservaVagaRepository.save(reservaVaga);
            }

        });
    }

    private void verificarSeReservaPeriodoFixoEstaVencidaEFinalizaPeriodoEstacinamento() throws Exception{
        List<ReservaVaga>  reservaAtiva = reservaVagaRepository.findByStatusReserva("ATIVA");
        reservaAtiva.stream().
                filter(reservaVaga -> reservaVaga.getTipoPeriodo().equals("Fixo"))
                .forEach(reservaVaga -> {
            if (reservaVaga.getDataEntrada().plusHours(Integer.parseInt(reservaVaga.getDuracao())).isBefore(LocalDateTime.now())){
                try {
                    vagaService.finalizarPeriodoDeEstacionamento(reservaVaga.getCodigoEstacionamento(), reservaVaga.getCodigoCondutor(), reservaVaga.getNumeroVaga());
                    reservaVaga.setStatusReserva("EXPIRADA");
                    reservaVagaRepository.save(reservaVaga);
                } catch (Exception e) {
                    // logar erro
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void enviarNotificacao(String tipoPeriodo){
        // enviar notificação para o condutor

        if(tipoPeriodo.equals("Variavel")){
            // enviar notificação para o condutor
            String notificacao = "Sua reserva está prestes a vencer, caso nao encerre a reserva, sera adicionado mais 1 hora de estacionamento";
            System.out.println(notificacao);
        }else{
            // enviar notificação para o condutor
            String notificacao = "Sua reserva está prestes a vencer, caso nao renove a vaga sera liberada para outro condutor";
            System.out.println(notificacao);

        }
    }

}
