package com.dssystems.dssystemsbackend.services;

import com.dssystems.dssystemsbackend.models.Transacao;
import com.dssystems.dssystemsbackend.repositories.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    /**
     * Regra de Negócio para o Lançamento de Transação (RF002)
     */
    @Transactional
    public Transacao lancarTransacao(Transacao novaTransacao) {


        if (novaTransacao.getValor() == null || novaTransacao.getValor().signum() <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser obrigatório e maior que zero.");
        }


        return transacaoRepository.save(novaTransacao);
    }
}