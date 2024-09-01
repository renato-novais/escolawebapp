package org.generation.renatonovais.escolawebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome não pode ter mais de 100 caracteres")
    private String nome;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 0, message = "Idade deve ser maior ou igual a 0")
    @Max(value = 120, message = "Idade deve ser menor ou igual a 120")
    private Integer idade;

    @NotNull(message = "Nota do primeiro semestre é obrigatória")
    @Min(value = 0, message = "Nota do primeiro semestre deve ser maior ou igual a 0")
    @Max(value = 10, message = "Nota do primeiro semestre deve ser menor ou igual a 10")
    private Double notaPrimeiroSemestre;

    @NotNull(message = "Nota do segundo semestre é obrigatória")
    @Min(value = 0, message = "Nota do segundo semestre deve ser maior ou igual a 0")
    @Max(value = 10, message = "Nota do segundo semestre deve ser menor ou igual a 10")
    private Double notaSegundoSemestre;

    @NotBlank(message = "Nome do professor é obrigatório")
    @Size(max = 100, message = "Nome do professor não pode ter mais de 100 caracteres")
    private String nomeProfessor;

    @NotNull(message = "Número da sala é obrigatório")
    @Min(value = 1, message = "Número da sala deve ser maior ou igual a 1")
    @Max(value = 9999, message = "Número da sala deve ser menor ou igual a 9999")
    private Integer numeroSala;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getNotaPrimeiroSemestre() {
        return notaPrimeiroSemestre;
    }

    public void setNotaPrimeiroSemestre(Double notaPrimeiroSemestre) {
        this.notaPrimeiroSemestre = notaPrimeiroSemestre;
    }

    public Double getNotaSegundoSemestre() {
        return notaSegundoSemestre;
    }

    public void setNotaSegundoSemestre(Double notaSegundoSemestre) {
        this.notaSegundoSemestre = notaSegundoSemestre;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }
}
