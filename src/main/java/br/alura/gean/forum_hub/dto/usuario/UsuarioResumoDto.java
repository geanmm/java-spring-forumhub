package br.alura.gean.forum_hub.dto.usuario;

import br.alura.gean.forum_hub.model.Usuario;

public record UsuarioResumoDto(
    Long id,
    String nome) {

  public UsuarioResumoDto(Usuario usuario) {
    this(usuario.getId(), usuario.getNome());
  }
}
