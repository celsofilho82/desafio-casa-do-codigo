package com.example.casadocodigo.cadastrolivro;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.example.casadocodigo.detalheslivro.LivroDetalhes;
import com.example.casadocodigo.detalheslivro.LivroResponse;

@RestController
@RequestMapping("/livros")
public class CadastroLivroController {

	@Autowired
	private EntityManager manager;

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
	public ResponseEntity<Livro> cadastrarLivro(@RequestBody @Valid NovoLivroRequest request,
			UriComponentsBuilder uriBuilder) {

		Livro livro = request.toModel(manager);
		livroRepository.save(livro);
		URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();

		return ResponseEntity.created(uri).body(livro);
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
