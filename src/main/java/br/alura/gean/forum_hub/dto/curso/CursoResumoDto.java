package br.alura.gean.forum_hub.dto.curso;

import java.util.List;

import br.alura.gean.forum_hub.model.Categoria;
import br.alura.gean.forum_hub.model.Curso;

public record CursoResumoDto(
    Long id,
    String nome,
    List<Categoria> categorias) {

  public CursoResumoDto(Curso curso) {
    this(curso.getId(),
        curso.getNome(),
        curso.getCategorias());
  }
}
