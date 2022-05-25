package com.apiavaliacoes.api.model;

import com.apiavaliacoes.domain.model.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AvaliacaoModel {

    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;

    public static AvaliacaoModel of(Avaliacao avaliacao) {
        return new AvaliacaoModel(
                avaliacao.getId(),
                avaliacao.getNota(),
                avaliacao.getDescricao(),
                avaliacao.getNomeAvaliador());
    }
}
