package org.java.app.api;

import java.util.List;

import org.java.app.api.email.db.pojo.Email;
import org.java.app.api.email.db.serv.EmailServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin
@RequestMapping("/api/emails")
public class EmailRestController {
	
	@Autowired
	private EmailServ emailServ;

	
	
	@GetMapping
	public ResponseEntity<List<Email>> getAll() {
		
		List<Email> emails = emailServ.findAll();
		
		return new ResponseEntity<>(emails, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Email> create(
				@RequestBody Email formEmail
			) {
		
		try {
			emailServ.save(formEmail);			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return new ResponseEntity<>(formEmail, HttpStatus.OK);
	}
	
}
