package br.com.giscelmo.bibliotecaDigital.principal;

import br.com.giscelmo.bibliotecaDigital.modelo.DadosAutor;
import br.com.giscelmo.bibliotecaDigital.modelo.DadosLivro;
import br.com.giscelmo.bibliotecaDigital.modelo.RespostaGutendex;
import br.com.giscelmo.bibliotecaDigital.service.ConverteDados;
import br.com.giscelmo.bibliotecaDigital.service.GuntendexConfig;
import br.com.giscelmo.bibliotecaDigital.service.GutendexApi;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Scanner;

@Component
public class Principal {
    private final GutendexApi api;
    private final GuntendexConfig config;
    private final ConverteDados converteDados;
    private RespostaGutendex respostaGutendex;
    private Scanner leitura = new Scanner(System.in);

    public Principal(GutendexApi api, GuntendexConfig config, ConverteDados converteDados) {
        this.api = api;
        this.config = config;
        this.converteDados = converteDados;
    }

    public void executar() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    1 - Buscar Livro
                    0 - Sair""";

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivros();
                    break;
                case 0:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção invalida.");
            }

        }
    }

    private void buscarLivros() {
        System.out.println("Digita nome do livro");
        String nomeLivro = leitura.nextLine();

        String url = UriComponentsBuilder
                .fromUriString(config.getUrl())
                .queryParam("search", nomeLivro)
                .toUriString();
        System.out.println(url);

        String json = api.obterDados(url);
        System.out.println("""
                Json recebido
                %s""".formatted(json));

        respostaGutendex = converteDados.obterDados(json, RespostaGutendex.class);

        DadosLivro dadosLivro = respostaGutendex.results().getFirst();



        String nomeAutor = dadosLivro.autor()
                .stream()
                .findFirst()
                .map(DadosAutor::nameAutor)
                .orElse("Desconhecido");

        String idioma = dadosLivro.idioma().stream()
                .findFirst()
                .orElse("desconhecido");

        System.out.println("""
                Titulo: %s
                Autor: %s
                Idioma: %s
                Downloads: %d
                """.formatted(dadosLivro.titulo(), nomeAutor,
                idioma, dadosLivro.download()));
    }
}
