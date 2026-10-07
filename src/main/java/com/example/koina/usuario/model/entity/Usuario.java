package com.example.koina.usuario.model.entity;

import org.hibernate.annotations.UuidGenerator;

import com.example.koina.usuario.model.enums.TipoPapel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name="db_usuario")
@Builder
public class Usuario {

	@Id
	@UuidGenerator
	@Column(name = "idUsuario", nullable = false, unique=true)
	private String idUsuario;

	private String apelido;

	private String nome;

	@Column(unique = true)
	private String email;

	private String senhaHash;

	private Boolean statusUsuario;

	@Enumerated(EnumType.STRING)
	private TipoPapel tipoPapel;

	@PrePersist
	public void prePersist() {
		statusUsuario = true;
	}
}
