package br.alura.gean.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import br.alura.gean.forum_hub.dto.AuthenticationDto;
import br.alura.gean.forum_hub.infra.security.TokenJWTDto;
import br.alura.gean.forum_hub.infra.security.TokenService;
import br.alura.gean.forum_hub.model.Usuario;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<Object> efetuarLogin(@RequestBody @Valid AuthenticationDto dados) {
    try {
      var authToken = new UsernamePasswordAuthenticationToken(dados.login(),
          dados.senha());
      var auth = manager.authenticate(authToken);

      var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());

      return ResponseEntity.ok(new TokenJWTDto(tokenJWT));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
