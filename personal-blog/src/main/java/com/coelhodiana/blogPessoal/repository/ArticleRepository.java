package com.coelhodiana.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coelhodiana.blogPessoal.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	public List<Article> findAllByTitleContainingIgnoreCase(String title);
	
}
