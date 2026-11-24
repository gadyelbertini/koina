package com.example.koina.chat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.koina.chat.dto.ChatRequestDTO;
import com.example.koina.chat.dto.ChatResponseAtendimentoDTO;
import com.example.koina.chat.dto.ChatResponseDTO;
import com.example.koina.chat.model.entity.Mensagem;

public interface ChatService {
	Page<ChatResponseDTO> listarChat(String idUsuario, Pageable pageable);

	ChatResponseAtendimentoDTO criar(ChatRequestDTO dto);

	ChatResponseDTO enviarMensagem(String idChat, String idUsuario);

	Page<Mensagem> listarMensagens(String idChat);
}
