package com.example.casadocodigo.cadastropais;

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
@RequestMapping("/paises")
public class CadastroPaisController {

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private NomePaisDeveSerUnicoValidator nomePaisDeveSerUnicoValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(nomePaisDeveSerUnicoValidator);
	}

	@PostMapping
	public ResponseEntity<Pais> cadastrarPais(@RequestBody @Valid NovoPaisRequest request,
			UriComponentsBuilder uriBuilder) {
		
		Pais pais = request.toModel();
		paisRepository.save(pais);
		URI uri = uriBuilder.path("/paises/{id}").buildAndExpand(pais.getId()).toUri();

		return ResponseEntity.created(uri).body(pais);
	}

}
