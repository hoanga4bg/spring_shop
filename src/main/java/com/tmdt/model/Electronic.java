package com.tmdt.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.Data;

@Data
@Entity
public class Electronic extends Product{
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private Types type;
	
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
}
