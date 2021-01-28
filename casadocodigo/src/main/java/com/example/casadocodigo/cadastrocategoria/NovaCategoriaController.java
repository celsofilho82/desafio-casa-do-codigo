package com.example.casadocodigo.cadastrocategoria;

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
@RequestMapping("/categorias")
public class NovaCategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private NomeCategoriaDeveSerUnicoValidator nomeCategoriaDeveSerUnicoValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(nomeCategoriaDeveSerUnicoValidator);
	}

	@PostMapping
	private ResponseEntity<Categoria> cadastrarCategoria(@RequestBody @Valid NovaCategoriaRequest request,
			UriComponentsBuilder uriBuilder) {

		Categoria categoria = request.toModel();
		categoriaRepository.save(categoria);
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();

		return ResponseEntity.created(uri).body(categoria);
	}
}
