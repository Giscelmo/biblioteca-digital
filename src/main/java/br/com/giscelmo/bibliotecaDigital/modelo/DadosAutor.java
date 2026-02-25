package br.com.giscelmo.bibliotecaDigital.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @JsonAlias("name")
        String nameAutor,
        @JsonAlias("birth_year")
        Integer dataNascimento,
        @JsonAlias("death_year")
        Integer dataFalecimento
) {
}
