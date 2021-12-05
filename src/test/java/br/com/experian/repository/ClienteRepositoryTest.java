package br.com.experian.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.experian.model.Cliente;

@RunWith(SpringRunner.class)
@DataJpaTest
class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	void deveRetornarTodosOsClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
		Assert.assertFalse(clientes.isEmpty());
	}
	
	@Test
	void deveriaRetornarUmClienteAoBuscarPeloId() {
		Long id = 1L;
		Optional<Cliente> cliente = clienteRepository.findById(id);
		Assert.assertNotNull(cliente);
	}
	
	@Test
	void naoDeveriaRetornarUmClienteAoBuscarPorUmIdNaoCadastrado() {
		Long id = 999L;
		Optional<Cliente> cliente = clienteRepository.findById(id);
		Assert.assertFalse(cliente.isPresent());
	}
}
