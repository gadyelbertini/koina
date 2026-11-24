package com.example.koina.chat.dto;

import com.example.koina.usuario.model.entity.Usuario;

public record ChatResponseDTO(
	String idChat,
	Usuario idUsuario1,
	Usuario idUsuario2
) {}
