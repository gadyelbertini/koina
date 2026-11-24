package com.example.koina.chat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.koina.chat.dto.MensagemRequestDTO;
import com.example.koina.chat.dto.MensagemResponseDTO;

public interface MensagemService {
	MensagemResponseDTO criar(MensagemRequestDTO dto);

	Page<MensagemResponseDTO> lista(String idChat, Pageable pageable);
}
