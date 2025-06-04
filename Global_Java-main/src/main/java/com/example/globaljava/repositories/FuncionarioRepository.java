package com.example.globaljava.repositories;

import com.example.globaljava.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findFuncionarioByRegistroFuncionario(Long registroProfissional);
}
