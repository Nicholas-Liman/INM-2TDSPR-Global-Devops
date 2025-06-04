package com.example.globaljava.repositories;

import com.example.globaljava.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {

    Optional<Endereco> findByUsuario_CpfUser(String cpfUser);

    Optional<Endereco> findByFuncionario_RegistroFuncionario(Long funcionarioRegistroFuncionario);
}
