package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.Clothes;

public interface ClothesRepository extends JpaRepository<Clothes, Long>{
	Clothes findOneById(Long id);

}
