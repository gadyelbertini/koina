package com.example.koina.chat.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import com.example.koina.usuario.model.entity.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "db_mensagem")
@Builder
public class Mensagem {

	@Id
	@UuidGenerator
	@Column(name = "idMensagem", nullable = false, unique=true)
	private String idMensagem;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario idUsuario;

	@ManyToOne
	@JoinColumn(name = "id_chat")
	private Chat idChat;

	@Column(nullable = false)
	private String texto;

	@Column(nullable = false)
	private LocalDateTime dataEnvio;

	@PrePersist
	public void prePersist() {
		dataEnvio = LocalDateTime.now();
	}
}
