package com.example.casadocodigo.cadastroautor;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private @NotBlank String nome;
	private @NotBlank @Email String email;
	private @NotBlank @Size(max = 400) String descricao;
	private @NotNull LocalDateTime dataCadastro;

	public Autor(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao,
			@NotNull LocalDateTime dataCadastro) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;

	}

	public Long getId() {
		return Id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

}
