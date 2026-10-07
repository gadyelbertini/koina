package com.example.koina.atendimento.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.koina.atendimento.dto.AtendimentoRequestDTO;
import com.example.koina.atendimento.dto.AtendimentoResponseDTO;
import com.example.koina.atendimento.model.enums.StatusAtendimento;
import com.example.koina.atendimento.service.AtendimentoServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/atendimentos")
@RequiredArgsConstructor
@Tag(
    name = "Atendimentos",
    description = "Operações de gerenciamento de atendimentos"
)
public class AtendimentoController {

	private final AtendimentoServiceImpl atendimentoServiceImpl;

	
    @Operation(
        summary = "Cria um atendimento",
        description = "Cadastra um novo atendimento."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Atendimento criado com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        )
    })
	@PostMapping
	public ResponseEntity<AtendimentoResponseDTO> criarAtendimento(@Valid @RequestBody AtendimentoRequestDTO dto) {
		AtendimentoResponseDTO atendimento = atendimentoServiceImpl.criar(dto);

		URI location = URI.create("/api/v1/atendimentos/" + atendimento.id());

		return ResponseEntity.created(location).body(atendimento);
	}

	@Operation(
        summary = "Busca atendimento por ID",
        description = "Retorna os dados de um atendimento pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Atendimento encontrado"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Atendimento não encontrado"
        )
    })
	@GetMapping("/{idAtendimento}")
	public ResponseEntity<AtendimentoResponseDTO> buscarPorId(
		@PathVariable("idAtendimento") String idAtendimento) {
		return ResponseEntity.ok(atendimentoServiceImpl.buscarPorId(idAtendimento));
	}

	@Operation(
		summary = "Lista atendimento",
		description = "Retorna uma lista paginada de atendimento. "
                    + "Permite filtrar por descrição parcial e status."
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "Atendimento encotrados"
		),
		@ApiResponse(
			responseCode = "400",
			description = "Parâmetros inválidos"
		)
	})
	@GetMapping
	public ResponseEntity<Page<AtendimentoResponseDTO>> listarAtendimento(
			@RequestParam(required = false) String descricao,
			@RequestParam(required = false) StatusAtendimento statusAtendimento,
			@PageableDefault(size = 10) Pageable pageable) {
		return ResponseEntity.ok(atendimentoServiceImpl.listarAtendimento(descricao, statusAtendimento, pageable));
	}

	@Operation(
        summary = "Aceitar um atendimento",
        description = "Atualização total dos dados de um atendimento."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Atendimento atualizado com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Atendimento não encontrado"
        ),

    })
	@PutMapping("/{idAtendimento}")
	public ResponseEntity<AtendimentoResponseDTO> aceitar(@PathVariable("idAtendimento") String idAtendimento,
			@Valid @RequestBody String idVoluntario) {
		
		return ResponseEntity.ok(atendimentoServiceImpl.aceitar(idAtendimento, idVoluntario));
	}

	@Operation(
        summary = "Remove um atendimento",
        description = "Exclui um atendimento pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Atendimento removido com sucesso"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Atendimento não encontrado"
        )
    })
    @DeleteMapping("/{idAtendimento}")
    public ResponseEntity<AtendimentoResponseDTO> deletar(
            @PathVariable("idAtendimento") String idAtendimento) {

        atendimentoServiceImpl.cancelar(idAtendimento);

        return ResponseEntity.ok(atendimentoServiceImpl.cancelar(idAtendimento));
    }
}
