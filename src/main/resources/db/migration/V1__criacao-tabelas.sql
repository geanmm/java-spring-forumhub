CREATE TABLE cursos(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,

  PRIMARY KEY(id)
);

CREATE TABLE usuarios(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL,

  PRIMARY KEY(id)
);

CREATE TABLE topicos(
  id BIGINT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  mensagem TEXT NOT NULL,
  data_criacao DATETIME NOT NULL,
  data_alteracao DATETIME NOT NULL,
  status VARCHAR(255) NOT NULL,
  autor_id BIGINT NOT NULL,
  curso_id BIGINT NOT NULL,

  PRIMARY KEY(id),
  CONSTRAINT fk_topicos_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios (id),
  CONSTRAINT fk_topicos_curso_id FOREIGN KEY (curso_id) REFERENCES cursos (id)
);

CREATE TABLE respostas(
  id BIGINT NOT NULL AUTO_INCREMENT,
  mensagem TEXT NOT NULL,
  topico_id BIGINT NOT NULL,
  data_criacao DATETIME NOT NULL,
  data_alteracao DATETIME NOT NULL,
  autor_id BIGINT NOT NULL,
  solucao TINYINT NOT NULL,

  PRIMARY KEY(id),
  CONSTRAINT fk_repostas_topico_id FOREIGN KEY (topico_id) REFERENCES topicos (id),
  CONSTRAINT fk_repostas_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios (id)
);

CREATE TABLE cursos_categorias(
  curso_id BIGINT NOT NULL,
  categoria VARCHAR(100) NOT NULL,

  PRIMARY KEY (curso_id, categoria),
  CONSTRAINT fk_curso_categoria FOREIGN KEY (curso_id) REFERENCES cursos (id)
);
