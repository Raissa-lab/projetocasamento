package ifrn.projeto.casamento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.casamento.models.Casamento;
import ifrn.projeto.casamento.models.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	List<Proposta> findByCasamento(Casamento casamento);
}
