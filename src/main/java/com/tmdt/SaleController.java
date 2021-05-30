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
	private OrdersRepository ordersRepository;
	
	@Autowired
	private OrderDAO orderDAO;

	
	
	@GetMapping
	public String sale(Model model) {

		List<Orders> orders =orderDAO.findAll();

		model.addAttribute("orders", orders);
		model.addAttribute("amount", orders.size());
		return "sale/index";
	}

	
	
	@GetMapping("/detail")
	public String detailOrder(Model model) {
		List<Orders> orders = new ArrayList<Orders>();
		orders = ordersRepository.findAll();
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findByRole("ROLE_USER");
		double total = 0;
		for (Orders order : orders) {
			total += order.getTotalPrice();
			
		}
		model.addAttribute("total",total);
		
		model.addAttribute("orders", orders);
		model.addAttribute("amount", orders.size());
		model.addAttribute("accounts", accounts);
		model.addAttribute("status", 1);
		

		return "sale/index";
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
	@GetMapping("/searchacc")
	public String searchByAccount(Model model,@RequestParam("id") String id) {
		Account account = accountRepository.findOneById(Long.parseLong(id));
		Customer customer = customerRepository.findByAccount(account);
		Cart cart = cartRepository.findOneByCustomer(customer);
		List<ItemInCart> items = new ArrayList<ItemInCart>();
		items = itemRepo.findByCart(cart);
		List<Orders> orders = new ArrayList<Orders>();
		for (ItemInCart item : items) {
			Orders order = ordersRepository.findByItem(item);
			orders.add(order);
		}
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findByRole("ROLE_USER");
		
		double total = 0;
		for (Orders order : orders) {
			total += order.getTotalPrice();
			
		}
		model.addAttribute("total",total);
		
		model.addAttribute("orders", orders);
		model.addAttribute("amount", orders.size());
		model.addAttribute("accounts", accounts);
		model.addAttribute("status", 1);
		
		return "sale/index";
	}
	
	@GetMapping("/search")
	public String searchByDate(@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate,Model model) throws ParseException {
		if(startdate == "" || enddate == "") {
			return "redirect:/sale/detail";
		}
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date start;
		Date end;
		start = sim.parse(startdate);
		end = sim.parse(enddate);
		LocalDate day = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate day1 = day.plusDays(1);
		Date dateend = Date.from(day1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountRepository.findByRole("ROLE_USER");
		List<Orders> orders = new ArrayList<Orders>();
		orders = ordersRepository.findAll();
		List<Orders> listOrders = new ArrayList<Orders>();
		for (Orders order : orders) {
			if(order.getItem().getCreateDate().compareTo(start) == 1 && order.getItem().getCreateDate().compareTo(dateend) == -1) {
				listOrders.add(order);
			}
		}
		
		double total = 0;
		for (Orders order : listOrders) {
			total += order.getTotalPrice();
			
		}
		model.addAttribute("total",total);
		model.addAttribute("orders", listOrders);
		model.addAttribute("amount", listOrders.size());
		model.addAttribute("accounts", accounts);
		model.addAttribute("status", 1);
		
		return "sale/index";
	}
}
