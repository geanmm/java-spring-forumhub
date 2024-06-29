package br.alura.gean.forum_hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.alura.gean.forum_hub.dto.curso.*;
import br.alura.gean.forum_hub.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

  @Autowired
  private CursoService service;

  @GetMapping
  @Operation(summary = "Lista todos os cursos com informações resumidas")
  public ResponseEntity<List<CursoResumoDto>> listar() {
    var cursos = service.getAll();
    return ResponseEntity.ok(cursos);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Detalha o curso com base no ID")
  public ResponseEntity<CursoDetailsDto> detalhar(@PathVariable Long id) {
    var curso = service.getById(id);
    return ResponseEntity.ok(curso);
  }

  @PostMapping
  @Operation(summary = "Adiciona um novo curso")
  public ResponseEntity<CursoDetailsDto> cadastrar(@RequestBody @Valid CursoDto dados,
      UriComponentsBuilder uriBuilder) {
    var curso = service.add(dados);
    var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.id()).toUri();

    return ResponseEntity.created(uri).body(curso);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Altera as informações do curso com base no ID")
  public ResponseEntity<CursoDetailsDto> atualizar(@PathVariable Long id, @RequestBody CursoDto dados) {
    var curso = service.update(id, dados);
    return ResponseEntity.ok(curso);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Remove o curso com base no ID")
  public ResponseEntity<String> excluir(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.ok("Deletado com sucesso");
  }

}
