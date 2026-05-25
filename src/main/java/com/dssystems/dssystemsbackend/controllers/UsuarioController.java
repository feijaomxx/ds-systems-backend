package com.dssystems.dssystemsbackend.controllers;

import com.dssystems.dssystemsbackend.models.Usuario;
import com.dssystems.dssystemsbackend.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para criar um novo usuário (RF001)
     */
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = usuarioService.cadastrarUsuario(usuario);


        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }
}