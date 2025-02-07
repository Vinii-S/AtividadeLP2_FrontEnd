package br.edu.ifba.demo.frontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.edu.ifba.demo.frontend.dto.GeneroDTO;

import reactor.core.publisher.Mono;

@Service
public class GeneroService {
    @Autowired
    private WebClient webClient;

    // Método existente (listar todos)
    public List<GeneroDTO> listAllGeneros() {
        Mono<List<GeneroDTO>> listObj = this.webClient
                .method(HttpMethod.GET)
                .uri("genero/listall")
                .retrieve()
                .bodyToFlux(GeneroDTO.class)
                .collectList();
        return listObj.block();
    }

    // Método existente (salvar)
    public boolean salvar(GeneroDTO generoDTO) {
        Mono<GeneroDTO> obj = this.webClient
                .method(HttpMethod.POST)
                .uri("genero")
                .bodyValue(generoDTO)
                .retrieve()
                .bodyToMono(GeneroDTO.class);
        return obj.block() != null;
    }

    // Buscar gênero por ID (para edição)
    public GeneroDTO getById(Long id) {
        return this.webClient
                .method(HttpMethod.GET)
                .uri("genero/{id}", id)
                .retrieve()
                .bodyToMono(GeneroDTO.class)
                .block();
    }

    // Atualizar gênero (PUT)
    public boolean atualizar(GeneroDTO generoDTO) {
        return this.webClient
                .method(HttpMethod.PUT) 
                .uri("genero/{id}", generoDTO.getId_genero()) 
                .bodyValue(generoDTO)
                .retrieve()
                .toBodilessEntity()
                .block()
                .getStatusCode()
                .is2xxSuccessful();
    }

    // Excluir gênero (DELETE)
    public boolean delete(Long id) {
        return this.webClient
                .method(HttpMethod.DELETE)
                .uri("genero/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block()
                .getStatusCode()
                .is2xxSuccessful();
    }
}
