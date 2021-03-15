package ifrn.projeto.casamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.casamento.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
}
