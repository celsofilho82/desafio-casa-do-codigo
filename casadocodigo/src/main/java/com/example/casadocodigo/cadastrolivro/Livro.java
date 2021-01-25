package com.example.casadocodigo.cadastrolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.casadocodigo.cadastroautor.Autor;
import com.example.casadocodigo.cadastrocategoria.Categoria;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String resumo;
	private String sumario;
	private @NotNull @Min(20) BigDecimal preco;
	private @NotNull @Min(100) Integer paginas;
	private @NotBlank String isbn;
	private @Future LocalDate dataPublicacao;

	@NotNull
	@Valid
	@ManyToOne
	private Autor autor;

	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	@Deprecated
	public Livro() {
	}

	public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer paginas, @NotBlank String isbn,
			@Future LocalDate dataPublicacao, Autor autor, Categoria categoria) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.paginas = paginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.autor = autor;
		this.categoria = categoria;
	}

	public Long getId() {
		return Id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public Autor getAutor() {
		return autor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

}
