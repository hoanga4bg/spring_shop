package com.tmdt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmdt.model.Product;
import com.tmdt.repository.ProductRepository;

@Controller
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public String store(Model model) {
		List<Product> products = new ArrayList<Product>();
		products = productRepository.findAll();
		int amount = products.size();
		model.addAttribute("products", products);
		model.addAttribute("amount", amount);
		return "store/index";
	}

}
