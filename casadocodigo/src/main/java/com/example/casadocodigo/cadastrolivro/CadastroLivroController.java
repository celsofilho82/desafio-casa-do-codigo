package com.example.casadocodigo.cadastrolivro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.casadocodigo.cadastroautor.AutorRepository;
import com.example.casadocodigo.cadastrocategoria.CategoriaRepository;

@RestController
@RequestMapping("/livros")
public class CadastroLivroController {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private LivroRepository livroRepository;

	@PostMapping
	public ResponseEntity<Livro> cadastrarLivro(@RequestBody @Valid NovoLivroRequest request) {

		Livro livro = request.toModel(autorRepository, categoriaRepository);
		livroRepository.save(livro);

		return ResponseEntity.ok(livro);
	}
}
