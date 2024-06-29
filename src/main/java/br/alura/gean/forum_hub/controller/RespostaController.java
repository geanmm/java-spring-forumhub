package br.alura.gean.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.alura.gean.forum_hub.dto.resposta.RespostaDetailsDto;
import br.alura.gean.forum_hub.dto.resposta.RespostaDto;
import br.alura.gean.forum_hub.service.RespostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("respostas")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

  @Autowired
  private RespostaService service;

  @GetMapping("/{id}")
  @Operation(summary = "Detalha a resposta com base no ID")
  public ResponseEntity<RespostaDetailsDto> detalhar(@PathVariable Long id) {
    var resposta = service.getById(id);

    return ResponseEntity.ok(resposta);
  }

  @PostMapping
  @Operation(summary = "Adiciona uma nova resposta")
  public ResponseEntity<RespostaDetailsDto> cadastrar(@RequestBody @Valid RespostaDto dados) {
    var resposta = service.add(dados);

    return ResponseEntity.ok(resposta);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Altera as informações da resposta com base no ID")

  public ResponseEntity<RespostaDetailsDto> atualizar(@PathVariable Long id, @RequestBody @Valid RespostaDto dados) {
    var resposta = service.update(id, dados);

    return ResponseEntity.ok(resposta);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Remove a resposta com base no ID")
  public ResponseEntity<String> deletar(@PathVariable Long id) {
    service.delete(id);

    return ResponseEntity.ok("Resposta removida com sucesso!");
  }

}
