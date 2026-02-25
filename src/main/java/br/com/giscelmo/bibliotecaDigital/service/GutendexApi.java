package br.com.giscelmo.bibliotecaDigital.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@ConfigurationProperties(prefix = "biblioteca.api")
public class GutendexApi {

    private final HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    public String obterDados(String endereco) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .GET()
                .build();

        try {

            HttpResponse<String> response =
                    client.send(request,
                            HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {

                throw new RuntimeException(
                        "Erro na API: " + response.statusCode()
                );

            }

            return response.body();

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException("Erro ao chamar API", e);

        }

    }
}
