package com.generation.lojagames.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;
import com.generation.lojagames.repository.UsuarioRepository;
import com.generation.lojagames.service.UsuarioService;

/**
 * Classe base para todos os testes de Controller.
 * Centraliza a configuração comum e a criação do usuário admin.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseControllerTest {
	
	@Autowired
	protected TestRestTemplate testRestTemplate;
	
	@Autowired
	protected UsuarioRepository usuarioRepository;
	
	@Autowired
	protected UsuarioService usuarioService;
	
	@Autowired
	protected CategoriaRepository categoriaRepository;
	
	@Autowired
	protected ProdutoRepository produtoRepository;
	
	// Credenciais do usuário admin para testes autenticados
	protected static final String ADMIN = "root@root.com";
	protected static final String SENHA = "rootroot";
	
	/**
	 * Método executado UMA VEZ antes de todos os testes da classe.
	 * Limpa o banco e cria o usuário admin necessário para autenticação.
	 */
	@BeforeAll
	public void inicializarTestes() {
		// Limpa o banco de dados para garantir ambiente limpo
		produtoRepository.deleteAll();
		categoriaRepository.deleteAll();
		usuarioRepository.deleteAll();
		
		// Cria o usuário admin para testes autenticados
		usuarioService.cadastrarUsuario(
			TestBuilder.criarUsuario(null, "Root", ADMIN, SENHA, null)
		);
	}
}