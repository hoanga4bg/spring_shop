package com.tmdt;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmdt.model.Book;
import com.tmdt.repository.BookRepository;

@Controller
@RequestMapping("/busi/book")
public class BusiBookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping
	public String book(Model model) {
		List<Book> books = new ArrayList<Book>();
		books = bookRepository.findAll();
		model.addAttribute("books", books);
		model.addAttribute("amount", books.size());
		return "busi/book/display";
	}
	
	@GetMapping("/form")
	public String addSaleOffAndImage(@RequestParam("id") String id,Model model) {
		Book book = bookRepository.findOneById(Long.parseLong(id));
		model.addAttribute("book", book);
		return "busi/book/form";
		
	}
	
	@PostMapping
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:/busi/book";
	}
	
	
	

}
