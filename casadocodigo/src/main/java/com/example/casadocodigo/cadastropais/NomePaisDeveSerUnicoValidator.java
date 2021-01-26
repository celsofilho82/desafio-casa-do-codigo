package com.example.casadocodigo.cadastropais;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NomePaisDeveSerUnicoValidator implements Validator {

	@Autowired
	private PaisRepository paisRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoPaisRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		NovoPaisRequest request = (NovoPaisRequest) target;
		Optional<Pais> possivelPais = paisRepository.findByNome(request.getNome());
		if (possivelPais.isPresent()) {
			errors.reject("pais", "Este pais: " + request.getNome() + " já está cadastrado");
		}
	}

}
