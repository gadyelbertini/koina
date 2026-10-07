package com.example.koina.atendimento.dto;

import java.time.LocalDateTime;

import com.example.koina.atendimento.model.enums.StatusAtendimento;
import com.example.koina.usuario.model.entity.Usuario;

public record AtendimentoResponseDTO(
	String id,
	Usuario acatante,
	Usuario voluntario,
	String descricao,
	LocalDateTime dataHoraSolicitacaoAtendimento,
	LocalDateTime dataHoraInicioAtendimento,
	LocalDateTime dataHoraFimAtendimento,
	StatusAtendimento statusAtendimento
) {}
