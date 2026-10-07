package com.example.koina.usuario.dto;

import com.example.koina.usuario.model.enums.TipoPapel;

public record UsuarioAnonimoResponseDTO(
	String id,
	String apelido,
	Boolean statusUsuario,
	TipoPapel tipoPapel
) {}
