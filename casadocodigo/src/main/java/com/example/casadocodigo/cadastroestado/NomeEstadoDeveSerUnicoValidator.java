package com.example.casadocodigo.cadastroestado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NomeEstadoDeveSerUnicoValidator implements Validator {

	@Autowired
	private EstadoRepository estadoRespostory;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoEstadoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovoEstadoRequest request = (NovoEstadoRequest) target;
		Optional<Estado> possivelEstado = estadoRespostory.findByNome(request.getNome());
		if (possivelEstado.isPresent()) {
			errors.reject("Estado", "O Estado: " + request.getNome() + " já está cadastrado!");
		}
	}

}
