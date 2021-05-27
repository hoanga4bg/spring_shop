package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	Book findOneById(Long id);


}
