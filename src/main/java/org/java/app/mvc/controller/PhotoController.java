package org.java.app.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.java.app.db.pojo.Category;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.CategoryServ;
import org.java.app.db.serv.PhotoServ;
import org.java.app.mvc.auth.db.serv.UserServ;
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
	
	@Autowired
	private UserServ userServ;
	
	
	
	
	@GetMapping
	public String index(
				@RequestParam(required = false, name = "search") String search,
				Model model
			) {
		
		Integer authId = userServ.authId();
		List<Photo> allPhotos = photoServ.findAll();
		List<Photo> photos = new ArrayList<Photo>();
		
		photos = allPhotos.stream()
					.filter(photo -> photo.getUser().getId() == authId)
					.toList();
				
		if (search == null)
			model.addAttribute("photos", photos);
		else {	
			photos = photos.stream()
                	.filter(photo -> photo.getTitle().contains(search))
                	.collect(Collectors.toList());
			model.addAttribute("photos", photos);
		}
		
		model.addAttribute("search", search);
		model.addAttribute("user", userServ.auth());
		
		return "photo/index";
	}
	
	
	
	
	@GetMapping("/{id}")
	public String show(
				@PathVariable int id,
				Model model
			) {
		
		Integer authId = userServ.authId();
		Photo photo = photoServ.findById(id).get();

		if (photo.getUser().getId() == authId) {
			model.addAttribute("photo", photo);
			return "photo/show";
		} else 
			return "redirect:/";
	}
	
	
	
	
	@GetMapping("/create-photo")
	public String create(
				Model model
			) {
		
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("photo", new Photo());
		return "/photo/form";
	}
	
	@PostMapping("/create-photo")
	public String store(
				@Valid @ModelAttribute("photo") Photo formPhoto,
				BindingResult bindingResult,
				Model model
			) {
		
		if(bindingResult.hasErrors()) {
			listCategories(model);
			return "/photo/form";
		}
		
		try {		
			formPhoto.setUser(userServ.auth());
			photoServ.save(formPhoto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			listCategories(model);
			return "photo/form";
		}
		
		return "redirect:/" + formPhoto.getId();
	}
	
	
	
	
	@GetMapping("/edit-photo/{id}")
	public String edit(
				@PathVariable int id,
				Model model
			) {
		
		listCategories(model);
		Optional<Photo> photo = photoServ.findById(id);
		model.addAttribute("photo", photo.get());

		return "/photo/form";
	}
	
	@PostMapping("/edit-photo/{id}")
	public String update(
				@Valid @ModelAttribute("photo") Photo formPhoto,
				BindingResult bindingResult,
				Model model
			) {
		
		if (bindingResult.hasErrors()) {
			listCategories(model);
			return "photo/form";
		}
		
		try {		
			formPhoto.setUser(userServ.auth());
			photoServ.save(formPhoto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("photo", formPhoto);
			listCategories(model);
			return "photo/form";
		}
		
		return "redirect:/" + formPhoto.getId();
	}
	
	
	
	
	@PostMapping("/delete-photo/{id}")
	public String delete(
				@PathVariable int id
			) {
		
		Optional<Photo> optPhoto = photoServ.findById(id);
		List<Category> categories = categoryServ.findAll();
		Photo photo = optPhoto.get();
		
		for (Category category : categories) 
			if (category.getPhotos().contains(photo))
				category.delPhoto(photo);
		
		photoServ.delete(photo);
		
		return "redirect:/";
	}
	
	
	
	
	private void listCategories(
				Model model
			) {
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories", categories);
	}
}
