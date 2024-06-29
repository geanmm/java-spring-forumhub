package br.alura.gean.forum_hub.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.alura.gean.forum_hub.repository.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {

    var tokenJWT = recuperarToken(request);
    if (tokenJWT != null) {
      var subject = tokenService.getSubject(tokenJWT);
      var usuario = usuarioRepository.findByEmail(subject);

      var auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    // Chama o próximo filtro caso exista;
    filterChain.doFilter(request, response);
  }

  private String recuperarToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader != null) {
      return authHeader.replace("Bearer ", "");
    }
    return null;
  }
}
