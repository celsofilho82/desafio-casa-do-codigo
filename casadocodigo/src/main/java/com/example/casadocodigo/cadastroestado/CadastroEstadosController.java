package com.example.casadocodigo.cadastroestado;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.casadocodigo.cadastropais.PaisRepository;

@RestController
@RequestMapping("/estados")
public class CadastroEstadosController {

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private NomeEstadoDeveSerUnicoValidator nomeEstadoDeveSerUnicoValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(nomeEstadoDeveSerUnicoValidator);
	}

	@PostMapping
	public ResponseEntity<Estado> cadastrarEstado(@RequestBody @Valid NovoEstadoRequest request) {
		Estado estado = request.toModel(paisRepository);
		estadoRepository.save(estado);

		return ResponseEntity.ok(estado);
	}
}
