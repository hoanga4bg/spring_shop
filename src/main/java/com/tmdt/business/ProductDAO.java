package com.tmdt.business;

import java.util.List;

import com.tmdt.model.Book;
import com.tmdt.model.Clothes;
import com.tmdt.model.Comment;
import com.tmdt.model.Electronic;
import com.tmdt.model.Product;

public interface ProductDAO extends GeneralDAO<Product>{
	public List<Book> findAllBook();
	public List<Electronic> findAllElectronic();
	public List<Clothes> findAllClothes();
	
	public void addComment(Comment comment);
	
	public List<Comment> getComment(Product p);
	
	public List<Product> getProductByName(String name);
}
