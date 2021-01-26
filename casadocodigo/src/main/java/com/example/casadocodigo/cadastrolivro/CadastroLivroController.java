package com.example.casadocodigo.cadastrolivro;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.casadocodigo.cadastroautor.AutorRepository;
import com.example.casadocodigo.cadastrocategoria.CategoriaRepository;
import com.example.casadocodigo.detalheslivro.LivroDetalhes;
import com.example.casadocodigo.detalheslivro.LivroResponse;

@RestController
@RequestMapping("/livros")
public class CadastroLivroController {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private TituloLivroDeveSerUnicoValidator tituloLivroDeveSerUnicoValidator;

	@Autowired
	private IsbnLivroDeveSerUnicoValidator isbnLivroDeveSerUnicoValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(tituloLivroDeveSerUnicoValidator, isbnLivroDeveSerUnicoValidator);
	}

	@PostMapping
	public ResponseEntity<Livro> cadastrarLivro(@RequestBody @Valid NovoLivroRequest request) {

		Livro livro = request.toModel(autorRepository, categoriaRepository);
		livroRepository.save(livro);

		return ResponseEntity.ok(livro);
	}

	@GetMapping
	public List<LivroResponse> listarLivros() {
		List<Livro> livros = livroRepository.findAll();

		return LivroResponse.convert(livros);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<LivroDetalhes> mostrarLivro(@PathVariable Long id) {
		Optional<Livro> possivelLivro = livroRepository.findById(id);
		if (possivelLivro.isPresent()) {
			LivroDetalhes livroDetalhes = LivroDetalhes.convert(possivelLivro.get());

			return ResponseEntity.ok(livroDetalhes);
		}

		return ResponseEntity.notFound().build();
	}
}
