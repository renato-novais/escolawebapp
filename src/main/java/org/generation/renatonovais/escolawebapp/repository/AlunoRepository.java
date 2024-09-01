package org.generation.renatonovais.escolawebapp.repository;

import org.generation.renatonovais.escolawebapp.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
