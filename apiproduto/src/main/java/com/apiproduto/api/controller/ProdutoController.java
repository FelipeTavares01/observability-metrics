package com.apiproduto.api.controller;

import com.apiproduto.api.exception.RecursoNaoEncontradoException;
import com.apiproduto.api.model.ProdutoModel;
import com.apiproduto.client.avaliacoes.AvaliacaoClient;
import com.apiproduto.client.model.AvaliacaoModel;
import com.apiproduto.domain.model.Produto;
import com.apiproduto.domain.repository.ProdutoRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final AvaliacaoClient avaliacaoClient;

    public ProdutoController(ProdutoRepository produtoRepository, AvaliacaoClient avaliacaoClient) {
        this.produtoRepository = produtoRepository;
        this.avaliacaoClient = avaliacaoClient;
    }

    @GetMapping
    @Timed(value = "getAll_requests_seconds_duration",
            description = "duration time in seconds of getAll method", percentiles = {0.95, 0.99})
    public List<ProdutoModel> getAll() {
        return produtoRepository.getAll()
                .stream()
                .map(this::converterProdutoParaModelo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{produtoId}")
    @Timed(value = "getOneById_requests_seconds_duration",
            description = "duration time in seconds of getOneById method", percentiles = {0.95, 0.99})
    public ProdutoModel getOneById(@PathVariable Long produtoId) {
        return produtoRepository.getOne(produtoId)
                .map(this::converterProdutoParaModeloComAvaliacao)
                .orElseThrow(RecursoNaoEncontradoException::new);
    }

    private ProdutoModel converterProdutoParaModelo(Produto produto) {
        return ProdutoModel.of(produto);
    }

    private ProdutoModel converterProdutoParaModeloComAvaliacao(Produto produto) {
        return ProdutoModel.of(produto, getAvaliacaoDoProduto(produto.getId()));
    }

    private List<AvaliacaoModel> getAvaliacaoDoProduto(Long productId) {
        return avaliacaoClient.getAllByProduto(productId);
    }
}
