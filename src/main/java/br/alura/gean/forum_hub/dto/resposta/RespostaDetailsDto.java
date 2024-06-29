package br.alura.gean.forum_hub.dto.resposta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.alura.gean.forum_hub.dto.topico.TopicoResumoDto;
import br.alura.gean.forum_hub.dto.usuario.UsuarioResumoDto;
import br.alura.gean.forum_hub.model.Resposta;

public record RespostaDetailsDto(
        Long id,
        @JsonProperty("data_criacao") LocalDateTime dataCriacacao,
        @JsonProperty("data_alteracao") LocalDateTime dataAlteracao,
        TopicoResumoDto topico,
        UsuarioResumoDto autor,
        Boolean solucao,
        String mensagem) {

    public RespostaDetailsDto(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getDataCriacao(),
                resposta.getDataAlteracao(),
                new TopicoResumoDto(resposta.getTopico()),
                new UsuarioResumoDto(resposta.getAutor()),
                resposta.getSolucao(),
                resposta.getMensagem());
    }
}
