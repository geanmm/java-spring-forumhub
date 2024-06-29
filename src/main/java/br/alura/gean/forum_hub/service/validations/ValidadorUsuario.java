package br.alura.gean.forum_hub.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.alura.gean.forum_hub.dto.usuario.UsuarioDto;
import br.alura.gean.forum_hub.repository.UsuarioRepository;

@Component
public class ValidadorUsuario implements ValidadorParametros<UsuarioDto> {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public void validar(Long id, UsuarioDto dados) {
    // Campos vazios já são validados no controller com @Valid;
    if (id != null) {
      validarId(id);
    }

    if (dados != null) {
      validarDados(id, dados);
    }

  }

  private void validarId(Long id) {
    Boolean usuarioExists = usuarioRepository.existsById(id);

    if (!usuarioExists) {
      throw new ValidacaoException("O usuário de id '" + id + "' não existe.");
    }
  }

  private void validarDados(Long id, UsuarioDto dados) {
    Boolean emailExists;

    if (id == null) {
      // Verifica se o email já está cadastrado em algum usuário (Post);
      emailExists = usuarioRepository.existsByEmail(dados.email());
    } else {
      // Verifica se o email existe e é de um usuário DIFERENTE do id atual (Update);
      emailExists = usuarioRepository.existsByEmailAndIdNot(dados.email(), id);
    }
    if (emailExists) {
      throw new ValidacaoException("O email inserido já existe.");
    }
  }

}
