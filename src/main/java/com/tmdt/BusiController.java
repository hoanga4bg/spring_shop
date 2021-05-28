package com.tmdt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmdt.business.StoreDAO;
import com.tmdt.model.Account;
import com.tmdt.model.Product;
import com.tmdt.repository.AccountRepository;
import com.tmdt.repository.ProductRepository;

@Controller
@RequestMapping("/busi")
public class BusiController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@GetMapping
	public String store(Model model) {
		List<Product> products = new ArrayList<Product>();
		products = productRepository.findAll();
		int amount = products.size();
		model.addAttribute("products", products);
		model.addAttribute("amount", amount);
		return "busi/index";
	}
	
	@GetMapping("/info")
	public String getInfo(Model model) {
		Account account = storeDAO.getAccount();
		model.addAttribute("account", account);
		return "busi/info";
	}
	
	@PostMapping
	public String saveAccount(Account account) {
		accountRepo.save(account);
		return "redirect:/busi/info";
	}

}
