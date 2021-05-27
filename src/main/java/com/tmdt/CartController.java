package com.tmdt;

import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmdt.business.CartDAO;
import com.tmdt.model.Cart;
import com.tmdt.model.ItemInCart;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartDAO cartDAO;
	@GetMapping
	public String cart(Model model) {
		Cart cart=cartDAO.getCart();
		List<ItemInCart> list=new ArrayList<ItemInCart>();
		list=cartDAO.getItemInCart(cart);
		model.addAttribute("list", list);
		return "cart";
	}
}
