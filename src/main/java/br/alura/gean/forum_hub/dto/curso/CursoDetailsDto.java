package br.alura.gean.forum_hub.dto.curso;

import java.util.List;

import br.alura.gean.forum_hub.dto.topico.TopicoResumoDto;
import br.alura.gean.forum_hub.model.Categoria;
import br.alura.gean.forum_hub.model.Curso;

public record CursoDetailsDto(
    Long id,
    String nome,
    List<Categoria> categorias,
    List<TopicoResumoDto> topicos) {

  public CursoDetailsDto(Curso curso) {
    this(curso.getId(),
        curso.getNome(),
        curso.getCategorias(),
        curso.getTopicos().stream().map(TopicoResumoDto::new).toList());
  }
}
