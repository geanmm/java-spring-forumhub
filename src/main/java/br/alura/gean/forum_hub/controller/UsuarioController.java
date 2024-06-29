package br.alura.gean.forum_hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.alura.gean.forum_hub.dto.usuario.*;
import br.alura.gean.forum_hub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

  @Autowired
  private UsuarioService service;

  @GetMapping("/usuarios")
  @Operation(summary = "Lista todos os usuários com informações resumidas")
  public ResponseEntity<List<UsuarioResumoDto>> listar() {
    var usuarios = service.getAll();
    return ResponseEntity.ok(usuarios);
  }

  @GetMapping("/usuarios/{id}")
  @Operation(summary = "Detalha o usuário com base no ID")
  public ResponseEntity<UsuarioDetailsDto> detalhar(@PathVariable Long id) {
    var user = service.getById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/registrar")
  @Operation(summary = "Adiciona um novo usuário")
  public ResponseEntity<UsuarioDetailsDto> cadastrar(@RequestBody @Valid UsuarioDto dados,
      UriComponentsBuilder uriBuilder) {
    var usuario = service.add(dados);
    var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();

    return ResponseEntity.created(uri).body(usuario);
  }

  @PutMapping("/usuarios/{id}")
  @Operation(summary = "Altera as informações do usuário com base no ID")
  public ResponseEntity<UsuarioDetailsDto> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioDto dados) {
    var usuario = service.update(id, dados);
    return ResponseEntity.ok(usuario);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Remove o usuário com base no ID")
  public ResponseEntity<String> excluir(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.ok("Usuário removido com sucesso");
  }

}
