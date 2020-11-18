package com.coelhodiana.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.coelhodiana.blogPessoal.model.Article;
import com.coelhodiana.blogPessoal.repository.ArticleRepository;

@RestController
@RequestMapping("/articles")
@CrossOrigin("*")
public class ArticleController {
	
	@Autowired
	private ArticleRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Article>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Article> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<List<Article>> GetByTitle(@PathVariable String title){
		return ResponseEntity.ok(repository.findAllByTitleContainingIgnoreCase(title));
	}
	
	@PostMapping
	public ResponseEntity<Article> post (@RequestBody Article article) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(article));
	}
	
	@PutMapping
	public ResponseEntity<Article> put (@RequestBody Article article) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(article));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
