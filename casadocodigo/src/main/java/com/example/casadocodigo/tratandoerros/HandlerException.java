package com.example.casadocodigo.tratandoerros;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<ErroResponse> erroResponse = new ArrayList<>();
		List<ObjectError> globalError = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroResponse error = new ErroResponse(e.getField(), message);

			erroResponse.add(error);
		});
		
		globalError.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroResponse error = new ErroResponse(e.getCode(), message);
			erroResponse.add(error);
		});
		
		return erroResponse;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ErroResponse handlerIllegalArgumentException(IllegalArgumentException exception) {
		ErroResponse error = new ErroResponse(exception.getLocalizedMessage());
		
		return error;
	}
}
