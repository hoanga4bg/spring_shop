package com.tmdt.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.business.ProductDAO;
import com.tmdt.model.Book;
import com.tmdt.model.Clothes;
import com.tmdt.model.Comment;
import com.tmdt.model.Electronic;
import com.tmdt.model.Product;
import com.tmdt.repository.BookRepository;
import com.tmdt.repository.ClothesRepository;
import com.tmdt.repository.CommentRepository;
import com.tmdt.repository.ElectronicRepository;
import com.tmdt.repository.ProductRepository;


@Service
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	private ProductRepository proRepo;
	
	@Autowired
	private ClothesRepository cloRepo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private ElectronicRepository elecRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	@Override
	public List<Product> findAll() {
		
		return proRepo.findAll();
	}

	@Override
	public Product save(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findOneById(long id) {
		Product p=new Product();
		p=proRepo.findOneById(id);
		return p;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product update(long id, Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAllBook() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

	@Override
	public List<Electronic> findAllElectronic() {
		// TODO Auto-generated method stub
		return elecRepo.findAll();
	}

	@Override
	public List<Clothes> findAllClothes() {
		// TODO Auto-generated method stub
		return cloRepo.findAll();
	}

	@Override
	public void addComment(Comment comment) {
		commentRepo.save(comment);
		
	}

	@Override
	public List<Comment> getComment(Product p) {
		List<Comment> list=commentRepo.findByProduct(p);
		return list;
	}

}
