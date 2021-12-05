package br.com.experian.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaRetornar200AoBuscarTodosOsClientes() throws Exception {
		URI uri = new URI("/clientes");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornar200AoBuscarUmClientePorUmId() throws Exception {
		Long id = 1L;
		URI uri = new URI("/clientes/" + id);
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornar404AoBuscarUmClientePorUmIdNaoCadastrado() throws Exception {
		Long id = 999L;
		URI uri = new URI("/clientes/" + id);
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void deveriaRetornar201AoCadastrarUmNovoCliente() throws Exception {
		String json = "{\"nome\": \"Paulo Dybala\", \"dataNascimento\": \"15/11/1993\", \"telefone\": \"(21) 96641-2344\"}";
		URI uri = new URI("/clientes");
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	public void deveriaRetornar200AoAtualizarUmCliente() throws Exception {
		String json = "{\"nome\": \"Emiliano Rigoni\", \"dataNascimento\": \"04/02/1993\", \"telefone\": \"(11) 93342-1241\"}";
		Long id = 1L;
		URI uri = new URI("/clientes/" + id);
		mockMvc.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornar200AoDeletarUmCliente() throws Exception {
		Long id = 1L;
		URI uri = new URI("/clientes/" + id);
		mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveriaRetornar404AoTentarDeletarUmClienteComUmIdNaoCadastrado() throws Exception {
		Long id = 999L;
		URI uri = new URI("/clientes/" + id);
		mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(404));
	}
}
