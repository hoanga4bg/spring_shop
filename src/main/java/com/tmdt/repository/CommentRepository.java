package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
