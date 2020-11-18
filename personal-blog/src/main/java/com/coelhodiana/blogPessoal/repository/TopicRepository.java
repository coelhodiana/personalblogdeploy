package com.coelhodiana.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coelhodiana.blogPessoal.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{

	public List<Topic> findAllByDescriptionContainingIgnoreCase(String description);
	
}
