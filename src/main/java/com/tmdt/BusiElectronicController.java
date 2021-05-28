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

import com.tmdt.model.Electronic;
import com.tmdt.repository.ElectronicRepository;

@Controller
@RequestMapping("/busi/electronic")
public class BusiElectronicController {
	
	@Autowired
	private ElectronicRepository electronicRepository;
	
	@GetMapping
	public String electronic(Model model) {
		List<Electronic> electronics = new ArrayList<Electronic>();
		electronics = electronicRepository.findAll();
		model.addAttribute("elecs", electronics);
		model.addAttribute("amount", electronics.size());
		return "busi/electronic/display";
	}
	
	@GetMapping("/form")
	public String addSaleoffAndImage(@RequestParam("id") String id, Model model) {
		Electronic electronic = electronicRepository.findOneById(Long.parseLong(id));
		model.addAttribute("electronic", electronic);
		return "busi/electronic/form";
	}
	
	@PostMapping
	public String saveElectronic(Electronic electronic) {
		electronicRepository.save(electronic);
		return "redirect:/busi/electronic";
	}

}
