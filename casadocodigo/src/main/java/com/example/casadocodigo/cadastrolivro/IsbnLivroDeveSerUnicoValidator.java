package com.example.casadocodigo.cadastrolivro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IsbnLivroDeveSerUnicoValidator implements Validator {

	@Autowired
	private LivroRepository livroRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoLivroRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovoLivroRequest request = (NovoLivroRequest) target;
		Optional<Livro> possivelLivro = livroRepository.findByIsbn(request.getIsbn());

		if (possivelLivro.isPresent()) {
			errors.reject("livro", "Esse ISBN: " + request.getIsbn() + " já está cadastrado");
		}
	}

}
