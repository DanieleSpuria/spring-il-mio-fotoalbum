package org.java.app.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.java.app.api.email.db.pojo.Email;
import org.java.app.api.email.db.serv.EmailServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;




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
	public ResponseEntity<?> create(
				@Valid @RequestBody Email formEmail,
				BindingResult bindingResult
			) {
		
		List<String> errors = new ArrayList<>(); 
		
		Map<String, String> errMap = new HashMap<>();
		
		if (bindingResult.hasErrors()) {
		    for (FieldError fieldError : bindingResult.getFieldErrors()) {
		       
		    	errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		    	
		        String error = "Errore nel campo " + fieldError.getField() + ": " + fieldError.getDefaultMessage();
		        errors.add(error);
		        
		        return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
		    }
		}
		
		try {
			emailServ.save(formEmail);			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return new ResponseEntity<>(formEmail.getEmail(), HttpStatus.OK);
	}
	
}
