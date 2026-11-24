
package com.example.koina.chat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.koina.chat.dto.MensagemRequestDTO;
import com.example.koina.chat.dto.MensagemResponseDTO;
import com.example.koina.chat.model.entity.Mensagem;
import com.example.koina.chat.repository.MensagemRepository;
import com.example.koina.usuario.model.entity.Usuario;
import com.example.koina.usuario.service.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class MensagemServiceImpl implements MensagemService{

	private final MensagemRepository mensagemRepository;
	private final UsuarioServiceImpl usuarioServiceImpl;
	private final ChatServiceImpl chatServiceImpl;

	public MensagemResponseDTO criar(MensagemRequestDTO dto){
		// Usuario usuario = usuarioServiceImpl.buscarPorIdUsuario(dto.idUsuario());
		// Chat chat = chatServiceImpl.buscarPorId(dto.idChat);
		return null;
	}

	public Page<MensagemResponseDTO> lista(String idChat, Pageable pageable){
		return null;
	}

	private MensagemResponseDTO tResponseDTO(Mensagem mensagem){
		return new MensagemResponseDTO(
			mensagem.getIdMensagem(),
			mensagem.getIdUsuario().getIdUsuario(),
			mensagem.getIdChat().getIdChat(),
			mensagem.getTexto(),
			mensagem.getDataEnvio()
		);
	}
}
