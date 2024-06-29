package br.alura.gean.forum_hub.dto.topico;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.alura.gean.forum_hub.model.Status;
import br.alura.gean.forum_hub.model.Topico;

public record TopicoResumoDto(
    Long id,
    @JsonProperty("data_alteracao") LocalDateTime dataAlteracao,
    String titulo,
    Status status,
    @JsonProperty("autor_id") Long autorId,
    @JsonProperty("curso_id") Long cursoId,
    Integer respostas) {

  public TopicoResumoDto(Topico topico) {
    this(topico.getId(),
        topico.getDataAlteracao(),
        topico.getTitulo(),
        topico.getStatus(),
        topico.getAutor().getId(),
        topico.getCurso().getId(),
        topico.getRespostas().size());
  }

}