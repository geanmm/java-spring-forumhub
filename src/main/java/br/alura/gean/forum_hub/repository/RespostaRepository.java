package br.alura.gean.forum_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.alura.gean.forum_hub.model.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
  Boolean existsByTopicoIdAndMensagem(Long topicoId, String mensagem);

  Boolean existsByIdAndTopicoId(Long id, Long idTopico);
}
