package br.com.experian.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.experian.form.ClienteForm;
import br.com.experian.model.Cliente;
import br.com.experian.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
@ResponseBody
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listaClientes() {
		List<Cliente> clientes = clienteRepository.findAll();

		return clientes;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscaClientePorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Cliente> cadastraNovoCliente(@RequestBody @Valid ClienteForm clienteForm,
			UriComponentsBuilder uriComponentsBuilder) {
		Cliente cliente = clienteForm.toCliente();

		clienteRepository.save(cliente);

		URI uri = uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

		return ResponseEntity.created(uri).body(cliente);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Cliente> atualizaCliente(@PathVariable Long id, @RequestBody @Valid ClienteForm clienteForm) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent()) {
			Cliente clienteAtualizado = cliente.get();
			clienteAtualizado.setNome(clienteForm.getNome());
			clienteAtualizado.setTelefone(clienteForm.getTelefone());
			clienteAtualizado.setDataNascimento(clienteForm.getDataNascimento());

			clienteRepository.save(clienteAtualizado);

			return ResponseEntity.ok(clienteAtualizado);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletaCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isPresent()) {
			clienteRepository.deleteById(id);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
