package br.alura.gean.forum_hub.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDto(
                @NotBlank(message = "O nome não pode ficar vazio.") String nome,
                @NotBlank(message = "O email não pode ficar vazio.") @Email String email,
                @NotBlank(message = "A senha não pode ficar vazia.") @Pattern(regexp = "[0-9a-zA-Z$*&@#]{8,}", message = "A senha tem que ter no mínimo 8 dígitos e sem caracteres acentudados") String senha) {

}
