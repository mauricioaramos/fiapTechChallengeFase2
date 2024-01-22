package br.com.fiap.estacionamento.controller;

import br.com.fiap.estacionamento.model.ReservaVaga;
import br.com.fiap.estacionamento.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    VagaService vagaService;
    @PostMapping("/reservarPeriodoDeEstacionamento")
    public ReservaVaga reservarPeriodoDeEstacionamento(@RequestParam("tipoPeriodo") String tipoPeriodo,
                                                 @RequestParam("duracao") String duracao,
                                                 @RequestBody ReservaVaga reservaVaga) throws Exception {
        return vagaService.reservarPeriodoDeEstacionamento(tipoPeriodo, duracao, reservaVaga);
    }

    @PutMapping("/finalizarPeriodoDeEstacionamento")
    public ReservaVaga finalizarPeriodoDeEstacionamento(@RequestParam("idEstacionamento") String idEstacionamento,
                                                        @RequestParam("idCondutor") String idCondutor,
                                                        @RequestParam("idVaga") String idVaga) throws Exception {
        return vagaService.finalizarPeriodoDeEstacionamento(idEstacionamento, idCondutor, idVaga);
    }



}
