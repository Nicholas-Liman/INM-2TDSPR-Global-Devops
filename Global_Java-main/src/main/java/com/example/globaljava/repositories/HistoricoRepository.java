package com.example.globaljava.repositories;

import com.example.globaljava.model.Historico;
import com.example.globaljava.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoricoRepository extends JpaRepository<Historico, String> {

    Optional<Historico> findByUsuario_CpfUser(String cpfUser);


}
