package br.alura.gean.forum_hub.dto.topico;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.alura.gean.forum_hub.dto.curso.CursoResumoDto;
import br.alura.gean.forum_hub.dto.resposta.RespostaResumoDto;
import br.alura.gean.forum_hub.dto.usuario.UsuarioResumoDto;
import br.alura.gean.forum_hub.model.Status;
import br.alura.gean.forum_hub.model.Topico;

public record TopicoDetailsDto(
        Long id,
        @JsonProperty("data_criacao") LocalDateTime dataCriacao,
        @JsonProperty("data_alteracao") LocalDateTime dataAlteracao,
        String titulo,
        Status status,
        String mensagem,
        UsuarioResumoDto autor,
        CursoResumoDto curso,
        List<RespostaResumoDto> respostas) {

    public TopicoDetailsDto(Topico topico) {
        this(
                topico.getId(),
                topico.getDataCriacao(),
                topico.getDataAlteracao(),
                topico.getTitulo(),
                topico.getStatus(),
                topico.getMensagem(),
                new UsuarioResumoDto(topico.getAutor()),
                new CursoResumoDto(topico.getCurso()),
                topico.getRespostas().stream().map(RespostaResumoDto::new).toList());
    }

}
