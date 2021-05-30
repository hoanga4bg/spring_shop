package com.tmdt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import com.tmdt.model.Cart;
import com.tmdt.model.Customer;
import com.tmdt.model.ItemInCart;
import com.tmdt.model.Orders;
import com.tmdt.repository.AccountRepository;
import com.tmdt.repository.CartRepository;
import com.tmdt.repository.CustomerRepository;
import com.tmdt.repository.ItemInCartRepository;
import com.tmdt.repository.OrdersRepository;
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
	
	@Autowired
	private OrderDAO orderDAO;

	
	
	@GetMapping
	public String sale(Model model) {

		List<Orders> list=orderDAO.findAll();

		model.addAttribute("list", list);

		return "sale/display";
	}
	@GetMapping("/confirm")
	public String confirm(@RequestParam("id") String id) {
		Orders order=orderDAO.findOneById(Long.parseLong(id));
		order.setStatus(true);
		orderDAO.save(order);

		return "redirect:/sale";
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
