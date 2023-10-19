package org.java.app.api;

import java.util.List;

import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoRestController {

	@Autowired
	private PhotoServ photoServ;
	
	@GetMapping
	public ResponseEntity<List<Photo>> getAll(
		@RequestParam(required = false, name = "search") String search
	){
		
		List<Photo> photos = null;

		if (search == null)
			photos = photoServ.findAll();
		else
			photos = photoServ.findByTitle(search);
		
		return new ResponseEntity<List<Photo>>(photos, HttpStatus.OK);
	}
}
