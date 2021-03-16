package ifrn.projeto.casamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.casamento.models.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
 
	Papel findByNome(String nome);
}
