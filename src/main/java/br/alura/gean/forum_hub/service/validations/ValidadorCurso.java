package br.alura.gean.forum_hub.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.alura.gean.forum_hub.dto.curso.CursoDto;
import br.alura.gean.forum_hub.repository.CursoRepository;

@Component
public class ValidadorCurso implements ValidadorParametros<CursoDto> {
  @Autowired
  private CursoRepository cursoRepository;

  @Override
  public void validar(Long id, CursoDto dados) {
    // Campos vazios já são validados no controller com @Valid;
    if (id != null) {
      validarId(id);
    }
    if (dados != null) {
      validarDados(dados);
    }
  }

  private void validarId(Long id) {
    Boolean cursoExists = cursoRepository.existsById(id);

    if (!cursoExists) {
      throw new ValidacaoException("O curso de id '" + id + "' não existe.");
    }
  }

  private void validarDados(CursoDto dados) {
    Boolean cursoExists = cursoRepository.existsByNomeAndCategoriasIn(dados.nome(), dados.categorias());

    if (cursoExists) {
      throw new ValidacaoException("Já existe um curso com esse nome e essa(s) mesma(s) categoria(s)!");
    }
  }
}
