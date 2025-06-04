package com.example.globaljava.configs;

import com.example.globaljava.model.Funcionario;
import com.example.globaljava.model.Usuario;
import com.example.globaljava.repositories.FuncionarioRepository;
import com.example.globaljava.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueListener {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final FuncionarioRepository funcionarioRepository;

    @RabbitListener(queues = "fila-usuario")
    public void usuarioReceiveMessage(String message) {
        try {
            Usuario usuario = objectMapper.readValue(message, Usuario.class);
            usuarioRepository.findByCpfUser(usuario.getCpfUser());
            usuarioRepository.save(usuario);
            System.out.println("✅ Usuário atualizado no banco de dados com sucesso!");
        } catch (Exception e) {
            System.err.println("❌ Erro ao processar a mensagem: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "fila-funcionario")
    public void profissionalReceiveMessage(String message) {
        try {
            Funcionario funcionario = objectMapper.readValue(message, Funcionario.class);

            funcionarioRepository.save(funcionario);

            System.out.println("✅ Funcionário atualizado no banco de dados com sucesso!");
        } catch (Exception e) {
            System.err.println("❌ Erro ao processar a mensagem: " + e.getMessage());
            e.printStackTrace();
        }
    }


}

