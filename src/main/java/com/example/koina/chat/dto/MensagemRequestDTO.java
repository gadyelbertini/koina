package com.example.koina.chat.dto;

public record MensagemRequestDTO(
	String idUsuario,
	String idChat,
	String texto
) {}
