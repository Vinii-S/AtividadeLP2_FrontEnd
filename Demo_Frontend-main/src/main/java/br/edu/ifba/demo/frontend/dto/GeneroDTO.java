package br.edu.ifba.demo.frontend.dto;
import java.io.Serializable;


import lombok.Data;

@Data
public class GeneroDTO implements Serializable {
    private Long id_genero;
    private String nome_genero;
    private Boolean status;
    
}
