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

import com.tmdt.business.StoreDAO;
import com.tmdt.model.Account;
import com.tmdt.model.Cart;
import com.tmdt.model.Customer;
import com.tmdt.model.ItemInCart;
import com.tmdt.repository.AccountRepository;
import com.tmdt.repository.CartRepository;
import com.tmdt.repository.CustomerRepository;
import com.tmdt.repository.ItemInCartRepository;
@Controller
@RequestMapping("/sale")
public class SaleController {
	@Autowired
	private ItemInCartRepository itemRepo;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private StoreDAO storeDAO;
	
	
	
	@GetMapping
	public String sale(Model model) {
		List<ItemInCart> items = new ArrayList<ItemInCart>();
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findByRole("ROLE_USER");
		items = itemRepo.findAll();
		model.addAttribute("items", items);
		model.addAttribute("amount", items.size());
		model.addAttribute("accounts", accounts);
		return "sale/display";
	}
	
	@GetMapping("/confirm")
	public String confirm(@RequestParam("id") String id) {
		ItemInCart items = itemRepo.findOneById(Long.parseLong(id));
		items.setStatus(true);
		itemRepo.save(items);
		return "redirect:/sale";
	}
	
	@GetMapping("/searchacc")
	public String searchByAccount(@RequestParam("id") String id,Model model) {
		Account account = accountRepository.findOneById(Long.parseLong(id));
		Customer customer =  customerRepository.findByAccount(account);
		Cart cart = cartRepository.findOneByCustomer(customer);
		List<ItemInCart> items = itemRepo.findByCart(cart);
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findByRole("ROLE_USER");
		model.addAttribute("items", items);
		model.addAttribute("amount", items.size());
		model.addAttribute("accounts", accounts);
		return "sale/display";
		
	}
	
	@GetMapping("/info")
	public String getInfo(Model model) {
		Account account = storeDAO.getAccount();
		model.addAttribute("account", account);
		return "sale/info";
	}
	
	@PostMapping
	public String saveAccount(Account account) {
		accountRepository.save(account);
		return "redirect:/sale/info";
	}

}
