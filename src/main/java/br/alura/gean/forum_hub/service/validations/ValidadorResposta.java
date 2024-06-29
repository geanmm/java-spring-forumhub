package br.alura.gean.forum_hub.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.alura.gean.forum_hub.dto.resposta.RespostaDto;
import br.alura.gean.forum_hub.model.Status;
import br.alura.gean.forum_hub.model.Topico;
import br.alura.gean.forum_hub.repository.RespostaRepository;
import br.alura.gean.forum_hub.repository.TopicoRepository;

@Component
public class ValidadorResposta implements ValidadorParametros<RespostaDto> {

  @Autowired
  private RespostaRepository respostaRepository;

  @Autowired
  private TopicoRepository topicoRepository;

  @Autowired
  private ValidadorUsuario validadorUsuario;

  @Autowired
  private ValidadorTopico validadorTopico;

  @Override
  public void validar(Long id, RespostaDto dados) {
    // Campos vazios já são validados no controller com @Valid;
    if (id != null) {
      validarId(id);
    }

    if (dados != null) {
      validarDados(id, dados);
    }
  }

  private void validarId(Long id) {
    Boolean respostaExists = respostaRepository.existsById(id);

    if (!respostaExists) {
      throw new ValidacaoException("O resposta de id '" + id + "' não existe.");
    }
  }

  private void validarDados(Long id, RespostaDto dados) {

    validadorTopico.validar(dados.topicoId(), null);
    validadorUsuario.validar(dados.autorId(), null);

    // Verifica se a resposta é realmente desse tópico
    Boolean respostaExisteNoTopico = respostaRepository.existsByIdAndTopicoId(id, dados.topicoId());
    if (!respostaExisteNoTopico) {
      throw new ValidacaoException(
          "A resposta de id '" + id + "' não pertence ao tópico de id '" + dados.topicoId() + "'.");
    }

    // Verifica se existe uma resposta identica nesse mesmo tópico
    Boolean respostaDuplicada = respostaRepository.existsByTopicoIdAndMensagem(dados.topicoId(),
        dados.mensagem());
    if (respostaDuplicada) {
      throw new ValidacaoException("Já existe uma resposta com a mesma mensagem para esse tópico.");
    }

    // Proibição de alterar resposta ou adicionar uma nova em um tópico fechado
    Topico topico = topicoRepository.findById(dados.topicoId()).get();
    if (Status.SOLUCIONADO.equals(topico.getStatus())) {
      throw new ValidacaoException("Não é possível realizar alterações um tópico solucionado.");
    }
  }

}
