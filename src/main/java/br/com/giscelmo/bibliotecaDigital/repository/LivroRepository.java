package br.com.giscelmo.bibliotecaDigital.repository;

import br.com.giscelmo.bibliotecaDigital.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);

    @Query("SELECT l FROM Livro l ORDER BY l.titulo DESC")
    List<Livro> livroBuscado();
}
