package com.tmdt;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmdt.business.CartDAO;
import com.tmdt.business.OrderDAO;
import com.tmdt.business.PaymentDAO;
import com.tmdt.business.ShipmentDAO;
import com.tmdt.model.Cart;
import com.tmdt.model.ItemInCart;
import com.tmdt.model.Orders;
import com.tmdt.model.Payment;
import com.tmdt.model.Shipment;
import com.tmdt.repository.ItemInCartRepository;

@Controller
@RequestMapping("/order")
public class OrderController {

	
	@Autowired
	private PaymentDAO payDAO;
	@Autowired
	private ShipmentDAO shipDAO;
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ItemInCartRepository itemRepo;
	@PostMapping
	public String order(Orders order) {
		Payment pay=payDAO.findOneById(order.getPayment().getId());
		Shipment ship=shipDAO.findOneById(order.getShipment().getId());
		String address=order.getAddress();
		String phone=order.getPhone();
		List<ItemInCart> list=cartDAO.getItemInCart(cartDAO.getCart());
		
		for(ItemInCart item:list) {
			Orders o=new Orders();
			o.setItem(item);
			o.setTotalPrice(item.getAmount()*item.getProduct().getSalePrice()*(1-item.getProduct().getSaleOff()));
			o.setStatus(false);
			o.setAddress(address);
			o.setShipment(ship);
			o.setPayment(pay);
			o.setPhone(phone);
			orderDAO.save(o);
			item.setStatus(false);
			orderDAO.updateItemStatus(item);
		}
		return "redirect:/cart";
	}
	
	@GetMapping
	public String order(Model model) {
		Cart cart=cartDAO.getCart();
		List<Orders> list=orderDAO.findByCart(cart);
		Collections.reverse(list);
		model.addAttribute("list", list);
		return "history";
	}
	
	@GetMapping("/cancel")
	public String deleteorder(Model model,@RequestParam("id") String id) {
		Orders order=orderDAO.findOneById(Long.parseLong(id));
		orderDAO.delete(Long.parseLong(id));
		ItemInCart item=order.getItem();
		item.setStatus(true);
		itemRepo.save(item);
		return "redirect:/order";
	}
}
