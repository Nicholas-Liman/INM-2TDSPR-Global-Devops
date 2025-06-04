package com.example.globaljava.repositories;

import com.example.globaljava.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByCpfUser(String cpf);
    List<Usuario> findUsuariosByEmailUser(String emailUser);
}
