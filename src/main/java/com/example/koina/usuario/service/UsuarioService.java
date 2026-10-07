package com.example.koina.usuario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.koina.usuario.dto.UsuarioAnonimoRequestDTO;
import com.example.koina.usuario.dto.UsuarioAnonimoResponseDTO;
import com.example.koina.usuario.dto.UsuarioCadastradoRequestDTO;
import com.example.koina.usuario.dto.UsuarioCadastradoResponseDTO;
import com.example.koina.usuario.dto.UsuarioResponseDTO;
import com.example.koina.usuario.model.enums.TipoPapel;

public interface UsuarioService {
	Page<UsuarioResponseDTO> listarUsuarios(String nome, TipoPapel tipoPapel, Pageable pageable);

	UsuarioResponseDTO buscarPorId(String id);

	UsuarioAnonimoResponseDTO criarUsuarioAnonimo(UsuarioAnonimoRequestDTO dto);

	UsuarioCadastradoResponseDTO criarUsuarioCadastrado(UsuarioCadastradoRequestDTO dto);

	UsuarioCadastradoResponseDTO atualizarUsuarioCadastrado(String id, UsuarioCadastradoRequestDTO dto);

	UsuarioResponseDTO deletar(String id);
}
