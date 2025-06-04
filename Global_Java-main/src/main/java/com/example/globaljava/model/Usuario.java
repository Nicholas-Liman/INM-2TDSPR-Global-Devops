package com.example.globaljava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Usuario")
public class Usuario {
    @Id
    @NotNull
    @CPF(message = "CPF deve conter 11 dígitos numéricos")
    @Column(name = "cpf_user", length = 11)
    private String cpfUser;

    @NotNull
    @Size(max = 30, message = "Nome deve ter no máximo 30 caracteres")
    @Column(name = "nome_user", length = 30, nullable = false)
    private String nomeUser;

    @NotNull
    @Size(max = 30, message = "Sobrenome deve ter no máximo 30 caracteres")
    @Column(name = "sobrenome_user", length = 30, nullable = false)
    private String sobrenomeUser;

    @NotNull
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter 10 a 11 dígitos")
    @Column(name = "telefone_user", nullable = false)
    private String telefoneUser;

    @NotNull
    @Column(name = "data_nascimento_user", nullable = false)
    private LocalDate dataNascimentoUser;


    @NotNull
    @Email(message = "Informe um e-mail válido")
    @Size(max = 50, message = "Email deve ter no máximo 50 caracteres")
    @Column(name = "email_user", length = 50, nullable = false)
    private String emailUser;



    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Alerta> alertas = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Historico historico;

    @JsonManagedReference
    @OneToOne(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
    private Endereco endereco;

    public void setCpfUser(String cpfUser) {
        if (cpfUser != null) {
            this.cpfUser = cpfUser.replaceAll("\\D+", "");
        } else {
            this.cpfUser = null;
        }
    }
}
