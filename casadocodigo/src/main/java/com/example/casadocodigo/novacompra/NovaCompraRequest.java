package com.example.casadocodigo.novacompra;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.util.Assert;

import com.example.casadocodigo.cadastroestado.Estado;
import com.example.casadocodigo.cadastroestado.EstadoRepository;
import com.example.casadocodigo.cadastropais.Pais;
import com.example.casadocodigo.cadastropais.PaisRepository;
import com.example.casadocodigo.validacaoCpfCnpj.DocumentoCnpj;
import com.example.casadocodigo.validacaoCpfCnpj.DocumentoCpf;
import com.example.casadocodigo.validacaoCpfCnpj.NovaCompraGroupSequenceProvider;

@GroupSequenceProvider(value = NovaCompraGroupSequenceProvider.class)
public class NovaCompraRequest {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String sobrenome;

	@NotBlank
	@CPF(groups = DocumentoCpf.class)
	@CNPJ(groups = DocumentoCnpj.class)
	private String documento;

	@NotBlank
	private String endereco;

	@NotBlank
	private String complemento;

	@NotBlank
	private String cidade;

	@NotNull
	private Long idPais;

	private Long idEstado;

	@NotBlank
	private String telefone;

	@NotBlank
	private String cep;

	public NovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long idPais, @NotBlank String telefone, @NotBlank String cep) {
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.telefone = telefone;
		this.cep = cep;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getDocumento() {
		return documento;
	}

	public Compra toModel(PaisRepository paisRepository, EstadoRepository estadoRepository) {
		Optional<Pais> pais = paisRepository.findById(idPais);
		Assert.state(pais.isPresent(), "O Pais com id: " + this.idPais + " não está cadastrado");

		Estado estado = setEstado(estadoRepository);

		paisComEstadoCadastradoDeveInformarEstado(estadoRepository);

		verificaSeEstadoPertenceAoPais(estadoRepository, estado);

		return new Compra(this.email, this.nome, this.sobrenome, this.documento, this.endereco, this.complemento,
				this.cidade, pais.get(), estado, this.telefone, this.cep);
	}

	private void verificaSeEstadoPertenceAoPais(EstadoRepository estadoRepository, Estado estado) {
		if (estado.equals(null)) {
			return;
		}

		List<Estado> listaEstadosDoPais = estadoRepository.findByPaisId(this.idPais);
		Assert.isTrue(listaEstadosDoPais.contains(estado), "O Estado informado não pertence a esse País");

	}

	private void paisComEstadoCadastradoDeveInformarEstado(EstadoRepository estadoRepository) {
		List<Estado> listaEstadosDoPais = estadoRepository.findByPaisId(this.idPais);
		if (this.idEstado == null) {
			Assert.isTrue(listaEstadosDoPais.isEmpty(),
					"O País possui Estados cadastrados. Um estado precisa ser selecionado");
		}
	}

	private Estado setEstado(EstadoRepository estadoRepository) {
		if (this.idEstado == null) {
			return null;
		}
		Optional<Estado> estado = estadoRepository.findById(this.idEstado);
		Assert.state(estado.isPresent(), "O Estado com id: " + this.idEstado + " não está cadastrado");

		return estado.get();
	}

}
