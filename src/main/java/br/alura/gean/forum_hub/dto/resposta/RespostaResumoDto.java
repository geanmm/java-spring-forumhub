package br.alura.gean.forum_hub.dto.resposta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.alura.gean.forum_hub.model.Resposta;

public record RespostaResumoDto(
    Long id,
    @JsonProperty("data_alteracao") LocalDateTime dataAlteracao,
    @JsonProperty("autor_id") Long autorId,
    @JsonProperty("topico_id") Long topicoId,
    Boolean solucao,
    String mensagem) {

  public RespostaResumoDto(Resposta resposta) {
    this(
        resposta.getId(),
        resposta.getDataAlteracao(),
        resposta.getAutor().getId(),
        resposta.getTopico().getId(),
        resposta.getSolucao(),
        resposta.getMensagem());
  }
}