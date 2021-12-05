package br.com.experian.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.experian.model.Cliente;

public class ClienteForm {
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;
	
	@NotNull
	@NotEmpty
	private String dataNascimento;
	
	@NotNull
	@NotEmpty
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Cliente toCliente() {
		return new Cliente(nome, dataNascimento, telefone);
	}
}
