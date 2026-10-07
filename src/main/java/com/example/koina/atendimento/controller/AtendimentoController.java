// package com.example.koina.atendimento.controller;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.koina.atendimento.dto.AtendimentoRequestDTO;
// import com.example.koina.atendimento.dto.AtendimentoResponseDTO;
// import com.example.koina.atendimento.service.AtendimentoService;

// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;


// @RestController
// @RequestMapping("/atendimentos")
// @RequiredArgsConstructor
// public class AtendimentoController {

// 	private final AtendimentoService atendimentoService;

// 	@PostMapping
// 	public AtendimentoResponseDTO abrirAtendimento(@RequestBody AtendimentoRequestDTO atendimento) {
// 		return atendimentoService.abrirAtendimento(atendimento);
// 	}
// }
