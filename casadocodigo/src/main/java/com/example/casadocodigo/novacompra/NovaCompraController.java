package com.example.casadocodigo.novacompra;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.casadocodigo.cadastroestado.EstadoRepository;
import com.example.casadocodigo.cadastropais.PaisRepository;

@RestController
@RequestMapping("/compras")
public class NovaCompraController {

	@Autowired
	private EstadoRepository EstadoRepository;
	
	@Autowired
	private PaisRepository PaisRepository;

	@PostMapping
	public String novaCompra(@RequestBody @Valid NovaCompraRequest request) {
		
		Compra compra = request.toModel(PaisRepository, EstadoRepository);
		return compra.toString();
	}
	
}
