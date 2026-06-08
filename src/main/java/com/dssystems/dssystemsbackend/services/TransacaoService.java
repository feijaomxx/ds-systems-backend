package com.dssystems.dssystemsbackend.services;

import com.dssystems.dssystemsbackend.models.Categoria;
import com.dssystems.dssystemsbackend.models.Transacao;
import com.dssystems.dssystemsbackend.models.Usuario;
import com.dssystems.dssystemsbackend.repositories.CategoriaRepository;
import com.dssystems.dssystemsbackend.repositories.TransacaoRepository;
import com.dssystems.dssystemsbackend.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository,
                            UsuarioRepository usuarioRepository,
                            CategoriaRepository categoriaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Regra de Negócio para o Lançamento de Transação (RF002)
     */
    @Transactional
    public Transacao lancarTransacao(Transacao novaTransacao) {

        if (novaTransacao.getValor() == null || novaTransacao.getValor().signum() <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser obrigatório e maior que zero.");
        }

        Usuario usuarioReal = usuarioRepository.findById(novaTransacao.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        Categoria categoriaReal = categoriaRepository.findById(novaTransacao.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));

        novaTransacao.setUsuario(usuarioReal);
        novaTransacao.setCategoria(categoriaReal);

        return transacaoRepository.save(novaTransacao);
    }

    public List<Transacao> listarPorUsuario(Integer usuarioId) {
        return transacaoRepository.findByUsuarioId(usuarioId);
    }
}