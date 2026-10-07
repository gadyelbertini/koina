package com.example.koina.exception;

public class AtendimentoNaoEncontradoException extends RuntimeException {
	public AtendimentoNaoEncontradoException(String id) {
		super("Atendimento não encontrado: " + id);
	}
}
