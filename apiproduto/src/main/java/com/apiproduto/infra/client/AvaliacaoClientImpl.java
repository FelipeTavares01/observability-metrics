package com.apiproduto.infra.client;

import com.apiproduto.client.avaliacoes.AvaliacaoClient;
import com.apiproduto.client.model.AvaliacaoModel;
import com.apiproduto.config.properties.ApiAvaliacoesProperties;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
public class AvaliacaoClientImpl implements AvaliacaoClient {

    private final Logger logger = LoggerFactory.getLogger(AvaliacaoClientImpl.class);

    private final RestTemplate restTemplate;
    private ApiAvaliacoesProperties apiAvaliacoesProperties;

    private final Map<Long, List<AvaliacaoModel>> CACHE = new HashMap<>();

    public AvaliacaoClientImpl(RestTemplate restTemplate, ApiAvaliacoesProperties apiAvaliacoesProperties) {
        logger.info(apiAvaliacoesProperties.getUrl());
        this.restTemplate = restTemplate;
        this.apiAvaliacoesProperties = apiAvaliacoesProperties;
    }

    @Override
    @CircuitBreaker(name = "avaliacaoCB", fallbackMethod = "getAllByProdutoNoCache")
    public List<AvaliacaoModel> getAllByProduto(Long produtoId) {
        final List<AvaliacaoModel> avaliacoes = executarRequisicao(produtoId);
        return avaliacoes;
    }

    private List<AvaliacaoModel> executarRequisicao(Long produtoId) {
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("produtoId", produtoId);

        logger.info("Buscando avaliações");
        final AvaliacaoModel[] avaliacoes;

        try {
            avaliacoes = restTemplate.getForObject(assemblerRequest(), AvaliacaoModel[].class, parametros);
        } catch (Exception e) {
            logger.error("Erro ao buscar avaliações");
            throw e;
        }

        logger.info("Alimentando o cache...");

        CACHE.put(produtoId, Arrays.asList(avaliacoes));

        return Arrays.asList(avaliacoes);
    }

    private List<AvaliacaoModel> getAllByProdutoNoCache(Long produtoId, Throwable e) {
        logger.info("Buscando no cache");
        return CACHE.getOrDefault(produtoId, new ArrayList<>());
    }

    private String assemblerRequest() {
        return UriComponentsBuilder
                .fromHttpUrl(apiAvaliacoesProperties.getUrl())
                .queryParam("produtoId", "{produtoId}")
                .encode()
                .toUriString();
    }
}
