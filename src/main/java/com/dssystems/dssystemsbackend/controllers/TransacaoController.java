package com.dssystems.dssystemsbackend.controllers;

import com.dssystems.dssystemsbackend.models.Transacao;
import com.dssystems.dssystemsbackend.services.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    /**
     * Endpoint para lançar uma nova Receita ou Despesa (RF002)
     */
    @PostMapping
    public ResponseEntity<Transacao> lancarTransacao(@RequestBody Transacao transacao) {
        Transacao transacaoSalva = transacaoService.lancarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoSalva);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Transacao>> listarTransacoes(@PathVariable Integer usuarioId) {
        List<Transacao> lista = transacaoService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(lista);
    }
}