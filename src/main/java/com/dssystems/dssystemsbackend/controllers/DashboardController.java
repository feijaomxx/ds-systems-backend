package com.dssystems.dssystemsbackend.controllers;

import com.dssystems.dssystemsbackend.models.ResumoMensalProjection;
import com.dssystems.dssystemsbackend.repositories.TransacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final TransacaoRepository transacaoRepository;

    public DashboardController(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    /**
     * Retorna os dados consolidados da View para o gráfico do Angular
     */
    @GetMapping("/resumo/{nomeUsuario}")
    public ResponseEntity<List<ResumoMensalProjection>> obterResumo(@PathVariable String nomeUsuario) {

        List<ResumoMensalProjection> resumo = transacaoRepository.buscarResumoDoDashboard(nomeUsuario);
        return ResponseEntity.ok(resumo);

    }
}