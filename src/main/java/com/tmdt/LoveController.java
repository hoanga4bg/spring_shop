package com.tmdt;

import org.hibernate.criterion.LikeExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmdt.business.AccountDAO;
import com.tmdt.business.CustomerDAO;
import com.tmdt.business.LovesDAO;
import com.tmdt.business.ProductDAO;
import com.tmdt.model.Customer;
import com.tmdt.model.Loves;
import com.tmdt.model.Product;

@Controller
@RequestMapping("/love")
public class LoveController {
	@Autowired
	private LovesDAO loveDAO;
	@Autowired
	private ProductDAO proDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@GetMapping
	public String like(@RequestParam("id") String id) {
		Loves love=new Loves();
		love.setCustomer(customerDAO.getCustomer());
		love.setProduct(proDAO.findOneById(Long.parseLong(id)));
		loveDAO.save(love);
		return "redirect:/detail?id="+id;
	}
	
	@GetMapping("/dis")
	public String dislike(@RequestParam("id") String id) {
		Customer cus=customerDAO.getCustomer();
		Product p=proDAO.findOneById(Long.parseLong(id));
		Loves love=loveDAO.findByProductAndCustomer(p, cus);
		loveDAO.delete(love.getId());
		return "redirect:/detail?id="+id;
	}
}
