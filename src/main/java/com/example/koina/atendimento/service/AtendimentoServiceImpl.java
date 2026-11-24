package com.example.koina.atendimento.service;

import com.example.koina.atendimento.repository.AtendimentoRepository;
import com.example.koina.exception.AtendimentoNaoEncontradoException;
import com.example.koina.exception.UsuarioSemPermissaoException;
import com.example.koina.usuario.model.entity.Usuario;
import com.example.koina.usuario.model.enums.TipoPapel;
import com.example.koina.usuario.service.UsuarioServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	public Page<AtendimentoResponseDTO> listarAtendimento(String texto, List<String> campos, StatusAtendimento statusAtendimento, Pageable pageable){


		Specification<Atendimento> spec = Specification.unrestricted();

		if (statusAtendimento != null) {
			spec = spec.and((root, query, cb) ->
				cb.equal(root.get("statusAtendimento"), statusAtendimento)
			);
		}

		if (texto != null && !texto.isBlank()) {
			String filter = "%" + texto.trim().toLowerCase() + "%";

			Specification<Atendimento> textoSpec = null;

			if (campos.contains("descricao")) {
				Specification<Atendimento> descricaoSpec = (root, query, cb) -> 
					cb.like(cb.lower(root.get("descricao")), filter);

				textoSpec = textoSpec == null?descricaoSpec:textoSpec.or(descricaoSpec);
			}

			if (campos.contains("acatante")) {
				Specification<Atendimento> acatanteSpec = (root, query, cb) ->
				cb.like(cb.lower(root.join("acatante").get("nome")), filter);

				textoSpec = textoSpec == null?acatanteSpec:textoSpec.or(acatanteSpec);
			}

			if (campos.contains("voluntario")) {
				Specification<Atendimento> voluntarioSpec = (root, query, cb) ->
				cb.like(cb.lower(root.join("voluntario").get("nome")), filter);

				textoSpec = textoSpec == null?voluntarioSpec:textoSpec.or(voluntarioSpec);
			}

			if (textoSpec != null) {
				spec = spec.and(textoSpec);
			}
		}

		return atendimentoRepository.findAll(spec, pageable).map(this::toResponseDTO);
	}

	@Override
	public AtendimentoResponseDTO buscarPorId(String id){
		return atendimentoRepository.findById(id).map(this::toResponseDTO).orElseThrow(() -> new AtendimentoNaoEncontradoException(id));
	};

	@Override
	public Atendimento buscarPorIdAtendimento(String id){
		return atendimentoRepository.findById(id).orElseThrow(() -> new AtendimentoNaoEncontradoException(id));
	}

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
