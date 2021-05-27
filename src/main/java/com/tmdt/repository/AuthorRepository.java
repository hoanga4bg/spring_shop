package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	
	Author findOneById(Long id);

}
