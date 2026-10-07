package com.example.koina.exception;

public class UsuarioEmailDuplicadoException extends RuntimeException{
	public UsuarioEmailDuplicadoException(String email) {
		super("Já existe um usuário cadastrado com esse e-mail: " + email);
	}
}
