package com.example.koina.chat.model.entity;

import org.hibernate.annotations.UuidGenerator;

import com.example.koina.atendimento.model.entity.Atendimento;
import com.example.koina.usuario.model.entity.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="db_chat")
@Builder
public class Chat {

	@Id
	@UuidGenerator
	@Column(name = "idChat", nullable = false, unique=true)
	private String idChat;

	@OneToOne
	@JoinColumn(name = "id_atendimento")
	private Atendimento atendimento;

	@ManyToOne
	@JoinColumn(name = "id_usuario_1")
	private Usuario usuario_1;

	@ManyToOne
	@JoinColumn(name = "id_usuario_2")
	private Usuario usuario_2;
}
