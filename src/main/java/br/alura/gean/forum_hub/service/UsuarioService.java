package br.alura.gean.forum_hub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.alura.gean.forum_hub.dto.usuario.*;
import br.alura.gean.forum_hub.model.Usuario;
import br.alura.gean.forum_hub.repository.UsuarioRepository;
import br.alura.gean.forum_hub.service.validations.ValidadorUsuario;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder senhaEncoder;

  @Autowired
  private ValidadorUsuario validadorUsuario;

  public List<UsuarioResumoDto> getAll() {
    return usuarioRepository.findAll().stream().map(UsuarioResumoDto::new).toList();
  }

  public UsuarioDetailsDto getById(Long id) {
    validadorUsuario.validar(id, null);

    var usuario = usuarioRepository.findById(id).get();
    return new UsuarioDetailsDto(usuario);
  }

  @Transactional
  public UsuarioDetailsDto add(UsuarioDto dados) {
    validadorUsuario.validar(null, dados);

    var usuario = new Usuario(dados);
    usuario.setSenha(senhaEncoder.encode(dados.senha()));

    usuarioRepository.save(usuario);
    return new UsuarioDetailsDto(usuario);
  }

  @Transactional
  public UsuarioDetailsDto update(Long id, UsuarioDto dados) {
    validadorUsuario.validar(id, dados);

    var usuario = usuarioRepository.findById(id).get();

    // Validações dos campos vazios já feitas na requisição com @Valid.
    usuario.setNome(dados.nome());
    usuario.setEmail(dados.email());
    usuario.setSenha(senhaEncoder.encode(dados.senha()));

    return new UsuarioDetailsDto(usuario);
  }

  @Transactional
  public void delete(Long id) {
    validadorUsuario.validar(id, null);

    usuarioRepository.deleteById(id);
  }

}
