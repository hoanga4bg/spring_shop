package com.tmdt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmdt.business.ProductDAO;
import com.tmdt.model.*;


@Controller
@RequestMapping("/")
public class ProductController {
	@Autowired
	private ProductDAO proDAO;
	
	@GetMapping
	public String home(Model model) {
		List<Product> list=new ArrayList<Product>();
		list=proDAO.findAll();

		Collections.reverse(list);
		
		model.addAttribute("list",list);
		return "index";
	}
	
	
	@GetMapping("/detail")
	public String detail(Model model,@RequestParam("id") String id) {
		Product p=new Product();
		p=proDAO.findOneById(Long.parseLong(id));
		model.addAttribute("product", p);
		return "detail";
	}
	
	
	@GetMapping("/book")
	public String book(Model model) {
		List<Book> list=new ArrayList<Book>();
		list=proDAO.findAllBook();

		Collections.reverse(list);
		
		model.addAttribute("list",list);
		return "index";
	}
	
	@GetMapping("/electronic")
	public String electronic(Model model) {
		List<Electronic> list=new ArrayList<Electronic>();
		list=proDAO.findAllElectronic();

		Collections.reverse(list);
		
		model.addAttribute("list",list);
		return "index";
	}
	@GetMapping("/clothes")
	public String closthes(Model model) {
		List<Clothes> list=new ArrayList<Clothes>();
		list=proDAO.findAllClothes();

		Collections.reverse(list);
		
		model.addAttribute("list",list);
		return "index";
	}
}
