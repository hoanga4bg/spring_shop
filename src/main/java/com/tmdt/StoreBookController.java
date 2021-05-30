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

import com.tmdt.model.Author;
import com.tmdt.model.Book;
import com.tmdt.model.Category;
import com.tmdt.repository.AuthorRepository;
import com.tmdt.repository.BookRepository;
import com.tmdt.repository.CategoryRepository;

@Controller
@RequestMapping("/store/book")
public class StoreBookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping
	public String book(Model model) {
		List<Book> books = new ArrayList<Book>();
		books = bookRepository.findAll();
		model.addAttribute("books", books);
		model.addAttribute("amount", books.size());
		return "store/book/display";
	}
	
	@GetMapping("/create")
	public String createNewBook(Model model) {
		Book book = new Book();
		List<Author> authors = new ArrayList<Author>();
		authors = authorRepository.findAll();
		List<Category> categorys = new ArrayList<Category>();
		categorys = categoryRepository.findAll();
		
		model.addAttribute("book", book);
		model.addAttribute("authors", authors);
		model.addAttribute("categorys", categorys);
		model.addAttribute("status", 1);
		
		return "store/book/form";
	}
	
	
	@PostMapping
	public String saveBook(Book book) {
		Author author = authorRepository.findOneById(book.getAuthor().getId());
		Category category = categoryRepository.findOneById(book.getCategory().getId());
		book.setAuthor(author);
		book.setCategory(category);
		book.setSaleOff(0.0);
		bookRepository.save(book);

		return "redirect:/store/book";
	}
	
	@GetMapping("/edit")
	public String importBook(Model model,@RequestParam("id") String id) {
		Book book = new Book();
		book = bookRepository.findOneById(Long.parseLong(id));
		List<Author> authors = new ArrayList<Author>();
		authors = authorRepository.findAll();
		List<Category> categorys = new ArrayList<Category>();
		categorys = categoryRepository.findAll();
		model.addAttribute("authors", authors);
		model.addAttribute("categorys", categorys);
		model.addAttribute("book", book);
		model.addAttribute("status", 0);
		
		return "store/book/form";
	}
	
	@GetMapping("/delete")
	public String deleteBook(@RequestParam("id") String id) {
		bookRepository.deleteById(Long.parseLong(id));
		return "redirect:/store/book";
	}
	
	@GetMapping("/author")
	public String createNewAuthor(Model model) {
		Author author = new Author();
		model.addAttribute("author", author);
		return "store/book/author";
	}
	
	@PostMapping("/author")
	public String saveAuthor(Author author) {
		authorRepository.save(author);
		return "redirect:/store/book";
	}
	
	@GetMapping("/category")
	public String createNewCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "store/book/category";
	}
	
	@PostMapping("/category")
	public String savaCategory(Category category) {
		categoryRepository.save(category);
		return "redirect:/store/book";
	}

}
