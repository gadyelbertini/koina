package com.example.koina.usuario.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.koina.usuario.model.entity.Usuario;
import com.example.koina.usuario.model.enums.TipoPapel;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Page<Usuario> findByTipoPapel(TipoPapel tipoPapel, Pageable pageable);

	Page<Usuario> findByNomeContainingIgnoreCaseOrApelidoContainingIgnoreCase(String nome, String apelido, Pageable pageable);

	Page<Usuario> findByTipoPapelAndNomeContainingIgnoreCaseOrTipoPapelAndApelidoContainingIgnoreCase(TipoPapel tipoPapel1, String nome, TipoPapel tipoPapel2, String apelido, Pageable pageable);

	boolean existsByEmail(String email);
}
