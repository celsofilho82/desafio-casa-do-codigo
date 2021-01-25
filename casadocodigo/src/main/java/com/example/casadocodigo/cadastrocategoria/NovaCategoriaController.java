package com.example.casadocodigo.cadastrocategoria;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class NovaCategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	private ResponseEntity<Categoria> cadastrarCategoria(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toModel();
		categoriaRepository.save(categoria);

		return ResponseEntity.ok(categoria);
	}
}
