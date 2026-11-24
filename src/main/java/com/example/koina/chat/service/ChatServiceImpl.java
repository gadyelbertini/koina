package com.example.koina.chat.service;

import com.example.koina.chat.repository.ChatRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.koina.atendimento.model.entity.Atendimento;
import com.example.koina.atendimento.service.AtendimentoServiceImpl;
import com.example.koina.chat.dto.ChatRequestDTO;
import com.example.koina.chat.dto.ChatResponseAtendimentoDTO;
import com.example.koina.chat.dto.ChatResponseDTO;
import com.example.koina.chat.model.entity.Chat;
import com.example.koina.chat.model.entity.Mensagem;
import com.example.koina.usuario.model.entity.Usuario;
import com.example.koina.usuario.service.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

	private final ChatRepository chatRepository;
	private final UsuarioServiceImpl usuarioServiceImpl;
	private final AtendimentoServiceImpl atendimentoServiceImpl;


	public Page<ChatResponseDTO> listarChat(String idUsuario, Pageable pageable){

		Specification<Chat> spec = Specification.unrestricted();

		if (idUsuario != null && !idUsuario.isBlank()) {
			spec = spec.and((root, query, cb) ->
				cb.equal(root.join("usuario_1").get("idUsuario"), idUsuario)
			);

			spec = spec.and((root, query, cb) ->
				cb.equal(root.join("usuario_2").get("idUsuario"), idUsuario)
			);

			return chatRepository.findAll(spec, pageable).map(this::toResponseDTO);
		}

		return null;
	}

	public ChatResponseAtendimentoDTO criar(ChatRequestDTO dto){
		Usuario usuarioEmissor = usuarioServiceImpl.buscarPorIdUsuario(dto.idUsuarioEmissor());
		Usuario UsuarioReceptor = usuarioServiceImpl.buscarPorIdUsuario(dto.idUsuarioReceptor());

		if (dto.idAtendimento() != null && dto.idAtendimento().isBlank()) {
			Atendimento atendimento = atendimentoServiceImpl.buscarPorIdAtendimento(dto.idAtendimento());

			Chat chat = Chat.builder()
				.usuario_1(usuarioEmissor)
				.usuario_2(UsuarioReceptor)
				.atendimento(atendimento)
				.build();
			
			return toAtendimentoResponseDTO(chatRepository.save(chat));
		} else {
			Chat chat = Chat.builder()
				.usuario_1(usuarioEmissor)
				.usuario_2(UsuarioReceptor)
				.build();
			
			return toAtendimentoResponseDTO(chatRepository.save(chat));
		}
	}

	public ChatResponseDTO enviarMensagem(String idChat, String idUsuario){
		return null;
	}

	public Page<Mensagem> listarMensagens(String idChat){
		return null;
	}

	private ChatResponseDTO toResponseDTO(Chat chat){
		return new ChatResponseDTO(
			chat.getIdChat(),
			chat.getUsuario_1(),
			chat.getUsuario_2()
		);
	}

	private ChatResponseAtendimentoDTO toAtendimentoResponseDTO(Chat chat){
		return new ChatResponseAtendimentoDTO(
			chat.getIdChat(),
			chat.getUsuario_1(),
			chat.getUsuario_2(),
			chat.getAtendimento()
		);
	}
}
