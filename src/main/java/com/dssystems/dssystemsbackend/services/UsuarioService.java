package com.dssystems.dssystemsbackend.services;

import com.dssystems.dssystemsbackend.models.Usuario;
import com.dssystems.dssystemsbackend.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

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


        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));

        return usuarioRepository.save(novoUsuario);
    }

    public Usuario autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("E-mail não encontrado."));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

        return usuario;
    }
}