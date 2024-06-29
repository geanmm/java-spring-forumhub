package br.alura.gean.forum_hub.dto.usuario;

import java.util.List;

import br.alura.gean.forum_hub.dto.resposta.RespostaResumoDto;
import br.alura.gean.forum_hub.dto.topico.TopicoResumoDto;
import br.alura.gean.forum_hub.model.Usuario;

public record UsuarioDetailsDto(
        Long id,
        String nome,
        String email,
        List<TopicoResumoDto> topicos,
        List<RespostaResumoDto> respostas) {

    public UsuarioDetailsDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTopicos().stream().map(TopicoResumoDto::new).toList(),
                usuario.getRespostas().stream().map(RespostaResumoDto::new).toList());
    }
}
