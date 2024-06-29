package br.alura.gean.forum_hub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.alura.gean.forum_hub.dto.curso.*;
import br.alura.gean.forum_hub.model.Curso;
import br.alura.gean.forum_hub.repository.CursoRepository;
import br.alura.gean.forum_hub.service.validations.ValidadorCurso;
import jakarta.transaction.Transactional;

@Service
public class CursoService {
  @Autowired
  private CursoRepository cursoRepository;

  @Autowired
  private ValidadorCurso validadorCurso;

  public List<CursoResumoDto> getAll() {
    return cursoRepository.findAll().stream().map(CursoResumoDto::new).toList();
  }

  public CursoDetailsDto getById(Long id) {
    validadorCurso.validar(id, null);

    var curso = cursoRepository.findById(id).get();
    return new CursoDetailsDto(curso);

  }

  @Transactional
  public CursoDetailsDto add(CursoDto dados) {
    validadorCurso.validar(null, dados);

    var curso = new Curso(dados);
    cursoRepository.save(curso);
    return new CursoDetailsDto(curso);
  }

  @Transactional
  public CursoDetailsDto update(Long id, CursoDto dados) {
    validadorCurso.validar(id, dados);

    var cursoEncontrado = cursoRepository.findById(id).get();

    cursoEncontrado.setNome(dados.nome());
    cursoEncontrado.setCategorias(dados.categorias());

    return new CursoDetailsDto(cursoEncontrado);
  }

  @Transactional
  public void delete(Long id) {
    validadorCurso.validar(id, null);
    cursoRepository.deleteById(id);
  }

}
