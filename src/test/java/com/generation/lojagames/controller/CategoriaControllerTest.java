package com.generation.lojagames.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.lojagames.model.Categoria;
import com.generation.lojagames.util.BaseControllerTest;
import com.generation.lojagames.util.JwtHelper;
import com.generation.lojagames.util.TestBuilder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Testes - Categoria Controller")
class CategoriaControllerTest extends BaseControllerTest {

	private static final String BASE_URL = "/categorias";

	@Test
	@Order(1)
	@DisplayName("01 - Deve cadastrar uma nova categoria")
	void deveCadastrarCategoria() {
		// Given
		Categoria categoria = TestBuilder.criarCategoria(null, "Tema 01");
		
		// When
		String token = JwtHelper.obterToken(testRestTemplate, ADMIN, SENHA);
		HttpEntity<Categoria> requisicao = JwtHelper.criarRequisicaoComToken(categoria, token);
		ResponseEntity<Categoria> resposta = testRestTemplate.exchange(
				BASE_URL, HttpMethod.POST, requisicao, Categoria.class);
		
		// Then
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertNotNull(resposta.getBody());
		assertEquals("Tema 01", resposta.getBody().getTipo());
	}

	@Test
	@Order(2)
	@DisplayName("02 - Deve atualizar uma categoria")
	void deveAtualizarCategoria() {
		// Given
		Categoria categoria = categoriaRepository.save(
				TestBuilder.criarCategoria(null, "Tema 02")
		);
		
		Categoria categoriaAtualizada = TestBuilder.criarCategoria(
				categoria.getId(), 
				"Tema 02 - Atualizado"
		);
		
		// When
		String token = JwtHelper.obterToken(testRestTemplate, ADMIN, SENHA);
		HttpEntity<Categoria> requisicao = JwtHelper.criarRequisicaoComToken(categoriaAtualizada, token);
		ResponseEntity<Categoria> resposta = testRestTemplate.exchange(
				BASE_URL, HttpMethod.PUT, requisicao, Categoria.class);
		
		// Then
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody());
		assertEquals("Tema 02 - Atualizado", resposta.getBody().getTipo());
	}

	@Test
	@Order(3)
	@DisplayName("03 - Deve listar todas as categorias")
	void deveListarTodasCategorias() {
		// Given
		categoriaRepository.save(TestBuilder.criarCategoria(null, "Tema 03"));
		categoriaRepository.save(TestBuilder.criarCategoria(null, "Tema 04"));
		
		// When
		String token = JwtHelper.obterToken(testRestTemplate, ADMIN, SENHA);
		HttpEntity<Void> requisicao = JwtHelper.criarRequisicaoComToken(token);
		ResponseEntity<Categoria[]> resposta = testRestTemplate.exchange(
				BASE_URL, HttpMethod.GET, requisicao, Categoria[].class);
		
		// Then
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody());
	}
	
	@Test
	@Order(4)
	@DisplayName("04 - Deve buscar categorias por tipo")
	void deveBuscarCategoriasPorTipo() {
		// Given
		categoriaRepository.save(TestBuilder.criarCategoria(null, "Tema 05"));
		
		// When
		String token = JwtHelper.obterToken(testRestTemplate, ADMIN, SENHA);
		HttpEntity<Void> requisicao = JwtHelper.criarRequisicaoComToken(token);
		ResponseEntity<Categoria[]> resposta = testRestTemplate.exchange(
				BASE_URL + "/tipo/3D", HttpMethod.GET, requisicao, Categoria[].class);
		
		// Then
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody());
	}
	
	@Test
	@Order(5)
	@DisplayName("05 - Deve buscar uma categoria por ID")
	void deveBuscarCategoriaPorId() {
		// Given
		Categoria categoria = categoriaRepository.save(
				TestBuilder.criarCategoria(null, "Tema 06")
		);
		
		// When
		String token = JwtHelper.obterToken(testRestTemplate, ADMIN, SENHA);
		HttpEntity<Void> requisicao = JwtHelper.criarRequisicaoComToken(token);
		ResponseEntity<Categoria> resposta = testRestTemplate.exchange(
				BASE_URL + "/" + categoria.getId(), HttpMethod.GET, requisicao, Categoria.class);
		
		// Then
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody());
		assertEquals("Tema 06", resposta.getBody().getTipo());
	}
	
	@Test
	@Order(6)
	@DisplayName("06 - Deve deletar uma categoria por ID")
	void deveDeletarCategoriaPorId() {
		// Given
		Categoria categoria = categoriaRepository.save(
				TestBuilder.criarCategoria(null, "Tema 07")
		);
		
		// When
		String token = JwtHelper.obterToken(testRestTemplate, ADMIN, SENHA);
		HttpEntity<Void> requisicao = JwtHelper.criarRequisicaoComToken(token);
		ResponseEntity<Void> resposta = testRestTemplate.exchange(
				BASE_URL + "/" + categoria.getId(), HttpMethod.DELETE, requisicao, Void.class);
		
		// Then
		assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	}
}