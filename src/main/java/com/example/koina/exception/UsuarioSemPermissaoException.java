package com.example.koina.exception;

public class UsuarioSemPermissaoException extends RuntimeException{
	public UsuarioSemPermissaoException(String id) {
		super("Usuário não tem permissão de acesso: " + id);
	}
}
