package br.com.giscelmo.bibliotecaDigital.principal;

import br.com.giscelmo.bibliotecaDigital.modelo.*;
import br.com.giscelmo.bibliotecaDigital.repository.AutorRepository;
import br.com.giscelmo.bibliotecaDigital.repository.LivroRepository;
import br.com.giscelmo.bibliotecaDigital.service.ConverteDados;
import br.com.giscelmo.bibliotecaDigital.service.GuntendexConfig;
import br.com.giscelmo.bibliotecaDigital.service.GutendexApi;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private final GutendexApi api;
    private final GuntendexConfig config;
    private final ConverteDados converteDados;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private RespostaGutendex respostaGutendex;
    private Scanner leitura = new Scanner(System.in);

    public Principal(GutendexApi api, GuntendexConfig config, ConverteDados converteDados, LivroRepository livroRepository, AutorRepository autorRepository) {
        this.api = api;
        this.config = config;
        this.converteDados = converteDados;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void executar() {

        while (true) {
            var menu = """
                    1 - Buscar Livro
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinando ano
                    0 - Sair""";
            System.out.println(menu);
            System.out.println("Digite a opção: ");
            String entrada = leitura.nextLine().trim();

            if(entrada.isEmpty()) {
                System.out.println("Opção inválida! Digite algu número.");
                continue;
            }

            int opcao;

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            if(opcao == 0) {
                System.out.println("Saindo...");
                break;
            }

            switch (opcao) {
                case 1:
                    buscarLivros();
                    break;
                case 2:
                    buscarLivrosRegistrado();
                    break;
                case 3:
                    buscarAutorRegistrado();
                    break;
                case 4:
                    buscarAutorVivo();
                    break;
                default:
                    System.out.println("Opção invalida.");
            }
        }
        leitura.close();
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

        respostaGutendex = converteDados.obterDados(json, RespostaGutendex.class);
        if (respostaGutendex.results().isEmpty()) {
            System.out.println("Livro não encotrado");
            return;
        }

        DadosLivro dadosLivro = respostaGutendex.results().getFirst();

        Livro livro = livroRepository
                .findByTitulo(dadosLivro.titulo())
                .orElseGet(() -> {
                    List<Autor> autores = dadosLivro.autor().stream()
                            .map(dadosAutor ->
                                    autorRepository
                                            .findByNomeAutor(dadosAutor.nomeAutor())
                                            .orElseGet(() ->
                                                    autorRepository.save(new Autor(dadosAutor))
                                            )
                            ).toList();

                    Livro novoLivro = new Livro(dadosLivro, autores);
                    return livroRepository.save(novoLivro);
                });

        System.out.println(livro);
    }

    private void buscarLivrosRegistrado() {
        List<Livro>  livroBuscado = livroRepository.livroBuscado();
        livroBuscado.forEach(System.out::println);
    }

    private void buscarAutorRegistrado() {
        List<Autor> autorBuscado = autorRepository.autorBuscado();
        autorBuscado.forEach(System.out::println);
    }

    private void buscarAutorVivo() {
        System.out.println("Insira o ano que deseja pesquisar: ");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autorVivoBuscado = autorRepository.autorVivoBuscado(ano);
        if(autorVivoBuscado.isEmpty()){
            System.out.println("Nenhum autor encontrado vivo em " + ano);
        } else {
            autorVivoBuscado.forEach(System.out::println);
        }
    }
}
