package com.example.koina.usuario.dto;

import com.example.koina.usuario.model.enums.TipoPapel;

public record UsuarioCadastradoResponseDTO(
	String id,
	String nome,
	String email,
	Boolean statusUsuario,
	TipoPapel tipoPapel
) {}
