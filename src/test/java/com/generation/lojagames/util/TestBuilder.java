package com.generation.lojagames.util;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.generation.lojagames.model.Categoria;
import com.generation.lojagames.model.Produto;
import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.model.UsuarioLogin;

public class TestBuilder {
	public static Usuario criarUsuario(Long id, String nome, String usuario, String senha, LocalDate dataNascimento) {
		Usuario novoUsuario = new Usuario();
		novoUsuario.setId(id);
		novoUsuario.setNome(nome);
		novoUsuario.setUsuario(usuario);
		novoUsuario.setSenha(senha);
		novoUsuario.setFoto("-");
		novoUsuario.setDataNascimento(dataNascimento != null ? dataNascimento : LocalDate.of(2000, 02, 12));
		return novoUsuario;
	}

	public static UsuarioLogin criarUsuarioLogin(String usuario, String senha) {
		UsuarioLogin usuarioLogin = new UsuarioLogin();
		usuarioLogin.setUsuario(usuario);
		usuarioLogin.setSenha(senha);
		return usuarioLogin;
	}
	
	public static Categoria criarCategoria(Long id, String tipo) {
		Categoria novaCategoria = new Categoria();
		novaCategoria.setId(id);
		novaCategoria.setTipo(tipo);
		return novaCategoria;
	}
	
	public static Produto criarProduto(Long id, String nome, BigDecimal preco, Categoria categoria) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setFoto("-");
        produto.setCategoria(categoria);
        return produto;
    }
}