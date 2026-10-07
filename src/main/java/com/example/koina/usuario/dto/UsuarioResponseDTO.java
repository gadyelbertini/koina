package com.example.koina.usuario.dto;

import com.example.koina.usuario.model.enums.TipoPapel;

public record UsuarioResponseDTO(
	String id,
	String apelido,
	String nome,
	String email,
	Boolean statusUsuario,
	TipoPapel tipoPapel
) {}
