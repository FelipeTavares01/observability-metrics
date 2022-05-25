package com.apiproduto.client.avaliacoes;

import com.apiproduto.client.model.AvaliacaoModel;

import java.util.List;

public interface AvaliacaoClient {

    List<AvaliacaoModel> getAllByProduto(Long produtoId);
}
