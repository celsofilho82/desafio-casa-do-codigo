package com.example.casadocodigo.cadastroestado;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	Optional<Estado> findByNome(String nome);

	List<Estado> findByPaisId(@NotNull Long idPais);

}
