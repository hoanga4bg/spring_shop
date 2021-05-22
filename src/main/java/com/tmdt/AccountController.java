package com.tmdt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmdt.model.Book;
import com.tmdt.repository.BookRepository;

@Controller
@RequestMapping
public class AccountController {
	@Autowired
	private BookRepository bookRepo;
	@GetMapping("/")
	public String home() {
		
		Book book=new Book();
		book.setImportPrice(10000.0);
		book.setSalePrice(200000.0);
		book.setName("A");
		book.setAmount(10);
		bookRepo.save(book);
		return "home";
	}
	
}
