package br.com.experian.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.experian.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findById(Long id);
	
}
