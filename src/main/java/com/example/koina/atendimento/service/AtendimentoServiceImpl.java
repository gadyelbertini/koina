package com.example.koina.atendimento.service;

import com.example.koina.atendimento.repository.AtendimentoRepository;
import com.example.koina.exception.AtendimentoNaoEncontradoException;
import com.example.koina.exception.UsuarioSemPermissaoException;
import com.example.koina.usuario.model.entity.Usuario;
import com.example.koina.usuario.model.enums.TipoPapel;
import com.example.koina.usuario.service.UsuarioServiceImpl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.koina.atendimento.dto.AtendimentoRequestDTO;
import com.example.koina.atendimento.dto.AtendimentoResponseDTO;
import com.example.koina.atendimento.model.entity.Atendimento;
import com.example.koina.atendimento.model.enums.StatusAtendimento;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class AtendimentoServiceImpl implements AtendimentoService{

	private final UsuarioServiceImpl usuarioServiceImpl;
	private final AtendimentoRepository atendimentoRepository;

	@Override
	public Page<AtendimentoResponseDTO> listarAtendimento(String descricao, StatusAtendimento statusAtendimento, Pageable pageable){
		boolean temDescricao = descricao != null && !descricao.isBlank();

		Page<Atendimento> atendimento;

		if (temDescricao && statusAtendimento != null) {
			atendimento = atendimentoRepository.findByDescricaoContainingIgnoreCaseAndStatusAtendimento(descricao.trim(), statusAtendimento, pageable);
		} else if (temDescricao) {
			atendimento = atendimentoRepository.findByDescricaoContainingIgnoreCase(descricao.trim(), pageable);
		} else if (statusAtendimento != null) {
			atendimento = atendimentoRepository.findByStatusAtendimento(statusAtendimento, pageable);
		} else {
			atendimento = atendimentoRepository.findAll(pageable);
		}

		return atendimento.map(this::toResponseDTO);
	}

	@Override
	public AtendimentoResponseDTO buscarPorId(String id){
		return atendimentoRepository.findById(id).map(this::toResponseDTO).orElseThrow(() -> new AtendimentoNaoEncontradoException(id));
	};

	@Override
	public AtendimentoResponseDTO criar(AtendimentoRequestDTO dto){
		Usuario usuario = usuarioServiceImpl.buscarPorIdUsuario(dto.idAcatante());

		if (usuario.getStatusUsuario() == false) {
			throw new UsuarioSemPermissaoException(usuario.getIdUsuario());
		}

		Atendimento atendimento = Atendimento.builder()
			.descricao(dto.descricao())
			.acatante(usuario)
			.build();
		
			return toResponseDTO(atendimentoRepository.save(atendimento));
	};

	@Override
	public AtendimentoResponseDTO aceitar(String idAtendimento, String idVoluntario){
		Atendimento atendimento = atendimentoRepository.findById(idAtendimento).orElseThrow(() -> new AtendimentoNaoEncontradoException(idAtendimento));
		Usuario voluntario = usuarioServiceImpl.buscarPorIdUsuario(idVoluntario);

		if (voluntario.getTipoPapel() != TipoPapel.VOLUNTARIO) {
			throw new UsuarioSemPermissaoException(voluntario.getIdUsuario());
		}

		atendimento.setVoluntario(voluntario);
		atendimento.setStatusAtendimento(StatusAtendimento.EM_ATENDIMENTO);
		atendimento.setDataHoraInicioAtendimento(LocalDateTime.now());

		return toResponseDTO(atendimentoRepository.save(atendimento));
	};

	@Override
	public AtendimentoResponseDTO cancelar(String id){
		Atendimento atendimento = atendimentoRepository.findById(id).orElseThrow(() -> new AtendimentoNaoEncontradoException(id));
		atendimento.setStatusAtendimento(StatusAtendimento.CANCELADO);
		return toResponseDTO(atendimentoRepository.save(atendimento));
	};

	private AtendimentoResponseDTO toResponseDTO(Atendimento atendimento){
		return new AtendimentoResponseDTO(
			atendimento.getIdAtendimento(),
			atendimento.getAcatante(),
			atendimento.getVoluntario(),
			atendimento.getDescricao(),
			atendimento.getDataHoraSolicitacaoAtendimento(),
			atendimento.getDataHoraInicioAtendimento(),
			atendimento.getDataHoraFimAtendimento(),
			atendimento.getStatusAtendimento()
		);
	}
}
