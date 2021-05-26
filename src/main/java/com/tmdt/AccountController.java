package com.tmdt;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tmdt.business.AccountDAO;
import com.tmdt.model.Account;
import com.tmdt.model.Book;
import com.tmdt.model.Customer;
import com.tmdt.model.FullName;
import com.tmdt.repository.AccountRepository;
import com.tmdt.repository.BookRepository;
import com.tmdt.repository.CustomerRepository;

@Controller
@RequestMapping
public class AccountController {
	@Autowired
	private BookRepository bookRepo;
//	@Autowired
//	private AccountRepository accountRepo;
//	@Autowired
//	private CustomerRepository customerRepo;
	@Autowired
	private AccountDAO accountDAO;
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultHome() {
		Collection<? extends GrantedAuthority> authorities;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        authorities = auth.getAuthorities();
        String myRole = authorities.toArray()[0].toString();
//        System.out.println(myRole);
		if(myRole.equals("ROLE_USER")) {
			return "redirect:/";
		} 
		else if(myRole.equals("ROLE_BUSI")) {
			return "redirect:/busi";
		}
		else if(myRole.equals("ROLE_STORE")) {
			return "redirect:/store";
		}
		else {
			return "redirect:/sale";
		}
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth instanceof AnonymousAuthenticationToken) {
				model.addAttribute("account",new Account());
				return "login";	
			}
			else {
				return "redirect:/";	
			}

	}
	
	
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user",new Account());
		model.addAttribute("message","");
		return "register";
	}
	
	
	@PostMapping("/register")
	public String register(Account account, Model model) {
		if(accountDAO.findByUsername(account.getUsername())==null) {
			accountDAO.save(account);
			model.addAttribute("message","Đăng ký thành công");
			return "redirect:/login";
		}
		else {
			model.addAttribute("message","Tài khoản đã tồn tại");
			return "redirect:/register";
		}
		
	}
}
