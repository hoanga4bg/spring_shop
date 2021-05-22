package com.tmdt.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;



@Data
@Entity
public class Book extends Product{
	
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
}
