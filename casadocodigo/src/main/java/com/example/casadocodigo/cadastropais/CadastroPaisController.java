package com.example.casadocodigo.cadastropais;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paises")
public class CadastroPaisController {
	
	@Autowired
	private PaisRepository paisRepository;

	@PostMapping
	public ResponseEntity<Pais> cadastrarPais(@RequestBody @Valid NovoPaisRequest request) {
		Pais pais = request.toModel();
		paisRepository.save(pais);
		
		return ResponseEntity.ok(pais);
	}

}
