package com.example.koina.atendimento.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.example.koina.atendimento.dto.AtendimentoRequestDTO;
import com.example.koina.atendimento.dto.AtendimentoResponseDTO;
import com.example.koina.atendimento.model.entity.Atendimento;
import com.example.koina.atendimento.model.enums.StatusAtendimento;

public interface AtendimentoService {

	Page<AtendimentoResponseDTO> listarAtendimento(String texto, List<String> campos, StatusAtendimento statusAtendimento, Pageable pageable);

	AtendimentoResponseDTO buscarPorId(String id);

	Atendimento buscarPorIdAtendimento(String id);

	AtendimentoResponseDTO criar(AtendimentoRequestDTO dto);

	AtendimentoResponseDTO aceitar(String idAtendimento, String idVoluntario);

	AtendimentoResponseDTO cancelar(String id);
}
