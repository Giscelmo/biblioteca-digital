package br.com.giscelmo.bibliotecaDigital.service;

import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
@Service
public class ConverteDados implements IConverteDados{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        return mapper.readValue(json, classe);
    }
}
