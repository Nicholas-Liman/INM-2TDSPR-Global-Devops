package com.example.globaljava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Endereco extends RepresentationModel<Endereco> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 25, message = "Rua deve ter no máximo 25 caracteres")
    private String ruaEndereco;

    @NotNull
    private int numeroEndereco;

    @Size(max = 20, message = "Complemento deve ter no máximo 20 caracteres")
    private String complementoEndereco;

    @NotNull
    @Size(max = 20, message = "Bairro deve ter no máximo 20 caracteres")
    private String bairroEndereco;

    @NotNull
    @Size(max = 30, message = "Cidade deve ter no máximo 30 caracteres")
    private String cidadeEndereco;

    @NotNull
    @Size(max = 9, message = "CEP deve ter no máximo 9 caracteres")
    private String cepEndereco;

    @NotNull
    @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
    private String estadoEndereco;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @JsonBackReference("funcionario-endereco")
    @OneToOne(mappedBy = "endereco")
    private Funcionario funcionario;

}