package com.example.globaljava.configs;

import com.example.globaljava.model.*;
import com.example.globaljava.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userSecurityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final AlertaRepository alertaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final HistoricoRepository historicoRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void setupDados() {
        Role roleAdmin = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN")));

        UserSecurity admin = new UserSecurity();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEnabled(true);
        admin.getRoles().add(roleAdmin);
        userSecurityRepository.save(admin);

        Historico historico = historicoRepository.save(Historico.builder()
                .quantidadeAlertas(0)
                .alertas(new ArrayList<>())
                .build());

        Usuario usuarioSalvo = Usuario.builder()
                .cpfUser("59997259092")
                .nomeUser("Teste inicial")
                .sobrenomeUser("Spring")
                .telefoneUser("1234567890")
                .dataNascimentoUser(LocalDate.of(1990, 1, 1))
                .emailUser("mariateste.fogolin@example.com")
                .historico(historico)
                .endereco(null)
                .build();

        usuarioSalvo = usuarioRepository.save(usuarioSalvo);
        historico.setUsuario(usuarioSalvo);
        historicoRepository.save(historico);

        Alerta alerta = new Alerta();
        alerta.setDataAlerta(new java.sql.Date(System.currentTimeMillis()));
        alerta.setHorario(java.time.LocalTime.now());
        alerta.setLatitude(-23.5505);
        alerta.setLongitude(-46.6333);
        alerta.setEvento("Teste de Alerta");
        alerta.setGravidade(3);
        alerta.setUsuario(usuarioSalvo);
        alerta.setHistorico(historico);
        alerta = alertaRepository.save(alerta);


        historico.getAlertas().add(alerta);
        historico.setQuantidadeAlertas(historico.getQuantidadeAlertas() + 1);
        historicoRepository.save(historico);

        Endereco endereco = Endereco.builder()
                .ruaEndereco("Rua Exemplo")
                .bairroEndereco("Centro")
                .cidadeEndereco("São Paulo")
                .estadoEndereco("SP")
                .cepEndereco("01001-000")
                .build();

        Funcionario funcionario = Funcionario.builder()
                .registroFuncionario(12345678L)
                .nomeFuncionario("João")
                .sobrenomeFuncionario("Silva")
                .telefoneFuncionario("11999999999")
                .tipoFuncionario("Técnico")
                .dataInscricaoFuncionario(Date.valueOf(LocalDate.now()))
                .emailFuncionario("joao.silva@example.com")
                .endereco(endereco)
                .build();

        endereco.setFuncionario(funcionario);
        funcionarioRepository.save(funcionario);

        System.out.println("Usuário de segurança 'admin' criado com sucesso.");
    }
}
