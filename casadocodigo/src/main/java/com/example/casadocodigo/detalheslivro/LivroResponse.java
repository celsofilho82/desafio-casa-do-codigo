package com.example.casadocodigo.detalheslivro;

import java.util.List;
import java.util.stream.Collectors;

import com.example.casadocodigo.cadastrolivro.Livro;

public class LivroResponse {

	private Long id;
	private String titulo;

	public LivroResponse(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public static List<LivroResponse> convert(List<Livro> livros) {

		return livros.stream().map(LivroResponse::new).collect(Collectors.toList());
	}

}
