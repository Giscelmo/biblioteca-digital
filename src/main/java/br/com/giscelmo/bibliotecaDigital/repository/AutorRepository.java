package br.com.giscelmo.bibliotecaDigital.repository;

import br.com.giscelmo.bibliotecaDigital.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeAutor(String nomeAutor);

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.livros ORDER BY a.nomeAutor ASC")
    List<Autor> autorBuscado();
}
