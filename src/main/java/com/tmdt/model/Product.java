package com.tmdt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;



import lombok.Data;


@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Double salePrice;
	private Double importPrice;
	private int amount;
	private String name;
	private Double saleOff;
	private String image;
	@Lob
	private String description;
	@OneToMany(mappedBy = "product")
	private List<ItemInCart> itemInCarts;

	@OneToMany(mappedBy = "product")
	private List<Comment> comments;
}
