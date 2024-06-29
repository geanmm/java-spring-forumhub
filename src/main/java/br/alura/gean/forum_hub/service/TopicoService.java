package br.alura.gean.forum_hub.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.alura.gean.forum_hub.dto.topico.*;
import br.alura.gean.forum_hub.model.Topico;
import br.alura.gean.forum_hub.repository.CursoRepository;
import br.alura.gean.forum_hub.repository.TopicoRepository;
import br.alura.gean.forum_hub.repository.UsuarioRepository;
import br.alura.gean.forum_hub.service.validations.ValidadorTopico;
import jakarta.transaction.Transactional;

@Service
public class TopicoService {

  @Autowired
  private TopicoRepository topicoRepository;

  @Autowired
  private CursoRepository cursoRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private ValidadorTopico validadorTopico;

  public List<TopicoResumoDto> getAll() {
    return topicoRepository.findAll().stream().map(TopicoResumoDto::new).toList();
  }

  public TopicoDetailsDto getById(Long id) {
    validadorTopico.validar(id, null);
    var topico = topicoRepository.findById(id).get();

    return new TopicoDetailsDto(topico);
  }

  @Transactional
  public TopicoDetailsDto add(TopicoDto dados) {
    // Busca e verifica os dados de topico, curso e autor são válidos;
    validadorTopico.validar(null, dados);

    var autor = usuarioRepository.findById(dados.autorId()).get();
    var curso = cursoRepository.findById(dados.cursoId()).get();

    var topico = new Topico(dados);
    topico.setAutor(autor);
    topico.setCurso(curso);
    topicoRepository.save(topico);

    return new TopicoDetailsDto(topico);
  }

  @Transactional
  public TopicoDetailsDto update(Long id, TopicoDto dados) {
    validadorTopico.validar(id, dados);
    var topico = topicoRepository.findById(id).get();
    var autor = usuarioRepository.findById(dados.autorId()).get();
    var curso = cursoRepository.findById(dados.cursoId()).get();

    topico.setTitulo(dados.titulo());
    topico.setMensagem(dados.mensagem());
    topico.setDataAlteracao(LocalDateTime.now());
    topico.setStatus(dados.status());
    topico.setAutor(autor);
    topico.setCurso(curso);

    topicoRepository.save(topico);
    return new TopicoDetailsDto(topico);
  }

  @Transactional
  public void delete(Long id) {
    validadorTopico.validar(id, null);
    topicoRepository.deleteById(id);
  }
}
