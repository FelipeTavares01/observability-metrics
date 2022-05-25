package com.apiproduto.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoModel {

    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;

}
