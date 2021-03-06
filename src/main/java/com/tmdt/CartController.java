package com.tmdt;

import java.security.interfaces.RSAPublicKey;
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

import com.tmdt.business.CartDAO;
import com.tmdt.business.PaymentDAO;
import com.tmdt.business.ProductDAO;
import com.tmdt.business.ShipmentDAO;
import com.tmdt.model.Cart;
import com.tmdt.model.ItemInCart;
import com.tmdt.model.Orders;
import com.tmdt.model.Payment;
import com.tmdt.model.Product;
import com.tmdt.model.Shipment;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private PaymentDAO payDAO;
	@Autowired
	private ShipmentDAO shipDAO;
	@GetMapping
	public String cart(Model model) {
		Cart cart=cartDAO.getCart();
		List<ItemInCart> list=new ArrayList<ItemInCart>();
		list=cartDAO.getItemInCart(cart);
		List<Payment> payList=payDAO.findAll();
		List<Shipment> shipList=shipDAO.findAll();
		model.addAttribute("list", list);
		model.addAttribute("shiplist",shipList );
		model.addAttribute("paylist", payList);
		model.addAttribute("order",new Orders());
		
		return "cart";
	}
	@GetMapping("/add")
	public String addCart(Model model,@RequestParam("id") String id) {
		Product p=productDAO.findOneById(Long.parseLong(id));
		Cart cart=cartDAO.getCart();
		ItemInCart iic=new ItemInCart();
		iic.setAmount(1);
		iic.setCreateDate(new Date());
		iic.setStatus(true);
		iic.setProduct(p);
		iic.setCart(cart);
		cartDAO.addToCart(iic);
		return "redirect:/cart";
	}
	
	@GetMapping("/edit")
	public String editCart(Model model,@RequestParam("id") String id) {
		ItemInCart iic=cartDAO.getItemInCartById(Long.parseLong(id));
		model.addAttribute("item", iic);
		return "editCart";
	}
	
	@PostMapping("/edit")
	public String editCart(@RequestParam("id") String id, @RequestParam("amount") String amount) {
		ItemInCart item=cartDAO.getItemInCartById(Long.parseLong(id));
		item.setAmount(Integer.parseInt(amount));
		cartDAO.addToCart(item);
		return "redirect:/cart";
	}
	
	@GetMapping("/delete")
	public String deleteCart(Model model,@RequestParam("id") String id) {
		cartDAO.delete(Long.parseLong(id));
		return "redirect:/cart";
	}
}
