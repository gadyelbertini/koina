// package com.example.koina.atendimento.service;

// import org.springframework.stereotype.Service;

// import com.example.koina.atendimento.dto.AtendimentoRequestDTO;
// import com.example.koina.atendimento.dto.AtendimentoResponseDTO;
// import com.example.koina.atendimento.model.entity.Atendimento;
// import com.example.koina.atendimento.repository.AtendimentoRepository;
// import com.example.koina.usuario.model.entity.Usuario;
// import com.example.koina.usuario.repository.UsuarioRepository;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class AtendimentoService {
	
// 	private final AtendimentoRepository atendimentoRepository;
// 	private final UsuarioRepository usuarioRepository;

// 	public class UsuarioNaoEncontradoException extends RuntimeException {
// 		public UsuarioNaoEncontradoException(String id) {
// 			super("Usuário não encontrado: " + id);
// 		}
// 	}

// 	public AtendimentoResponseDTO abrirAtendimento(AtendimentoRequestDTO dto){
// 		Usuario usuario = usuarioRepository.findById(dto.idAcatante()).orElseThrow(() -> new UsuarioNaoEncontradoException(dto.idAcatante()));

// 		if (usuario.getStatusUsuario() == true) {
// 			Atendimento atendimento = new Atendimento();
// 			atendimento.setAcatante(usuario);
// 			atendimento.setDescricao(dto.descricao());

// 			atendimentoRepository.save(atendimento);

// 			return new AtendimentoResponseDTO(
// 				atendimento.getIdAtendimento(),
// 				atendimento.getAcatante(),
// 				atendimento.getVoluntario(),
// 				atendimento.getDescricao(),
// 				atendimento.getDataHoraSolicitacaoAtendimento(),
// 				atendimento.getStatusAtendimento()
// 			);
// 		} else {
// 			throw new IllegalStateException("Usuário desativado.");
// 		}
// 	}

// }
