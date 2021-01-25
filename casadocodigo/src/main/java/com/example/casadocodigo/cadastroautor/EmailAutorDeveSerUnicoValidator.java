package com.example.casadocodigo.cadastroautor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailAutorDeveSerUnicoValidator implements Validator {

	@Autowired
	private AutorRepository autorRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoAutorRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovoAutorRequest request = (NovoAutorRequest) target;
		Optional<Autor> possivelAutor = autorRepository.findByEmail(request.getEmail());
		if (possivelAutor.isPresent()) {
			errors.reject("email", "Este email: " + request.getEmail() + " já está cadastrado");
		}
	}

}
