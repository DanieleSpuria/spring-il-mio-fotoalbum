package org.java.app.mvc.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class PhotoController {

	@Autowired
	private PhotoServ photoServ;
	
	
	
	
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
}
