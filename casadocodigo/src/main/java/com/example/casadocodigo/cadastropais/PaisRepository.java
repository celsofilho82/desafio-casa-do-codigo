package com.example.casadocodigo.cadastropais;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {

	Optional<Pais> findByNome(String nome);
}
