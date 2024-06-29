package br.alura.gean.forum_hub.dto.topico;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.alura.gean.forum_hub.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDto(
        @NotBlank(message = "O título não pode ficar vazio.") String titulo,
        @NotBlank(message = "A mensagem não pode ficar vazia.") String mensagem,
        @JsonAlias("autor_id") @NotNull(message = "O id do autor não pode ser nulo.") Long autorId,
        @JsonAlias("curso_id") @NotNull(message = "O id do curso não pode ser nulo.") Long cursoId,
        Status status) {
}
