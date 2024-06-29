package br.alura.gean.forum_hub.service.validations;

public class ValidacaoException extends RuntimeException {
  public ValidacaoException(String mensagem) {
    super(mensagem);
  }
}
