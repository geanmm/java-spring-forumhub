package br.alura.gean.forum_hub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.alura.gean.forum_hub.model.Categoria;
import br.alura.gean.forum_hub.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

  Boolean existsByNomeAndCategoriasIn(String nome, List<Categoria> categorias);

}
