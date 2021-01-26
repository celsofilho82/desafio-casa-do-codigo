package com.example.casadocodigo.cadastropais;

import javax.validation.constraints.NotBlank;

public class NovoPaisRequest {

	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais toModel() {
		return new Pais(this.nome);
	}

}
