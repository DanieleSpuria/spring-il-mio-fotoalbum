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
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired
	private PhotoServ photoServ;
	
	
	

	@GetMapping
	public String index(
				Model model
			) {
		
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories", categories);
		
		return "category/index";
	}
	
	
	
	
	@GetMapping("/create-category")
	public String create(
				Model model
			) {
		
		List<Photo> photos = photoServ.findAll();
		model.addAttribute("photos", photos);
		model.addAttribute("category", new Category());
		
		return "category/form";
	}
	
	@PostMapping("/create-category")
	public String store(
				@Valid @ModelAttribute("category") Category formCategory,
				BindingResult bindingResult,
				Model model
			) {
		
		if(bindingResult.hasErrors()) {
			listPhotos(model);
			return "category/form";
		}
					
		try {		
			for (Photo photo : formCategory.getPhotos()) 
				photo.addCategory(formCategory);
			
			categoryServ.save(formCategory);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			listPhotos(model);
			return "category/form";
		}
		
		return "redirect:/categories";
	}
	
	
	
	
	@PostMapping("/delete/{id}")
	public String delete(
				@PathVariable int id,
				Model model
			) {
		
		Optional<Category> optCategory = categoryServ.findById(id);
		Category category = optCategory.get();
		List<Photo> photos = photoServ.findAll();
		
		for(Photo photo : photos)
			if (photo.getCategories().contains(category))
				photo.delCategory(category);
				
		categoryServ.delete(category);
			
		return "redirect:/categories";
	}
	
	
	
	
	private void listPhotos(
			Model model
		) {
	List<Photo> photos = photoServ.findAll();
	model.addAttribute("photos", photos);
	}
}
