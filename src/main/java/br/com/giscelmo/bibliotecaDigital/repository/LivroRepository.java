package br.com.giscelmo.bibliotecaDigital.repository;

import br.com.giscelmo.bibliotecaDigital.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);
}
