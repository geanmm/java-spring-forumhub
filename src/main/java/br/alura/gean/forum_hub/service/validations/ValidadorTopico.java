package br.alura.gean.forum_hub.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.alura.gean.forum_hub.dto.topico.TopicoDto;
import br.alura.gean.forum_hub.model.Status;
import br.alura.gean.forum_hub.model.Topico;
import br.alura.gean.forum_hub.repository.TopicoRepository;

@Component
public class ValidadorTopico implements ValidadorParametros<TopicoDto> {

  @Autowired
  private TopicoRepository topicoRepository;

  @Autowired
  private ValidadorCurso validadorCurso;

  @Autowired
  private ValidadorUsuario validadorUsuario;

  @Override
  public void validar(Long id, TopicoDto dados) {
    // Campos vazios já são validados no controller com @Valid;
    if (id != null) {
      validarId(id);
    }

    if (dados != null) {
      validarDados(id, dados);
    }
  }

  private void validarId(Long id) {
    Boolean topicoExists = topicoRepository.existsById(id);

    if (!topicoExists) {
      throw new ValidacaoException("O tópico de id '" + id + "' não existe.");
    }
  }

  private void validarDados(Long id, TopicoDto dados) {
    // Verificar se o curso e o autor são válidos;
    validadorCurso.validar(dados.cursoId(), null);
    validadorUsuario.validar(dados.autorId(), null);

    // Verifica se existe algum tópico com o mesmo título e mensagem
    // e que seja de um autor difente (em caso de atualização);
    Boolean topicoDuplicado;
    if (id == null) {
      // Verifica se já existe um tópico com mesmo conteúdo (Post);
      topicoDuplicado = topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem());
    } else {
      // Verifica se já existe um tópico com mesmo e que não seja do mesmo autor
      // (Update);
      topicoDuplicado = topicoRepository.existsByTituloAndMensagemAndAutorIdNot(dados.titulo(), dados.mensagem(),
          dados.autorId());

      // Proibição de alterar resposta ou adicionar uma nova em um tópico fechado
      Topico topico = topicoRepository.findById(id).get();
      if (Status.SOLUCIONADO.equals(topico.getStatus())) {
        throw new ValidacaoException("Não é possível realizar alterações um tópico solucionado.");
      }
    }

    if (topicoDuplicado) {
      throw new ValidacaoException("Já existe um tópico com mesmo título e mensagem.");
    }

  }

}
