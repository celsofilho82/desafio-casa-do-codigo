package com.example.casadocodigo.cadastrolivro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TituloLivroDeveSerUnicoValidator implements Validator {

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
		Optional<Livro> possivelLivro = livroRepository.findByTitulo(request.getTitulo());

		if (possivelLivro.isPresent()) {
			errors.reject("livro", "Um livro com esse titulo: " + request.getTitulo() + " já está cadastrado");
		}
	}

}
