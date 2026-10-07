// package com.example.koina.atendimento.model.entity;

// import java.time.LocalDateTime;

// import org.hibernate.annotations.UuidGenerator;

// import com.example.koina.atendimento.model.enums.StatusAtendimento;
// import com.example.koina.usuario.model.entity.Usuario;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.PrePersist;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Table(name="db_atendimento")
// @Builder
// public class Atendimento {
	
// 	@Id
// 	@UuidGenerator
// 	@Column(name = "idAtendimento", nullable = false, unique=true)
// 	private String idAtendimento;

// 	@ManyToOne
// 	private Usuario acatante;

// 	@ManyToOne
// 	private Usuario voluntario;

// 	private String descricao;

// 	@Column(nullable = false)
// 	private LocalDateTime dataHoraSolicitacaoAtendimento;

// 	private LocalDateTime dataHoraInicioAtendimento;

// 	private LocalDateTime dataHoraFimAtendimento;

// 	@Enumerated(EnumType.STRING)
// 	@Column(nullable = false)
// 	private StatusAtendimento statusAtendimento;

// 	@PrePersist
// 	public void prePersist() {
// 		dataHoraSolicitacaoAtendimento = LocalDateTime.now();
// 		statusAtendimento = StatusAtendimento.PENDENTE;
// 	}
// }
