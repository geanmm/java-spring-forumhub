package br.alura.gean.forum_hub.dto.resposta;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespostaDto(
                @NotBlank String mensagem,
                @JsonAlias("topico_id") @NotNull Long topicoId,
                @JsonAlias("autor_id") @NotNull Long autorId) {

}
