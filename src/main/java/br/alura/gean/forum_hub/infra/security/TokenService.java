package br.alura.gean.forum_hub.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.alura.gean.forum_hub.model.Usuario;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String secret;

  private static final String ISSUER = "Forum Hub API";

  public String gerarToken(Usuario usuario) {
    try {
      var algoritimo = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer(ISSUER)
          .withSubject(usuario.getEmail())
          .withExpiresAt(dataExpiracao())
          .sign(algoritimo);
    } catch (JWTCreationException e) {
      throw new RuntimeException("Erro ao gerar token JWT: ", e);
    }
  }

  public String getSubject(String tokenJWT) {
    try {
      var algoritimo = Algorithm.HMAC256(secret);
      return JWT.require(algoritimo)
          .withIssuer(ISSUER)
          .build()
          .verify(tokenJWT)
          .getSubject();
    } catch (JWTVerificationException e) {
      throw new RuntimeException("Token JWT inválido ou expirado: " + tokenJWT);
    }
  }

  private Instant dataExpiracao() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
