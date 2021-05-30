package com.tmdt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmdt.business.CustomerDAO;
import com.tmdt.business.LovesDAO;
import com.tmdt.business.ProductDAO;
import com.tmdt.model.*;


@Controller
@RequestMapping("/")
public class ProductController {
	@Autowired
	private ProductDAO proDAO;
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private LovesDAO loveDAO;
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
		
		List<Comment> list=new ArrayList<Comment>();
		list=proDAO.getComment(p);
		List<Loves> listLove=loveDAO.findAllByProduct(p);
//		Customer cus=customerDAO.getCustomer();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken) {
			model.addAttribute("liked", 0);
		}
		else {
			Loves loves=loveDAO.findByProductAndCustomer(p, customerDAO.getCustomer());
			if(loves==null) {
				model.addAttribute("liked", 0);
			}
			else {
				model.addAttribute("liked", 1);
			}
		}
		model.addAttribute("amount", listLove.size());
		model.addAttribute("list", list);
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
	
	@PostMapping("/comment")
	public String comment(@RequestParam("comment") String comment, @RequestParam("product") String id) {
		Product p=new Product();
		p=proDAO.findOneById(Long.parseLong(id));
		Comment cmt=new Comment();
		cmt.setContent(comment);
		Customer customer=customerDAO.getCustomer();
		//System.out.print(customer.getId());
		cmt.setCustomer(customer);
		cmt.setProduct(p);
		
		proDAO.addComment(cmt);
		return "redirect:/detail?id="+id;
		
	}
	@GetMapping("/search")
	public String search(@RequestParam("name") String name,Model model) {
		List<Product> list=new ArrayList<Product>();
		list=proDAO.getProductByName(name);

		Collections.reverse(list);
		
		model.addAttribute("list",list);
		return "index";
		
	}
}
