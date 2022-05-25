package com.apiavaliacoes.domain.repository;


import com.apiavaliacoes.domain.model.Avaliacao;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository {

    void save(Avaliacao avaliacao);
    Optional<Avaliacao> getOne(Long id);
    List<Avaliacao> getAll();
}
