package com.example.casadocodigo.validacaoCpfCnpj;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.example.casadocodigo.novacompra.NovaCompraRequest;

/**
 * 
 * @author celso
 * 
 *         Implementei essa classe pois, segundo os requisitos tenho que validar
 *         CPF ou CNPJ em um mesmo campo ou seja aqui temos uma validação
 *         condicional. Fazem parte dessa implementação as interfaces
 *         DocumentoCpf e DocumentoCnpj que nesse caso só vão servir para
 *         sinalizar qual das BEAN Validations deve ser executada. @CPF ou @CNPJ
 */

public class NovaCompraGroupSequenceProvider implements DefaultGroupSequenceProvider<NovaCompraRequest> {

	@Override
	public List<Class<?>> getValidationGroups(NovaCompraRequest request) {
		/*
		 * Informamos ao HibernateValidator para usar as validações default definidas na
		 * classe Cliente.
		 */

		List<Class<?>> groups = new ArrayList<>();

		groups.add(NovaCompraRequest.class);

		/*
		 * Aqui nós implementamos a lógica que determina o grupo de validação a ser
		 * aplicado ao bean.
		 */
		if (request != null) {
			if (request.getDocumento().length() <= 11) {
				groups.add(DocumentoCpf.class);
			} else {
				groups.add(DocumentoCnpj.class);
			}
		}

		return groups;
	}

}
