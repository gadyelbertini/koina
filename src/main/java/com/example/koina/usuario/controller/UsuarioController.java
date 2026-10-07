package com.example.koina.usuario.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.koina.usuario.dto.UsuarioAnonimoRequestDTO;
import com.example.koina.usuario.dto.UsuarioAnonimoResponseDTO;
import com.example.koina.usuario.dto.UsuarioCadastradoRequestDTO;
import com.example.koina.usuario.dto.UsuarioCadastradoResponseDTO;
import com.example.koina.usuario.dto.UsuarioResponseDTO;
import com.example.koina.usuario.model.enums.TipoPapel;
import com.example.koina.usuario.service.UsuarioServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@Tag(
    name = "Usuarios",
    description = "Operações de gerenciamento de usuários"
)
public class UsuarioController {

	private final UsuarioServiceImpl usuarioServiceImpl;

    @Operation(
        summary = "Cria um usuáro anonimo",
        description = "Cadastra um novo usuário anonimo."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Usuário anonimo criado com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        )
    })
	@PostMapping("/anonimos")
	public ResponseEntity<UsuarioAnonimoResponseDTO> criarUsuarioAnonimo(@Valid @RequestBody UsuarioAnonimoRequestDTO dto) {
		UsuarioAnonimoResponseDTO usuarioAnonimo = usuarioServiceImpl.criarUsuarioAnonimo(dto);

		URI location = URI.create("/api/v1/usuarios/anonimos/" + usuarioAnonimo.id());

		return ResponseEntity.created(location).body(usuarioAnonimo);
	}

	@Operation(
        summary = "Cria um usuáro cadastrado",
        description = "Cadastra um novo usuário cadastrado."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Usuário cadastrado criado com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        )
    })
	@PostMapping("/cadastrados")
	public ResponseEntity<UsuarioCadastradoResponseDTO> criarUsuarioCadastrado(@RequestBody UsuarioCadastradoRequestDTO dto) {
		UsuarioCadastradoResponseDTO usuarioCadastrado = usuarioServiceImpl.criarUsuarioCadastrado(dto);

		URI location = URI.create("/api/v1/usuarios/cadastrados/" + usuarioCadastrado.id());

		return ResponseEntity.created(location).body(usuarioCadastrado);
	}

    @Operation(
        summary = "Busca usuário por ID",
        description = "Retorna os dados de um usuário pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Usuário encontrado"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
        )
    })
	@GetMapping("/{idUsuario}")
	public ResponseEntity<UsuarioResponseDTO> buscarPorId(
		@PathVariable("idUsuario") String idUsuario) {
		return ResponseEntity.ok(usuarioServiceImpl.buscarPorId(idUsuario));
	}

	@Operation(
		summary = "Lista usuários",
		description = "Retorna uma lista paginada de usuários. "
                    + "Permite filtrar por nome parcial e papel."
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "Usuários encotrados"
		),
		@ApiResponse(
			responseCode = "400",
			description = "Parâmetros inválidos"
		)
	})
	@GetMapping
	public ResponseEntity<Page<UsuarioResponseDTO>> listarUsuarios(
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) TipoPapel tipoPapel,
			@PageableDefault(size = 10) Pageable pageable) {
		return ResponseEntity.ok(usuarioServiceImpl.listarUsuarios(nome, tipoPapel, pageable));
	}
	
		
	@Operation(
        summary = "Atualiza um usuário",
        description = "Atualização total dos dados de um usuário."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Usuário atualizado com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Usuário com e-mail já cadastrado"
        )
    })
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioCadastradoResponseDTO> atualizar(
            @PathVariable("idUsuario") String idUsuario,
            @Valid @RequestBody UsuarioCadastradoRequestDTO dto) {

        return ResponseEntity.ok(usuarioServiceImpl.atualizarUsuarioCadastrado(idUsuario, dto));
    }

	@Operation(
        summary = "Remove um usuário",
        description = "Exclui um usuário pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Usuário removido com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
        )
    })
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> deletar(
            @PathVariable("idUsuario") String idUsuario) {

        usuarioServiceImpl.deletar(idUsuario);

        return ResponseEntity.ok(usuarioServiceImpl.deletar(idUsuario));
    }
}
