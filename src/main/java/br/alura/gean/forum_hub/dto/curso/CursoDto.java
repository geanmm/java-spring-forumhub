package br.alura.gean.forum_hub.dto.curso;

import java.util.List;

import br.alura.gean.forum_hub.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CursoDto(
        @NotBlank(message = "O nome não pode ficar vazio.") String nome,
        @NotEmpty(message = "É preciso informar pelo menos uma categoria.") List<Categoria> categorias) {
}
