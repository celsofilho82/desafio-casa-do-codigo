package com.example.casadocodigo.cadastrolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.example.casadocodigo.cadastroautor.Autor;
import com.example.casadocodigo.cadastrocategoria.Categoria;

public class NovoLivroRequest {

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String resumo;

	private String sumario;

	@NotNull
	@Min(value = 20)
	private BigDecimal preco;

	@NotNull
	@Min(value = 100)
	private Integer paginas;

	@NotBlank
	private String isbn;

	@Future
	private LocalDate dataPublicacao;

	@NotNull
	private Long idCategoria;

	@NotNull
	private Long idAutor;

	public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer paginas, @NotBlank String isbn,
			@Future LocalDate dataPublicacao, @NotNull Long idCategoria, @NotNull Long idAutor) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.paginas = paginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.idCategoria = idCategoria;
		this.idAutor = idAutor;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public Livro toModel(EntityManager manager) {
		Autor autor = manager.find(Autor.class, idAutor);
		Categoria categoria = manager.find(Categoria.class, idCategoria);

		Assert.state(autor != null, "O Autor informado não está cadastrado");
		Assert.state(categoria != null, "A categoria informada não está cadstrada");

		return new Livro(this.titulo, this.resumo, this.sumario, this.preco, this.paginas, this.isbn,
				this.dataPublicacao, autor, categoria);
	}

}
