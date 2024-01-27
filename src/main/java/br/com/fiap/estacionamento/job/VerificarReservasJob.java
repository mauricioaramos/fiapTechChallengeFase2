package br.com.fiap.estacionamento.job;

import br.com.fiap.estacionamento.service.VerificarReservaService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class VerificarReservasJob {

    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;

    Logger logger = org.slf4j.LoggerFactory.getLogger(VerificarReservasJob.class);

    @Autowired
    VerificarReservaService verificarReservaService;

    @Scheduled(fixedDelay = MINUTO)
    public void verificaPorMinuto() {
        System.out.println(" Init Verificando reservas a cada minuto");
        try {
            verificarReservaService.verificarReserva();
        } catch (Exception e) {
            System.out.println("Erro ao verificar reservas" + e.getMessage());
        }
        System.out.println(" Finish Verificando reservas a cada minuto");
    }
}
