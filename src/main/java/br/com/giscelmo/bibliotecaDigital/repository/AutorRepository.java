package br.com.giscelmo.bibliotecaDigital.repository;

import br.com.giscelmo.bibliotecaDigital.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeAutor(String nomeAutor);
}
