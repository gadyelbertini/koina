package com.example.koina.atendimento.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.koina.atendimento.model.entity.Atendimento;
import com.example.koina.atendimento.model.enums.StatusAtendimento;
// import com.example.koina.usuario.model.entity.Usuario;

public interface AtendimentoRepository extends JpaRepository<Atendimento, String>{

	Page<Atendimento> findByStatusAtendimento(StatusAtendimento statusAtendimento, Pageable pageable);

	Page<Atendimento> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
	// Page<Atendimento> findByAcatanteContainingIgnoringCase(Usuario acatante, Pageable pageable);
	// Page<Atendimento> findByVoluntarioContainingIgnoringCase(Usuario voluntario, Pageable pageable);

	Page<Atendimento> findByDescricaoContainingIgnoreCaseAndStatusAtendimento(String descricao, StatusAtendimento statusAtendimento, Pageable Pegeable);
	// Page<Atendimento> findByAcatanteContainingIgnoringCaseAndStatusAtendimento(Usuario acatante, StatusAtendimento statusAtendimento, Pageable Pegeable);
	// Page<Atendimento> findByVoluntarioContainingIgnoringCaseAndStatusAtendimento(Usuario voluntario, StatusAtendimento statusAtendimento, Pageable Pegeable);

	// Page<Atendimento> findByDescricaoContainingIgnoreCaseOrAcatanteContainingIgnoringCase(String descricao, Usuario acatante, Pageable pageable);
	// Page<Atendimento> findByDescricaoContainingIgnoreCaseOrVoluntarioContainingIgnoringCase(String descricao, Usuario voluntario, Pageable pageable);
	// Page<Atendimento> findByAcatanteContainingIgnoreCaseOrVoluntarioContainingIgnoringCase(Usuario acatante, Usuario voluntario, Pageable pageable);

	// Page<Atendimento> findByStatusAtendimentoAndDescricaoContainingIgnoreCaseOrStatusAtendimentoAndAcatanteContainingIgnoringCase(StatusAtendimento statusAtendimento1, String descricao, StatusAtendimento statusAtendimento2, Usuario acatante, Pageable pageable);
	// Page<Atendimento> findByStatusAtendimentoAndDescricaoContainingIgnoreCaseOrStatusAtendimentoAndVoluntarioContainingIgnoringCase(StatusAtendimento statusAtendimento1, String descricao, StatusAtendimento statusAtendimento2, Usuario voluntario, Pageable pageable);
	// Page<Atendimento> findByStatusAtendimentoAndAcatanteContainingIgnoreCaseOrStatusAtendimentoAndVoluntarioContainingIgnoringCase(StatusAtendimento statusAtendimento1, Usuario acatante, StatusAtendimento statusAtendimento2, Usuario voluntario, Pageable pageable);

	// Page<Atendimento> findByDescricaoContainingIgnoreCaseOrAcatanteContainingIgnoringCaseOrVoluntarioContainingIgnoringCase(String descricao, Usuario acatante, Usuario voluntario, Pageable pageable);

	// Page<Atendimento> findByStatusAtendimentoAndDescricaoContainingIgnoreCaseOrStatusAtendimentoAndAcatanteContainingIgnoringCaseOrStatusAtendimentoAndVoluntarioContainingIgnoringCase(StatusAtendimento statusAtendimento1, String descricao, StatusAtendimento statusAtendimento2, Usuario acatante, StatusAtendimento statusAtendimento3, Usuario voluntario, Pageable pageable);
}
