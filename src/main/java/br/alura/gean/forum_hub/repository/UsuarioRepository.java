package br.alura.gean.forum_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.alura.gean.forum_hub.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  UserDetails findByEmail(String email);

  Boolean existsByEmail(String email);

  Boolean existsByEmailAndIdNot(String email, Long id);

}
