package com.apiavaliacoes.api.controller;

import com.apiavaliacoes.api.model.AvaliacaoModel;
import com.apiavaliacoes.domain.repository.AvaliacaoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @GetMapping
    public List<AvaliacaoModel> getByProduto(@RequestParam Long produtoId) {
        return avaliacaoRepository.getAll()
                .stream()
                .filter(avaliacao -> avaliacao.getProdutoId().equals(produtoId))
                .map(AvaliacaoModel::of)
                .collect(Collectors.toList());
    }
}