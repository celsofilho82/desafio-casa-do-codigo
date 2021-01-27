package com.example.casadocodigo.novacompra;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.casadocodigo.cadastroestado.Estado;
import com.example.casadocodigo.cadastropais.Pais;

public class Compra {

	private @NotBlank @Email String email;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	private @NotBlank String cidade;
	@NotNull
	@Valid
	private Pais pais;

	private Estado estado;
	private @NotBlank String telefone;
	private @NotBlank String cep;

	public Compra(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, Pais pais, Estado estado, @NotBlank String telefone, @NotBlank String cep) {
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.pais = pais;
		this.estado = estado;
		this.telefone = telefone;
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Compra [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento=" + documento
				+ ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade + ", pais=" + pais
				+ ", estado=" + estado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}

}
