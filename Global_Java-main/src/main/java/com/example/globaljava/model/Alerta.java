package com.example.globaljava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_inscricao_funcionario", nullable = false)
    private Date dataAlerta;

    @NotNull
    private LocalTime horario;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @NotNull
    private String evento;

    @NotNull
    private int gravidade;


    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "fk_user", referencedColumnName = "cpf_user", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "fk_historico", nullable = false)
    private Historico historico;
}
