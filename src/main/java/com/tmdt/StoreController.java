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

import com.tmdt.business.OrderDAO;
import com.tmdt.business.StoreDAO;
import com.tmdt.model.Account;
import com.tmdt.model.ItemInCart;
import com.tmdt.model.Orders;
import com.tmdt.model.Product;
import com.tmdt.repository.AccountRepository;
import com.tmdt.repository.OrdersRepository;
import com.tmdt.repository.ProductRepository;

@Controller
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private OrdersRepository ordersRepo;
	
	@GetMapping
	public String store(Model model) {
		List<Product> products = new ArrayList<Product>();
		products = productRepository.findAll();
		int amount = products.size();
		model.addAttribute("products", products);
		model.addAttribute("amount", amount);
		return "store/index";
	}
	
	@GetMapping("/info")
	public String getInfo(Model model) {
		Account account = storeDAO.getAccount();
		model.addAttribute("account", account);
		return "store/info";
	}
	
	@PostMapping
	public String saveAccount(Account account) {
		accountRepo.save(account);
		return "redirect:/store/info";
	}
	
	@GetMapping("/order")
	public String update(Model model) {
		List<Orders> orders =ordersRepo.findByStatus(1);

		model.addAttribute("orders", orders);
		model.addAttribute("amount", orders.size());
		return "store/order";
	}
	
	@GetMapping("/confirm")
	public String confirm(@RequestParam("id") String id) {
		Orders order=orderDAO.findOneById(Long.parseLong(id));
		order.setStatus(2);
		ItemInCart item = order.getItem();
		Product product = item.getProduct();
		product.setAmount(product.getAmount() - item.getAmount());
		productRepository.save(product);
		orderDAO.save(order);
		return "redirect:/store/order";
	}
	
	@GetMapping("/thongke")
	public String thongke(Model model) {
		List<Orders> orders =ordersRepo.findByStatus(2);

		model.addAttribute("orders", orders);
		model.addAttribute("amount", orders.size());
		model.addAttribute("status", 1);
		return "store/order";
	}

}
