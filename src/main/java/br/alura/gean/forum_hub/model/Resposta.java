package br.alura.gean.forum_hub.model;

import java.time.LocalDateTime;

import br.alura.gean.forum_hub.dto.resposta.RespostaDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Reposta")
@Table(name = "respostas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String mensagem;

  @ManyToOne
  @JoinColumn(name = "topico_id")
  private Topico topico;

  @Column(name = "data_criacao")
  private LocalDateTime dataCriacao;

  @Column(name = "data_alteracao")
  private LocalDateTime dataAlteracao;

  @ManyToOne
  @JoinColumn(name = "autor_id")
  private Usuario autor;
  private Boolean solucao;

  public Resposta(RespostaDto dados) {
    this.solucao = false;
    this.mensagem = dados.mensagem();
    this.dataCriacao = LocalDateTime.now();
    this.dataAlteracao = LocalDateTime.now();
  }
}
