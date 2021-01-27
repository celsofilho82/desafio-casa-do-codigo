package com.example.casadocodigo.cadastroestado;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.example.casadocodigo.cadastropais.Pais;
import com.example.casadocodigo.cadastropais.PaisRepository;

public class NovoEstadoRequest {

	@NotBlank
	private String nome;

	@NotNull
	private Long idPais;

	public NovoEstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
		this.nome = nome;
		this.idPais = idPais;
	}

	public Estado toModel(PaisRepository paisRepository) {
		Optional<Pais> possivelPais = paisRepository.findById(idPais);
		Assert.state(possivelPais != null, "O id do País informado não foi encontrado");

		return new Estado(this.nome, possivelPais.get());
	}

}
