package org.generation.renatonovais.escolawebapp.service;

import org.generation.renatonovais.escolawebapp.dto.AlunoDTO;
import org.generation.renatonovais.escolawebapp.model.Aluno;
import org.generation.renatonovais.escolawebapp.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoDTO getAlunoById(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.map(this::toDTO).orElse(null);
    }

    public List<AlunoDTO> getAllAlunos() {
        return alunoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AlunoDTO saveAluno(AlunoDTO alunoDTO) {
        Aluno aluno = toEntity(alunoDTO);
        aluno = alunoRepository.save(aluno);
        return toDTO(aluno);
    }

    public AlunoDTO updateAluno(Long id, AlunoDTO alunoDTO) {
        Aluno aluno = toEntity(alunoDTO);
        aluno.setId(id);
        aluno = alunoRepository.save(aluno);
        return toDTO(aluno);
    }

    public void deleteAluno(Long id) {
        alunoRepository.deleteById(id);
    }

    private AlunoDTO toDTO(Aluno aluno) {
        if (aluno == null) {
            return null;
        }

        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setIdade(aluno.getIdade());
        dto.setNotaPrimeiroSemestre(aluno.getNotaPrimeiroSemestre());
        dto.setNotaSegundoSemestre(aluno.getNotaSegundoSemestre());
        dto.setNomeProfessor(aluno.getNomeProfessor());
        dto.setNumeroSala(aluno.getNumeroSala());

        return dto;
    }

    private Aluno toEntity(AlunoDTO dto) {
        if (dto == null) {
            return null;
        }

        Aluno aluno = new Aluno();
        aluno.setId(dto.getId());
        aluno.setNome(dto.getNome());
        aluno.setIdade(dto.getIdade());
        aluno.setNotaPrimeiroSemestre(dto.getNotaPrimeiroSemestre());
        aluno.setNotaSegundoSemestre(dto.getNotaSegundoSemestre());
        aluno.setNomeProfessor(dto.getNomeProfessor());
        aluno.setNumeroSala(dto.getNumeroSala());

        return aluno;
    }
}
