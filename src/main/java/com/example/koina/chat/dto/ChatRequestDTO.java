package com.example.koina.chat.dto;

public record ChatRequestDTO(
	String idUsuarioEmissor,
	String idUsuarioReceptor,
	String idAtendimento
) {}
