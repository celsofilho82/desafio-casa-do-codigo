package com.example.casadocodigo.cadastrolivro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	Optional<Livro> findByTitulo(String titulo);

	Optional<Livro> findByIsbn(String isbn);
}
