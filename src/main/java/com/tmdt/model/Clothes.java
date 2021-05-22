package com.tmdt.model;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


@Data
@Entity
public class Clothes extends Product{
	
	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;
	
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
}
