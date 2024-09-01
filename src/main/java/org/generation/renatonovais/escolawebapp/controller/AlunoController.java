package org.generation.renatonovais.escolawebapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.generation.renatonovais.escolawebapp.dto.AlunoDTO;
import org.generation.renatonovais.escolawebapp.service.AlunoService;
import org.generation.renatonovais.escolawebapp.util.SwaggerStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Alunos", description = "CRUD para armazenar dados dos alunos de uma escola")
@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(summary = "Obter todos os alunos", description = "Retorna uma lista de todos os alunos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NO_CONTENT, description = SwaggerStatusHelper.NO_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.HTTP_ERROR_STRING)
    })
    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        List<AlunoDTO> alunos = alunoService.getAllAlunos();
        if (alunos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alunos);
    }

    @Operation(summary = "Obter aluno por ID", description = "Retorna os detalhes de um aluno específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_FOUND, description = SwaggerStatusHelper.HTTP_NOT_FOUND_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.HTTP_ERROR_STRING)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable Long id) {
        AlunoDTO aluno = alunoService.getAlunoById(id);
        if (aluno == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(aluno);
    }

    @Operation(summary = "Criar um novo aluno", description = "Cria um novo aluno com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_CREATED, description = SwaggerStatusHelper.CREATED_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.HTTP_ERROR_STRING)
    })
    @PostMapping
    public ResponseEntity<AlunoDTO> criarAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        AlunoDTO createdAluno = alunoService.saveAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAluno);
    }

    @Operation(summary = "Atualizar aluno existente", description = "Atualiza as informações de um aluno existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_OK, description = SwaggerStatusHelper.OK_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_FOUND, description = SwaggerStatusHelper.HTTP_NOT_FOUND_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.HTTP_ERROR_STRING)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable Long id, @Valid @RequestBody AlunoDTO alunoDTO) {
        AlunoDTO existingAluno = alunoService.getAlunoById(id);
        if (existingAluno == null) {
            return ResponseEntity.status(404).build();
        }
        AlunoDTO updatedAluno = alunoService.updateAluno(id, alunoDTO);
        return ResponseEntity.ok(updatedAluno);
    }

    @Operation(summary = "Excluir aluno", description = "Remove um aluno existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NO_CONTENT, description = SwaggerStatusHelper.NO_CONTENT_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_NOT_FOUND, description = SwaggerStatusHelper.HTTP_NOT_FOUND_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_BAD_REQUEST, description = SwaggerStatusHelper.BAD_REQUEST_STRING),
            @ApiResponse(responseCode = SwaggerStatusHelper.HTTP_ERROR, description = SwaggerStatusHelper.HTTP_ERROR_STRING)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        if (alunoService.getAlunoById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        alunoService.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }
}