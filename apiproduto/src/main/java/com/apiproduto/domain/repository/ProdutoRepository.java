package com.apiproduto.domain.repository;

import com.apiproduto.domain.model.Produto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProdutoRepository {

    void save(Produto produto);
    Optional<Produto> getOne(Long id);
    List<Produto> getAll();
}
