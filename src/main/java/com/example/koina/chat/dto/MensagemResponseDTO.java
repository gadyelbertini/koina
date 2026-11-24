package com.example.koina.chat.dto;

import java.time.LocalDateTime;

public record MensagemResponseDTO(
	String idMensagem,
	String idUsuario,
	String idChat,
	String texto,
	LocalDateTime dataEnvio
) {}