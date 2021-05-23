package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
