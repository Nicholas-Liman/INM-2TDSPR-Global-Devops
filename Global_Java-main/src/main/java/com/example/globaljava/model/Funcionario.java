package com.example.globaljava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Funcionario")
public class Funcionario {
    
    @NotNull
    @Size(max = 20, message = "Nome deve ter no máximo 20 caracteres")
    @Column(name = "nome_funcionario", length = 20, nullable = false)
    private String nomeFuncionario;

    @NotNull
    @Size(max = 30, message = "Sobrenome deve ter no máximo 30 caracteres")
    @Column(name = "sobrenome_funcionario", length = 30, nullable = false)
    private String sobrenomeFuncionario;

    @NotNull
    @Column(name = "telefone_funcionario", nullable = false)
    private String telefoneFuncionario;

    @NotNull
    @Size(max = 20, message = "Tipo de Funcionario deve ter no máximo 20 caracteres")
    @Column(name = "tipo_funcionario", length = 20, nullable = false)
    private String tipoFuncionario;

    @NotNull
    @Column(name = "data_inscricao_funcionario", nullable = false)
    private Date dataInscricaoFuncionario;

    @Id
    @NotNull
    @Digits(integer = 8, fraction = 0, message = "O registro deve conter no máximo 8 dígitos")
    @Column(name = "registro_funcionario", length = 8, nullable = false)
    private Long registroFuncionario;

    @NotNull
    @Email(message = "Informe um e-mail válido")
    @Column(name = "email_funcionario", length = 50, nullable = false)
    private String emailFuncionario;


    @JsonManagedReference("funcionario-endereco")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

 }
