package br.alura.gean.forum_hub.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.alura.gean.forum_hub.dto.topico.TopicoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String titulo;
  private String mensagem;

  @Column(name = "data_criacao")
  private LocalDateTime dataCriacao;

  @Column(name = "data_alteracao")
  private LocalDateTime dataAlteracao;

  @Enumerated(EnumType.STRING)
  private Status status;

  @ManyToOne
  @JoinColumn(name = "autor_id")
  private Usuario autor;

  @ManyToOne
  @JoinColumn(name = "curso_id")
  private Curso curso;

  @OneToMany(mappedBy = "topico")
  private List<Resposta> respostas = new ArrayList<>();

  public Topico(TopicoDto dados) {
    this.status = Status.ABERTO;
    this.titulo = dados.titulo();
    this.mensagem = dados.mensagem();
    this.dataCriacao = LocalDateTime.now();
    this.dataAlteracao = LocalDateTime.now();
  }

}
