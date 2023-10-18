package org.java.app.mvc.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.CategoryServ;
import org.java.app.db.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;




@Controller
public class PhotoController {

	@Autowired
	private PhotoServ photoServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	
	
	
	@GetMapping
	public String index(
				@RequestParam(required = false, name = "search") String search,
				Model model
			) {
		
		List<Photo> photos = null;
		
		photos = search == null ? photoServ.findAll() : photoServ.findByTitle(search);
		
		model.addAttribute("photos", photos);
		model.addAttribute("search", search);
		
		return "photo/index";
	}
	
	
	
	
	@GetMapping("/{id}")
	public String show(
				@PathVariable int id,
				Model model
			) {
		
		Optional<Photo> photo = photoServ.findById(id);
		model.addAttribute("photo", photo.get());
		
		return "photo/show";
	}
	
	
	
	
	@GetMapping("/formPhoto")
	public String create(
				Model model
			) {
		
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("photo", new Photo());
		return "/photo/form";
	}
	
	@PostMapping("/formPhoto")
	public String store(
				@Valid @ModelAttribute("photo") Photo formPhoto,
				BindingResult bindingResult,
				Model model
			) {
		
		if(bindingResult.hasErrors()) {
			List<Category> categories = categoryServ.findAll();
			model.addAttribute("categories", categories);
			return "/photo/form";
		}
		
		photoServ.save(formPhoto);
		
		return "redirect:/";
	}
}
