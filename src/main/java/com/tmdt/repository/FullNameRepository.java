package com.tmdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmdt.model.FullName;

public interface FullNameRepository extends JpaRepository<FullName, Long>{

}
