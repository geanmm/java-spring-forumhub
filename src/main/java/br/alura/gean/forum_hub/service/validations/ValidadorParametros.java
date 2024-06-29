package br.alura.gean.forum_hub.service.validations;

public interface ValidadorParametros<T> {
  void validar(Long id, T dados);
}
