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

import com.coelhodiana.blogPessoal.model.Topic;
import com.coelhodiana.blogPessoal.repository.TopicRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	private TopicRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Topic>> getAll() {
		return ResponseEntity.ok(repository.findAll());	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Topic> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
    }
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Topic>> getByName(@PathVariable String name) {
		return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(name));
	}
	
	@PostMapping
	public ResponseEntity<Topic> post (@RequestBody Topic topic) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(topic));
	}
	
	@PutMapping
	public ResponseEntity<Topic> put (@RequestBody Topic topic) {
		return ResponseEntity.ok(repository.save(topic));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
