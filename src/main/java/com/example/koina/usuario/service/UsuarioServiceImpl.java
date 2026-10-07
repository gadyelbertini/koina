package com.example.koina.usuario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.koina.exception.UsuarioEmailDuplicadoException;
import com.example.koina.exception.UsuarioNaoEncontradoException;
import com.example.koina.usuario.dto.UsuarioAnonimoRequestDTO;
import com.example.koina.usuario.dto.UsuarioAnonimoResponseDTO;
import com.example.koina.usuario.dto.UsuarioCadastradoRequestDTO;
import com.example.koina.usuario.dto.UsuarioCadastradoResponseDTO;
import com.example.koina.usuario.dto.UsuarioResponseDTO;
import com.example.koina.usuario.model.entity.Usuario;
import com.example.koina.usuario.model.enums.TipoPapel;
import com.example.koina.usuario.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository usuarioRepository;

	@Override
	public Page<UsuarioResponseDTO> listarUsuarios(String nome, TipoPapel tipoPapel, Pageable pageable){
		boolean temNome = nome != null && !nome.isBlank();
		Page<Usuario> usuario;

		if (temNome && tipoPapel != null) {
			usuario = usuarioRepository.findByTipoPapelAndNomeContainingIgnoreCaseOrTipoPapelAndApelidoContainingIgnoreCase(tipoPapel, nome.trim(), tipoPapel, nome.trim(), pageable);
		} else if (temNome) {
			usuario = usuarioRepository.findByNomeContainingIgnoreCaseOrApelidoContainingIgnoreCase(nome.trim(), nome.trim(), pageable);
		} else if (tipoPapel != null) {
			usuario = usuarioRepository.findByTipoPapel(tipoPapel, pageable);
		} else {
			usuario = usuarioRepository.findAll(pageable);
		}

		return usuario.map(this::toResponseDTO);
	};

	@Override
	public UsuarioResponseDTO buscarPorId(String id){
		return usuarioRepository.findById(id).map(this::toResponseDTO).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

	@Override
	public Usuario buscarPorIdUsuario(String id){
		return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

	@Override
	public UsuarioAnonimoResponseDTO criarUsuarioAnonimo(UsuarioAnonimoRequestDTO dto){
		Usuario usuario = Usuario.builder().apelido(dto.apelido()).tipoPapel(TipoPapel.ANONIOM).build();
		
		return toAnonimoResponse(usuarioRepository.save(usuario));
	}

	@Override
	public UsuarioCadastradoResponseDTO criarUsuarioCadastrado(UsuarioCadastradoRequestDTO dto){
		Usuario usuario = Usuario.builder()
			.email(dto.email())
			.senhaHash(dto.senha())
			.nome(dto.nome())
			.tipoPapel(TipoPapel.COMUM)
			.build();

		return toCadastradoResponse(usuarioRepository.save(usuario));
	}

	@Override 
	public UsuarioCadastradoResponseDTO atualizarUsuarioCadastrado(String id, UsuarioCadastradoRequestDTO dto){
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));

		if (usuarioRepository.existsByEmail(dto.email())) {
			throw new UsuarioEmailDuplicadoException(dto.email());
		}

		usuario.setNome(dto.nome());
		usuario.setEmail(dto.email());
		usuario.setSenhaHash(dto.senha());

		return toCadastradoResponse(usuarioRepository.save(usuario));
	}

	@Override 
	public UsuarioResponseDTO deletar(String id){
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
		
		usuario.setStatusUsuario(false);

		return toResponseDTO(usuarioRepository.save(usuario));
	}

	private UsuarioResponseDTO toResponseDTO(Usuario usuario){
		return new UsuarioResponseDTO(
			usuario.getIdUsuario(),
			usuario.getApelido(),
			usuario.getNome(),
			usuario.getEmail(),
			usuario.getStatusUsuario(),
			usuario.getTipoPapel()
		);
	}

	private UsuarioAnonimoResponseDTO toAnonimoResponse(Usuario usuario){
		return new UsuarioAnonimoResponseDTO(
			usuario.getIdUsuario(),
			usuario.getApelido(),
			usuario.getStatusUsuario(),
			usuario.getTipoPapel()
		);
	}

	private UsuarioCadastradoResponseDTO toCadastradoResponse(Usuario usuario){
		return new UsuarioCadastradoResponseDTO(
			usuario.getIdUsuario(),
			usuario.getNome(),
			usuario.getEmail(),
			usuario.getStatusUsuario(),
			usuario.getTipoPapel()
		);
	}
}