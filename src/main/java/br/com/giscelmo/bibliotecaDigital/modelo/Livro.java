package br.com.giscelmo.bibliotecaDigital.modelo;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autor;
    private String idioma;
    private Integer download;

    public Livro() {}


    public Livro(DadosLivro dadosLivro, List<Autor> autores) {
        this.titulo = dadosLivro.titulo();
        this.autor = autores;
        this.idioma = dadosLivro.idioma()
                .stream()
                .findFirst()
                .orElse("desconhecido");
        this.download = dadosLivro.download();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    @Override
    public String toString() {
        String nomeAutores = autor.stream()
                .map(Autor::getNomeAutor)
                .collect(Collectors.joining(", "));
        return ("""
                Titulo: %s
                Autor: %s
                Idioma: %s
                Downloads: %d
                """.formatted(
                titulo,
                nomeAutores,
                idioma,
                download)
        );
    }
}

