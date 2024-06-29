package br.alura.gean.forum_hub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.alura.gean.forum_hub.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

  Boolean existsByTituloAndMensagem(String titulo, String mensagem);

  Boolean existsByTituloAndMensagemAndAutorIdNot(String titulo, String mensagem, Long autor);

  List<Topico> findAllByCursoId(Long id);

  List<Topico> findAllByAutorId(Long id);

}
