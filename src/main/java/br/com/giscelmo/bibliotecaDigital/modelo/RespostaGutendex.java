package br.com.giscelmo.bibliotecaDigital.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaGutendex(
        List<DadosLivro> results
) {
}
