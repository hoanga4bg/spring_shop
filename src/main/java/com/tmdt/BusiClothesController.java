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
import com.tmdt.model.Clothes;
import com.tmdt.repository.ClothesRepository;

@Controller
@RequestMapping("/busi/clothes")
public class BusiClothesController {
	@Autowired
	private ClothesRepository clothesRepository;
	
	@GetMapping
	public String clothes(Model model) {
		List<Clothes> clothes = new ArrayList<Clothes>();
		clothes = clothesRepository.findAll();
		model.addAttribute("clothes", clothes);
		model.addAttribute("amount", clothes.size());
		return "busi/clothes/display";
	}
	
	@GetMapping("/form")
	public String addSaleoffAndImage(@RequestParam("id") String id, Model model) {
		Clothes clothes = clothesRepository.findOneById(Long.parseLong(id));
		model.addAttribute("clothes", clothes);
		return "busi/clothes/form";
	}
	
	@PostMapping
	public String saveClothes(Clothes clothes) {
		clothesRepository.save(clothes);
		return "redirect:/busi/clothes";
	}

}
