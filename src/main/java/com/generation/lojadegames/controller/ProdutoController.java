package com.generation.lojadegames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojadegames.model.Produto;
import com.generation.lojadegames.repository.ProdutoRepository;


@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@GetMapping
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{nome}")
	public ResponseEntity<List<Produto>> GetByNomeGame(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeGameContaining(nome));
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto (@Valid @RequestBody Produto produto){
			return ResponseEntity.status(201).body(repository.save(produto));
	}
	
	/*
	 @PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Usuario novoUsuario) {
		Optional<Object> usuarioCadastrado = services.cadastrarUsuario(novoUsuario);

		if (usuarioCadastrado.isEmpty()) {
			return ResponseEntity.status(200).body("Usuario Existente");
		} else {
			return ResponseEntity.status(201).body("Usuario Criado");
		}
	}
	 * 
	 * */
	
	
	
	@PutMapping
	public ResponseEntity<Produto> putProduto (@RequestBody Produto produto){
		return ResponseEntity.status(200).body(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto (@PathVariable long id) {
		repository.deleteById(id);
	} 
}
