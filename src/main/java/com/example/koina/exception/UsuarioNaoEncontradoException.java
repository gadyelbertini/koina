package com.example.koina.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
	public UsuarioNaoEncontradoException(String id) {
		super("Usuário não encontrado: " + id);
	}
}
