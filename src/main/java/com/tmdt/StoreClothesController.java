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

import com.tmdt.model.Brand;
import com.tmdt.model.Clothes;
import com.tmdt.model.Material;
import com.tmdt.repository.BrandRepository;
import com.tmdt.repository.ClothesRepository;
import com.tmdt.repository.MaterialRepository;

@Controller
@RequestMapping("/store/clothes")
public class StoreClothesController {
	
	@Autowired
	private ClothesRepository clothesRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private MaterialRepository materialRepository;
	
	@GetMapping
	public String clothes(Model model) {
		List<Clothes> clothes = new ArrayList<Clothes>();
		clothes = clothesRepository.findAll();
		model.addAttribute("clothes", clothes);
		model.addAttribute("amount", clothes.size());
		return "store/clothes/display";
	}
	
	@GetMapping("/create")
	public String createNewClothes(Model model) {
		Clothes clothes = new Clothes();
		List<Brand> brands = new ArrayList<Brand>();
		brands = brandRepository.findAll();
		List<Material> materials = new ArrayList<Material>();
		materials = materialRepository.findAll();
		model.addAttribute("clothes", clothes);
		model.addAttribute("brands", brands);
		model.addAttribute("materials", materials);
		model.addAttribute("status", 1);
		return "store/clothes/form";
	}
	
	@PostMapping
	public String saveClothes(Clothes clothes) {
		Brand brand = new Brand();
		brand = brandRepository.findOneById(clothes.getBrand().getId());
		Material material = new Material();
		material = materialRepository.findOneById(clothes.getMaterial().getId());
		clothes.setBrand(brand);
		clothes.setMaterial(material);
		clothesRepository.save(clothes);
		return "redirect:/store/clothes";
	}
	
	@GetMapping("/delete")
	public String deleteClothes(@RequestParam("id") String id) {
		clothesRepository.deleteById(Long.parseLong(id));
		return "redirect:/store/clothes";
	}
	
	@GetMapping("/edit")
	public String editClothes(@RequestParam("id") String id,Model model) {
		Clothes clothes = clothesRepository.findOneById(Long.parseLong(id));
		List<Brand> brands = new ArrayList<Brand>();
		brands = brandRepository.findAll();
		List<Material> materials = new ArrayList<Material>();
		materials = materialRepository.findAll();
		model.addAttribute("clothes", clothes);
		model.addAttribute("brands", brands);
		model.addAttribute("materials", materials);
		model.addAttribute("status", 0);
		
		return "store/clothes/form";
	}
	
	@GetMapping("/brand")
	public String createNewBrand(Model model) {
		model.addAttribute("brand", new Brand());
		return "store/clothes/brand";
	}
	
	@PostMapping("/brand")
	public String saveBrand(Brand brand) {
		brandRepository.save(brand);
		return "redirect:/store/clothes";
	}
	
	@GetMapping("/material")
	public String createNewMaterial(Model model) {
		model.addAttribute("material", new Material());
		return "store/clothes/material";
	}
	
	@PostMapping("/material")
	public String saveMaterial(Material material) {
		materialRepository.save(material);
		return "redirect:/store/clothes";
	}
	
	

}
