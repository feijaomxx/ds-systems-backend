package com.dssystems.dssystemsbackend.services;

import com.dssystems.dssystemsbackend.models.Usuario;
import com.dssystems.dssystemsbackend.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Regra de Negócio para o Cadastramento de Usuário (RF001)
     */
    @Transactional
    public Usuario cadastrarUsuario(Usuario novoUsuario) {


        if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("O e-mail informado já está cadastrado no sistema.");
        }


        if (novoUsuario.getCpf() != null && !novoUsuario.getCpf().isBlank()) {
            if (usuarioRepository.findByCpf(novoUsuario.getCpf()).isPresent()) {
                throw new IllegalArgumentException("O CPF informado já está cadastrado no sistema.");
            }
        }


        // novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));


        return usuarioRepository.save(novoUsuario);
    }
}