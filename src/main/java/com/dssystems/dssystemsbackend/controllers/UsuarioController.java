package com.dssystems.dssystemsbackend.controllers;

import com.dssystems.dssystemsbackend.models.Usuario;
import com.dssystems.dssystemsbackend.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "https://zora-indol.vercel.app/")
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

    /**
     * Endpoint para autenticar o usuário (Login)
     */
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senha");

        Usuario usuarioLogado = usuarioService.autenticarUsuario(email, senha);

        return ResponseEntity.ok(usuarioLogado);
    }
}