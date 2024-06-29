package br.alura.gean.forum_hub.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.alura.gean.forum_hub.dto.resposta.RespostaDetailsDto;
import br.alura.gean.forum_hub.dto.resposta.RespostaDto;
import br.alura.gean.forum_hub.model.Resposta;
import br.alura.gean.forum_hub.repository.RespostaRepository;
import br.alura.gean.forum_hub.repository.TopicoRepository;
import br.alura.gean.forum_hub.repository.UsuarioRepository;
import br.alura.gean.forum_hub.service.validations.ValidadorResposta;
import jakarta.transaction.Transactional;

@Service
public class RespostaService {

  @Autowired
  private RespostaRepository respostaRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private TopicoRepository topicoRepository;

  @Autowired
  private ValidadorResposta validadorResposta;

  public RespostaDetailsDto getById(Long id) {
    validadorResposta.validar(id, null);

    var resposta = respostaRepository.findById(id).get();
    return new RespostaDetailsDto(resposta);
  }

  @Transactional
  public RespostaDetailsDto add(RespostaDto dados) {
    validadorResposta.validar(null, dados);

    var autor = usuarioRepository.findById(dados.autorId()).get();
    var topico = topicoRepository.findById(dados.topicoId()).get();
    var resposta = new Resposta(dados);

    resposta.setAutor(autor);
    resposta.setTopico(topico);
    respostaRepository.save(resposta);

    return new RespostaDetailsDto(resposta);
  }

  @Transactional
  public RespostaDetailsDto update(Long id, RespostaDto dados) {
    validadorResposta.validar(id, dados);

    var resposta = respostaRepository.findById(id).get();

    // Não é necessário atualizar o autor e nem o tópico
    resposta.setMensagem(dados.mensagem());
    resposta.setDataAlteracao(LocalDateTime.now());
    respostaRepository.save(resposta);

    return new RespostaDetailsDto(resposta);
  }

  @Transactional
  public void delete(Long id) {
    validadorResposta.validar(id, null);

    respostaRepository.deleteById(id);
  }

}
