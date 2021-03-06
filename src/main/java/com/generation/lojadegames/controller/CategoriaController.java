package com.generation.lojadegames.controller;

import java.util.List;

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

import com.generation.lojadegames.model.Categoria;
import com.generation.lojadegames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@GetMapping
	public ResponseEntity<List<Categoria>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{nome}")
	public ResponseEntity<List<Categoria>> GetByNomeCategoria(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeCategoriaContaining(nome));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria (@RequestBody Categoria categoria){
		return ResponseEntity.status(201).body(repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria (@RequestBody Categoria categoria){
		return ResponseEntity.status(200).body(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria (@PathVariable long id) {
		repository.deleteById(id);
	} 

}

/*
 * findAllCategoria = um endPoint com a capacidade de trazer todas as
 * categorias. findByIDCategoria = um endPoint com a fun????o de trazer uma ??nica
 * categoria por id. findByDescricaoCategoria = um endPoint com a fun????o de
 * trazer uma categoria turma por Descricao. 
 * postCategoria = um endPoint com a fun????o de gravar uma nova categoria no banco de dados. 
 * putCategoria = um endPoint com a fun????o de atualizar dados de uma categoria. 
 * deleteCategoria = um endPoint com a fun????o de apagar uma categoria do banco de dados).
 */