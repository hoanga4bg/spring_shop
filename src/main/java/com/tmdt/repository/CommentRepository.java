package com.tmdt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Comment;
import com.tmdt.model.Product;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	public List<Comment> findByProduct(Product p);

}
