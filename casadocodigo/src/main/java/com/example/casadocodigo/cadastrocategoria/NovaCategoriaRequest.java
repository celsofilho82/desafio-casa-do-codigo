package com.example.casadocodigo.cadastrocategoria;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria toModel() {
		return new Categoria(this.nome);
	}

}
