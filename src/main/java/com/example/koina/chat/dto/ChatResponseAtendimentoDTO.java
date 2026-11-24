package com.example.koina.chat.dto;

import com.example.koina.atendimento.model.entity.Atendimento;
import com.example.koina.usuario.model.entity.Usuario;

public record ChatResponseAtendimentoDTO(
	String idChat,
	Usuario idUsuario1,
	Usuario idUsuario2,
	Atendimento idAtendimento
) {}
