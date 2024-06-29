package br.alura.gean.forum_hub.model;

import java.util.ArrayList;
import java.util.List;

import br.alura.gean.forum_hub.dto.curso.CursoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Curso")
@Table(name = "cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  @ElementCollection
  @CollectionTable(name = "cursos_categorias", joinColumns = @JoinColumn(name = "curso_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "categoria")
  private List<Categoria> categorias = new ArrayList<>();

  @OneToMany(mappedBy = "curso")
  private List<Topico> topicos = new ArrayList<>();

  public Curso(CursoDto dados) {
    this.nome = dados.nome();
    this.categorias = dados.categorias();
  }

  public void atualizarDados(CursoDto dados) {
    if (dados.nome() != null) {
      this.nome = dados.nome();
    }
    if (!dados.categorias().isEmpty()) {
      this.categorias = dados.categorias();
    }
  }

}
