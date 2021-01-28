package com.example.casadocodigo.cadastroautor;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/autores")
public class CadastroAutorController {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private EmailAutorDeveSerUnicoValidator emailUnicoValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(emailUnicoValidator);
	}

	@PostMapping
	public ResponseEntity<Autor> cadastraAutor(@RequestBody @Valid NovoAutorRequest request,
			UriComponentsBuilder uriBuilder) {

		Autor autor = request.toModel();
		autorRepository.save(autor);
		URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();

		return ResponseEntity.created(uri).body(autor);
	}
}
