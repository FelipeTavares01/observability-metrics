package com.apiavaliacoes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Avaliacao {

    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;
    private Long produtoId;
}
