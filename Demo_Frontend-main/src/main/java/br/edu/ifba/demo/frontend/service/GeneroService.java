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

    public List<GeneroDTO> listAllGeneros(){
        Mono<List<GeneroDTO>> listObj = this.webClient
            .method(HttpMethod.GET)
            .uri("genero/listall")
            .retrieve()
            .bodyToFlux(GeneroDTO.class)
            .collectList();
        
        List<GeneroDTO> list = listObj.block();
        return list;
    }
     // Método para salvar ou atualizar um gênero
     public boolean salvarOuAtualizar(GeneroDTO generoDTO) {
        Mono<GeneroDTO> obj = this.webClient
            .method(HttpMethod.POST)  
            .uri("genero") // Ajuste a URL de acordo com a sua configuração
            .bodyValue(generoDTO)
            .retrieve()
            .bodyToMono(GeneroDTO.class);
    
        GeneroDTO savedGenero = obj.block(); // bloqueia até a resposta chegar
    
        // Verifica se o gênero foi realmente salvo
        return savedGenero != null;
    }
}
