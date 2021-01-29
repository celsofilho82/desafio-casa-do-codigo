package com.example.casadocodigo.tratandoerros;

public class ErroResponse {

	public String field;
	public String message;

	public ErroResponse(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public ErroResponse(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
