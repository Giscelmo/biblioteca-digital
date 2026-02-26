package br.com.giscelmo.bibliotecaDigital.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomeAutor;
    private Integer dataNascimento;
    private Integer dataFalecimento;
    @ManyToMany(mappedBy = "autor")
    private List<Livro> livros;

    public Autor() {}

    public Autor(DadosAutor dadosAutor) {
        this.nomeAutor = dadosAutor.nomeAutor();
        this.dataNascimento = dadosAutor.dataNascimento();
        this.dataFalecimento = dadosAutor.dataFalecimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getDataFalecimento() {
        return dataFalecimento;
    }

    public void setDataFalecimento(Integer dataFalecimento) {
        this.dataFalecimento = dataFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return ("""
                Autor: %s
                Data Nascimento: %d
                Falecimento: %d
                Livros: %s
                """.formatted(nomeAutor, dataNascimento, dataFalecimento, livros));
    }
}
