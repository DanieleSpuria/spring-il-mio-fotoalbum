package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class PhotoController {

	@Autowired
	private PhotoServ photoServ;
	
	
	
	
	@GetMapping
	public String getIndex(
				@RequestParam(required = false, name = "search") String search,
				Model model
			) {
		
		List<Photo> photos = null;
		
		photos = search == null ? photoServ.findAll() : photoServ.findByTitle(search);
		
		model.addAttribute("photos", photos);
		model.addAttribute("search", search);
		
		return "photo/index";
	}
}
