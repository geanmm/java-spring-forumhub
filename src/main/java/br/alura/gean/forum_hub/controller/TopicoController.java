package br.alura.gean.forum_hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.alura.gean.forum_hub.dto.topico.TopicoDetailsDto;
import br.alura.gean.forum_hub.dto.topico.TopicoDto;
import br.alura.gean.forum_hub.dto.topico.TopicoResumoDto;
import br.alura.gean.forum_hub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

  @Autowired
  private TopicoService topicoService;

  @GetMapping
  @Operation(summary = "Lista todos os tópicos com informações resumidas")
  public ResponseEntity<List<TopicoResumoDto>> listarTopicos() {
    var topicos = topicoService.getAll();
    return ResponseEntity.ok(topicos);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Detalha o tópico com base no ID")
  public ResponseEntity<TopicoDetailsDto> detalhar(@PathVariable Long id) {
    var topico = topicoService.getById(id);
    return ResponseEntity.ok(topico);
  }

  @PostMapping
  @Operation(summary = "Adiciona um novo tópico")
  public ResponseEntity<TopicoDetailsDto> cadastrar(@RequestBody @Valid TopicoDto dados,
      UriComponentsBuilder uriBuilder) {
    var topico = topicoService.add(dados);
    var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();

    return ResponseEntity.created(uri).body(topico);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Altera as informações do tópico com base no ID")
  public ResponseEntity<TopicoDetailsDto> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoDto dados) {
    var topico = topicoService.update(id, dados);
    return ResponseEntity.ok(topico);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Remove o tópico com base no ID")
  public ResponseEntity<String> deletar(@PathVariable Long id) {
    topicoService.delete(id);
    return ResponseEntity.ok("Tópico removido com sucesso!");
  }

}
